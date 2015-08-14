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

import br.com.uktech.bmt.dto.bacula.storage.BaculaStatusStorageDto;
import br.com.uktech.bmt.dto.bacula.storage.BaculaStorageDto;
import br.com.uktech.bmt.dto.model.director.DirectorDto;
import br.com.uktech.bmt.service.BaculaDirectorService;
import br.com.uktech.bmt.service.BaculaStorageService;
import java.util.ArrayList;
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
@RequestMapping(value = "/bacula/storage")
public class StorageController extends BasicBaculaController {
    
    private final Logger logger = LoggerFactory.getLogger(StorageController.class);
    
    @Autowired
    BaculaDirectorService baculaDirectorService;
    
    @Autowired
    BaculaStorageService baculaStorageService;
    
    @Override
    public String getModule() {
        return "storage";
    }
    
    @RequestMapping(value = "/{directorId}", method = RequestMethod.GET)
    public ModelAndView index(@PathVariable Long directorId, HttpServletRequest request, HttpServletResponse response, Pageable p) {
        logger.debug("Show Bacula Storage Home");
        ModelAndView mav;
        List<BaculaStorageDto> storages = new ArrayList<>();
        mav = new ModelAndView("bacula/storage/index");
        DirectorDto directorDto = baculaDirectorService.getBaculaDirectorById(directorId);
        if (directorDto != null) {
            storages = baculaStorageService.getListStorage(directorDto);
        }
        mav.addObject("directorId", directorId);
        mav.addObject("storages", storages);
        return mav;
    }
    
    @RequestMapping(value = "/status/{directorId}/{storageName}", method = RequestMethod.GET)
    public ModelAndView status(@PathVariable Long directorId, @PathVariable String storageName, HttpServletRequest request, HttpServletResponse response, Pageable p) {
        logger.debug("Show Bacula Storage Status");
        ModelAndView mav;
        BaculaStatusStorageDto status = null;
        mav = new ModelAndView("bacula/storage/status");
        DirectorDto directorDto = baculaDirectorService.getBaculaDirectorById(directorId);
        if (directorDto != null) {
            status = baculaStorageService.getStatusStorage(directorDto, storageName);
        }
        mav.addObject("status", status);
        mav.addObject("directorId", directorId);
        return mav;
    }
    
}
