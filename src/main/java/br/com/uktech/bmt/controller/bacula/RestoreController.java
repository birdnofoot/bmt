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

package br.com.uktech.bmt.controller.bacula;

import br.com.uktech.bmt.bacula.bean.dot.BaculaDotClient;
import br.com.uktech.bmt.dto.bacula.BaculaClientDto;
import br.com.uktech.bmt.dto.bacula.BaculaDirectoryDto;
import br.com.uktech.bmt.dto.bacula.BaculaFileDto;
import br.com.uktech.bmt.dto.bacula.BaculaFileVersionsDto;
import br.com.uktech.bmt.dto.bacula.BaculaRestoreFileDefaultDto;
import br.com.uktech.bmt.dto.bacula.BaculaRestoreJobDto;
import br.com.uktech.bmt.dto.bacula.dot.BaculaDotClientDto;
import br.com.uktech.bmt.dto.bacula.dot.BaculaDotStorageDto;
import br.com.uktech.bmt.dto.bacula.sql.BaculaSqlJobToRestoreDto;
import br.com.uktech.bmt.dto.model.director.DirectorDto;
import br.com.uktech.bmt.service.BaculaClientService;
import br.com.uktech.bmt.service.BaculaDirectorService;
import br.com.uktech.bmt.service.BaculaRestoreService;
import br.com.uktech.bmt.service.BaculaStorageService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
@Controller
@RequestMapping(value = "/bacula/restore")
public class RestoreController extends BasicBaculaController {
    
    private final Logger logger = LoggerFactory.getLogger(RestoreController.class);
    
    @Autowired
    BaculaDirectorService baculaDirectorService;
    
    @Autowired
    BaculaRestoreService baculaRestoreService;
    
    @Autowired
    BaculaClientService baculaClientService;
    
    @Autowired
    BaculaStorageService baculaStorageService;
    
    private BaculaDirectoryDto root;
    private BaculaDirectoryDto current;
    private Map<Long, BaculaDirectoryDto> directory = new HashMap<>();
    private Integer limit = 500, offset = 0;
    
    private List<BaculaDirectoryDto> selectedDirectories = new ArrayList<>();
    private List<BaculaFileDto> selectedFiles = new ArrayList<>();
    private List<BaculaFileVersionsDto> selectedFileVersions = new ArrayList<>();
    
    @Override
    public String getModule() {
        return "restore";
    }
    
    @RequestMapping(value = "/{directorId}", method = RequestMethod.GET)
    public ModelAndView index(@PathVariable Long directorId, HttpServletRequest request, HttpServletResponse response, Pageable p) {
        logger.debug("Index Bacula Restore Home");
        ModelAndView mav;
        mav = new ModelAndView("bacula/restore/index");
        DirectorDto directorDto = baculaDirectorService.getBaculaDirectorById(directorId);
        List<BaculaDotClientDto> clientDtos = baculaClientService.getListDotClientDtos(directorDto);
        mav.addObject("directorId", directorId);
        mav.addObject("clientDtos", clientDtos);
        return mav;
    }
    
    @RequestMapping(value = "/{directorId}/{clientName}", method = RequestMethod.GET)
    public ModelAndView jobs(@PathVariable Long directorId, @PathVariable String clientName, HttpServletRequest request, HttpServletResponse response, Pageable p) {
        logger.debug("jobs Bacula Restore Home");
        directory.clear();
        selectedDirectories.clear();
        selectedFiles.clear();
        selectedFileVersions.clear();
        ModelAndView mav = index(directorId, request, response, p);
        DirectorDto directorDto = baculaDirectorService.getBaculaDirectorById(directorId);
        List<BaculaSqlJobToRestoreDto> jobToRestoreDtos = null;
        if(clientName != null) {
            jobToRestoreDtos = baculaRestoreService.getJobsToRestore(directorDto, clientName);
        }
        mav.addObject("clientName", clientName);
        mav.addObject("jobToRestoreDtos", jobToRestoreDtos);
        return mav;
    }
    
