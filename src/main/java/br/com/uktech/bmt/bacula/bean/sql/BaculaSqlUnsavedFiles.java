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
public class BaculaSqlUnsavedFiles extends AbstractBaculaSql {
    
    private BaculaSqlJob job;
    private BaculaSqlPath path;
    private BaculaSqlFileName filename;

    public BaculaSqlUnsavedFiles() {
        super();
    }

    public BaculaSqlUnsavedFiles(Long id) {
        super(id);
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

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 61 * hash + Objects.hashCode(this.job);
        hash = 61 * hash + Objects.hashCode(this.path);
        hash = 61 * hash + Objects.hashCode(this.filename);
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
        final BaculaSqlUnsavedFiles other = (BaculaSqlUnsavedFiles) obj;
        if (!Objects.equals(this.job, other.job)) {
            return false;
        }
        if (!Objects.equals(this.path, other.path)) {
            return false;
        }
        if (!Objects.equals(this.filename, other.filename)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaculaSqlUnsavedFiles{" + super.toString() + ", job=" + job.toString() + ", path=" + path.toString() + ", filename=" + filename.toString() + '}';
    }
    
}