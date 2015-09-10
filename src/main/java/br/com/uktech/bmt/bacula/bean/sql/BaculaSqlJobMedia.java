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
public class BaculaSqlJobMedia extends AbstractBaculaSql {
    
    private BaculaSqlJob job;
    private BaculaSqlMedia media;
    private Integer firstindex;
    private Integer lastindex;
    private Integer startfile;
    private Integer endfile;
    private Long startblock;
    private Long endblock;
    private Integer volindex;

    public BaculaSqlJobMedia() {
        super();
    }

    public BaculaSqlJobMedia(Long id) {
        super(id);
    }

    public BaculaSqlJob getJob() {
        return job;
    }

    public void setJob(BaculaSqlJob job) {
        this.job = job;
    }

    public BaculaSqlMedia getMedia() {
        return media;
    }

    public void setMedia(BaculaSqlMedia media) {
        this.media = media;
    }

    public Integer getFirstindex() {
        return firstindex;
    }

    public void setFirstindex(Integer firstindex) {
        this.firstindex = firstindex;
    }

    public Integer getLastindex() {
        return lastindex;
    }

    public void setLastindex(Integer lastindex) {
        this.lastindex = lastindex;
    }

    public Integer getStartfile() {
        return startfile;
    }

    public void setStartfile(Integer startfile) {
        this.startfile = startfile;
    }

    public Integer getEndfile() {
        return endfile;
    }

    public void setEndfile(Integer endfile) {
        this.endfile = endfile;
    }

    public Long getStartblock() {
        return startblock;
    }

    public void setStartblock(Long startblock) {
        this.startblock = startblock;
    }

    public Long getEndblock() {
        return endblock;
    }

    public void setEndblock(Long endblock) {
        this.endblock = endblock;
    }

    public Integer getVolindex() {
        return volindex;
    }

    public void setVolindex(Integer volindex) {
        this.volindex = volindex;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 83 * hash + Objects.hashCode(this.job);
        hash = 83 * hash + Objects.hashCode(this.media);
        hash = 83 * hash + Objects.hashCode(this.firstindex);
        hash = 83 * hash + Objects.hashCode(this.lastindex);
        hash = 83 * hash + Objects.hashCode(this.startfile);
        hash = 83 * hash + Objects.hashCode(this.endfile);
        hash = 83 * hash + Objects.hashCode(this.startblock);
        hash = 83 * hash + Objects.hashCode(this.endblock);
        hash = 83 * hash + Objects.hashCode(this.volindex);
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
        final BaculaSqlJobMedia other = (BaculaSqlJobMedia) obj;
        if (!Objects.equals(this.job, other.job)) {
            return false;
        }
        if (!Objects.equals(this.media, other.media)) {
            return false;
        }
        if (!Objects.equals(this.firstindex, other.firstindex)) {
            return false;
        }
        if (!Objects.equals(this.lastindex, other.lastindex)) {
            return false;
        }
        if (!Objects.equals(this.startfile, other.startfile)) {
            return false;
        }
        if (!Objects.equals(this.endfile, other.endfile)) {
            return false;
        }
        if (!Objects.equals(this.startblock, other.startblock)) {
            return false;
        }
        if (!Objects.equals(this.endblock, other.endblock)) {
            return false;
        }
        if (!Objects.equals(this.volindex, other.volindex)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaculaSqlJobMedia{" + super.toString() + ", job=" + job.toString() + ", media=" + media.toString() + ", firstindex=" + firstindex + ", lastindex=" + lastindex + ", startfile=" + startfile + ", endfile=" + endfile + ", startblock=" + startblock + ", endblock=" + endblock + ", volindex=" + volindex + '}';
    }
    
}