    //http://localhost:8084/bmt/bacula/restore/2/vuktechdev01-fd/72
    @RequestMapping(value = "/{directorId}/{clientName}/{jobId}", method = RequestMethod.GET)
    public ModelAndView listJobs(@PathVariable Long directorId, @PathVariable String clientName, @PathVariable Long jobId, HttpServletRequest request, HttpServletResponse response, Pageable p) {
        logger.debug("Select listJobs");
        directory.clear();
        selectedDirectories.clear();
        selectedFiles.clear();
        selectedFileVersions.clear();
        ModelAndView mav = jobs(directorId, clientName, request, response, p);
        DirectorDto directorDto = baculaDirectorService.getBaculaDirectorById(directorId);
        String listJobs = null;
        root = null;
        if(jobId != null) {
            listJobs = baculaRestoreService.getRelatedJobs(directorDto, jobId);
            if(listJobs!=null) {
                root = baculaRestoreService.getRootDirectory(directorDto, listJobs, limit, offset);
                current = root;
                if (current.getChild() != null) {
                    for (Iterator<BaculaDirectoryDto> iterator = current.getChild().iterator(); iterator.hasNext();) {
                        BaculaDirectoryDto next = iterator.next();
                        next.setSelected(Boolean.FALSE);
                    }
                }
                directory.put(root.getPathId(), current);
            }
        }
        if(current.getFiles() != null) {
            for(int i=0;i<current.getFiles().size();i++) {
                Collections.reverse(current.getFiles().get(i).getFileVersions());
            }
        }
        //Integer i = 0;
        mav.addObject("jobId", jobId);
        mav.addObject("listJobs", listJobs);
        mav.addObject("current", current);
        mav.addObject("selectedDirectories", selectedDirectories);
        mav.addObject("selectedFiles", selectedFiles);
        mav.addObject("selectedFileVersions", selectedFileVersions);
        
        //mav.addObject("i", i);
        //mav.addObject("ix", i);
        //mav.addObject("idx", i);
        return mav;
    }
    
    //http://localhost:8084/bmt/bacula/restore/2/vuktechdev01-fd/72/72,57/5
    @RequestMapping(value = "/{directorId}/{clientName}/{jobId}/{listJobs}/{pathId}", method = RequestMethod.GET)
    public ModelAndView browserDirectory(@PathVariable Long directorId, @PathVariable String clientName, @PathVariable Long jobId, @PathVariable String listJobs, @PathVariable Long pathId, HttpServletRequest request, HttpServletResponse response, Pageable p) {
        logger.debug("browserDirectory");
        
        ModelAndView mav = new ModelAndView("bacula/restore/index");
        DirectorDto directorDto = baculaDirectorService.getBaculaDirectorById(directorId);
        List<BaculaDotClientDto> clientDtos = baculaClientService.getListDotClientDtos(directorDto);
        List<BaculaSqlJobToRestoreDto> jobToRestoreDtos = null;
        
        if(clientName != null) {
            jobToRestoreDtos = baculaRestoreService.getJobsToRestore(directorDto, clientName);
        }
        
        if(listJobs!=null) {
            if(directory.containsKey(pathId)) {
                current = directory.get(pathId);
            } else {
                current = baculaRestoreService.getCurrentDirectory(directorDto, listJobs, pathId, clientName, limit, offset);
                //setar o campo selected de todos os filhos como false
                if (current.getChild() != null) {
                    for (Iterator<BaculaDirectoryDto> iterator = current.getChild().iterator(); iterator.hasNext();) {
                        BaculaDirectoryDto next = iterator.next();
                        next.setSelected(Boolean.FALSE);
                    }
                }
                directory.put(pathId, current);
            }
        }
        //inverter a lista de versões dos arquivos
        if(current.getFiles() != null) {
            for(int i=0;i<current.getFiles().size();i++) {
                Collections.reverse(current.getFiles().get(i).getFileVersions());
            }
        }
        
        //Integer i = 0;
        mav.addObject("directorId", directorId);
        mav.addObject("clientDtos", clientDtos);
        mav.addObject("clientName", clientName);
        mav.addObject("jobToRestoreDtos", jobToRestoreDtos);
        mav.addObject("jobId", jobId);
        mav.addObject("listJobs", listJobs);
        mav.addObject("current", current);
        mav.addObject("selectedDirectories", selectedDirectories);
        mav.addObject("selectedFiles", selectedFiles);
        mav.addObject("selectedFileVersions", selectedFileVersions);
        //mav.addObject("i", i);
        //mav.addObject("ix", i);
        //mav.addObject("idx", i);
        return mav;
    }
    
