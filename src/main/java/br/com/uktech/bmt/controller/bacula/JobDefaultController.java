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

import br.com.uktech.bmt.dto.bacula.BaculaEstimateDto;
import br.com.uktech.bmt.dto.bacula.BaculaFormEstimateDto;
import br.com.uktech.bmt.dto.bacula.BaculaJobDefaultDto;
import br.com.uktech.bmt.dto.model.director.DirectorDto;
import br.com.uktech.bmt.service.BaculaDirectorService;
import br.com.uktech.bmt.service.BaculaJobDefaultService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
@Controller
@RequestMapping(value = "/bacula/jobDefault")
public class JobDefaultController extends BasicBaculaController {
    
    private final Logger logger = LoggerFactory.getLogger(ClientController.class);
    
    @Autowired
    BaculaDirectorService baculaDirectorService;
    
    @Autowired
    BaculaJobDefaultService baculaJobDefaultService;
    
    @Override
    public String getModule() {
        return "jobDefault";
    }
    
    @RequestMapping(value = "/{directorId}", method = RequestMethod.GET)
    public ModelAndView index(@PathVariable Long directorId, HttpServletRequest request, HttpServletResponse response, Pageable p) {
        logger.debug("Show Bacula JobDefault Home");
        ModelAndView mav;
        List<BaculaJobDefaultDto> jobsDefault = null;
        mav = new ModelAndView("bacula/jobDefault/index");
        DirectorDto directorDto = baculaDirectorService.getBaculaDirectorById(directorId);
        if (directorDto != null) {
            jobsDefault = baculaJobDefaultService.getListJobDefault(directorDto);
            logger.trace("saída {}", jobsDefault);
        }
        mav.addObject("directorId", directorId);
        mav.addObject("jobsDefault", jobsDefault);
        return mav;
    }
    
    @RequestMapping(value = "/estimate/form/{directorId}/{jobDefaultName}", method = RequestMethod.GET)
    public ModelAndView form(@PathVariable Long directorId, @PathVariable String jobDefaultName, HttpServletRequest request, HttpServletResponse response) {
        //Continuar a partir daqui
        logger.debug("completing a new form for the estimate");
        ModelAndView mav;
        BaculaFormEstimateDto formestimate;
        mav = new ModelAndView("bacula/jobDefault/estimate/form");
        formestimate = baculaJobDefaultService.newFormEstimate();
        formestimate.setId(directorId);
        formestimate.setJob(jobDefaultName);
        formestimate.setAccurate(Boolean.TRUE);
        formestimate.setType("Incremental");
        mav.addObject("formestimate", formestimate);
        return mav;
    }
    
    @RequestMapping(value = "/estimate/show", method = RequestMethod.POST)
    public ModelAndView estimate(@Valid BaculaFormEstimateDto formestimate, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Show Estimate Job");
        ModelAndView mav;
        if (result.hasErrors()) {
            logger.debug("Show Estimate Job Error!");
            mav = new ModelAndView("bacula/jobDefault/estimate/form");
            //mav.addObject("org.springframework.validation.BindingResult.formestimate", result);
            //mav.addObject("formestimate", formestimate);
            
        } else {
            DirectorDto baculadirdto = baculaDirectorService.getBaculaDirectorById(formestimate.getId());
            BaculaEstimateDto estimate = baculaJobDefaultService.getEstimate(baculadirdto, formestimate);
            mav = new ModelAndView("bacula/jobDefault/estimate/show");
            if(estimate != null) {
                mav.addObject("estimate", estimate.toString());
            } else {
                mav.addObject("estimate", "Erro!<br/>Estimate null");
            }
            
        }
        return mav;
    }
}
