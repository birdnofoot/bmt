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
import br.com.uktech.bmt.dto.bacula.BaculaFileDto;
import br.com.uktech.bmt.dto.bacula.BaculaFileVersionsDto;
import br.com.uktech.bmt.dto.bacula.BaculaRestoreFileDefaultDto;
import br.com.uktech.bmt.dto.bacula.BaculaRestoreJobDto;
import br.com.uktech.bmt.dto.bacula.sql.BaculaSqlJobToRestoreDto;
import br.com.uktech.bmt.dto.model.director.DirectorDto;
import java.util.List;
import java.util.Map;

/**
 *
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public interface BaculaRestoreService {
    
    //para pegar uma lista de clients, deverá ser uzado o service BaculaClientService
    //public List<BaculaDotClientDto> getBaculaDotClientDtos();
    
    //Métodos para retornar novos objetos.
    public BaculaDirectoryDto newBaculaDirectory();
    public BaculaRestoreFileDefaultDto newBaculaRestoreFileDefault();
    public BaculaRestoreJobDto newBaculaRestoreJob();
    
    //Métodos para retornar novas listas de objetos
    public List<BaculaSqlJobToRestoreDto> newBaculaSqlJobToRestoresDtos();
    public List<BaculaDirectoryDto> newBaculaDirectorysDtos();
    public List<BaculaFileDto> newBaculaFilesDtos();
    public List<BaculaFileVersionsDto> newBaculaFileVersionsDtos();
    public Map<Long, BaculaDirectoryDto> newMapBaculaDirectoryDto();
    
    //retorna a lista de jobs ao informar o nome de um client
    public List<BaculaSqlJobToRestoreDto> getJobsToRestore(DirectorDto baculadirdto, String client);
    
    //retornar uma listas concatenada de jobs relacionados ao job informando pole seu id.
    public String getRelatedJobs(DirectorDto baculadirdto, Long jobid);
    
    //retorna o diretório raiz, contento seus diretórios filhos e arquivos.
    public BaculaDirectoryDto getRootDirectory(DirectorDto baculadirdto, String listJobs, Integer limit, Integer offset);
    
    //retorna o diretório atual, informando a lista de jobs relacionados, o nome do client, o limit e offset.
    public BaculaDirectoryDto getCurrentDirectory(DirectorDto baculadirdto, String listJobs, Long pathId, String client, Integer limit, Integer offset);
    
    //retorna o RestoreFileDefaultDto.
    public BaculaRestoreFileDefaultDto getBaculaRestoreFileDefaultDto(DirectorDto baculadirdto);
    
    //retornar verdadeiro se a tebela foi criada.
    public Boolean createTableRestore(DirectorDto baculadirdto, List<BaculaDirectoryDto> selectedDirectoriesDtos, List<BaculaFileDto> selectedFilesDtos, List<BaculaFileVersionsDto> selectedFileVersionsDtos, String listJobs);
    
    //retorna o BaculaRestoreJobDto após rodar o trabalho de restauração
    public BaculaRestoreJobDto runRestore(DirectorDto baculadirdto, BaculaRestoreFileDefaultDto restoreFileDefaultDto);
    
    //libera a tabela após a restauração, retorna false se o comando não funcionou!
    public Boolean cleanTable(DirectorDto baculadirdto);
    
}
