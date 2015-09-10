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
public class BaculaSqlFile extends AbstractBaculaSql {
    
    private Integer fileindex;
    private BaculaSqlJob job;
    private BaculaSqlPath path;
    private BaculaSqlFileName filename;
    private Integer deltaseq;
    private BaculaSqlMark mark;
    private String lstat;
    private String md5;

    public BaculaSqlFile() {
        super();
    }

    public BaculaSqlFile(Long id) {
        super(id);
    }

    public Integer getFileindex() {
        return fileindex;
    }

    public void setFileindex(Integer fileindex) {
        this.fileindex = fileindex;
    }

    public BaculaSqlJob getJob() {
        return job;
    }

    public void setJob(BaculaSqlJob job) {
        this.job = job;
    }

    public BaculaSqlPath getPath() {
        return path;
    }

    public void setPath(BaculaSqlPath path) {
        this.path = path;
    }

    public BaculaSqlFileName getFilename() {
        return filename;
    }

    public void setFilename(BaculaSqlFileName filename) {
        this.filename = filename;
    }

    public Integer getDeltaseq() {
        return deltaseq;
    }

    public void setDeltaseq(Integer deltaseq) {
        this.deltaseq = deltaseq;
    }

    public BaculaSqlMark getMark() {
        return mark;
    }

    public void setMark(BaculaSqlMark mark) {
        this.mark = mark;
    }

    public String getLstat() {
        return lstat;
    }

    public void setLstat(String lstat) {
        this.lstat = lstat;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 79 * hash + Objects.hashCode(this.fileindex);
        hash = 79 * hash + Objects.hashCode(this.job);
        hash = 79 * hash + Objects.hashCode(this.path);
        hash = 79 * hash + Objects.hashCode(this.filename);
        hash = 79 * hash + Objects.hashCode(this.deltaseq);
        hash = 79 * hash + Objects.hashCode(this.mark);
        hash = 79 * hash + Objects.hashCode(this.lstat);
        hash = 79 * hash + Objects.hashCode(this.md5);
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
        final BaculaSqlFile other = (BaculaSqlFile) obj;
        if (!Objects.equals(this.fileindex, other.fileindex)) {
            return false;
        }
        if (!Objects.equals(this.job, other.job)) {
            return false;
        }
        if (!Objects.equals(this.path, other.path)) {
            return false;
        }
        if (!Objects.equals(this.filename, other.filename)) {
            return false;
        }
        if (!Objects.equals(this.deltaseq, other.deltaseq)) {
            return false;
        }
        if (!Objects.equals(this.mark, other.mark)) {
            return false;
        }
        if (!Objects.equals(this.lstat, other.lstat)) {
            return false;
        }
        if (!Objects.equals(this.md5, other.md5)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaculaSqlFile{" + super.toString() + ", fileindex=" + fileindex + ", job=" + job.toString() + ", path=" + path.toString() + ", filename=" + filename.toString() + ", deltaseq=" + deltaseq + ", mark=" + mark.toString() + ", lstat=" + lstat + ", md5=" + md5 + '}';
    }
}