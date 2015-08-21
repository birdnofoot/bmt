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

import br.com.uktech.bmt.bacula.exceptions.BaculaCommandException;
import br.com.uktech.bmt.bacula.exceptions.BaculaInvalidDataSize;
import br.com.uktech.bmt.bacula.exceptions.BaculaNoInteger;
import java.io.IOException;
import java.io.InputStream;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class SocketReader implements Runnable {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(SocketReader.class);
    
    private InputStream inputStream;
    private boolean executionFinished;
    private boolean serverReady;
    private boolean hasErrors;
    private final StringBuffer returnMessage;
    private Exception lastException;

    public SocketReader() {
        this.inputStream = null;
        this.returnMessage = new StringBuffer();
    }
    
    /**
     * @return the inputStream
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * @param inputStream the inputStream to set
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    
    public void sendingNewCommand() {
        this.serverReady = false;
        this.executionFinished = false;
        this.hasErrors = false;
        this.returnMessage.delete(0, this.returnMessage.length());
    }
    
    public synchronized boolean executionSuccessfuly() throws InterruptedException {
        while (!this.executionFinished) {
            wait();
        }
        return !this.hasErrors;
    }
    
    private synchronized void setExecutionFinished(boolean executionFinished) {
        this.executionFinished = executionFinished;
        this.logger.debug("Execution Finished: " + this.executionFinished);
        notify();
    }
    
    public synchronized void waitForServerReady() throws InterruptedException {
        while (!this.serverReady) {
            wait();
        }
    }
    
    private synchronized void setServerReady(boolean serverReady) {
        this.serverReady = serverReady;
        this.logger.debug("Server ready: " + this.serverReady);
        notify();
    }
    
    public String getReturnMessage() throws InterruptedException {
        return this.returnMessage.toString();
    }
    
    private void setReturnMessage(String returnMessage) {
        this.returnMessage.append(returnMessage);
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Exception getLastException() {
        return lastException;
    }

    public void setLastException(Exception lastException) {
        this.lastException = lastException;
    }
    
    @Override
    public void run() {
        boolean finished = (this.getInputStream() == null);
        byte data[] = new byte[300];
        byte dataSize[] = new byte[4];
        int bytesReturned;
        while (!finished) {
            try {
                bytesReturned = this.getInputStream().read(dataSize, 0, dataSize.length);
                if (bytesReturned < 0) {
                    finished = true;
                } else if (bytesReturned == 4) {
                    Integer returned;
                    try {
                        returned = Utils.convertToInteger(dataSize);
                        this.logger.trace("SocketReader converted: " + returned);
                    } catch (BaculaNoInteger ex) {
                        returned = 0;
                        this.logger.error(ex.getLocalizedMessage());
                        this.setHasErrors(true);
                        this.setLastException(ex);
                    }
                    if (returned == 0) {
                        
                    } else if (returned < 0) {
                        switch (returned) {
                            case BNet.BNET_ERROR_MSG:
                                this.setHasErrors(true);
                                this.setLastException(new BaculaCommandException());
                                this.logger.error(this.lastException.getLocalizedMessage());
                            case BNet.BNET_CMD_OK:
                                setExecutionFinished(true);
                                break;
                            case BNet.BNET_CMD_BEGIN:
                                setExecutionFinished(false);    
                                break;
                            case BNet.BNET_MAIN_PROMPT:
                                setServerReady(true);
                                break;
                            default:
                                this.logger.error("BNet signal not implemented - signal(" + returned + ")");
                        }
                    } else {
                        int avaliableData = returned;
                        StringBuffer serverData = new StringBuffer();
                        if (avaliableData <= data.length) {
                            while (avaliableData > 0) {
                                int i = this.getInputStream().read(data, 0, avaliableData);
                                serverData.append(new String(data, 0, i));
                                avaliableData -= i;
                            }                            
                        } else {
                            this.setHasErrors(true);
                            this.setLastException(new BaculaInvalidDataSize());
                            this.logger.error(this.lastException.getLocalizedMessage());
                        }
                        setReturnMessage(serverData.toString());
                    }
                    
                }
            } catch (IOException ex) {
                finished = true;
                this.logger.debug("SocketReader exception: " + ex.getLocalizedMessage());
            }
        }
    }
}
