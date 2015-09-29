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

import br.com.uktech.bmt.dto.bacula.BaculaDirectoryDto;
import br.com.uktech.bmt.dto.bacula.sql.BaculaSqlJobToRestoreDto;
import java.util.List;

/**
 *
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public interface BaculaRestoreService {
    
    //para pegar uma lista de clients, deverá ser uzado o service BaculaClientService
    
    //retorna a lista de jobs ao informar o nome de um client
    public List<BaculaSqlJobToRestoreDto> getJobsToRestore(String client);
    
    //retornar uma listas concatenada de jobs relacionados ao job informando pole seu id.
    public String getRelatedJobs(Long jobid);
    
    //retorna o diretório raiz, contento seus diretórios filhos e arquivos.
    public BaculaDirectoryDto getRootDirectory(String listJobs, Integer limit, Integer offset);
    
    //retorna o diretório atual, informando a lista de jobs relacionados, o nome do client, o limit e offset.
    public BaculaDirectoryDto getCurrentDirectory(String listJobs, Long pathId, String client, Integer limit, Integer offset);
    
    
    
}
