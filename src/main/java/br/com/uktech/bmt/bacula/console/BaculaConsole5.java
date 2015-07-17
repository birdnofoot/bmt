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
import br.com.uktech.bmt.bacula.bean.Client;
import br.com.uktech.bmt.bacula.bean.StatusClient;
import br.com.uktech.bmt.bacula.bean.StatusDirector;
import br.com.uktech.bmt.bacula.exceptions.BaculaInvalidDataSize;
import br.com.uktech.bmt.bacula.exceptions.BaculaNoInteger;
import br.com.uktech.bmt.bacula.lib.Constants;
import br.com.uktech.bmt.bacula.lib.DataPackage;
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
    }

    @Override
    public String getDirectorName() {
        return this.directorName;
    }
    
    @Override
    public StatusDirector getStatusDirector() {
        StatusDirector sd;
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
    public List<Client> getClients() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StatusClient getStatusClient(String clientName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
