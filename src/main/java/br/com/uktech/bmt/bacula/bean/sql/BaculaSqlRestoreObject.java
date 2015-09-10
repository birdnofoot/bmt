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

import java.util.Arrays;
import java.util.Objects;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class BaculaSqlRestoreObject extends AbstractBaculaSql {
    
    private String objectname;
    private Byte[] restoreobject;
    private String pluginname;
    private Integer objectlength;
    private Integer objectfulllength;
    private Integer objectindex;
    private Integer objecttype;
    private Integer fileindex;
    private BaculaSqlJob job;
    private Integer objectcompression;

    public BaculaSqlRestoreObject() {
        super();
    }

    public BaculaSqlRestoreObject(Long id) {
        super(id);
    }

    public String getObjectname() {
        return objectname;
    }

    public void setObjectname(String objectname) {
        this.objectname = objectname;
    }

    public Byte[] getRestoreobject() {
        return restoreobject;
    }

    public void setRestoreobject(Byte[] restoreobject) {
        this.restoreobject = restoreobject;
    }

    public String getPluginname() {
        return pluginname;
    }

    public void setPluginname(String pluginname) {
        this.pluginname = pluginname;
    }

    public Integer getObjectlength() {
        return objectlength;
    }

    public void setObjectlength(Integer objectlength) {
        this.objectlength = objectlength;
    }

    public Integer getObjectfulllength() {
        return objectfulllength;
    }

    public void setObjectfulllength(Integer objectfulllength) {
        this.objectfulllength = objectfulllength;
    }

    public Integer getObjectindex() {
        return objectindex;
    }

    public void setObjectindex(Integer objectindex) {
        this.objectindex = objectindex;
    }

    public Integer getObjecttype() {
        return objecttype;
    }

    public void setObjecttype(Integer objecttype) {
        this.objecttype = objecttype;
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

    public Integer getObjectcompression() {
        return objectcompression;
    }

    public void setObjectcompression(Integer objectcompression) {
        this.objectcompression = objectcompression;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 43 * hash + Objects.hashCode(this.objectname);
        hash = 43 * hash + Arrays.deepHashCode(this.restoreobject);
        hash = 43 * hash + Objects.hashCode(this.pluginname);
        hash = 43 * hash + Objects.hashCode(this.objectlength);
        hash = 43 * hash + Objects.hashCode(this.objectfulllength);
        hash = 43 * hash + Objects.hashCode(this.objectindex);
        hash = 43 * hash + Objects.hashCode(this.objecttype);
        hash = 43 * hash + Objects.hashCode(this.fileindex);
        hash = 43 * hash + Objects.hashCode(this.job);
        hash = 43 * hash + Objects.hashCode(this.objectcompression);
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
        final BaculaSqlRestoreObject other = (BaculaSqlRestoreObject) obj;
        if (!Objects.equals(this.objectname, other.objectname)) {
            return false;
        }
        if (!Arrays.deepEquals(this.restoreobject, other.restoreobject)) {
            return false;
        }
        if (!Objects.equals(this.pluginname, other.pluginname)) {
            return false;
        }
        if (!Objects.equals(this.objectlength, other.objectlength)) {
            return false;
        }
        if (!Objects.equals(this.objectfulllength, other.objectfulllength)) {
            return false;
        }
        if (!Objects.equals(this.objectindex, other.objectindex)) {
            return false;
        }
        if (!Objects.equals(this.objecttype, other.objecttype)) {
            return false;
        }
        if (!Objects.equals(this.fileindex, other.fileindex)) {
            return false;
        }
        if (!Objects.equals(this.job, other.job)) {
            return false;
        }
        if (!Objects.equals(this.objectcompression, other.objectcompression)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaculaSqlRestoreObject{" + super.toString() + ", objectname=" + objectname + ", restoreobject=" + restoreobject + ", pluginname=" + pluginname + ", objectlength=" + objectlength + ", objectfulllength=" + objectfulllength + ", objectindex=" + objectindex + ", objecttype=" + objecttype + ", fileindex=" + fileindex + ", job=" + job.toString() + ", objectcompression=" + objectcompression + '}';
    }
}