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
package br.com.uktech.bmt.bacula;

import br.com.uktech.bmt.bacula.bean.BaculaVersion;
import br.com.uktech.bmt.bacula.lib.Connection;
import br.com.uktech.bmt.bacula.lib.ConnectionFactory;
import br.com.uktech.bmt.bacula.console.BaculaConsole5;
import br.com.uktech.bmt.bacula.console.BaculaConsole7;
import br.com.uktech.bmt.bacula.exceptions.BaculaAuthenticationException;
import br.com.uktech.bmt.bacula.exceptions.BaculaDirectorNotSupported;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class BaculaConsoleFactory {
    
    private static BaculaConsoleFactory factory;
    private final Map<String, BaculaConsole> consoles;
    
    private BaculaConsoleFactory() {
        this.consoles = new ConcurrentHashMap<>(2);
        this.consoles.clear();
    }
    
    public static BaculaConsoleFactory getFactory() {
        if (BaculaConsoleFactory.factory == null) {
            BaculaConsoleFactory.factory = new BaculaConsoleFactory();
        }
        return BaculaConsoleFactory.factory;
    }
    
    public BaculaConsole getConsole(String directorName, String address, Integer port, String password) throws UnknownHostException, IOException, BaculaAuthenticationException, BaculaDirectorNotSupported {
        BaculaConsole console = this.consoles.get(directorName.toLowerCase());
        if (console == null) {
            Connection connection = ConnectionFactory.getFactory().getConnection(address, port, password);
            connection.connect();
            BaculaVersion version = connection.getDirectorVersion();
            Integer major = version.getMajor();
            if (major != null) {
                switch (major) {
                    case 5: 
                        console = new BaculaConsole5(directorName, connection);
                        break;
                    case 7: 
                        console = new BaculaConsole7(directorName, connection);
                        break;
                    default:
                        throw new BaculaDirectorNotSupported();
                }
                this.consoles.put(directorName.toLowerCase(), console);
            } else {
                throw new BaculaDirectorNotSupported();
            }
        }
        return console;
    }
    
}
