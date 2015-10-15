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

import br.com.uktech.bmt.dto.bacula.BaculaClientDto;
import br.com.uktech.bmt.dto.bacula.BaculaStatusClientDto;
import br.com.uktech.bmt.dto.bacula.dot.BaculaDotClientDto;
import br.com.uktech.bmt.dto.bacula.dot.BaculaDotStatusClientRunningDto;
import br.com.uktech.bmt.dto.model.director.DirectorDto;
import java.util.List;

/**
 *
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public interface BaculaClientService {
    
    public BaculaClientDto newClient();
    //Retornar um Dto
    public List<BaculaClientDto> getListClients(DirectorDto baculadirdto);
    public BaculaStatusClientDto getStatusClient(DirectorDto baculadirdto, String clientName);
    public BaculaDotStatusClientRunningDto getDotStatusClient(DirectorDto baculadirdto, String clientName);
    public List<BaculaDotClientDto> getListDotClientDtos(DirectorDto baculadirdto);
}
