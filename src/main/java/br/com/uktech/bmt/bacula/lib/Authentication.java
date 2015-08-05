/*
 * Copyright (C) 2015 Uhlig e Korovsky Tecnologia Ltda - ME
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.uktech.bmt.bacula.lib;

import br.com.uktech.bmt.bacula.exceptions.BaculaAuthenticationException;
import br.com.uktech.bmt.bacula.exceptions.BaculaAuthenticationFailed;
import br.com.uktech.bmt.bacula.exceptions.BaculaInvalidDataSize;
import br.com.uktech.bmt.bacula.exceptions.BaculaNoInteger;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.LoggerFactory;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public abstract class Authentication {
    
    //TODO: Criar método para retornar o nome do director
    //TODO: Criar método para retornar a versão do director
    
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(Authentication.class);
    
    private String directorName;
    private Integer directorMajorVersion;
    private Integer directorMinorVersion;
    private Integer directorRevisionVersion;
    private Date directorRelease;
    
    public abstract String getHostname();
    
    protected abstract AuthPackage authenticationProcess(AuthPackage data) throws IOException, BaculaInvalidDataSize, BaculaNoInteger;

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    protected Integer getDirectorMajorVersion() {
        return directorMajorVersion;
    }

    protected void setDirectorMajorVersion(Integer directorMajorVersion) {
        this.directorMajorVersion = directorMajorVersion;
    }

    protected Integer getDirectorMinorVersion() {
        return directorMinorVersion;
    }

    protected void setDirectorMinorVersion(Integer directorMinorVersion) {
        this.directorMinorVersion = directorMinorVersion;
    }

    protected Integer getDirectorRevisionVersion() {
        return directorRevisionVersion;
    }

    protected void setDirectorRevisionVersion(Integer directorRevisionVersion) {
        this.directorRevisionVersion = directorRevisionVersion;
    }

    protected Date getDirectorRelease() {
        return directorRelease;
    }

    protected void setDirectorRelease(Date directorRelease) {
        this.directorRelease = directorRelease;
    }
    
    private String generateHash(String key, String challenger) throws NoSuchAlgorithmException, InvalidKeyException {
        MessageDigest md = MessageDigest.getInstance(Constants.Connection.Auth.MD5);
        md.update(key.getBytes());
        byte[] raw = md.digest();
        String s = Utils.convertToHexString(raw);
        SecretKey skey = new SecretKeySpec(s.getBytes(), Constants.Connection.Auth.HMACMD5);
        Mac mac = Mac.getInstance(skey.getAlgorithm());
        mac.init(skey);
        byte digest[] = mac.doFinal(challenger.getBytes());
        String digestB64 = BaculaBase64.binToBase64(digest);
        return digestB64.substring(0, digestB64.length());
    }
    
    protected void authenticate(String password) {
        try {
            //Sending hello and authenticating with the server
            AuthPackage data = new AuthPackage(Constants.Connection.Messages.HELLO);
            AuthPackage receivedData = this.authenticationProcess(data);
            String tokens[] = receivedData.getData().split(Constants.SPACE);
            if (!tokens[0].equalsIgnoreCase(Constants.Connection.Tokens.AUTH)) {
                this.logger.error("Error authentication client");
                throw new BaculaAuthenticationException();
            }
            if (!tokens[1].equalsIgnoreCase(Constants.Connection.Tokens.CRAM_MD5)) {
                this.logger.error("Invalid token received");
                throw new BaculaAuthenticationException();
            }
            String challenger = tokens[2];
            
            data = new AuthPackage(generateHash(password, challenger));
            
            receivedData = this.authenticationProcess(data);
            if (receivedData.getReturnCode() != Constants.Connection.ReturnCodes.SUCCESS) {
                this.logger.error("AUTHENTICATION_FAILED - " + receivedData.getMessage());
                throw new BaculaAuthenticationFailed();
            }
            
            //Sending to server authenticate
            Random random = new Random();
            challenger = new StringBuffer(Constants.LESS_THAN)
                    .append(Math.abs(random.nextInt()))
                    .append(Constants.DOT)
                    .append(Math.abs(random.nextInt()))
                    .append(Constants.AT)
                    .append(this.getHostname())
                    .append(Constants.GREATHER_THAN).toString();
            
            StringBuffer auth = new StringBuffer(Constants.Connection.Tokens.AUTH)
                    .append(Constants.SPACE)
                    .append(Constants.Connection.Tokens.CRAM_MD5)
                    .append(Constants.SPACE)
                    .append(challenger)
                    .append(Constants.SPACE)
                    .append(Constants.Connection.Tokens.SSL)
                    .append(Constants.EQUAL)
                    .append(Constants.ZERO)
                    .append(Constants.CR);
            
            data = new AuthPackage(auth.toString());
            
            receivedData = this.authenticationProcess(data);
            
            challenger = generateHash(password, challenger);
            if (!receivedData.getData().equals(challenger)) {
                this.logger.error("AUTHENTICATION_FAILED - " + receivedData.getMessage());
                throw new BaculaAuthenticationFailed();
            }
            
            data = new AuthPackage(Constants.Connection.Messages.AUTH_OK);
            receivedData = this.authenticationProcess(data);
            
            if (receivedData.getReturnCode() != Constants.Connection.ReturnCodes.SUCCESS) {
                throw new BaculaAuthenticationFailed();
            } else {
                this.logger.debug("Auth received: " + receivedData.getData());
                extractVersion(receivedData.getData());
            }
        }
        catch (IOException | BaculaInvalidDataSize | BaculaNoInteger | BaculaAuthenticationException | BaculaAuthenticationFailed | NoSuchAlgorithmException | InvalidKeyException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
    }
    
    private void extractVersion(String linha) {
        Pattern p = Pattern.compile("(1000 OK:) +(.*) (Version:) +((\\d+)\\.(\\d+)\\.(\\d+)) +\\((.+)\\)");
        Matcher m = p.matcher(linha);
        if (m.find()) {
            this.setDirectorName(m.group(2));
            this.setDirectorMajorVersion(Integer.parseInt(m.group(5)));
            this.setDirectorMinorVersion(Integer.parseInt(m.group(6)));
            this.setDirectorRevisionVersion(Integer.parseInt(m.group(7)));
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
            try {
                this.setDirectorRelease(sdf.parse(m.group(8)));
            }
            catch (ParseException ex) {
                this.logger.error(ex.getLocalizedMessage());
                this.setDirectorRelease(new Date());
            }
        }
    }
}
