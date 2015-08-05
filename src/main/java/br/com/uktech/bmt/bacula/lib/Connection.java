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
import java.io.IOException;
import java.net.InetAddress;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public interface Connection {
    
    public static int MAX_PACKET_SIZE = 1000000;
    
    public Boolean isConnected();
    
    public Boolean connect() throws IOException, InterruptedException, BaculaInvalidDataSize, BaculaNoInteger, BaculaAuthenticationException;
    
    public void disconnect();
      
    public InetAddress getAddress();
    
    public Integer getPort();
    
    public String getPassword();
    
    public String getHostname();
    
    public String getDirectorName();
    
    public BaculaVersion getDirectorVersion();
    
    public String sendAndReceive(String command) throws IOException, InterruptedException, BaculaInvalidDataSize, BaculaNoInteger;
    
}
