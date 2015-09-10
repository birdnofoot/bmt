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

import br.com.uktech.bmt.dto.bacula.BaculaEstimateDto;
import br.com.uktech.bmt.dto.bacula.BaculaFormEstimateDto;
import br.com.uktech.bmt.dto.bacula.BaculaFormRunJobDto;
import br.com.uktech.bmt.dto.bacula.BaculaJobDefaultDto;
import br.com.uktech.bmt.dto.bacula.sql.BaculaSqlJobDto;
import br.com.uktech.bmt.dto.model.director.DirectorDto;
import java.util.List;

/**
 *
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public interface BaculaJobDefaultService {
    
    public BaculaJobDefaultDto newJobDefault();
    public BaculaFormEstimateDto newFormEstimate();
    public BaculaFormRunJobDto newFormRunJob();
    public List<BaculaJobDefaultDto> getListJobDefault(DirectorDto baculadirdto);
    public BaculaEstimateDto getEstimate(DirectorDto baculadirdto, BaculaFormEstimateDto formEstimate);
    public Long runJob(DirectorDto baculadirdto, BaculaFormRunJobDto formRunJobDto);
    public BaculaFormRunJobDto getFormRunJob(DirectorDto baculadirdto, String jobDefaultName);
    public BaculaSqlJobDto getBaculaSqlJobDtoById(DirectorDto baculadirdto, Long jobId);
}
