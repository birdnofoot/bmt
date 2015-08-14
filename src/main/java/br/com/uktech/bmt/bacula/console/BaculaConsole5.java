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
import br.com.uktech.bmt.bacula.bean.BaculaJob;
import br.com.uktech.bmt.bacula.bean.BaculaStatusClient;
import br.com.uktech.bmt.bacula.bean.BaculaStatusDirector;
import br.com.uktech.bmt.bacula.bean.BaculaStatusStorage;
import br.com.uktech.bmt.bacula.bean.BaculaStorage;
import br.com.uktech.bmt.bacula.exceptions.BaculaCommandException;
import br.com.uktech.bmt.bacula.exceptions.BaculaInvalidDataSize;
import br.com.uktech.bmt.bacula.exceptions.BaculaNoInteger;
import br.com.uktech.bmt.bacula.lib.Constants;
import br.com.uktech.bmt.bacula.lib.parser.ParseJobs;
import br.com.uktech.bmt.bacula.lib.parser.ParseListClient;
import br.com.uktech.bmt.bacula.lib.parser.ParseStatusClient;
import br.com.uktech.bmt.bacula.lib.parser.ParseStatusDirector;
import br.com.uktech.bmt.bacula.lib.parser.ParseStatusStorage;
import br.com.uktech.bmt.bacula.lib.parser.ParseStorage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.slf4j.LoggerFactory;


public class BaculaConsole5 extends AbstractBaculaConsole implements BaculaConsole {
    
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(BaculaConsole5.class);
    
    private final String directorName;
    
    public BaculaConsole5(String directorName, Connection connection) {
        super(connection);
        this.directorName = directorName;
        this.logger.info("Created a new Bacula " + this.getConnection().getDirectorVersion().toString() + " console connected on " + this.directorName + " at " + this.getConnection().getHostname());
    }
    
    @Override
    public String getDirectorName() {
        return this.directorName;
    }
    
    @Override
    public BaculaStatusDirector getStatusDirector() {
        BaculaStatusDirector sd = null;
        try {
            String receivedData = this.getConnection().sendAndReceive(Constants.Connection.Commands.STATUS_DIRECTOR);
            sd = new ParseStatusDirector().parse(receivedData);
            for (Iterator<BaculaJob> iterator = sd.getTerminatedJobs().iterator(); iterator.hasNext();) {
                BaculaJob job = iterator.next();
                detailBaculaJob(job);
            }
            for (Iterator<BaculaJob> iterator = sd.getRunningJobs().iterator(); iterator.hasNext();) {
                BaculaJob job = iterator.next();
                detailBaculaJob(job);
            }
        }
        catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return sd;
    }

    @Override
    public List<BaculaClient> getClients() {
        List<BaculaClient> clients = null;
        try {
            String receivedData = this.getConnection().sendAndReceive(Constants.Connection.Commands.LLIST_CLIENTS);
            clients = new ParseListClient().parse(receivedData);
        }
        catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return clients;
    }

    @Override
    public BaculaStatusClient getStatusClient(String clientName) {
        BaculaStatusClient statusClient = null;
        try {
            String receivedData = this.getConnection().sendAndReceive(Constants.Connection.Commands.STATUS_CLIENT+clientName);
            statusClient = new ParseStatusClient().parse(receivedData);
            for (Iterator<BaculaJob> iterator = statusClient.getTerminatedJobs().iterator(); iterator.hasNext();) {
                BaculaJob job = iterator.next();
                detailBaculaJob(job);
            }
        }
        catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return statusClient;
    }

    @Override
    public void detailBaculaJob(BaculaJob job) {
        updateListJobId(job);
        updateLlistJobId(job);
    }

    @Override
    public void updateListJobId(BaculaJob job) {
        try {
            
            String receivedData = this.getConnection().sendAndReceive(Constants.Connection.Commands.LIST_JOBID+job.getJobid());
            this.logger.trace(receivedData);
            new ParseJobs().parseListJob(receivedData, job);
            
        } catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
    }

    @Override
    public void updateLlistJobId(BaculaJob job) {
        try {
            String receivedData = this.getConnection().sendAndReceive(Constants.Connection.Commands.LLIST_JOBID+job.getJobid());
            this.logger.trace(receivedData);
            new ParseJobs().parseLlistJob(receivedData, job);
        } catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
    }

    @Override
    public List<BaculaStorage> getStorages() {
        List<BaculaStorage> baculaStorage = new ArrayList<>();
        try {
            String receivedData = this.getConnection().sendAndReceive(Constants.Connection.Commands.SHOW_STORAGE);
            baculaStorage = new ParseStorage().parse(receivedData);
        } catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return baculaStorage;
    }

    @Override
    public BaculaStatusStorage getStatusStorage(String storageName) {
        BaculaStatusStorage statusStorage = null;
        try {
            String receivedData = this.getConnection().sendAndReceive(Constants.Connection.Commands.STATUS_STORAGE+storageName);
            statusStorage = new ParseStatusStorage().parse(receivedData);
            for (Iterator<BaculaJob> iterator = statusStorage.getTerminatedJobs().iterator(); iterator.hasNext();) {
                BaculaJob job = iterator.next();
                detailBaculaJob(job);
            }
            //System.err.println("\n\n\n"+statusStorage.toString()+"\n\n\n");
        } catch(IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return statusStorage;
    }
}