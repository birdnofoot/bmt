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
public class BaculaSqlStatus {
    
    private String jobstatus;
    private String jobstatuslong;
    private Integer severity;

    public String getJobstatus() {
        return jobstatus;
    }

    public void setJobstatus(String jobstatus) {
        this.jobstatus = jobstatus;
    }

    public String getJobstatuslong() {
        return jobstatuslong;
    }

    public void setJobstatuslong(String jobstatuslong) {
        this.jobstatuslong = jobstatuslong;
    }

    public Integer getSeverity() {
        return severity;
    }

    public void setSeverity(Integer severity) {
        this.severity = severity;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.jobstatus);
        hash = 41 * hash + Objects.hashCode(this.jobstatuslong);
        hash = 41 * hash + Objects.hashCode(this.severity);
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
        final BaculaSqlStatus other = (BaculaSqlStatus) obj;
        if (!Objects.equals(this.jobstatus, other.jobstatus)) {
            return false;
        }
        if (!Objects.equals(this.jobstatuslong, other.jobstatuslong)) {
            return false;
        }
        if (!Objects.equals(this.severity, other.severity)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaculaSqlStatus{" + "jobstatus=" + jobstatus + ", jobstatuslong=" + jobstatuslong + ", severity=" + severity + '}';
    }
    
}