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
package br.com.uktech.bmt.dto.bacula.storage;

import java.util.Objects;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class BaculaStorageDto {
    
    private String name;
    private Long storageId;
    private String address;
    private Integer SDport;
    private Integer maxJobs;
    private String deviceName;
    private String mediaType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSDport() {
        return SDport;
    }

    public void setSDport(Integer SDport) {
        this.SDport = SDport;
    }

    public Integer getMaxJobs() {
        return maxJobs;
    }

    public void setMaxJobs(Integer maxJobs) {
        this.maxJobs = maxJobs;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.storageId);
        hash = 53 * hash + Objects.hashCode(this.address);
        hash = 53 * hash + Objects.hashCode(this.SDport);
        hash = 53 * hash + Objects.hashCode(this.maxJobs);
        hash = 53 * hash + Objects.hashCode(this.deviceName);
        hash = 53 * hash + Objects.hashCode(this.mediaType);
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
        final BaculaStorageDto other = (BaculaStorageDto) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.storageId, other.storageId)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.SDport, other.SDport)) {
            return false;
        }
        if (!Objects.equals(this.maxJobs, other.maxJobs)) {
            return false;
        }
        if (!Objects.equals(this.deviceName, other.deviceName)) {
            return false;
        }
        if (!Objects.equals(this.mediaType, other.mediaType)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaculaStorageDto{" + "name=" + name + ", storageId=" + storageId + ", address=" + address + ", SDport=" + SDport + ", maxJobs=" + maxJobs + ", deviceName=" + deviceName + ", mediaType=" + mediaType + '}';
    }
    
}
