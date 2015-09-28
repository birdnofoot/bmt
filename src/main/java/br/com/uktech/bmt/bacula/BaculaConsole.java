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
import br.com.uktech.bmt.bacula.exceptions.BaculaAuthenticationException;
import br.com.uktech.bmt.bacula.exceptions.BaculaCommunicationException;
import java.util.List;

/**
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public interface BaculaConsole {
    
    public String getDirectorName();
    
    public BaculaStatusDirector getStatusDirector();
    
    public List<BaculaClient> getClients();
    
    public BaculaStatusClient getStatusClient(String clientName);
    
    public void reconnect() throws BaculaAuthenticationException, BaculaCommunicationException;
    
    public void disconnect();
    
    public Boolean isConnected();
    
    public void detailBaculaJob(BaculaJob job);
    
    public void updateListJobId(BaculaJob job);
    
    public void updateLlistJobId(BaculaJob job);
    
    public List<BaculaStorage> getStorages();
    
    public BaculaStatusStorage getStatusStorage(String storageName);
    
    public BaculaEstimate getEstimate(String nameJob, String level, Boolean accurate, Boolean listing);
    
    public List<BaculaJobDefault> getListJobsDefault();
    
    public BaculaJobDefault getJobDefault(String jobDefaultName);
    
    public Long runJob(BaculaJobDefault jobDefault, String when, Integer priority);
    
    public List<BaculaDotClient> getListDotClients();
    
    public List<BaculaDotFileset> getListDotFilesets();
    
    public List<BaculaDotJob> getListDotJobs();
    
    public List<BaculaDotLevel> getListDotLevels();
    
    public List<BaculaDotPool> getListDotPools();
    
    public List<BaculaDotStorage> getListDotStorage();
    
    public List<BaculaDotType> getListDotTypes();
    
    public String executeSql(String sql);
    
    public BaculaSqlJob getSqlJob(Long id);
    
    public BaculaSqlJob getSqlJob(BaculaSqlJob job);
    
    public List<BaculaSqlJob> getListSqlJob();
    
    public List<BaculaSqlJobToRestore> getListSqlJobToRestore(String clientName);
    
    public BaculaSqlClient getSqlClient(Long id);
    
    public BaculaSqlClient getSqlClient(BaculaSqlClient client);
    
    public List<BaculaSqlClient> getListSqlClient();
    
    public BaculaSqlPool getSqlPool(Long id);
    
    public BaculaSqlPool getSqlPool(BaculaSqlPool pool);
    
    public BaculaSqlFileSet getSqlFileSet(Long id);
    
    public BaculaSqlFileSet getSqlFileSet(BaculaSqlFileSet fileset);
    
    public BaculaDotStatusClientRunning getDotStatusClientRunning(String client);
    
    public String getListJobsRestore(Long jobid);
    
    public BaculaDirectory getRootDirectory(String listJobs, Integer limit, Integer offset);
    
    public BaculaDirectory browseDirectory(String listJobs, Long pathid, String client, Integer limit, Integer offset);
    
    public List<BaculaFileVersions> getFileVersions(String listJobs, Long pathid, Long fileNameId, String client);
    
    public BaculaRestoreFileDefault getBaculaRestoreFileDefault();
    
    public Boolean createTableRestore( List<BaculaDirectory> selectedDirectories, List<BaculaFile> selectedFiles, List<BaculaFileVersions> selectedFileVersions, String listJobs);
    
    //public BaculaRestoreJob runRestore(String client, String storage, String where, String when);
    public BaculaRestoreJob runRestore(BaculaRestoreFileDefault restoreFileDefault);
    
    public void cleanTable();
    
}