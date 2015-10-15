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

package br.com.uktech.bmt.service;

import br.com.uktech.bmt.bacula.BaculaConsole;
import br.com.uktech.bmt.bacula.BaculaConsoleFactory;
import br.com.uktech.bmt.bacula.bean.BaculaDirectory;
import br.com.uktech.bmt.bacula.bean.BaculaFile;
import br.com.uktech.bmt.bacula.bean.BaculaFileVersions;
import br.com.uktech.bmt.bacula.bean.BaculaRestoreFileDefault;
import br.com.uktech.bmt.bacula.bean.BaculaRestoreJob;
import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlJobToRestore;
import br.com.uktech.bmt.bacula.exceptions.BaculaAuthenticationException;
import br.com.uktech.bmt.bacula.exceptions.BaculaCommunicationException;
import br.com.uktech.bmt.bacula.exceptions.BaculaDirectorNotSupported;
import br.com.uktech.bmt.dto.bacula.BaculaDirectoryDto;
import br.com.uktech.bmt.dto.bacula.BaculaFileDto;
import br.com.uktech.bmt.dto.bacula.BaculaFileVersionsDto;
import br.com.uktech.bmt.dto.bacula.BaculaRestoreFileDefaultDto;
import br.com.uktech.bmt.dto.bacula.BaculaRestoreJobDto;
import br.com.uktech.bmt.dto.bacula.sql.BaculaSqlJobToRestoreDto;
import br.com.uktech.bmt.dto.model.director.DirectorDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dozer.Mapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
@Service("BaculaRestoreService")
public class BaculaRestoreServiceImpl implements BaculaRestoreService{
    
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(BaculaRestoreServiceImpl.class);
    
    @Autowired
    private BaculaConsoleFactory consoleFactory;
    
    @Autowired
    private Mapper mapper;
    
    @Override
    public BaculaDirectoryDto newBaculaDirectory() {
        return new BaculaDirectoryDto();
    }

    @Override
    public BaculaRestoreFileDefaultDto newBaculaRestoreFileDefault() {
        return new BaculaRestoreFileDefaultDto();
    }

    @Override
    public BaculaRestoreJobDto newBaculaRestoreJob() {
        return new BaculaRestoreJobDto();
    }

    @Override
    public List<BaculaSqlJobToRestoreDto> newBaculaSqlJobToRestoresDtos() {
        return new ArrayList<>();
    }

    @Override
    public List<BaculaDirectoryDto> newBaculaDirectorysDtos() {
        return new ArrayList<>();
    }

    @Override
    public List<BaculaFileDto> newBaculaFilesDtos() {
        return new ArrayList<>();
    }

    @Override
    public List<BaculaFileVersionsDto> newBaculaFileVersionsDtos() {
        return new ArrayList<>();
    }

    @Override
    public Map<Long, BaculaDirectoryDto> newMapBaculaDirectoryDto() {
        return new HashMap<>();
    }

