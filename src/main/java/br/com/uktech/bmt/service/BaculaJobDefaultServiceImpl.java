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
import br.com.uktech.bmt.bacula.bean.BaculaEstimate;
import br.com.uktech.bmt.bacula.bean.BaculaJobDefault;
import br.com.uktech.bmt.bacula.bean.dot.BaculaDotClient;
import br.com.uktech.bmt.bacula.bean.dot.BaculaDotFileset;
import br.com.uktech.bmt.bacula.bean.dot.BaculaDotJob;
import br.com.uktech.bmt.bacula.bean.dot.BaculaDotLevel;
import br.com.uktech.bmt.bacula.bean.dot.BaculaDotPool;
import br.com.uktech.bmt.bacula.bean.dot.BaculaDotStorage;
import br.com.uktech.bmt.bacula.bean.dot.BaculaDotType;
import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlJob;
import br.com.uktech.bmt.bacula.exceptions.BaculaAuthenticationException;
import br.com.uktech.bmt.bacula.exceptions.BaculaCommunicationException;
import br.com.uktech.bmt.bacula.exceptions.BaculaDirectorNotSupported;
import br.com.uktech.bmt.dto.bacula.BaculaEstimateDto;
import br.com.uktech.bmt.dto.bacula.BaculaFormEstimateDto;
import br.com.uktech.bmt.dto.bacula.BaculaFormRunJobDto;
import br.com.uktech.bmt.dto.bacula.BaculaJobDefaultDto;
import br.com.uktech.bmt.dto.bacula.dot.BaculaDotClientDto;
import br.com.uktech.bmt.dto.bacula.dot.BaculaDotFilesetDto;
import br.com.uktech.bmt.dto.bacula.dot.BaculaDotJobDto;
import br.com.uktech.bmt.dto.bacula.dot.BaculaDotLevelDto;
import br.com.uktech.bmt.dto.bacula.dot.BaculaDotPoolDto;
import br.com.uktech.bmt.dto.bacula.dot.BaculaDotStorageDto;
import br.com.uktech.bmt.dto.bacula.dot.BaculaDotTypeDto;
import br.com.uktech.bmt.dto.bacula.sql.BaculaSqlJobDto;
import br.com.uktech.bmt.dto.model.director.DirectorDto;
import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
@Service("BaculaJobDefaultService")
public class BaculaJobDefaultServiceImpl implements BaculaJobDefaultService{
    
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(BaculaClientServiceImpl.class);
    
    @Autowired
    private BaculaConsoleFactory consoleFactory;
    
    @Autowired
    private Mapper mapper;
    
    @Override
    public BaculaJobDefaultDto newJobDefault() {
        return new BaculaJobDefaultDto();
    }

    @Override
    public BaculaFormEstimateDto newFormEstimate() {
        return new BaculaFormEstimateDto();
    }
    
