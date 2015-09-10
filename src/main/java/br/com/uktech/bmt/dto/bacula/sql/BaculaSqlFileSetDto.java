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

package br.com.uktech.bmt.dto.bacula.sql;

import java.util.Date;
import java.util.Objects;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class BaculaSqlFileSetDto extends AbstractBaculaSqlDto {
    
    private String fileset;
    private String md5;
    private Date createtime;

    public BaculaSqlFileSetDto() {
        super();
    }

    public BaculaSqlFileSetDto(Long id) {
        super(id);
    }

    public String getFileset() {
        return fileset;
    }

    public void setFileset(String fileset) {
        this.fileset = fileset;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 29 * hash + Objects.hashCode(this.fileset);
        hash = 29 * hash + Objects.hashCode(this.md5);
        hash = 29 * hash + Objects.hashCode(this.createtime);
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
        final BaculaSqlFileSetDto other = (BaculaSqlFileSetDto) obj;
        if (!Objects.equals(this.fileset, other.fileset)) {
            return false;
        }
        if (!Objects.equals(this.md5, other.md5)) {
            return false;
        }
        if (!Objects.equals(this.createtime, other.createtime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaculaSqlFileSetDto{" + super.toString() + "fileset=" + fileset + ", md5=" + md5 + ", createtime=" + createtime + '}';
    }
    
}