    @Override
    public List<BaculaSqlJobToRestoreDto> getJobsToRestore(DirectorDto baculadirdto, String client) {
        List<BaculaSqlJobToRestoreDto> jobToRestoreDtos = new ArrayList<>();
        List<BaculaSqlJobToRestore> jobToRestore = null;
        BaculaSqlJobToRestoreDto sqlJobToRestoreDto = null;
        try{
            BaculaConsole console = consoleFactory.getConsole(baculadirdto.getName(), baculadirdto.getHostname(), baculadirdto.getPort(), baculadirdto.getPassword());
            if(console != null) {
                jobToRestore = console.getListSqlJobToRestore(client);
                if(jobToRestore != null) {
                    for (BaculaSqlJobToRestore job : jobToRestore) {
                        sqlJobToRestoreDto = new BaculaSqlJobToRestoreDto();
                        mapper.map(job, sqlJobToRestoreDto);
                        jobToRestoreDtos.add(sqlJobToRestoreDto);
                    }
                }
            }
        } catch(BaculaCommunicationException | BaculaAuthenticationException | BaculaDirectorNotSupported ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return jobToRestoreDtos;
    }

    @Override
    public String getRelatedJobs(DirectorDto baculadirdto, Long jobid) {
        String listJobs = null;
        try{
            BaculaConsole console = consoleFactory.getConsole(baculadirdto.getName(), baculadirdto.getHostname(), baculadirdto.getPort(), baculadirdto.getPassword());
            if(console != null) {
                listJobs = console.getListJobsRestore(jobid);
            }
        } catch(BaculaCommunicationException | BaculaAuthenticationException | BaculaDirectorNotSupported ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return listJobs;
    }

    @Override
    public BaculaDirectoryDto getRootDirectory(DirectorDto baculadirdto, String listJobs, Integer limit, Integer offset) {
        BaculaDirectoryDto rootDto = new BaculaDirectoryDto();
        BaculaDirectory root = null;
        try{
            BaculaConsole console = consoleFactory.getConsole(baculadirdto.getName(), baculadirdto.getHostname(), baculadirdto.getPort(), baculadirdto.getPassword());
            if(console != null) {
                root = console.getRootDirectory(listJobs, limit, offset);
                if(root != null) {
                    mapper.map(root, rootDto);
                    rootDto.setSelected(Boolean.FALSE);
                }
            }
        } catch(BaculaCommunicationException | BaculaAuthenticationException | BaculaDirectorNotSupported ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return rootDto;
    }

    @Override
    public BaculaDirectoryDto getCurrentDirectory(DirectorDto baculadirdto, String listJobs, Long pathId, String client, Integer limit, Integer offset) {
        BaculaDirectoryDto directoryDto = null;
        BaculaDirectory directory = null;
        try{
            BaculaConsole console = consoleFactory.getConsole(baculadirdto.getName(), baculadirdto.getHostname(), baculadirdto.getPort(), baculadirdto.getPassword());
            if(console != null) {
                directory = console.browseDirectory(listJobs, pathId, client, limit, offset);
                if(directory != null) {
                    directoryDto = new BaculaDirectoryDto();
                    mapper.map(directory, directoryDto);
                    directoryDto.setSelected(Boolean.FALSE);
                }
            }
        } catch(BaculaCommunicationException | BaculaAuthenticationException | BaculaDirectorNotSupported ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return directoryDto;
    }

    @Override
    public BaculaRestoreFileDefaultDto getBaculaRestoreFileDefaultDto(DirectorDto baculadirdto) {
        BaculaRestoreFileDefaultDto restoreFileDefaultDto = null;
        BaculaRestoreFileDefault restoreFileDefault = null;
        try{
            BaculaConsole console = consoleFactory.getConsole(baculadirdto.getName(), baculadirdto.getHostname(), baculadirdto.getPort(), baculadirdto.getPassword());
            if(console != null) {
                restoreFileDefault = console.getBaculaRestoreFileDefault();
                if(restoreFileDefault != null) {
                    restoreFileDefaultDto = new BaculaRestoreFileDefaultDto();
                    mapper.map(restoreFileDefault, restoreFileDefaultDto);
                }
            }
        } catch(BaculaCommunicationException | BaculaAuthenticationException | BaculaDirectorNotSupported ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return restoreFileDefaultDto;
    }

    @Override
    public Boolean createTableRestore(DirectorDto baculadirdto, List<BaculaDirectoryDto> selectedDirectoriesDtos, List<BaculaFileDto> selectedFilesDtos, List<BaculaFileVersionsDto> selectedFileVersionsDtos, String listJobs) {
        Boolean createTable = false;
        List<BaculaDirectory> selectedDirectories = new ArrayList<>();
        List<BaculaFile> selectedFiles = new ArrayList<>();
        List<BaculaFileVersions> selectedFileVersions = new ArrayList<>();
        BaculaDirectory directory = null;
        BaculaFile file = null;
        BaculaFileVersions fileVersions = null;
        
        try{
            BaculaConsole console = consoleFactory.getConsole(baculadirdto.getName(), baculadirdto.getHostname(), baculadirdto.getPort(), baculadirdto.getPassword());
            if(console != null) {
                
                for (BaculaDirectoryDto dir : selectedDirectoriesDtos) {
                    directory = new BaculaDirectory();
                    mapper.map(dir, directory);
                    selectedDirectories.add(directory);
                }
                
                for (BaculaFileDto fil : selectedFilesDtos) {
                    file = new BaculaFile();
                    mapper.map(fil, file);
                    selectedFiles.add(file);
                }
                
                for (BaculaFileVersions filver : selectedFileVersions) {
                    fileVersions = new BaculaFileVersions();
                    mapper.map(filver, fileVersions);
                    selectedFileVersions.add(fileVersions);
                }
                
                createTable = console.createTableRestore(selectedDirectories, selectedFiles, selectedFileVersions, listJobs);
            }
        } catch(BaculaCommunicationException | BaculaAuthenticationException | BaculaDirectorNotSupported ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return createTable;
    }

    @Override
    public BaculaRestoreJobDto runRestore(DirectorDto baculadirdto, BaculaRestoreFileDefaultDto restoreFileDefaultDto) {
        BaculaRestoreJobDto restoreJobDto = null;
        BaculaRestoreJob restoreJob = null;
        BaculaRestoreFileDefault restoreFileDefault = null;
        try{
            BaculaConsole console = consoleFactory.getConsole(baculadirdto.getName(), baculadirdto.getHostname(), baculadirdto.getPort(), baculadirdto.getPassword());
            if(console != null) {
                restoreFileDefault = new BaculaRestoreFileDefault();
                restoreJobDto = new BaculaRestoreJobDto();
                mapper.map(restoreFileDefaultDto, restoreFileDefault);
                restoreJob = console.runRestore(restoreFileDefault);
                mapper.map(restoreJob, restoreJobDto);
            }
        } catch(BaculaCommunicationException | BaculaAuthenticationException | BaculaDirectorNotSupported ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return restoreJobDto;
    }

    @Override
    public Boolean cleanTable(DirectorDto baculadirdto) {
        try{
            BaculaConsole console = consoleFactory.getConsole(baculadirdto.getName(), baculadirdto.getHostname(), baculadirdto.getPort(), baculadirdto.getPassword());
            if(console != null) {
                console.cleanTable();
                return true;
            } else {
                return false;
            }
        } catch(BaculaCommunicationException | BaculaAuthenticationException | BaculaDirectorNotSupported ex) {
            this.logger.error(ex.getLocalizedMessage());
            return false;
        }
    }

}