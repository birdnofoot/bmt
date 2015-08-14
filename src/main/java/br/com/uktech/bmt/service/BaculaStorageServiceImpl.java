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
import br.com.uktech.bmt.bacula.bean.BaculaStatusStorage;
import br.com.uktech.bmt.bacula.bean.BaculaStorage;
import br.com.uktech.bmt.bacula.exceptions.BaculaAuthenticationException;
import br.com.uktech.bmt.bacula.exceptions.BaculaCommunicationException;
import br.com.uktech.bmt.bacula.exceptions.BaculaDirectorNotSupported;
import br.com.uktech.bmt.dto.bacula.storage.BaculaStatusStorageDto;
import br.com.uktech.bmt.dto.bacula.storage.BaculaStorageDto;
import br.com.uktech.bmt.dto.model.director.DirectorDto;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dozer.Mapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
@Service("BaculaStorageService")
public class BaculaStorageServiceImpl implements BaculaStorageService{
    
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(BaculaStorageServiceImpl.class);
    
    @Autowired
    private BaculaConsoleFactory consoleFactory;
    
    @Autowired
    private Mapper mapper;
    
    @Override
    public BaculaStorageDto newStorage() {
        return new BaculaStorageDto();
    }
    
    @Transactional(readOnly = true) //Se é apenas uma consulta e não uma alteração. Perguntar se é isto mesmo.
    @Override
    public List<BaculaStorageDto> getListStorage(DirectorDto baculadirdto) {
        List<BaculaStorageDto> storagesDto = new ArrayList<>();
        List<BaculaStorage> storages = null;
        try{
            BaculaConsole console = consoleFactory.getConsole(baculadirdto.getName(), baculadirdto.getHostname(), baculadirdto.getPort(), baculadirdto.getPassword());
            if(console != null) {
                storages = console.getStorages();
                if(storages != null) {
                    for (Iterator<BaculaStorage> iterator = storages.iterator(); iterator.hasNext();) {
                        BaculaStorage storage = iterator.next();
                        BaculaStorageDto storageDto = new BaculaStorageDto();
                        mapper.map(storage, storageDto);
                        storagesDto.add(storageDto);
                    }
                }
            }
        } catch(BaculaCommunicationException | BaculaAuthenticationException | BaculaDirectorNotSupported ex) {
            this.logger.error(ex.getLocalizedMessage());
            return null;
        }
        return storagesDto;
    }

    @Override
    public BaculaStatusStorageDto getStatusStorage(DirectorDto baculadirdto, String nameStorage) {
        BaculaStatusStorageDto statusStorageDto = new BaculaStatusStorageDto();
        BaculaStatusStorage statusStorage = null;
        try {
            BaculaConsole console = consoleFactory.getConsole(baculadirdto.getName(), baculadirdto.getHostname(), baculadirdto.getPort(), baculadirdto.getPassword());
            if (console != null) {
                statusStorage = console.getStatusStorage(nameStorage);
                if(statusStorage!=null) {
                    mapper.map(statusStorage, statusStorageDto);
                }
            }
        }
        catch (BaculaAuthenticationException | BaculaDirectorNotSupported | BaculaCommunicationException ex) {
            Logger.getLogger(BaculaStorageServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statusStorageDto;
    }

}
