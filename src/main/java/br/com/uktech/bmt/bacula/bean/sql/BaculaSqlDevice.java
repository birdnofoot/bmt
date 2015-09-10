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

import java.util.Date;
import java.util.Objects;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class BaculaSqlDevice extends AbstractBaculaSql {
    
    private String name;
    private BaculaSqlMediaType mediatype;
    private BaculaSqlStorage storage;
    private Integer devmounts;
    private Long devreadbytes;
    private Long devwritebytes;
    private Long devreadbytessincecleaning;
    private Long devwritebytessincecleaning;
    private Long devreadtime;
    private Long devwritetime;
    private Long devreadtimesincecleaning;
    private Long devwritetimesincecleaning;
    private Date cleaningdate;
    private Long cleaningperiod;

    public BaculaSqlDevice() {
        super();
    }

    public BaculaSqlDevice(Long id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BaculaSqlMediaType getMediatype() {
        return mediatype;
    }

    public void setMediatype(BaculaSqlMediaType mediatype) {
        this.mediatype = mediatype;
    }

    public BaculaSqlStorage getStorage() {
        return storage;
    }

    public void setStorage(BaculaSqlStorage storage) {
        this.storage = storage;
    }

    public Integer getDevmounts() {
        return devmounts;
    }

    public void setDevmounts(Integer devmounts) {
        this.devmounts = devmounts;
    }

    public Long getDevreadbytes() {
        return devreadbytes;
    }

    public void setDevreadbytes(Long devreadbytes) {
        this.devreadbytes = devreadbytes;
    }

    public Long getDevwritebytes() {
        return devwritebytes;
    }

    public void setDevwritebytes(Long devwritebytes) {
        this.devwritebytes = devwritebytes;
    }

    public Long getDevreadbytessincecleaning() {
        return devreadbytessincecleaning;
    }

    public void setDevreadbytessincecleaning(Long devreadbytessincecleaning) {
        this.devreadbytessincecleaning = devreadbytessincecleaning;
    }

    public Long getDevwritebytessincecleaning() {
        return devwritebytessincecleaning;
    }

    public void setDevwritebytessincecleaning(Long devwritebytessincecleaning) {
        this.devwritebytessincecleaning = devwritebytessincecleaning;
    }

    public Long getDevreadtime() {
        return devreadtime;
    }

    public void setDevreadtime(Long devreadtime) {
        this.devreadtime = devreadtime;
    }

    public Long getDevwritetime() {
        return devwritetime;
    }

    public void setDevwritetime(Long devwritetime) {
        this.devwritetime = devwritetime;
    }

    public Long getDevreadtimesincecleaning() {
        return devreadtimesincecleaning;
    }

    public void setDevreadtimesincecleaning(Long devreadtimesincecleaning) {
        this.devreadtimesincecleaning = devreadtimesincecleaning;
    }

    public Long getDevwritetimesincecleaning() {
        return devwritetimesincecleaning;
    }

    public void setDevwritetimesincecleaning(Long devwritetimesincecleaning) {
        this.devwritetimesincecleaning = devwritetimesincecleaning;
    }

    public Date getCleaningdate() {
        return cleaningdate;
    }

    public void setCleaningdate(Date cleaningdate) {
        this.cleaningdate = cleaningdate;
    }

    public Long getCleaningperiod() {
        return cleaningperiod;
    }

    public void setCleaningperiod(Long cleaningperiod) {
        this.cleaningperiod = cleaningperiod;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.mediatype);
        hash = 89 * hash + Objects.hashCode(this.storage);
        hash = 89 * hash + Objects.hashCode(this.devmounts);
        hash = 89 * hash + Objects.hashCode(this.devreadbytes);
        hash = 89 * hash + Objects.hashCode(this.devwritebytes);
        hash = 89 * hash + Objects.hashCode(this.devreadbytessincecleaning);
        hash = 89 * hash + Objects.hashCode(this.devwritebytessincecleaning);
        hash = 89 * hash + Objects.hashCode(this.devreadtime);
        hash = 89 * hash + Objects.hashCode(this.devwritetime);
        hash = 89 * hash + Objects.hashCode(this.devreadtimesincecleaning);
        hash = 89 * hash + Objects.hashCode(this.devwritetimesincecleaning);
        hash = 89 * hash + Objects.hashCode(this.cleaningdate);
        hash = 89 * hash + Objects.hashCode(this.cleaningperiod);
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
        final BaculaSqlDevice other = (BaculaSqlDevice) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.mediatype, other.mediatype)) {
            return false;
        }
        if (!Objects.equals(this.storage, other.storage)) {
            return false;
        }
        if (!Objects.equals(this.devmounts, other.devmounts)) {
            return false;
        }
        if (!Objects.equals(this.devreadbytes, other.devreadbytes)) {
            return false;
        }
        if (!Objects.equals(this.devwritebytes, other.devwritebytes)) {
            return false;
        }
        if (!Objects.equals(this.devreadbytessincecleaning, other.devreadbytessincecleaning)) {
            return false;
        }
        if (!Objects.equals(this.devwritebytessincecleaning, other.devwritebytessincecleaning)) {
            return false;
        }
        if (!Objects.equals(this.devreadtime, other.devreadtime)) {
            return false;
        }
        if (!Objects.equals(this.devwritetime, other.devwritetime)) {
            return false;
        }
        if (!Objects.equals(this.devreadtimesincecleaning, other.devreadtimesincecleaning)) {
            return false;
        }
        if (!Objects.equals(this.devwritetimesincecleaning, other.devwritetimesincecleaning)) {
            return false;
        }
        if (!Objects.equals(this.cleaningdate, other.cleaningdate)) {
            return false;
        }
        if (!Objects.equals(this.cleaningperiod, other.cleaningperiod)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaculaSqlDevice{" + super.toString() + ", name=" + name + ", mediatype=" + mediatype.toString() + ", storage=" + storage.toString() + ", devmounts=" + devmounts + ", devreadbytes=" + devreadbytes + ", devwritebytes=" + devwritebytes + ", devreadbytessincecleaning=" + devreadbytessincecleaning + ", devwritebytessincecleaning=" + devwritebytessincecleaning + ", devreadtime=" + devreadtime + ", devwritetime=" + devwritetime + ", devreadtimesincecleaning=" + devreadtimesincecleaning + ", devwritetimesincecleaning=" + devwritetimesincecleaning + ", cleaningdate=" + cleaningdate + ", cleaningperiod=" + cleaningperiod + '}';
    }
    
}