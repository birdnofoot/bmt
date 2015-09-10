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
public class BaculaSqlBaseFiles extends AbstractBaculaSql {
    
    private BaculaSqlJob job;
    private BaculaSqlFile file;
    private Long fileindex;
    private BaculaSqlBaseJob basejob;

    public BaculaSqlBaseFiles() {
        super();
    }

    public BaculaSqlBaseFiles(Long id) {
        super(id);
    }

    public BaculaSqlJob getJob() {
        return job;
    }

    public void setJob(BaculaSqlJob job) {
        this.job = job;
    }

    public BaculaSqlFile getFile() {
        return file;
    }

    public void setFile(BaculaSqlFile file) {
        this.file = file;
    }

    public Long getFileindex() {
        return fileindex;
    }

    public void setFileindex(Long fileindex) {
        this.fileindex = fileindex;
    }

    public BaculaSqlBaseJob getBasejob() {
        return basejob;
    }

    public void setBasejob(BaculaSqlBaseJob basejob) {
        this.basejob = basejob;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 89 * hash + Objects.hashCode(this.job);
        hash = 89 * hash + Objects.hashCode(this.file);
        hash = 89 * hash + Objects.hashCode(this.fileindex);
        hash = 89 * hash + Objects.hashCode(this.basejob);
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
        final BaculaSqlBaseFiles other = (BaculaSqlBaseFiles) obj;
        if (!Objects.equals(this.job, other.job)) {
            return false;
        }
        if (!Objects.equals(this.file, other.file)) {
            return false;
        }
        if (!Objects.equals(this.fileindex, other.fileindex)) {
            return false;
        }
        if (!Objects.equals(this.basejob, other.basejob)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaculaSqlBaseFiles{" + super.toString() + ", job=" + job + ", file=" + file + ", fileindex=" + fileindex + ", basejob=" + basejob + '}';
    }
    
}
