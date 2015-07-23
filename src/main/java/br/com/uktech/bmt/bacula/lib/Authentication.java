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
import java.util.Random;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.LoggerFactory;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class Authentication {
    
    //TODO: Criar método para retornar o nome do director
    //TODO: Criar método para retornar a versão do director
    
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(Authentication.class);
    
    private final Connection connection;
    private Boolean authenticated;
    
    public Authentication(Connection connection) {
        this.connection = connection;
        this.authenticated = false;
    }
 
    private String convertToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; ++i) {
            sb.append(Integer.toHexString((b[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }
    
    private String generateHash(String key, String challenger) throws NoSuchAlgorithmException, InvalidKeyException {
        MessageDigest md = MessageDigest.getInstance(Constants.Connection.Auth.MD5);
        md.update(key.getBytes());
        byte[] raw = md.digest();
        String s = convertToHexString(raw);
        SecretKey skey = new SecretKeySpec(s.getBytes(), Constants.Connection.Auth.HMACMD5);
        Mac mac = Mac.getInstance(skey.getAlgorithm());
        mac.init(skey);
        byte digest[] = mac.doFinal(challenger.getBytes());
        String digestB64 = BaculaBase64.binToBase64(digest);
        return digestB64.substring(0, digestB64.length());
    }
    
    public boolean autenticate(String password) {
        this.authenticated = false;
        try {
            //Sending hello and authenticating with the server
            DataPackage data = new DataPackage(Constants.Connection.Messages.HELLO);
            DataPackage receivedData = this.connection.sendAndReceive(data, false);
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
            
            data = new DataPackage(generateHash(password, challenger));
            
            receivedData = this.connection.sendAndReceive(data, false);
            if (receivedData.getReturnCode() != Constants.Connection.ReturnCodes.SUCCESS) {
                this.logger.error("AUTHENTICATION_FAILED - " + receivedData.getMessage());
                throw new BaculaAuthenticationFailed();
            }
            
            //Sending to server authenticate
            Random random = new Random();
            StringBuffer auth = new StringBuffer(Constants.Connection.Tokens.AUTH).append(Constants.SPACE);
            auth.append(Constants.Connection.Tokens.CRAM_MD5).append(Constants.SPACE);
            
            challenger = new StringBuffer(Constants.LESS_THAN).append(Math.abs(random.nextInt())).append(Constants.DOT).append(Math.abs(random.nextInt())).append(Constants.AT).append(this.connection.getHostname()).append(Constants.GREATHER_THAN).toString();
            
            auth.append(challenger).append(Constants.SPACE);
            auth.append(Constants.Connection.Tokens.SSL).append(Constants.EQUAL).append(Constants.ZERO).append(Constants.CR);
            
            data = new DataPackage(auth.toString());
            
            receivedData = this.connection.sendAndReceive(data, false);
            
            challenger = generateHash(password, challenger);
            if (!receivedData.getData().equals(challenger)) {
                this.logger.error("AUTHENTICATION_FAILED - " + receivedData.getMessage());
                throw new BaculaAuthenticationFailed();
            }
            
            data = new DataPackage(Constants.Connection.Messages.AUTH_OK);
            receivedData = this.connection.sendAndReceive(data, false);
            
            if (receivedData.getReturnCode() != Constants.Connection.ReturnCodes.SUCCESS) {
                throw new BaculaAuthenticationFailed();
            } else {
                logger.debug("Auth received: " + receivedData.getData());
                //TODO: Parse nome e versão do director
            }
            this.authenticated = true;
        }
        catch (IOException | BaculaInvalidDataSize | BaculaNoInteger | BaculaAuthenticationException | BaculaAuthenticationFailed | NoSuchAlgorithmException | InvalidKeyException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        
        return this.authenticated;
    }
    
}
