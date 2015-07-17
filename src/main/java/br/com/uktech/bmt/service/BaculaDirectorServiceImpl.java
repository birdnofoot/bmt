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
import br.com.uktech.bmt.bacula.bean.StatusDirector;
import br.com.uktech.bmt.bacula.exceptions.BaculaAuthenticationException;
import br.com.uktech.bmt.bacula.exceptions.BaculaDirectorNotSupported;
import br.com.uktech.bmt.dto.bacula.director.BaculaDirectorDto;
import br.com.uktech.bmt.model.BaculaDirector;
import br.com.uktech.bmt.model.repository.BaculaDirectorRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dozer.Mapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("BaculaDirectorService")
public class BaculaDirectorServiceImpl implements BaculaDirectorService {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(BaculaDirectorServiceImpl.class);
    
    @Autowired
    private BaculaConsoleFactory consoleFactory;
    
    @Autowired
    private BaculaDirectorRepository repository;

    @Autowired
    private Mapper mapper;
    
    @Override
    public BaculaDirectorDto newBaculaDirector() {
        return new BaculaDirectorDto();
    }

    @Override
    public BaculaDirectorDto save(BaculaDirectorDto baculadirdto) {
        BaculaDirector baculadir = new BaculaDirector();
        mapper.map(baculadirdto, baculadir);
        baculadir = repository.save(baculadir);
        mapper.map(baculadirdto, baculadir);
        return baculadirdto;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<BaculaDirectorDto> searchAllBaculaDirectors(Pageable p) {
        List <BaculaDirectorDto> directors = new ArrayList<>();
        Page<BaculaDirector> baculaDirectors = repository.findAll(p);
        BaculaDirectorDto directorDto;
        Iterator<BaculaDirector> itr = baculaDirectors.getContent().iterator();
        while(itr.hasNext()) {
            directorDto = new BaculaDirectorDto();
            mapper.map(itr.next(), directorDto);
            directors.add(directorDto);
        }
        Page<BaculaDirectorDto> page = null;
        if (!directors.isEmpty()) {
            page = new PageImpl<>(directors, p, baculaDirectors.getTotalElements());
        }        
        return page;
    }
    
    @Transactional(readOnly = true)
    @Override
    public BaculaDirectorDto getBaculaDirectorById(Long id) {
        BaculaDirectorDto baculadirdto = null;
        BaculaDirector baculadir = repository.findOne(id);
        if (baculadir != null) {
            baculadirdto = new BaculaDirectorDto();
            mapper.map(baculadir, baculadirdto);
        }
        return baculadirdto;
    }

    @Transactional(readOnly = true)
    @Override
    public StatusDirector getStatusDirector(BaculaDirectorDto baculadirdto) {
        StatusDirector statusDir = null;
        try {
            BaculaConsole console = consoleFactory.getConsole(baculadirdto.getName(), baculadirdto.getHostname(), baculadirdto.getPort(), baculadirdto.getPassword());
            if (console != null) {
                statusDir = console.getStatusDirector();
            }
        }
        catch (IOException | BaculaAuthenticationException | BaculaDirectorNotSupported ex) {
            this.logger.error(ex.getLocalizedMessage());
            return null;
        }
        return statusDir;
    }
    
}
