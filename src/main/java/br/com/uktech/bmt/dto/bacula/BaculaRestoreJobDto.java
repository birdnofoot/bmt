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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class BaculaRestoreJobDto implements Serializable {
    
    List<String> volumes = new ArrayList<>();
    List<String> storages = new ArrayList<>();
    List<String> sdDevices = new ArrayList<>();

    Long fileNumbers;

    Long jobQueued;

    public List<String> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<String> volumes) {
        this.volumes = volumes;
    }

    public List<String> getStorages() {
        return storages;
    }

    public void setStorages(List<String> storages) {
        this.storages = storages;
    }

    public List<String> getSdDevices() {
        return sdDevices;
    }

    public void setSdDevices(List<String> sdDevices) {
        this.sdDevices = sdDevices;
    }

    public Long getFileNumbers() {
        return fileNumbers;
    }

    public void setFileNumbers(Long fileNumbers) {
        this.fileNumbers = fileNumbers;
    }

    public Long getJobQueued() {
        return jobQueued;
    }

    public void setJobQueued(Long jobQueued) {
        this.jobQueued = jobQueued;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.volumes);
        hash = 61 * hash + Objects.hashCode(this.storages);
        hash = 61 * hash + Objects.hashCode(this.sdDevices);
        hash = 61 * hash + Objects.hashCode(this.fileNumbers);
        hash = 61 * hash + Objects.hashCode(this.jobQueued);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaculaRestoreJobDto other = (BaculaRestoreJobDto) obj;
        if (!Objects.equals(this.volumes, other.volumes)) {
            return false;
        }
        if (!Objects.equals(this.storages, other.storages)) {
            return false;
        }
        if (!Objects.equals(this.sdDevices, other.sdDevices)) {
            return false;
        }
        if (!Objects.equals(this.fileNumbers, other.fileNumbers)) {
            return false;
        }
        if (!Objects.equals(this.jobQueued, other.jobQueued)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaculaRestoreJobDto{" + "volumes=" + volumes + ", storages=" + storages + ", sdDevices=" + sdDevices + ", fileNumbers=" + fileNumbers + ", jobQueued=" + jobQueued + '}';
    }
    
}
