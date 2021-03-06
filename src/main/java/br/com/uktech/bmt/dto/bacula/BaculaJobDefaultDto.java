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
public class BaculaJobDefaultDto implements Serializable {
    
    private String job;
    private String pool;
    private String poolmessages;
    private String client;
    private String storage;
    private String where;
    private String level;
    private String type;
    private String fileset;
    private Boolean enabled;
    private String catalog;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    public String getPoolmessages() {
        return poolmessages;
    }

    public void setPoolmessages(String Poolmessages) {
        this.poolmessages = Poolmessages;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileset() {
        return fileset;
    }

    public void setFileset(String fileset) {
        this.fileset = fileset;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.job);
        hash = 29 * hash + Objects.hashCode(this.pool);
        hash = 29 * hash + Objects.hashCode(this.poolmessages);
        hash = 29 * hash + Objects.hashCode(this.client);
        hash = 29 * hash + Objects.hashCode(this.storage);
        hash = 29 * hash + Objects.hashCode(this.where);
        hash = 29 * hash + Objects.hashCode(this.level);
        hash = 29 * hash + Objects.hashCode(this.type);
        hash = 29 * hash + Objects.hashCode(this.fileset);
        hash = 29 * hash + Objects.hashCode(this.enabled);
        hash = 29 * hash + Objects.hashCode(this.catalog);
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
        final BaculaJobDefaultDto other = (BaculaJobDefaultDto) obj;
        if (!Objects.equals(this.job, other.job)) {
            return false;
        }
        if (!Objects.equals(this.pool, other.pool)) {
            return false;
        }
        if (!Objects.equals(this.poolmessages, other.poolmessages)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        if (!Objects.equals(this.storage, other.storage)) {
            return false;
        }
        if (!Objects.equals(this.where, other.where)) {
            return false;
        }
        if (!Objects.equals(this.level, other.level)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.fileset, other.fileset)) {
            return false;
        }
        if (!Objects.equals(this.enabled, other.enabled)) {
            return false;
        }
        if (!Objects.equals(this.catalog, other.catalog)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaculaJobDefaultDto{" + "job=" + job + ", pool=" + pool + ", Poolmessages=" + poolmessages + ", client=" + client + ", storage=" + storage + ", where=" + where + ", level=" + level + ", type=" + type + ", fileset=" + fileset + ", enabled=" + enabled + ", catalog=" + catalog + '}';
    }
    
}
