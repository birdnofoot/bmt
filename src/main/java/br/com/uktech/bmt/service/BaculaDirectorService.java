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

import br.com.uktech.bmt.bacula.bean.BaculaStatusDirector;
import br.com.uktech.bmt.dto.model.director.DirectorDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public interface BaculaDirectorService {
    
    public DirectorDto newBaculaDirector();
    public DirectorDto save(DirectorDto baculadirdto);
    public Page<DirectorDto> searchAllBaculaDirectors(Pageable p);
    public DirectorDto getBaculaDirectorById(Long id);
    public List<DirectorDto> searchAllBaculaDirectors();
    //Adicionar Dto do baculaSD
    public BaculaStatusDirector getStatusDirector(DirectorDto baculadirdto);
    
}
