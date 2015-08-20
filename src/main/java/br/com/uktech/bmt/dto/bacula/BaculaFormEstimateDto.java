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
public class BaculaFormEstimateDto implements Serializable{
    
    private Long id; //id do director
    private String job;
    private String type;
    private Boolean listing;
    private Boolean accurate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getListing() {
        return listing;
    }

    public void setListing(Boolean listing) {
        this.listing = listing;
    }

    public Boolean getAccurate() {
        return accurate;
    }

    public void setAccurate(Boolean accurate) {
        this.accurate = accurate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.job);
        hash = 67 * hash + Objects.hashCode(this.type);
        hash = 67 * hash + Objects.hashCode(this.listing);
        hash = 67 * hash + Objects.hashCode(this.accurate);
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
        final BaculaFormEstimateDto other = (BaculaFormEstimateDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.job, other.job)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.listing, other.listing)) {
            return false;
        }
        if (!Objects.equals(this.accurate, other.accurate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaculaFormEstimateDto{" + "id=" + id + ", job=" + job + ", type=" + type + ", listing=" + listing + ", accurate=" + accurate + '}';
    }
    
}
