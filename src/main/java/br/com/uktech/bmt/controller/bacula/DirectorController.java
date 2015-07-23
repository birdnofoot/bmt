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
import br.com.uktech.bmt.dto.model.director.DirectorDto;
import br.com.uktech.bmt.service.BaculaDirectorService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
@Controller
@RequestMapping(value = "/bacula/director")
public class DirectorController extends BasicBaculaController {

    private final Logger logger = LoggerFactory.getLogger(DirectorController.class);
    
    @Autowired
    BaculaDirectorService baculaDirectorService;
    
    @Override
    public String getModule() {
        return "director";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, Pageable p) {
        logger.debug("Show Bacula Director Home");
        ModelAndView mav;
        mav = new ModelAndView("bacula/director/index");
        Page<DirectorDto> page = baculaDirectorService.searchAllBaculaDirectors(p);
        mav.addObject("page", page);
        return mav;
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Adding a new Bacula Director ");
        ModelAndView mav;
        DirectorDto baculadir;
        mav = new ModelAndView("bacula/director/add");
        baculadir = baculaDirectorService.newBaculaDirector();
        mav.addObject("baculadir", baculadir);
        return mav;
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@Valid DirectorDto baculadir, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Saving a new Bacula Director ");
        ModelAndView mav;
        if (result.hasErrors()) {
            mav = new ModelAndView("bacula/director/add");
            mav.addObject("org.springframework.validation.BindingResult.baculadir", result);
            mav.addObject("baculadir", baculadir);
        } else {
            DirectorDto baculadirdto = baculaDirectorService.save(baculadir);
            mav = new ModelAndView("redirect:/bacula/director");
            mav.addObject("save", baculadirdto);
        }
        return mav;
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Edit a Bacula Director ");
        ModelAndView mav;
        mav = new ModelAndView("bacula/director/edit");

        return mav;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@Valid DirectorDto baculadir, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Update a Bacula Director ");
        ModelAndView mav;
        mav = new ModelAndView("bacula/director/edit");

        return mav;
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Deleting a Bacula Director ");
        ModelAndView mav;
        mav = new ModelAndView("bacula/director/delete");
        
        return mav;
    }
    
    @RequestMapping(value = "/status/{id}", method = RequestMethod.GET)
    public ModelAndView status(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Getting status of a Bacula Director ");
        BaculaStatusDirector statusdir = null;
        ModelAndView mav;
        mav = new ModelAndView("bacula/director/status");
        
        DirectorDto directorDto = baculaDirectorService.getBaculaDirectorById(id);
        if (directorDto != null) {
            statusdir = baculaDirectorService.getStatusDirector(directorDto);
        }
        
        mav.addObject("statusdir", statusdir);
        return mav;
    }
}
