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
package br.com.uktech.bmt.bacula.bean;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class BaculaClient implements Serializable {
    
    private int id;
    private String name;
    private String uname;
    private boolean autoprune;
    private int fileretention;
    private int jobretention;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public boolean isAutoprune() {
        return autoprune;
    }

    public void setAutoprune(boolean autoprune) {
        this.autoprune = autoprune;
    }

    public int getFileretention() {
        return fileretention;
    }

    public void setFileretention(int fileretention) {
        this.fileretention = fileretention;
    }

    public int getJobretention() {
        return jobretention;
    }

    public void setJobretention(int jobretention) {
        this.jobretention = jobretention;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.uname);
        hash = 59 * hash + (this.autoprune ? 1 : 0);
        hash = 59 * hash + this.fileretention;
        hash = 59 * hash + this.jobretention;
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
        final BaculaClient other = (BaculaClient) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.uname, other.uname)) {
            return false;
        }
        if (this.autoprune != other.autoprune) {
            return false;
        }
        if (this.fileretention != other.fileretention) {
            return false;
        }
        if (this.jobretention != other.jobretention) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id:\t\t" + id + "\nname:\t\t" + name + "\nuname:\t\t" + uname + "\nautoprune:\t" + autoprune + "\nfileretention:\t" + fileretention + "\njobretention:\t" + jobretention;
    }
    
}
