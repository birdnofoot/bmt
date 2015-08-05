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
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.Semaphore;
import org.slf4j.LoggerFactory;


public class ConnectionImpl extends Authentication implements Connection {
    
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ConnectionImpl.class);

    private final InetAddress address;
    private final Integer port;
    private final String password;
    private final Semaphore semaphore;
    private final SocketReader reader;
    private Socket socket;
    private OutputStream out;
    private InputStream in;
    private Thread thReader;
    
    public ConnectionImpl(InetAddress address, Integer port, String password) {
        this.address = address;
        this.port = port;
        this.password = password;
        this.semaphore = new Semaphore(1, true);
        this.reader = new SocketReader();
        this.socket = null;
        this.in = null;
        this.out = null;
        this.thReader = null;
    }
    
    private void setAPIMode() throws IOException, InterruptedException, BaculaInvalidDataSize, BaculaNoInteger {
        sendAndReceive(".api 1");       
    }
    
    @Override
    public String getHostname() {
        return this.getAddress().getHostName();
    }

    @Override
    protected AuthPackage authenticationProcess(AuthPackage data) throws IOException, BaculaInvalidDataSize, BaculaNoInteger {
        AuthPackage receivedData = new AuthPackage();
        if (this.isConnected()) {
            try {
                this.semaphore.acquire();
                StringBuffer serverData = new StringBuffer();
                byte buffer[] = new byte[1024];
                int available, i;
                byte dataSize[] = Utils.convertToByteArray(data.getData().length());
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
                    available = Utils.convertToInteger(dataSize);
                    this.logger.debug("Int read: " + available + " bytes");
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
                    break;
                }
                receivedData.setData(serverData.toString());
                this.logger.debug("Received: " + receivedData.getData());
                this.semaphore.release();
            } catch (InterruptedException ex) {
                this.logger.error(ex.getLocalizedMessage());
            }
        }
        return receivedData;
    }

    @Override
    public Boolean isConnected() {
        if (this.socket != null) {
            return this.socket.isConnected();
        }
        return false;
    }

    @Override
    public Boolean connect() throws IOException, InterruptedException, BaculaInvalidDataSize, BaculaNoInteger, BaculaAuthenticationException {
        if (this.socket == null) {
            this.socket = new Socket(this.getAddress(), this.getPort());
            this.socket.setKeepAlive(true);
            this.out = new BufferedOutputStream(this.socket.getOutputStream());
            this.in = new BufferedInputStream(this.socket.getInputStream());
            if (isConnected()) {
                this.authenticate(this.getPassword());
                this.reader.setInputStream(this.in);
                this.thReader = new Thread(this.reader, "Thread: " + this.getDirectorName() + " - " + this.getDirectorVersion());
                this.thReader.start();
                this.setAPIMode();
            }
        }
        return isConnected();
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
                this.logger.error("Error disconnecting: " +ex.getLocalizedMessage());
            }
            this.in = null;
            this.out = null;
            this.socket = null;
            if (this.thReader != null) {
                try {
                    this.thReader.join(1000);
                } catch (InterruptedException ex) {
                    this.logger.error("Error finishing the thread: " + ex.getLocalizedMessage());
                }
            }
        }
    }

    @Override
    public InetAddress getAddress() {
        return this.address;
    }

    @Override
    public Integer getPort() {
        return this.port;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public BaculaVersion getDirectorVersion() {
        BaculaVersion version = new BaculaVersion();
        version.setMajor(this.getDirectorMajorVersion());
        version.setMinor(this.getDirectorMinorVersion());
        version.setRevision(this.getDirectorRevisionVersion());
        version.setRelease(this.getDirectorRelease());
        return version;
    }

    @Override
    public String sendAndReceive(String command) throws IOException, InterruptedException, BaculaInvalidDataSize, BaculaNoInteger {
        String returnMessage = null;
        try {
            this.semaphore.acquire();
            if (this.isConnected()) {
                byte dataSize[] = Utils.convertToByteArray(command.length());
                this.logger.debug("Send: " + command);
                this.reader.sendingNewCommand();
                out.write(dataSize);
                out.write(command.getBytes());
                out.flush();
                this.logger.debug("waitForCommandExecution");
                if (!this.reader.executionSuccessfuly()) {
                    this.logger.error("Errors on the execution of the command: " + command);
                }
                this.logger.debug("waitForServerReady");
                this.reader.waitForServerReady();
                this.logger.debug("ServerReady");
                returnMessage = this.reader.getReturnMessage();
                if (returnMessage.isEmpty()) {
                    returnMessage = null;
                }
            }
        } finally {
            this.semaphore.release();
        }
        return returnMessage;        
    }
}
