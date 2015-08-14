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

package br.com.uktech.bmt.dozer.bacula;

import br.com.uktech.bmt.bacula.bean.BaculaStatusStorage;
import br.com.uktech.bmt.dto.bacula.storage.BaculaStatusStorageDto;
import org.dozer.loader.api.BeanMappingBuilder;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class BaculaStatusStorageMapper extends BeanMappingBuilder {

    @Override
    protected void configure() {
        this.mapping(BaculaStatusStorage.class, BaculaStatusStorageDto.class);
    }

}
