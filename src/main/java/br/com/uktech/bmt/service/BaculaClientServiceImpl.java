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
import br.com.uktech.bmt.bacula.bean.BaculaClient;
import br.com.uktech.bmt.bacula.bean.BaculaStatusClient;
import br.com.uktech.bmt.bacula.bean.dot.BaculaDotStatusClientRunning;
import br.com.uktech.bmt.bacula.exceptions.BaculaAuthenticationException;
import br.com.uktech.bmt.bacula.exceptions.BaculaCommunicationException;
import br.com.uktech.bmt.bacula.exceptions.BaculaDirectorNotSupported;
import br.com.uktech.bmt.dto.bacula.BaculaClientDto;
import br.com.uktech.bmt.dto.bacula.BaculaStatusClientDto;
import br.com.uktech.bmt.dto.bacula.dot.BaculaDotStatusClientRunningDto;
import br.com.uktech.bmt.dto.model.director.DirectorDto;
import java.util.ArrayList;
import java.util.Iterator;
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
@Service("BaculaClientService")
public class BaculaClientServiceImpl implements BaculaClientService{
    
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(BaculaClientServiceImpl.class);
    
    @Autowired
    private BaculaConsoleFactory consoleFactory;
    
    @Autowired
    private Mapper mapper;
    
    @Override
    public BaculaClientDto newClient() {
        return new BaculaClientDto();
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<BaculaClientDto> getListClients(DirectorDto baculadirdto) {
        List<BaculaClient> clients = null;
        List<BaculaClientDto> clientsDto = new ArrayList<>();
        try{
            BaculaConsole console = consoleFactory.getConsole(baculadirdto.getName(), baculadirdto.getHostname(), baculadirdto.getPort(), baculadirdto.getPassword());
            if (console != null) {
                clients = console.getClients();
                if(clients != null) {
                    for (BaculaClient client : clients) {
                        BaculaClientDto clientDto = new BaculaClientDto();
                        mapper.map(client, clientDto);
                        clientsDto.add(clientDto);
                    }
                }
            }
        } catch (BaculaCommunicationException | BaculaAuthenticationException | BaculaDirectorNotSupported ex) {
            this.logger.error(ex.getLocalizedMessage());
            return null;
        }
        return clientsDto;
    }

    @Override
    public BaculaStatusClientDto getStatusClient(DirectorDto baculadirdto, String clientName) {
        BaculaStatusClientDto statusClientDto = null;
        BaculaStatusClient statusClient = null;
        //BaculaClient client = null;
        try{
            BaculaConsole console = consoleFactory.getConsole(baculadirdto.getName(), baculadirdto.getHostname(), baculadirdto.getPort(), baculadirdto.getPassword());
            if (console != null) {
                statusClient = console.getStatusClient(clientName);
                if(statusClient!=null) {
                    statusClientDto = new BaculaStatusClientDto();
                    mapper.map(statusClient,statusClientDto);
                }
            }
        } catch (BaculaCommunicationException | BaculaAuthenticationException | BaculaDirectorNotSupported ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return statusClientDto;
    }

    @Override
    public BaculaDotStatusClientRunningDto getDotStatusClient(DirectorDto baculadirdto, String clientName) {
        BaculaDotStatusClientRunningDto statusClientRunningDto = new BaculaDotStatusClientRunningDto();
        BaculaDotStatusClientRunning statusClientRunning = null;
        
        try{
            BaculaConsole console = consoleFactory.getConsole(baculadirdto.getName(), baculadirdto.getHostname(), baculadirdto.getPort(), baculadirdto.getPassword());
            if (console != null) {
                statusClientRunning = console.getDotStatusClientRunning(clientName);
                if(statusClientRunning!=null) {
                    mapper.map(statusClientRunning,statusClientRunningDto);
                }
            }
        } catch (BaculaCommunicationException | BaculaAuthenticationException | BaculaDirectorNotSupported ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return statusClientRunningDto;
    }
    
}
