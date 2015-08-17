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
import java.util.Objects;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public abstract class AbstractJobRunningDto implements Serializable{
    
    private Long jobid;
    private String level;
    private String type;
    private Long jobfiles;
    private Long jobbytes;
    private Long rate;
    private Integer fd;

    public Long getJobid() {
        return jobid;
    }

    public void setJobid(Long jobid) {
        this.jobid = jobid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getJobfiles() {
        return jobfiles;
    }

    public void setJobfiles(Long jobfiles) {
        this.jobfiles = jobfiles;
    }

    public Long getJobbytes() {
        return jobbytes;
    }

    public void setJobbytes(Long jobbytes) {
        this.jobbytes = jobbytes;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public Integer getFd() {
        return fd;
    }

    public void setFd(Integer fd) {
        this.fd = fd;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.jobid);
        hash = 61 * hash + Objects.hashCode(this.level);
        hash = 61 * hash + Objects.hashCode(this.type);
        hash = 61 * hash + Objects.hashCode(this.jobfiles);
        hash = 61 * hash + Objects.hashCode(this.jobbytes);
        hash = 61 * hash + Objects.hashCode(this.rate);
        hash = 61 * hash + Objects.hashCode(this.fd);
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
        final AbstractJobRunningDto other = (AbstractJobRunningDto) obj;
        if (!Objects.equals(this.jobid, other.jobid)) {
            return false;
        }
        if (!Objects.equals(this.level, other.level)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.jobfiles, other.jobfiles)) {
            return false;
        }
        if (!Objects.equals(this.jobbytes, other.jobbytes)) {
            return false;
        }
        if (!Objects.equals(this.rate, other.rate)) {
            return false;
        }
        if (!Objects.equals(this.fd, other.fd)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "jobid=" + jobid + ", level=" + level + ", type=" + type + ", jobfiles=" + jobfiles + ", jobbytes=" + jobbytes + ", rate=" + rate + ", fd=" + fd;
    }
}
