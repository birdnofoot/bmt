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
package br.com.uktech.bmt.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class SystemUserPermissionDto implements Serializable {

    private SystemUserPermissionIdDto systemUserPermissionId;

    private Boolean read;

    private Boolean add;

    private Boolean edit;

    private Boolean delete;

    /**
     * @return the systemUserPermissionId
     */
    public SystemUserPermissionIdDto getSystemUserPermissionId() {
        return systemUserPermissionId;
    }

    /**
     * @param systemUserPermissionId the systemUserPermissionId to set
     */
    public void setSystemUserPermissionId(SystemUserPermissionIdDto systemUserPermissionId) {
        this.systemUserPermissionId = systemUserPermissionId;
    }

    /**
     * @return the read
     */
    public Boolean getRead() {
        return read;
    }

    /**
     * @param read the read to set
     */
    public void setRead(Boolean read) {
        this.read = read;
    }

    /**
     * @return the add
     */
    public Boolean getAdd() {
        return add;
    }

    /**
     * @param add the add to set
     */
    public void setAdd(Boolean add) {
        this.add = add;
    }

    /**
     * @return the edit
     */
    public Boolean getEdit() {
        return edit;
    }

    /**
     * @param edit the edit to set
     */
    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    /**
     * @return the delete
     */
    public Boolean getDelete() {
        return delete;
    }

    /**
     * @param delete the delete to set
     */
    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.systemUserPermissionId);
        hash = 61 * hash + Objects.hashCode(this.read);
        hash = 61 * hash + Objects.hashCode(this.add);
        hash = 61 * hash + Objects.hashCode(this.edit);
        hash = 61 * hash + Objects.hashCode(this.delete);
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
        final SystemUserPermissionDto other = (SystemUserPermissionDto) obj;
        if (!Objects.equals(this.systemUserPermissionId, other.systemUserPermissionId)) {
            return false;
        }
        if (!Objects.equals(this.read, other.read)) {
            return false;
        }
        if (!Objects.equals(this.add, other.add)) {
            return false;
        }
        if (!Objects.equals(this.edit, other.edit)) {
            return false;
        }
        if (!Objects.equals(this.delete, other.delete)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SystemUserPermissionDto{" + "systemUserPermissionId=" + systemUserPermissionId + ", read=" + read + ", add=" + add + ", edit=" + edit + ", delete=" + delete + '}';
    }

}
