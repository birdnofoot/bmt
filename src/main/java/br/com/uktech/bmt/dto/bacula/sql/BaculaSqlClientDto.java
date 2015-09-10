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

import java.util.Objects;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class BaculaSqlClientDto extends AbstractBaculaSqlDto {

    private String name;
    private String uname;
    private Integer autoprune;
    private Long fileretention;
    private Long jobretention;

    public BaculaSqlClientDto() {
        super();
    }

    public BaculaSqlClientDto(Long id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Integer getAutoprune() {
        return autoprune;
    }

    public void setAutoprune(Integer autoprune) {
        this.autoprune = autoprune;
    }

    public Long getFileretention() {
        return fileretention;
    }

    public void setFileretention(Long fileretention) {
        this.fileretention = fileretention;
    }

    public Long getJobretention() {
        return jobretention;
    }

    public void setJobretention(Long jobretention) {
        this.jobretention = jobretention;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.uname);
        hash = 53 * hash + Objects.hashCode(this.autoprune);
        hash = 53 * hash + Objects.hashCode(this.fileretention);
        hash = 53 * hash + Objects.hashCode(this.jobretention);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (super.equals(obj)==false) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaculaSqlClientDto other = (BaculaSqlClientDto) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.uname, other.uname)) {
            return false;
        }
        if (!Objects.equals(this.autoprune, other.autoprune)) {
            return false;
        }
        if (!Objects.equals(this.fileretention, other.fileretention)) {
            return false;
        }
        if (!Objects.equals(this.jobretention, other.jobretention)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaculaSqlClientDto{" + super.toString() + "name=" + name + ", uname=" + uname + ", autoprune=" + autoprune + ", fileretention=" + fileretention + ", jobretention=" + jobretention + '}';
    }
    
}
