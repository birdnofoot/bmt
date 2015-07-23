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

import br.com.uktech.bmt.bacula.bean.BaculaStatusDirector;
import br.com.uktech.bmt.dto.bacula.client.BaculaClientDto;
import br.com.uktech.bmt.dto.bacula.client.BaculaStatusClientDto;
import br.com.uktech.bmt.dto.model.director.DirectorDto;
import br.com.uktech.bmt.service.BaculaClientService;
import br.com.uktech.bmt.service.BaculaDirectorService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping(value = "/bacula/client")
public class ClientController extends BasicBaculaController {
    
    private final Logger logger = LoggerFactory.getLogger(ClientController.class);
    
    @Autowired
    BaculaDirectorService baculaDirectorService;
    
    @Autowired
    BaculaClientService baculaClientService;
    
    @Override
    public String getModule() {
        return "client";
    }
    
    @RequestMapping(value = "/{directorId}", method = RequestMethod.GET)
    public ModelAndView index(@PathVariable Long directorId, HttpServletRequest request, HttpServletResponse response, Pageable p) {
        logger.debug("Show Bacula Client Home");
        ModelAndView mav;
        List<BaculaClientDto> clients = null;
        mav = new ModelAndView("bacula/client/index");
        DirectorDto directorDto = baculaDirectorService.getBaculaDirectorById(directorId);
        if (directorDto != null) {
            clients = baculaClientService.getListClients(directorDto);
        }
        mav.addObject("directorId", directorId);
        mav.addObject("clients", clients);
        return mav;
    }
    
    @RequestMapping(value = "/status/{directorId}/{clientName}", method = RequestMethod.GET)
    public ModelAndView status(@PathVariable Long directorId, @PathVariable String clientName, HttpServletRequest request, HttpServletResponse response, Pageable p) {
        logger.debug("Show Bacula Client Status");
        ModelAndView mav;
        BaculaStatusClientDto status = null;
        mav = new ModelAndView("bacula/client/status");
        DirectorDto directorDto = baculaDirectorService.getBaculaDirectorById(directorId);
        if (directorDto != null) {
            status = baculaClientService.getStatusClient(directorDto, clientName);
        }
        
        mav.addObject("status", status);
        mav.addObject("directorId", directorId);
        return mav;
    }
}
