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

import br.com.uktech.bmt.bacula.bean.BaculaVersion;
import br.com.uktech.bmt.bacula.exceptions.BaculaAuthenticationException;
import br.com.uktech.bmt.bacula.exceptions.BaculaInvalidDataSize;
import br.com.uktech.bmt.bacula.exceptions.BaculaNoInteger;
import br.com.uktech.bmt.bacula.lib.parser.ParseVersion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import org.slf4j.LoggerFactory;


public class ConnectionImpl implements Connection {
    
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ConnectionImpl.class);
    
    private final InetAddress address;
    private final Integer port;
    private final String password;
    
    private final Authentication auth;
    
    private Socket socket;
    public OutputStream out;
    public InputStream in;
    
    public ConnectionImpl(InetAddress address, Integer port, String password) {
        this.address = address;
        this.port = port;
        this.password = password;
        this.socket = null;
        this.in = null;
        this.out = null;
        this.auth = new Authentication(this);
    }

    /**
     * @return the address
     */
    public InetAddress getAddress() {
        return address;
    }

    /**
     * @return the port
     */
    public Integer getPort() {
        return port;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    
    private byte[] convertToByteArray(int value) {
        byte[] byteArray = new byte[4];
        byteArray[0] = (byte) ((value >> 24) & 0xFF);
        byteArray[1] = (byte) ((value >> 16) & 0xFF);
        byteArray[2] = (byte) ((value >> 8) & 0xFF);
        byteArray[3] = (byte) (value & 0xFF);
        return byteArray;
    }
    
    private Integer convertToInteger(byte byteArray[]) throws BaculaNoInteger {
        if (byteArray.length != 4) {
            throw new BaculaNoInteger();
        }
        short b;
        int result = 0;

        for (int i=0; i<4; i++) {
            b = (short)byteArray[i];
            b &= 0x00FF;
            result <<= 8;
            result |= (int)b;
        }
        return result;
    }
       
    private void authenticate(String password) throws BaculaAuthenticationException {
        this.logger.debug("Start authentication process.");
        if (!this.auth.autenticate(password)) {
            throw new BaculaAuthenticationException();
        }
    }
    
    @Override
    public Boolean isConnected() {
        if (this.socket != null) {
            return this.socket.isConnected();
        }
        return false;
    }
    
    @Override
    public Boolean connect() throws IOException, BaculaAuthenticationException {
        if (this.socket == null) {
            this.socket = new Socket(this.getAddress(), this.getPort());
            this.socket.setKeepAlive(true);
            this.out = new BufferedOutputStream(this.socket.getOutputStream());
            this.in = new BufferedInputStream(this.socket.getInputStream());
            if (this.socket.isConnected()) {
                this.authenticate(this.getPassword());
            }
        }
        return this.socket.isConnected();
    }
    
    @Override
    public void disconnect() {
        if (this.socket != null) {
            try {
                this.in.close();
                this.out.close();                
                this.socket.close();
            }
            catch (IOException ex) {
                this.logger.error("Error disconnecting", ex);
            }
            this.in = null;
            this.out = null;
            this.socket = null;
        }
    }

    @Override
    public DataPackage sendAndReceive(DataPackage data, Boolean handleSignals) throws IOException, BaculaInvalidDataSize, BaculaNoInteger {
        DataPackage receivedData = new DataPackage();
        if (this.isConnected()) {
            StringBuffer serverData = new StringBuffer();
            byte buffer[] = new byte[Connection.MAX_PACKET_SIZE];
            int available, i;
            byte dataSize[] = convertToByteArray(data.getData().length());
            this.logger.debug("Send: " + data.getData());
            out.write(dataSize);
            out.write(data.getData().getBytes());
            out.flush();
            while (true) {
                i = in.read(dataSize, 0, 4); // Reading a int
                if (i < 4) {
                    this.logger.error("Invalid data size.");
                    throw new BaculaInvalidDataSize();
                }
                available = convertToInteger(dataSize);
                logger.debug("Int read" + available);
                if (available < 0) { //Received a signal
                    receivedData.setSignal(available);
                    break;
                } else if (available > buffer.length) {
                    this.logger.error("Invalid data size.");
                    throw new BaculaInvalidDataSize();
                }
                while (available > 0) {
                    i = in.read(buffer, 0, available);
                    serverData.append(new String(buffer, 0, i));
                    available -= i;
                }
                if (!handleSignals) {
                    break;
                }
            }
            receivedData.setData(serverData.toString());
            this.logger.debug("Received: " + receivedData.getData());
        }
        return receivedData;
    }
    
    @Override
    public String getHostname() {
        return this.address.getHostName();
    }
    
    @Override
    public BaculaVersion getDirectorVersion() {
        BaculaVersion version = new BaculaVersion();
        version.setMajor(auth.getMajor());
        version.setMinor(auth.getMinor());
        version.setRevision(auth.getRevision());
        version.setRelease(auth.getRelease());
        return version;
    }
}
