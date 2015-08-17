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

import br.com.uktech.bmt.dto.bacula.BaculaStatusStorageDto;
import br.com.uktech.bmt.dto.bacula.BaculaStorageDto;
import br.com.uktech.bmt.dto.model.director.DirectorDto;
import java.util.List;

/**
 *
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public interface BaculaStorageService {
    
    public BaculaStorageDto newStorage();
    public List<BaculaStorageDto> getListStorage(DirectorDto baculadirdto);
    public BaculaStatusStorageDto getStatusStorage(DirectorDto baculadirdto, String nameStorage);
    
}
