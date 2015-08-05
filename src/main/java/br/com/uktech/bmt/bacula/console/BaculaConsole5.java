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
package br.com.uktech.bmt.bacula.console;

import br.com.uktech.bmt.bacula.lib.Connection;
import br.com.uktech.bmt.bacula.BaculaConsole;
import br.com.uktech.bmt.bacula.bean.BaculaClient;
import br.com.uktech.bmt.bacula.bean.BaculaStatusClient;
import br.com.uktech.bmt.bacula.bean.BaculaStatusDirector;
import br.com.uktech.bmt.bacula.exceptions.BaculaInvalidDataSize;
import br.com.uktech.bmt.bacula.exceptions.BaculaNoInteger;
import br.com.uktech.bmt.bacula.lib.Constants;
import br.com.uktech.bmt.bacula.lib.parser.ParseListClient;
import br.com.uktech.bmt.bacula.lib.parser.ParseStatusClient;
import br.com.uktech.bmt.bacula.lib.parser.ParseStatusDirector;
import java.io.IOException;
import java.util.List;
import org.slf4j.LoggerFactory;


public class BaculaConsole5 implements BaculaConsole {
    
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(BaculaConsole5.class);
    
    private final String directorName;
    private final Connection connection;
    
    public BaculaConsole5(String directorName, Connection connection) {
        this.directorName = directorName;
        this.connection = connection;
        this.logger.info("Created a new Bacula " + this.connection.getDirectorVersion().toString() + " console connected on " + this.directorName + " at " + this.connection.getHostname());
    }
    
    @Override
    public String getDirectorName() {
        return this.directorName;
    }
    
    @Override
    public BaculaStatusDirector getStatusDirector() {
        BaculaStatusDirector sd = null;
        try {
            String receivedData = this.connection.sendAndReceive(Constants.Connection.Commands.STATUS_DIRECTOR);
            sd = new ParseStatusDirector().parse(receivedData);
        }
        catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return sd;
    }

    @Override
    public List<BaculaClient> getClients() {
        List<BaculaClient> clients = null;
        try {
            String receivedData = this.connection.sendAndReceive(Constants.Connection.Commands.LLIST_CLIENTS);
            clients = new ParseListClient().parse(receivedData);
        }
        catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return clients;
    }

    @Override
    public BaculaStatusClient getStatusClient(String clientName) {
        BaculaStatusClient statusClient = null;
        try {
            String receivedData = this.connection.sendAndReceive(Constants.Connection.Commands.STATUS_CLIENT+clientName);
            statusClient = new ParseStatusClient().parse(receivedData);
        }
        catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return statusClient;
    }

    @Override
    public void disconnect() {
        if (this.connection.isConnected()) {
            this.connection.disconnect();
        }
    }
}
