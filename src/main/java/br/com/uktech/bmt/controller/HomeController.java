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
package br.com.uktech.bmt.controller;

import br.com.uktech.bmt.dto.model.director.DirectorDto;
import br.com.uktech.bmt.service.BaculaDirectorService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
@Controller
public class HomeController implements BasicCategoryModuleController {
    
    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    BaculaDirectorService baculaDirectorService;
    
    @Override
    public String getCategory() {
        return "dashboard";
    }

    @Override
    public String getModule() {
        return "home";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav;
        mav = new ModelAndView("home/index");
        List<DirectorDto> directors = baculaDirectorService.searchAllBaculaDirectors();
        mav.addObject("directors", directors);
        return mav;
    }
    
}
