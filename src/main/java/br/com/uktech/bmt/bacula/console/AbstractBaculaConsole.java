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

import br.com.uktech.bmt.bacula.BaculaConsole;
import br.com.uktech.bmt.bacula.exceptions.BaculaAuthenticationException;
import br.com.uktech.bmt.bacula.exceptions.BaculaCommandException;
import br.com.uktech.bmt.bacula.exceptions.BaculaCommunicationException;
import br.com.uktech.bmt.bacula.exceptions.BaculaInvalidDataSize;
import br.com.uktech.bmt.bacula.exceptions.BaculaNoInteger;
import br.com.uktech.bmt.bacula.lib.Connection;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public abstract class AbstractBaculaConsole implements BaculaConsole {
    
    private final Connection connection;
    
    public AbstractBaculaConsole(Connection connection) {
        this.connection = connection;
    }
    
    protected Connection getConnection() {
        return this.connection;
    }

    @Override
    public void reconnect() throws BaculaAuthenticationException, BaculaCommunicationException {
        if (this.connection.isConnected()) {
            disconnect();
        }
        try {
            this.connection.connect();
        } catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            throw new BaculaCommunicationException();
        }
    }
    
    @Override
    public void disconnect() {
        if (this.connection.isConnected()) {
            this.connection.disconnect();
        }
    }

    @Override
    public Boolean isConnected() {
        return this.connection.isConnected();
    }
}
