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
import br.com.uktech.bmt.bacula.lib.DataPackage;
import br.com.uktech.bmt.bacula.lib.parser.ParseListClient;
import br.com.uktech.bmt.bacula.lib.parser.ParseStatusClient;
import br.com.uktech.bmt.bacula.lib.parser.ParseStatusDirector;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;


public class BaculaConsole5 implements BaculaConsole {
    
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(BaculaConsole5.class);
    
    private final String directorName;
    private final Connection connection;
    
    public BaculaConsole5(String directorName, Connection connection) {
        this.directorName = directorName;
        this.connection = connection;
    }

    @Override
    public String getDirectorName() {
        return this.directorName;
    }
    
    @Override
    public BaculaStatusDirector getStatusDirector() {
        BaculaStatusDirector sd;
        DataPackage data = new DataPackage(Constants.Connection.Commands.STATUS_DIRECTOR);
        try {
            DataPackage receivedData = this.connection.sendAndReceive(data, true);
            sd = new ParseStatusDirector().parse(receivedData.getData());
        }
        catch (IOException | BaculaInvalidDataSize | BaculaNoInteger ex) {
            this.logger.error(ex.getLocalizedMessage());
            return null;
        }
        return sd;
    }

    @Override
    public List<BaculaClient> getClients() {
        List<BaculaClient> clients = new ArrayList<>();
        DataPackage data = new DataPackage(Constants.Connection.Commands.LLIST_CLIENTS);
        try {
            DataPackage receivedData = this.connection.sendAndReceive(data, true);
            //Aqui está o Bug
            clients = new ParseListClient().parse(receivedData.getData());
        }
        catch (IOException | BaculaInvalidDataSize | BaculaNoInteger ex) {
            this.logger.error(ex.getLocalizedMessage());
            return null;
        }
        return clients;
    }

    @Override
    public BaculaStatusClient getStatusClient(String clientName) {
        BaculaStatusClient statusClient = null;
        DataPackage data = new DataPackage(Constants.Connection.Commands.STATUS_CLIENT+clientName);
        try {
            DataPackage receivedData = this.connection.sendAndReceive(data, true);
            
            statusClient = new ParseStatusClient().parse(receivedData.getData());
        }
        catch (IOException | BaculaInvalidDataSize | BaculaNoInteger ex) {
            this.logger.error(ex.getLocalizedMessage());
            return null;
        }
        return statusClient;
    }
}
