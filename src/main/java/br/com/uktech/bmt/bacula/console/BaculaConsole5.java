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
import br.com.uktech.bmt.bacula.bean.BaculaDirectory;
import br.com.uktech.bmt.bacula.bean.BaculaEstimate;
import br.com.uktech.bmt.bacula.bean.BaculaFile;
import br.com.uktech.bmt.bacula.bean.BaculaFileVersions;
import br.com.uktech.bmt.bacula.bean.BaculaJob;
import br.com.uktech.bmt.bacula.bean.BaculaJobDefault;
import br.com.uktech.bmt.bacula.bean.BaculaRestoreFileDefault;
import br.com.uktech.bmt.bacula.bean.BaculaRestoreJob;
import br.com.uktech.bmt.bacula.bean.BaculaStatusClient;
import br.com.uktech.bmt.bacula.bean.BaculaStatusDirector;
import br.com.uktech.bmt.bacula.bean.BaculaStatusStorage;
import br.com.uktech.bmt.bacula.bean.BaculaStorage;
import br.com.uktech.bmt.bacula.bean.dot.BaculaDotClient;
import br.com.uktech.bmt.bacula.bean.dot.BaculaDotFileset;
import br.com.uktech.bmt.bacula.bean.dot.BaculaDotJob;
import br.com.uktech.bmt.bacula.bean.dot.BaculaDotLevel;
import br.com.uktech.bmt.bacula.bean.dot.BaculaDotPool;
import br.com.uktech.bmt.bacula.bean.dot.BaculaDotStatusClientRunning;
import br.com.uktech.bmt.bacula.bean.dot.BaculaDotStorage;
import br.com.uktech.bmt.bacula.bean.dot.BaculaDotType;
import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlClient;
import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlFileSet;
import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlJob;
import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlJobToRestore;
import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlPool;
import br.com.uktech.bmt.bacula.exceptions.BaculaCommandException;
import br.com.uktech.bmt.bacula.exceptions.BaculaInvalidDataSize;
import br.com.uktech.bmt.bacula.exceptions.BaculaNoInteger;
import br.com.uktech.bmt.bacula.lib.Constants;
import br.com.uktech.bmt.bacula.lib.parser.ParseDirectories;
import br.com.uktech.bmt.bacula.lib.parser.ParseEstimate;
import br.com.uktech.bmt.bacula.lib.parser.ParseJobs;
import br.com.uktech.bmt.bacula.lib.parser.ParseJobsDefault;
import br.com.uktech.bmt.bacula.lib.parser.ParseListClient;
import br.com.uktech.bmt.bacula.lib.parser.ParseRestoreFileDefault;
import br.com.uktech.bmt.bacula.lib.parser.ParseRestoreJob;
import br.com.uktech.bmt.bacula.lib.parser.ParseStatusClient;
import br.com.uktech.bmt.bacula.lib.parser.ParseStatusDirector;
import br.com.uktech.bmt.bacula.lib.parser.ParseStatusStorage;
import br.com.uktech.bmt.bacula.lib.parser.ParseStorage;
import br.com.uktech.bmt.bacula.lib.parser.ParseTableRestore;
import br.com.uktech.bmt.bacula.lib.parser.dot.ParseDotClients;
import br.com.uktech.bmt.bacula.lib.parser.dot.ParseDotFilesets;
import br.com.uktech.bmt.bacula.lib.parser.dot.ParseDotJobs;
import br.com.uktech.bmt.bacula.lib.parser.dot.ParseDotLevels;
import br.com.uktech.bmt.bacula.lib.parser.dot.ParseDotPools;
import br.com.uktech.bmt.bacula.lib.parser.dot.ParseDotStatusClient;
import br.com.uktech.bmt.bacula.lib.parser.dot.ParseDotStorage;
import br.com.uktech.bmt.bacula.lib.parser.dot.ParseDotTypes;
import br.com.uktech.bmt.bacula.lib.parser.sql.ParseSqlClient;
import br.com.uktech.bmt.bacula.lib.parser.sql.ParseSqlFileSet;
import br.com.uktech.bmt.bacula.lib.parser.sql.ParseSqlJob;
import br.com.uktech.bmt.bacula.lib.parser.sql.ParseSqlJobToRestore;
import br.com.uktech.bmt.bacula.lib.parser.sql.ParseSqlPool;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            for (BaculaJob job : sd.getTerminatedJobs()) {
                detailBaculaJob(job);
            }
            for (BaculaJob job : sd.getRunningJobs()) {
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
            for (BaculaJob job : statusClient.getTerminatedJobs()) {
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
        //updateListJobId(job);
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
            for (BaculaJob job : statusStorage.getTerminatedJobs()) {
                detailBaculaJob(job);
            } //System.err.println("\n\n\n"+statusStorage.toString()+"\n\n\n");
        } catch(IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return statusStorage;
    }

    @Override
    public BaculaEstimate getEstimate(String nameJob, String level, Boolean accurate, Boolean listing) {
        BaculaEstimate estimate = null;
        try {//estimate job=Backup_BACKUP-00 level=Incremental accurate=yes listing
            String comand = "", accuratedAux = "no", listingAux = "";
            if(accurate) {
                accuratedAux="yes";
            }
            if(listing) {
                listingAux=" listing";
            }
            comand = "estimate job=" + nameJob + " level=" + level + " accurate=" + accuratedAux + listingAux;
            String receivedData = this.getConnection().sendAndReceive(comand);
            estimate = new ParseEstimate().parse(receivedData);
        } catch(IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return estimate;
    }

    @Override
    public List<BaculaJobDefault> getListJobsDefault() {
        List<BaculaJobDefault> jobsDefault = new ArrayList<>();
        BaculaJobDefault jobDefault = null;
        try {
            String receivedJobs = this.getConnection().sendAndReceive(Constants.Connection.DotCommands.JOBS);
            jobsDefault = new ParseJobsDefault().setNames(receivedJobs);
            logger.info("Lista jobsD: {}", jobsDefault);
            for (int i = 0; i < jobsDefault.size(); i++) {
                String receivedJobsDefault = this.getConnection().sendAndReceive(Constants.Connection.DotCommands.DEFAULT_JOBS+jobsDefault.get(i).getJob());
                logger.info("comando recebido: {}", receivedJobsDefault);
                jobDefault = new BaculaJobDefault();
                jobDefault = new ParseJobsDefault().detailJobDefault(receivedJobsDefault);
                jobsDefault.set(i, jobDefault);
            }
        }
        catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return jobsDefault;
    }
    
    @Override
    public BaculaJobDefault getJobDefault(String jobDefaultName) {
        BaculaJobDefault jobDefault = new BaculaJobDefault();
        try {
            String receivedData = this.getConnection().sendAndReceive(Constants.Connection.DotCommands.DEFAULT_JOBS+jobDefaultName);
            jobDefault = new ParseJobsDefault().detailJobDefault(receivedData);
            this.logger.trace("getJobDefault: {}",jobDefault.toString());
        }
        catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return jobDefault;
    }
    
    @Override
    public Long runJob(BaculaJobDefault jobDefault, String when, Integer priority) {
        Long jobId = null;
        try {
            logger.info("Run Job: {}",jobDefault.getJob());
            String comand = "run job="+jobDefault.getJob()+" fileset="+jobDefault.getFileset()+" level="+jobDefault.getLevel()+" client="+jobDefault.getClient()+" pool="+jobDefault.getPool()+" storage="+jobDefault.getStorage()+" priority="+priority+" when=\""+when+"\" yes";
            comand = comand.replaceAll("\r", "");
            comand = comand.replaceAll("\t", "");
            comand = comand.replaceAll("\n", "");
            String receivedJobs = this.getConnection().sendAndReceive(comand);
            jobId = new ParseJobsDefault().getJobId(receivedJobs);
            this.logger.debug("\n\n\nJobId: {}\n\n\n",jobId);
        }
        catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return  jobId;
    }

    @Override
    public List<BaculaDotClient> getListDotClients() {
        List<BaculaDotClient> clients = new ArrayList<>();
        try {
            String receivedData = this.getConnection().sendAndReceive(Constants.Connection.DotCommands.CLIENTS);
            clients = new ParseDotClients().getReturn(receivedData);
        
        } catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return clients;
    }

    @Override
    public List<BaculaDotFileset> getListDotFilesets() {
        List<BaculaDotFileset> filesets = new ArrayList<>();
        try {
            String receivedData = this.getConnection().sendAndReceive(Constants.Connection.DotCommands.FILESETS);
            filesets = new ParseDotFilesets().getReturn(receivedData);
        
        } catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return filesets;
    }

    @Override
    public List<BaculaDotJob> getListDotJobs() {
        List<BaculaDotJob> jobs = new ArrayList<>();
        try {
            String receivedData = this.getConnection().sendAndReceive(Constants.Connection.DotCommands.JOBS);
            jobs = new ParseDotJobs().getReturn(receivedData);
        
        } catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return jobs;
    }

    @Override
    public List<BaculaDotLevel> getListDotLevels() {
        List<BaculaDotLevel> levels = new ArrayList<>();
        try {
            String receivedData = this.getConnection().sendAndReceive(Constants.Connection.DotCommands.LEVELS);
            levels = new ParseDotLevels().getReturn(receivedData);
        
        } catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return levels;
    }

    @Override
    public List<BaculaDotPool> getListDotPools() {
        List<BaculaDotPool> pools = new ArrayList<>();
        try {
            String receivedData = this.getConnection().sendAndReceive(Constants.Connection.DotCommands.POOLS);
            pools = new ParseDotPools().getReturn(receivedData);
        
        } catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return pools;
    }

    @Override
    public List<BaculaDotStorage> getListDotStorage() {
        List<BaculaDotStorage> storage = new ArrayList<>();
        try {
            String receivedData = this.getConnection().sendAndReceive(Constants.Connection.DotCommands.STORAGE);
            storage = new ParseDotStorage().getReturn(receivedData);
        
        } catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return storage;
    }

    @Override
    public List<BaculaDotType> getListDotTypes() {
        List<BaculaDotType> types = new ArrayList<>();
        try {
            String receivedData = this.getConnection().sendAndReceive(Constants.Connection.DotCommands.TYPES);
            types = new ParseDotTypes().getReturn(receivedData);
        
        } catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return types;
    }

    @Override
    public String executeSql(String sql) {
        StringBuilder sb = new StringBuilder(".sql query=\"");
        sb.append(sql);
        sb.append("\"");
        String receivedData = "";
        this.logger.debug(sb.toString());
        try {
            receivedData = this.getConnection().sendAndReceive(sb.toString());
        } catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        this.logger.trace("Received data: " + receivedData);
        return receivedData;
    }

    @Override
    public BaculaSqlJob getSqlJob(Long id) {
        return getSqlJob(new BaculaSqlJob(id));
    }
    
    @Override
    public BaculaSqlJob getSqlJob(BaculaSqlJob job) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("jobid, job, name, type, level, clientid, "
                + "jobstatus, schedtime, starttime, endtime, "
                + "realendtime, jobtdate, volsessionid, volsessiontime, "
                + "jobfiles, jobbytes, readbytes, joberrors, jobmissingfiles, "
                + "poolid, filesetid, priorjobid, purgedfiles, "
                + "hasbase, hascache, reviewed, comment ");
        sb.append("FROM ");
        sb.append("job ");
        sb.append("WHERE ");
        sb.append(" (jobid = ").append(job.getId()).append(") ");
        sb.append("LIMIT 1");
        this.logger.trace(sb.toString());
        String receivedData = executeSql(sb.toString());
        this.logger.debug("Dados Recebidos: {}",receivedData);
        if(receivedData != null) {
            if (!receivedData.trim().isEmpty()) {
                job = new ParseSqlJob().parseJob(receivedData, job);
                job.setClient(getSqlClient(job.getClient().getId()));
                job.setPool(getSqlPool(job.getPool().getId()));
                job.setFileset(getSqlFileSet(job.getFileset().getId()));
            }
            if (job.getId() == null) {
                job = null;
            }
        } else {
            job = null;
        }
        
        return job;
    }
    
    @Override
    public List<BaculaSqlJob> getListSqlJob() {
        List<BaculaSqlJob> jobs = new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("'###',jobid,'###;' ");
        sb.append("FROM ");
         sb.append("job ");
        sb.append("ORDER ");
        sb.append("BY ");
        sb.append("jobid ");
        this.logger.trace(sb.toString());
        String receivedData = executeSql(sb.toString());
        
        if(receivedData != null) {
            if (!receivedData.trim().isEmpty()) {
                ids = new ParseSqlJob().parseId(receivedData);
                if(!ids.isEmpty()) {
                    for (Long id : ids) {
                        jobs.add(getSqlJob(id));
                    }
                    if (jobs.isEmpty()) {
                        jobs = null;
                    }
                }
            }
        } else {
            jobs = null;
        }
        return jobs;
    }
    
    @Override
    public List<BaculaSqlJobToRestore> getListSqlJobToRestore(String clientName) {
        List<BaculaSqlJobToRestore> jobs = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("Job.Jobid, Job.StartTime, Job.Level, Job.Name, '###' ");
        sb.append("FROM ");
        sb.append("job ");
        sb.append("JOIN Client USING (ClientId) WHERE ");
        sb.append("Job.JobStatus IN ('T') AND Job.Type='B' AND Client.Name='"+clientName.trim()+"' ");
        sb.append("ORDER BY StartTime DESC");
        this.logger.trace(sb.toString());
        String receivedData = executeSql(sb.toString());
        this.logger.debug("Dados Recebidos: {}",receivedData);
        if(receivedData != null) {
            if (!receivedData.trim().isEmpty()) {
                jobs = new ParseSqlJobToRestore().parseJobs(receivedData);
            }
            if (jobs.isEmpty()) {
                jobs = null;
            }
        } else {
            jobs = null;
        }
        
        return jobs;
    }
    
    @Override
    public BaculaSqlClient getSqlClient(Long id) {
        return getSqlClient(new BaculaSqlClient(id));
    }

    @Override
    public BaculaSqlClient getSqlClient(BaculaSqlClient client) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("clientid, name, \'###\', uname, \'###\', autoprune, fileretention, jobretention ");
        sb.append("FROM ");
        sb.append("client ");
        sb.append("WHERE ");
        sb.append(" (clientid = ").append(client.getId()).append(") ");
        sb.append("LIMIT 1");
        this.logger.trace(sb.toString());
        String receivedData = executeSql(sb.toString());
        if(receivedData != null) {
            if (!receivedData.trim().isEmpty()) {
                client = new ParseSqlClient().parseClient(receivedData, client);
            }
        } else {
            client.setId(null);
        }
        if (client.getId() == null) {
            client = null;
        }
        return client;
    }
    
    @Override
    public List<BaculaSqlClient> getListSqlClient() {
        List<BaculaSqlClient> clients = new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("'###',clientid,'###;' ");
        sb.append("FROM ");
        sb.append("client ");
        sb.append("ORDER ");
        sb.append("BY ");
        sb.append("clientid ");
        this.logger.trace(sb.toString());
        String receivedData = executeSql(sb.toString());
        if(receivedData != null) {
            if (!receivedData.trim().isEmpty()) {
                ids = new ParseSqlClient().parseId(receivedData);
                if(!ids.isEmpty()) {
                    for (Long id : ids) {
                        clients.add(getSqlClient(id));
                    }
                    if (clients.isEmpty()) {
                        clients = null;
                    }
                }
            }
        } else {
            clients = null;
        }
        return clients;
    }

    @Override
    public BaculaSqlPool getSqlPool(Long id) {
        return getSqlPool(new BaculaSqlPool(id));
    }

    @Override
    public BaculaSqlPool getSqlPool(BaculaSqlPool pool) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("poolid, name, numvols, maxvols, useonce, usecatalog, ");
        sb.append("acceptanyvolume, volretention, voluseduration, maxvoljobs, ");
        sb.append("maxvolfiles, maxvolbytes, autoprune, recycle, actiononpurge, ");
        sb.append("pooltype, labeltype, labelformat, enabled, scratchpoolid, ");
        sb.append("recyclepoolid, nextpoolid, migrationhighbytes, ");
        sb.append("migrationlowbytes, migrationtime ");
        sb.append("FROM ");
        sb.append("pool ");
        sb.append("WHERE ");
        sb.append("(poolid = ").append(pool.getId()).append(") ");
        sb.append("LIMIT 1");
        this.logger.trace(sb.toString());
        String receivedData = executeSql(sb.toString());
        if(receivedData != null) {
            if (!receivedData.trim().isEmpty()) {
                pool = new ParseSqlPool().parsePool(receivedData, pool);
            }
        } else {
            pool.setId(null);
        }
        if (pool.getId() == null) {
            pool = null;
        }
        this.logger.trace(String.valueOf(pool));
        return pool;
    }

    @Override
    public BaculaSqlFileSet getSqlFileSet(Long id) {
        return getSqlFileSet(new BaculaSqlFileSet(id));
    }

    @Override
    public BaculaSqlFileSet getSqlFileSet(BaculaSqlFileSet fileset) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("filesetid, fileset, md5, createtime ");
        sb.append("FROM ");
        sb.append("fileset ");
        sb.append("WHERE ");
        sb.append("(filesetid = ").append(fileset.getId()).append(") ");
        sb.append("LIMIT 1");
        this.logger.trace(sb.toString());
        String receivedData = executeSql(sb.toString());
        if(receivedData != null) { 
            if (!receivedData.trim().isEmpty()) {
                fileset = new ParseSqlFileSet().parseFileSet(receivedData, fileset);
            }
        } else {
            fileset.setId(null);
        }
        if (fileset.getId() == null) {
            fileset = null;
        }
        return fileset;
    }

    @Override
    public BaculaDotStatusClientRunning getDotStatusClientRunning(String client) {
        BaculaDotStatusClientRunning statusClientRunning = null;
        String receivedData = "";
        try {
            receivedData = this.getConnection().sendAndReceive(Constants.Connection.DotCommands.STATUS_CLIENT+client+" running");
            statusClientRunning = new ParseDotStatusClient().parse(receivedData);
        } catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return statusClientRunning;
    }
    
    @Override
    public String getListJobsRestore(Long jobid) {
        String receivedData = "";
        String listJobs = null;
        try {
            receivedData = this.getConnection().sendAndReceive(".bvfs_get_jobids jobid=" + jobid + " all");
            if(!receivedData.isEmpty()) {
                listJobs = new ParseDirectories().parseListJobids(receivedData);
            }
        } catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return listJobs;
    }
    
    @Override
    public BaculaDirectory getRootDirectory(String listJobs, Integer limit, Integer offset) {
        BaculaDirectory directory = new BaculaDirectory();
        String receivedData = "";
        try {
            if(!listJobs.isEmpty()) {
                logger.trace(".bvfs_lsdir jobid=$ path=\"\" limit=$ offset=$", listJobs,limit, offset);
                
                receivedData = this.getConnection().sendAndReceive(".bvfs_lsdir jobid="+listJobs+" path=\"\" limit="+limit+" offset="+offset);
                
                directory = new ParseDirectories().parseRootDirectory(receivedData);
                receivedData = this.getConnection().sendAndReceive(".bvfs_lsfiles jobid="+listJobs+" path=\"\" limit="+limit+" offset="+offset);
                if(receivedData!=null) {
                    if(!receivedData.isEmpty()) {
                        new ParseDirectories().parseFiles(receivedData, directory);
                    }
                }
            }
        } catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return directory;
    }

    @Override
    public BaculaDirectory browseDirectory(String listJobs, Long pathid, String client, Integer limit, Integer offset) {
        BaculaDirectory directory = new BaculaDirectory();
        String receivedData = "";
        try {
            if(!listJobs.isEmpty() && pathid != null) {
                
                receivedData = this.getConnection().sendAndReceive(".bvfs_lsdir jobid=" + listJobs + " pathid=" + pathid + " limit=" + limit + " offset=" + offset);
                
                directory = new ParseDirectories().parseDirectories(receivedData);
                
                receivedData = this.getConnection().sendAndReceive(".bvfs_lsfiles jobid=" + listJobs+" pathid=" + pathid + " limit=" + limit + " offset=" + offset);
                
                if(receivedData!=null) {
                    if(!receivedData.isEmpty()) {
                        new ParseDirectories().parseFiles(receivedData, directory);
                        for(int i=0; i<directory.getFiles().size();i++) {
                            directory.getFiles().get(i).setFileVersions(getFileVersions(listJobs, pathid,directory.getFiles().get(i).getFileNameId() , client));
                        }
                    }
                }
            }
        } catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        //".bvfs_lsdir jobid=72 pathid=5 limit=500 offset=0"
        logger.trace("diretorio: {}"+directory);
        return directory;
    }

    @Override
    public List<BaculaFileVersions> getFileVersions(String listJobs, Long pathid, Long fileNameId, String client) {
        List<BaculaFileVersions> fileVersions = null;
        String receivedData = "";
        try {
            if(!listJobs.isEmpty() && pathid != null && fileNameId != null && client != null) {
                receivedData = this.getConnection().sendAndReceive(".bvfs_versions jobid=" + listJobs + " pathid=" + pathid + " fnid=" + fileNameId + " client=" + client + " versions");
                if(receivedData != null) {
                    if(!receivedData.isEmpty()) {
                        fileVersions = new ParseDirectories().parseFileVersions(receivedData);
                    }
                }
            }
        } catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return fileVersions;
    }

    @Override
    public BaculaRestoreFileDefault getBaculaRestoreFileDefault() {
        BaculaRestoreFileDefault restoreFileDefault = null;
        try {
            String receivedData = this.getConnection().sendAndReceive(".defaults job=\"RestoreFiles\"");
            restoreFileDefault = new ParseRestoreFileDefault().parse(receivedData);
        } catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            Logger.getLogger(BaculaConsole5.class.getName()).log(Level.SEVERE, null, ex);
        }
        return restoreFileDefault;
    }

    @Override
    public Boolean createTableRestore(List<BaculaDirectory> selectedDirectories, List<BaculaFile> selectedFiles, List<BaculaFileVersions> selectedFileVersions, String listJobs) {
        this.logger.trace("Entra no Método createTableRestore");
        Boolean createTable = false;
        try{
            String command = new ParseTableRestore().createCommand(selectedDirectories, selectedFiles, selectedFileVersions, listJobs);
            this.logger.trace("Comando para Criar a tabela: "+command);
            if(command != null) {
                if(!command.isEmpty()) {
                    String receivedData = this.getConnection().sendAndReceive(command);
                    createTable = receivedData.contains("OK");
                }
            }
        } catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            Logger.getLogger(BaculaConsole5.class.getName()).log(Level.SEVERE, null, ex);
        }
        return createTable;
    }

    @Override
    public void cleanTable() {
        try {
            String receivedData = this.getConnection().sendAndReceive(".bvfs_cleanup path=b2123");
        } catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            Logger.getLogger(BaculaConsole5.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public BaculaRestoreJob runRestore(BaculaRestoreFileDefault restoreFileDefault) {
        BaculaRestoreJob restoreJob = null;
        logger.trace("BaculaRestoreFileDefault: {}",restoreFileDefault);
        try {
            if(restoreFileDefault != null) {
                String receivedData = this.getConnection().sendAndReceive("restore client=" + restoreFileDefault.getClient() + " storage=" + restoreFileDefault.getStorage() + " where=\"" + restoreFileDefault.getWhere() + "\" file=\"?b2123\" when=\"" + restoreFileDefault.getWhen() + "\" done yes");
                restoreJob = new ParseRestoreJob().parse(receivedData);
                logger.trace("Fez a Restauração");
            }
        } catch (IOException | InterruptedException | BaculaInvalidDataSize | BaculaNoInteger | BaculaCommandException ex) {
            Logger.getLogger(BaculaConsole5.class.getName()).log(Level.SEVERE, null, ex);
        }
        return restoreJob;
    }

}