    //atualizar o cache com os tiretórios selecionados.
    @RequestMapping(value = "/{directorId}/{clientName}/{jobId}/{listJobs}/{pathId}", method = RequestMethod.POST)
    public ModelAndView selectDirectory(@Valid BaculaDirectoryDto currentSelected, @PathVariable Long directorId, @PathVariable String clientName, @PathVariable Long jobId, @PathVariable String listJobs, @PathVariable Long pathId, HttpServletRequest request, HttpServletResponse response, Pageable p) {
        
        logger.debug("Select Directory");
        
        ModelAndView mav = new ModelAndView("bacula/restore/index");
        
        DirectorDto directorDto = baculaDirectorService.getBaculaDirectorById(directorId);
        List<BaculaDotClientDto> clientDtos = baculaClientService.getListDotClientDtos(directorDto);
        List<BaculaSqlJobToRestoreDto> jobToRestoreDtos = null;
        
        if(clientName != null) {
            jobToRestoreDtos = baculaRestoreService.getJobsToRestore(directorDto, clientName);
        }
        
        if(listJobs!=null) {
            if(directory.containsKey(pathId)) {
                current = directory.get(pathId);
            } else {
                current = baculaRestoreService.getCurrentDirectory(directorDto, listJobs, pathId, clientName, limit, offset);
                //setar o campo selected de todos os filhos como false
                if (current.getChild() != null) {
                    for (Iterator<BaculaDirectoryDto> iterator = current.getChild().iterator(); iterator.hasNext();) {
                        BaculaDirectoryDto next = iterator.next();
                        next.setSelected(Boolean.FALSE);
                    }
                }
                directory.put(pathId, current);
            }
        }
        //inverter a lista de versões dos arquivos
        if(current.getFiles() != null) {
            for(int i=0;i<current.getFiles().size();i++) {
                Collections.reverse(current.getFiles().get(i).getFileVersions());
            }
        }
        //Seleciona os diretórios
        if(currentSelected.getChild() != null) {
            for (BaculaDirectoryDto child : currentSelected.getChild()) {
                for (BaculaDirectoryDto ch : current.getChild()) {
                    if(ch.getPathId().equals(child.getPathId()) && ch.getJobId().equals(child.getJobId())) {
                        if(child.getSelected()) {
                            ch.setSelected(Boolean.TRUE);
                            if(!selectedDirectories.contains(ch)) {
                                selectedDirectories.add(ch);
                            }
                        } else {
                            ch.setSelected(Boolean.FALSE);
                            if(selectedDirectories.contains(ch)) {
                                selectedDirectories.remove(ch);
                            }
                        }
                    }
                }
            }
        }
        //Criar as condições para selecionar os arquivos
        if(currentSelected.getFiles() != null) {
            for (BaculaFileDto file : currentSelected.getFiles()) {
                for (BaculaFileDto fl : current.getFiles()) {
                    if(fl.getFileId().equals(file.getFileId()) && fl.getJobId().equals(file.getJobId())) {
                        if(file.getSelected()) {
                            fl.setSelected(Boolean.TRUE);
                            if(!selectedFiles.contains(fl)) {
                                selectedFiles.add(fl);
                            }
                        } else {
                            fl.setSelected(Boolean.FALSE);
                            if(selectedFiles.contains(fl)) {
                                selectedFiles.remove(fl);
                            }
                        }
                    }
                }
            }
        }
        
        if(currentSelected.getFiles() != null) {
            for (BaculaFileDto file : currentSelected.getFiles()) {
                if(file.getFileVersions() != null) {
                    for (BaculaFileVersionsDto fileV : file.getFileVersions()) {
                        for (BaculaFileDto fl : current.getFiles()) {
                            if(fl.getFileVersions() != null) {
                                for (BaculaFileVersionsDto flv : fl.getFileVersions()) {
                                    if(fileV.getJobId().equals(flv.getJobId()) && fileV.getFileId().equals(flv.getFileId())) {
                                        if(fileV.getSelected()) {
                                            flv.setSelected(Boolean.TRUE);
                                            flv.setPath(fl.getPath());
                                            if(!selectedFileVersions.contains(flv)) {
                                                selectedFileVersions.add(flv);
                                            }
                                        } else {
                                            flv.setSelected(Boolean.FALSE);
                                            if(selectedFileVersions.contains(flv)) {
                                                selectedFileVersions.remove(flv);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        //Integer i = 0;
        mav.addObject("directorId", directorId);
        mav.addObject("clientDtos", clientDtos);
        mav.addObject("clientName", clientName);
        mav.addObject("jobToRestoreDtos", jobToRestoreDtos);
        mav.addObject("jobId", jobId);
        mav.addObject("listJobs", listJobs);
        mav.addObject("current", current);
        mav.addObject("selectedDirectories", selectedDirectories);
        mav.addObject("selectedFiles", selectedFiles);
        mav.addObject("selectedFileVersions", selectedFileVersions);
        //mav.addObject("i", i);
        //mav.addObject("ix", i);
        //mav.addObject("idx", i);
        return mav;
    }
    
    
    //http://localhost:8084/bmt/bacula/restore/run/2/vuktechdev01-fd/72
    @RequestMapping(value = "/run/{directorId}/{clientName}/{jobId}", method = RequestMethod.GET)
    public ModelAndView createTable(@PathVariable Long directorId, @PathVariable String clientName, @PathVariable Long jobId, HttpServletRequest request, HttpServletResponse response, Pageable p) {
        logger.debug("Create Table");
        
        ModelAndView mav = new ModelAndView("bacula/restore/run");
        
        DirectorDto directorDto = baculaDirectorService.getBaculaDirectorById(directorId);
        BaculaRestoreFileDefaultDto fileDefaultDto = null;
        String listJobs = null;
        Boolean createTable = Boolean.FALSE, cleanTable = Boolean.FALSE;
        List<BaculaDotStorageDto> storages = null;
        
        if(directorDto != null && jobId != null) {
            listJobs = baculaRestoreService.getRelatedJobs(directorDto, jobId);
            fileDefaultDto = baculaRestoreService.getBaculaRestoreFileDefaultDto(directorDto);
            cleanTable = baculaRestoreService.cleanTable(directorDto);
            createTable = baculaRestoreService.createTableRestore(directorDto, selectedDirectories, selectedFiles, selectedFileVersions, listJobs);
            logger.debug("tabela Criada: "+createTable);
            storages = baculaStorageService.getListStoragesDto(directorDto);
            if(current==null) {
                current = baculaRestoreService.getRootDirectory(directorDto, listJobs, limit, offset);
            }
        }
        mav.addObject("directorId", directorId);
        mav.addObject("clientName", clientName);
        mav.addObject("jobId", jobId);
        mav.addObject("listJobs", listJobs);
        mav.addObject("current",current);
        mav.addObject("selectedDirectories", selectedDirectories);
        mav.addObject("selectedFiles", selectedFiles);
        mav.addObject("selectedFileVersions", selectedFileVersions);
        mav.addObject("fileDefaultDto", fileDefaultDto);
        mav.addObject("storages", storages);
        return mav;
    }
    
    @RequestMapping(value = "/run/{directorId}/{clientName}/{jobId}", method = RequestMethod.POST)
    public ModelAndView runRestore(@Valid BaculaRestoreFileDefaultDto fileDefaultDto, @PathVariable Long directorId, @PathVariable String clientName, @PathVariable Long jobId, HttpServletRequest request, HttpServletResponse response, Pageable p) {
        logger.debug("Restore Files");
        logger.debug("\n\nFileDefaultDto :"+fileDefaultDto.toString());
        ModelAndView mav = new ModelAndView("bacula/restore/show");
        
        DirectorDto directorDto = baculaDirectorService.getBaculaDirectorById(directorId);
        BaculaRestoreJobDto restoreJobDto = null;
        Boolean cleanTable = false;
        
        try {
            if(fileDefaultDto != null) {
                restoreJobDto = baculaRestoreService.runRestore(directorDto, fileDefaultDto);
            }
        } catch (Exception e) {
            logger.error("Erro: "+e.getMessage());
        } finally {
            cleanTable = baculaRestoreService.cleanTable(directorDto);
        }
        
        mav.addObject("directorId", directorId);
        mav.addObject("restoreJobDto", restoreJobDto);
        return mav;
    }
}
