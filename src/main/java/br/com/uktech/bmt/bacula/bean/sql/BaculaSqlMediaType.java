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

package br.com.uktech.bmt.bacula.bean.sql;

import java.util.Objects;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class BaculaSqlMediaType extends AbstractBaculaSql {
    
    private String mediatype;
    private Integer readonly;

    public BaculaSqlMediaType() {
        super();
    }

    public BaculaSqlMediaType(Long id) {
        super(id);
    }

    public String getMediatype() {
        return mediatype;
    }

    public void setMediatype(String mediatype) {
        this.mediatype = mediatype;
    }

    public Integer getReadonly() {
        return readonly;
    }

    public void setReadonly(Integer readonly) {
        this.readonly = readonly;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 71 * hash + Objects.hashCode(this.mediatype);
        hash = 71 * hash + Objects.hashCode(this.readonly);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (super.equals(obj) == false) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaculaSqlMediaType other = (BaculaSqlMediaType) obj;
        if (!Objects.equals(this.mediatype, other.mediatype)) {
            return false;
        }
        if (!Objects.equals(this.readonly, other.readonly)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaculaSqlMediaType{" + super.toString() + ", mediatype=" + mediatype + ", readonly=" + readonly + '}';
    }
}
