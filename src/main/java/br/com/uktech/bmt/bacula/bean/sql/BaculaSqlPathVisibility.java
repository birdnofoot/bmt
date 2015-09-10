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
public class BaculaSqlPathVisibility {
    
    private BaculaSqlPath path;
    private BaculaSqlJob job;
    private Long size;
    private Integer files;

    public BaculaSqlPath getPath() {
        return path;
    }

    public void setPath(BaculaSqlPath path) {
        this.path = path;
    }

    public BaculaSqlJob getJob() {
        return job;
    }

    public void setJob(BaculaSqlJob job) {
        this.job = job;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Integer getFiles() {
        return files;
    }

    public void setFiles(Integer files) {
        this.files = files;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.path);
        hash = 41 * hash + Objects.hashCode(this.job);
        hash = 41 * hash + Objects.hashCode(this.size);
        hash = 41 * hash + Objects.hashCode(this.files);
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
        final BaculaSqlPathVisibility other = (BaculaSqlPathVisibility) obj;
        if (!Objects.equals(this.path, other.path)) {
            return false;
        }
        if (!Objects.equals(this.job, other.job)) {
            return false;
        }
        if (!Objects.equals(this.size, other.size)) {
            return false;
        }
        if (!Objects.equals(this.files, other.files)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "BaculaSqlPathVisibility{" + "path=" + path.toString() + ", job=" + job.toString() + ", size=" + size + ", files=" + files + '}';
    }
}