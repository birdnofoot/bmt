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
import br.com.uktech.bmt.bacula.bean.BaculaStatusDirector;
import br.com.uktech.bmt.bacula.exceptions.BaculaAuthenticationException;
import br.com.uktech.bmt.bacula.exceptions.BaculaCommunicationException;
import br.com.uktech.bmt.bacula.exceptions.BaculaDirectorNotSupported;
import br.com.uktech.bmt.bacula.exceptions.BaculaInvalidDataSize;
import br.com.uktech.bmt.bacula.exceptions.BaculaNoInteger;
import br.com.uktech.bmt.dto.model.director.DirectorDto;
import br.com.uktech.bmt.model.Director;
import br.com.uktech.bmt.model.repository.BaculaDirectorRepository;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public DirectorDto newBaculaDirector() {
        return new DirectorDto();
    }

    @Override
    public DirectorDto save(DirectorDto baculadirdto) {
        Director baculadir = new Director();
        mapper.map(baculadirdto, baculadir);
        baculadir = repository.save(baculadir);
        mapper.map(baculadirdto, baculadir);
        return baculadirdto;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<DirectorDto> searchAllBaculaDirectors(Pageable p) {
        List <DirectorDto> directors = new ArrayList<>();
        Page<Director> baculaDirectors = repository.findAll(p);
        DirectorDto directorDto;
        Iterator<Director> itr = baculaDirectors.getContent().iterator();
        while(itr.hasNext()) {
            directorDto = new DirectorDto();
            mapper.map(itr.next(), directorDto);
            directors.add(directorDto);
        }
        Page<DirectorDto> page = null;
        if (!directors.isEmpty()) {
            page = new PageImpl<>(directors, p, baculaDirectors.getTotalElements());
        }        
        return page;
    }
    
    @Transactional(readOnly = true)
    @Override
    public DirectorDto getBaculaDirectorById(Long id) {
        DirectorDto baculadirdto = null;
        Director baculadir = repository.findOne(id);
        if (baculadir != null) {
            baculadirdto = new DirectorDto();
            mapper.map(baculadir, baculadirdto);
        }
        return baculadirdto;
    }

    @Transactional(readOnly = true)
    @Override
    public BaculaStatusDirector getStatusDirector(DirectorDto baculadirdto) {
        BaculaStatusDirector statusDir = null;
        try {
            BaculaConsole console = consoleFactory.getConsole(baculadirdto.getName(), baculadirdto.getHostname(), baculadirdto.getPort(), baculadirdto.getPassword());
            if (console != null) {
                statusDir = console.getStatusDirector();
            }
        }
        catch (IOException | BaculaCommunicationException | BaculaAuthenticationException | BaculaDirectorNotSupported ex) {
            this.logger.error(ex.getLocalizedMessage());
            return null;
        }
        
        return statusDir;
    }
    
}
