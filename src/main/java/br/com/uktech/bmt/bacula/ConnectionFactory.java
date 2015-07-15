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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class ConnectionFactory {
    
    private static ConnectionFactory factory;
    
    private final Map<String, Connection> connections;

    private ConnectionFactory() {
        this.connections = new ConcurrentHashMap<>(2);
        this.connections.clear();
    }
    
    public static ConnectionFactory getFactory() {
        if (ConnectionFactory.factory == null) {
            ConnectionFactory.factory = new ConnectionFactory();
        }
        return ConnectionFactory.factory;
    }
    
    public Connection getConnection(String address, Integer port, String password) throws UnknownHostException {
        InetAddress host = InetAddress.getByName(address.trim());
        String index = host.getHostAddress() + ":" + port.toString();
        Connection aux = this.connections.get(index);
        if (aux == null) {
            aux = new ConnectionImpl(host, port, password);
            this.connections.put(index, aux);
        }
        return aux;
    }
    
    @Override
    protected void finalize() throws Throwable {
        for (Iterator<Connection> baculaConnectionIter = this.connections.values().iterator(); baculaConnectionIter.hasNext();){
            baculaConnectionIter.next().disconnect();
        }
        this.connections.clear();
        super.finalize();
    }
}