    @Override
    public BaculaFormRunJobDto newFormRunJob() {
        return new BaculaFormRunJobDto();
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<BaculaJobDefaultDto> getListJobDefault(DirectorDto baculadirdto) {
        List<BaculaJobDefault> jobsDefault = null;
        List<BaculaJobDefaultDto> jobsDefaultDto = new ArrayList<>();
        try{
            BaculaConsole console = consoleFactory.getConsole(baculadirdto.getName(), baculadirdto.getHostname(), baculadirdto.getPort(), baculadirdto.getPassword());
            if (console != null) {
                jobsDefault = console.getListJobsDefault();
                if(jobsDefault!=null) {
                    for(BaculaJobDefault jobDefault : jobsDefault) {
                        BaculaJobDefaultDto jobDefaultDto = new BaculaJobDefaultDto();
                        mapper.map(jobDefault, jobDefaultDto);
                        jobsDefaultDto.add(jobDefaultDto);
                    }
                }
            }
        } catch (BaculaCommunicationException | BaculaAuthenticationException | BaculaDirectorNotSupported ex) {
            this.logger.error(ex.getLocalizedMessage());
            return null;
        }
        return jobsDefaultDto;
    }

    @Transactional(readOnly = true)
    @Override
    public BaculaEstimateDto getEstimate(DirectorDto baculadirdto, BaculaFormEstimateDto formEstimate) {
        BaculaEstimate estimate = null;
        BaculaEstimateDto estimateDto = new BaculaEstimateDto();
        try{
            BaculaConsole console = consoleFactory.getConsole(baculadirdto.getName(), baculadirdto.getHostname(), baculadirdto.getPort(), baculadirdto.getPassword());
            if (console != null) {
                estimate = console.getEstimate(formEstimate.getJob(), formEstimate.getType(), formEstimate.getAccurate(), formEstimate.getListing());
                if(estimate != null) {
                    mapper.map(estimate, estimateDto);
                }
            }
        } catch (BaculaCommunicationException | BaculaAuthenticationException | BaculaDirectorNotSupported ex) {
            this.logger.error(ex.getLocalizedMessage());
            return null;
        }
        return estimateDto;
    }

    @Override
    public Long runJob(DirectorDto baculadirdto, BaculaFormRunJobDto formRunJobDto) {
        Long jobId = null;
        BaculaJobDefault jobDefault = new BaculaJobDefault();
        mapper.map((BaculaJobDefaultDto) formRunJobDto, jobDefault);
        try{
            BaculaConsole console = consoleFactory.getConsole(baculadirdto.getName(), baculadirdto.getHostname(), baculadirdto.getPort(), baculadirdto.getPassword());
            if (console != null) {
                jobId = console.runJob(jobDefault, formRunJobDto.getWhen(), formRunJobDto.getPriority());
            }
        } catch (BaculaCommunicationException | BaculaAuthenticationException | BaculaDirectorNotSupported ex) {
            this.logger.error(ex.getLocalizedMessage());
            return null;
        }
        return jobId;
    }
    
    @Override
    public BaculaFormRunJobDto getFormRunJob(DirectorDto baculadirdto, String jobDefaultName) {
        BaculaJobDefaultDto jobDefaultDto = new BaculaJobDefaultDto();
        BaculaJobDefault jobDefault = null;
        BaculaFormRunJobDto formrunjob = new BaculaFormRunJobDto();
        
        List <BaculaDotClientDto> dotClientDto = new ArrayList();
        List <BaculaDotFilesetDto> dotFilesetDto = new ArrayList();
        List <BaculaDotJobDto> dotJobDto = new ArrayList();
        List <BaculaDotLevelDto> dotLevelDto = new ArrayList();
        List <BaculaDotPoolDto> dotPoolDto = new ArrayList();
        List <BaculaDotStorageDto> dotStorageDto = new ArrayList();
        List <BaculaDotTypeDto> dotTypeDto = new ArrayList();
        
        List <BaculaDotClient> dotClient = null;
        List <BaculaDotFileset> dotFileset = null;
        List <BaculaDotJob> dotJob = null;
        List <BaculaDotLevel> dotLevel = null;
        List <BaculaDotPool> dotPool = null;
        List <BaculaDotStorage> dotStorage = null;
        List <BaculaDotType> dotType = null;
        
        try{
            BaculaConsole console = consoleFactory.getConsole(baculadirdto.getName(), baculadirdto.getHostname(), baculadirdto.getPort(), baculadirdto.getPassword());
            if (console != null) {
                jobDefault = console.getJobDefault(jobDefaultName);
                
                dotClient = console.getListDotClients();
                dotFileset = console.getListDotFilesets();
                dotJob = console.getListDotJobs();
                dotLevel = console.getListDotLevels();
                dotPool = console.getListDotPools();
                dotStorage = console.getListDotStorage();
                dotType = console.getListDotTypes();
                
                if(dotClient!=null) {
                    mapper.map(dotClient,dotClientDto);
                    formrunjob.setClients(dotClientDto);
                }
                if(dotFileset!=null) {
                    mapper.map(dotFileset,dotFilesetDto);
                    formrunjob.setFilesets(dotFilesetDto);
                }
                if(dotJob!=null) {
                    mapper.map(dotJob,dotJobDto);
                    formrunjob.setJobs(dotJobDto);
                }
                if(dotLevel!=null) {
                    mapper.map(dotLevel,dotLevelDto);
                    formrunjob.setLevels(dotLevelDto);
                }
                if(dotPool!=null) {
                    mapper.map(dotPool,dotPoolDto);
                    formrunjob.setPools(dotPoolDto);
                }
                if(dotStorage!=null) {
                    mapper.map(dotStorage,dotStorageDto);
                    formrunjob.setStorages(dotStorageDto);
                }
                if(dotType!=null) {
                    mapper.map(dotType,dotTypeDto);
                    formrunjob.setTypes(dotTypeDto);
                }
                
                if(jobDefault!=null) {
                    mapper.map(jobDefault, jobDefaultDto);
                    mapper.map(jobDefaultDto, formrunjob);
                }
            }
            this.logger.trace("getFormRunJob: {}",formrunjob.toString());
        } catch (BaculaCommunicationException | BaculaAuthenticationException | BaculaDirectorNotSupported ex) {
            this.logger.error(ex.getLocalizedMessage());
            return null;
        }
        
        return formrunjob;
    }

    @Override
    public BaculaSqlJobDto getBaculaSqlJobDtoById(DirectorDto baculadirdto, Long jobId) {
        BaculaSqlJobDto jobDto = new BaculaSqlJobDto();
        BaculaSqlJob job = null;
        try{
            BaculaConsole console = consoleFactory.getConsole(baculadirdto.getName(), baculadirdto.getHostname(), baculadirdto.getPort(), baculadirdto.getPassword());
            if (console != null) {
                job = console.getSqlJob(jobId);
                if(job != null) {
                    mapper.map(job, jobDto);
                }
            }
        } catch (BaculaCommunicationException | BaculaAuthenticationException | BaculaDirectorNotSupported ex) {
            this.logger.error(ex.getLocalizedMessage());
            return null;
        }
        return jobDto;
    }
}
