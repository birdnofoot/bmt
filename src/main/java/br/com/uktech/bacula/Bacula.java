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

package br.com.uktech.bacula;

import br.com.uktech.bmt.bacula.BaculaConsole;
import br.com.uktech.bmt.bacula.BaculaConsoleFactory;
import br.com.uktech.bmt.bacula.bean.dot.BaculaDotStatusClientRunning;
import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlClient;
import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlFileSet;
import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlJob;
import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlPool;
import br.com.uktech.bmt.bacula.exceptions.BaculaAuthenticationException;
import br.com.uktech.bmt.bacula.exceptions.BaculaCommunicationException;
import br.com.uktech.bmt.bacula.exceptions.BaculaDirectorNotSupported;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class Bacula {
    
    
    public static void main(String[] args) {
        
        
        try {
            BaculaConsole console = BaculaConsoleFactory.getFactory().getConsole("director-dir", "192.168.103.1", 9101, "H6K-Ofy8725jCAoc7S0HmrYypiPHJSJxw");
            //BaculaConsole console = BaculaConsoleFactory.getFactory().getConsole("director-dir", "127.0.0.1", 9101, "BWy4h3EcfCZ9fJJj1Qcgd3sS2GAt7CW30");
            //String returnMensage = "";
            BaculaSqlJob job = null;
            //BaculaSqlClient client = null;
            //BaculaSqlPool pool = null;
            //BaculaSqlFileSet fileset = null;
            BaculaDotStatusClientRunning statusClientRunning = null;
            
            //returnMensage = console.executeSql("SELECT filesetid, fileset, md5, createtime FROM fileset");
            job = console.getSqlJob(1373l);
            //client = console.getSqlClient(1l);
            //pool = console.getSqlPool(1l);
            //fileset = console.getSqlFileSet(1l);
            statusClientRunning = console.getDotStatusClientRunning(job.getClient().getName());
                    
            //System.out.println("\n\n"+returnMensage+"\n\n");
            System.out.println("\n\n"+job+"\n\n");
            //System.out.println("\n\n"+client.toString()+"\n\n");
            //System.out.println("\n\n"+pool.toString()+"\n\n");
            //System.out.println("\n\n"+fileset.toString()+"\n\n");
            System.out.println("\n\n"+statusClientRunning+"\n\n");
            
            console.disconnect();
        } catch (BaculaAuthenticationException | BaculaDirectorNotSupported | BaculaCommunicationException ex) {
            Logger.getLogger(Bacula.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
