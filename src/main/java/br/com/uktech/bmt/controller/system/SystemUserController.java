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
package br.com.uktech.bmt.controller.system;

import br.com.uktech.bmt.dto.SystemUserDto;
import br.com.uktech.bmt.dto.SystemUserFormDto;
import br.com.uktech.bmt.service.SystemUserService;
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
@RequestMapping(value = "/system/user")
public class SystemUserController extends BasicSystemModule {
    
    private final Logger logger = LoggerFactory.getLogger(SystemUserController.class);
    
    @Autowired
    SystemUserService systemUserService;
    
    @Override
    public String getModule() {
        return "user";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, Pageable p) {
        logger.debug("Show System User Home");
        ModelAndView mav;
        mav = new ModelAndView("system/user/index");
        Page<SystemUserDto> page = systemUserService.searchAllUsers(p);
        mav.addObject("page", page);
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Adding a new System User");
        ModelAndView mav;
        SystemUserFormDto sysuser;
        mav = new ModelAndView("system/user/add");
        sysuser = systemUserService.newSystemUser();
        mav.addObject("sysuser", sysuser);
        return mav;
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@Valid SystemUserFormDto sysuser, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Saving a new System User");
        ModelAndView mav;
        if (result.hasErrors()) {
            mav = new ModelAndView("system/user/add");
            mav.addObject("org.springframework.validation.BindingResult.sysuser", result);
            mav.addObject("sysuser", sysuser);
        } else {
            SystemUserDto sysuserdto = systemUserService.save(sysuser);
            mav = new ModelAndView("redirect:/system/user");
            mav.addObject("save", sysuserdto);
        }
        return mav;
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Edit a System User");
        ModelAndView mav;
        mav = new ModelAndView("system/user/edit");

        return mav;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@Valid SystemUserFormDto sysuser, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Update a System User");
        ModelAndView mav;
        mav = new ModelAndView("system/user/edit");

        return mav;
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Deleting a System User");
        ModelAndView mav;
        mav = new ModelAndView("system/user/delete");
        
        return mav;
    }
}
