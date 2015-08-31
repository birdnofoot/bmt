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

package br.com.uktech.bmt.dto.bacula;

import br.com.uktech.bmt.dto.bacula.dot.BaculaDotClientDto;
import br.com.uktech.bmt.dto.bacula.dot.BaculaDotFilesetDto;
import br.com.uktech.bmt.dto.bacula.dot.BaculaDotJobDto;
import br.com.uktech.bmt.dto.bacula.dot.BaculaDotLevelDto;
import br.com.uktech.bmt.dto.bacula.dot.BaculaDotPoolDto;
import br.com.uktech.bmt.dto.bacula.dot.BaculaDotStorageDto;
import br.com.uktech.bmt.dto.bacula.dot.BaculaDotTypeDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class BaculaFormRunJobDto extends BaculaJobDefaultDto {
    
    private Long id;    //id do director
    private Integer priority;
    private String when;
    
    private List<BaculaDotClientDto> clients = new ArrayList<>();
    private List<BaculaDotFilesetDto> filesets = new ArrayList<>();
    private List<BaculaDotJobDto> jobs = new ArrayList<>();
    private List<BaculaDotLevelDto> levels = new ArrayList<>();
    private List<BaculaDotPoolDto> pools = new ArrayList<>();
    private List<BaculaDotStorageDto> storages = new ArrayList<>();
    private List<BaculaDotTypeDto> types = new ArrayList<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public List<BaculaDotClientDto> getClients() {
        return clients;
    }

    public void setClients(List<BaculaDotClientDto> clients) {
        this.clients = clients;
    }

    public List<BaculaDotFilesetDto> getFilesets() {
        return filesets;
    }

    public void setFilesets(List<BaculaDotFilesetDto> filesets) {
        this.filesets = filesets;
    }

    public List<BaculaDotJobDto> getJobs() {
        return jobs;
    }

    public void setJobs(List<BaculaDotJobDto> jobs) {
        this.jobs = jobs;
    }

    public List<BaculaDotLevelDto> getLevels() {
        return levels;
    }

    public void setLevels(List<BaculaDotLevelDto> levels) {
        this.levels = levels;
    }

    public List<BaculaDotPoolDto> getPools() {
        return pools;
    }

    public void setPools(List<BaculaDotPoolDto> pools) {
        this.pools = pools;
    }

    public List<BaculaDotStorageDto> getStorages() {
        return storages;
    }

    public void setStorages(List<BaculaDotStorageDto> storages) {
        this.storages = storages;
    }

    public List<BaculaDotTypeDto> getTypes() {
        return types;
    }

    public void setTypes(List<BaculaDotTypeDto> types) {
        this.types = types;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.priority);
        hash = 11 * hash + Objects.hashCode(this.when);
        hash = 11 * hash + Objects.hashCode(this.jobs);
        hash = 11 * hash + Objects.hashCode(this.clients);
        hash = 11 * hash + Objects.hashCode(this.levels);
        hash = 11 * hash + Objects.hashCode(this.filesets);
        hash = 11 * hash + Objects.hashCode(this.pools);
        hash = 11 * hash + Objects.hashCode(this.storages);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaculaFormRunJobDto other = (BaculaFormRunJobDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.priority, other.priority)) {
            return false;
        }
        if (!Objects.equals(this.when, other.when)) {
            return false;
        }
        if (!Objects.equals(this.jobs, other.jobs)) {
            return false;
        }
        if (!Objects.equals(this.clients, other.clients)) {
            return false;
        }
        if (!Objects.equals(this.levels, other.levels)) {
            return false;
        }
        if (!Objects.equals(this.filesets, other.filesets)) {
            return false;
        }
        if (!Objects.equals(this.pools, other.pools)) {
            return false;
        }
        if (!Objects.equals(this.storages, other.storages)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaculaFormRunJobDto{" + super.toString() + ", id=" + id + ", priority=" + priority + ", when=" + when + ", jobs=" + jobs + ", clients=" + clients + ", levels=" + levels + ", filesets=" + filesets + ", pools=" + pools + ", storages=" + storages + '}';
    }
    
}
