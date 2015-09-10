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
public class BaculaSqlLocationLog extends AbstractBaculaSql {
    
    private Date date;
    private String comment;
    private BaculaSqlMedia media = new BaculaSqlMedia();
    private BaculaSqlLocation location = new BaculaSqlLocation();
    private String newvolstatus;
    private Integer newenabled;

    public BaculaSqlLocationLog() {
        super();
    }

    public BaculaSqlLocationLog(Long id) {
        super(id);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BaculaSqlMedia getMedia() {
        return media;
    }

    public void setMedia(BaculaSqlMedia media) {
        this.media = media;
    }

    public BaculaSqlLocation getLocation() {
        return location;
    }

    public void setLocation(BaculaSqlLocation location) {
        this.location = location;
    }

    public String getNewvolstatus() {
        return newvolstatus;
    }

    public void setNewvolstatus(String newvolstatus) {
        this.newvolstatus = newvolstatus;
    }

    public Integer getNewenabled() {
        return newenabled;
    }

    public void setNewenabled(Integer newenabled) {
        this.newenabled = newenabled;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 41 * hash + Objects.hashCode(this.date);
        hash = 41 * hash + Objects.hashCode(this.comment);
        hash = 41 * hash + Objects.hashCode(this.media);
        hash = 41 * hash + Objects.hashCode(this.location);
        hash = 41 * hash + Objects.hashCode(this.newvolstatus);
        hash = 41 * hash + Objects.hashCode(this.newenabled);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (super.equals(obj) == false ) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaculaSqlLocationLog other = (BaculaSqlLocationLog) obj;
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.comment, other.comment)) {
            return false;
        }
        if (!Objects.equals(this.media, other.media)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.newvolstatus, other.newvolstatus)) {
            return false;
        }
        if (!Objects.equals(this.newenabled, other.newenabled)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaculaSqlLocationLog{" + super.toString() + ", date=" + date + ", comment=" + comment + ", media=" + media.toString() + ", location=" + location.toString() + ", newvolstatus=" + newvolstatus + ", newenabled=" + newenabled + '}';
    }
}