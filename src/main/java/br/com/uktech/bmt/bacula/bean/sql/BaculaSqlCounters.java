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
public class BaculaSqlCounters {
    
    private String counter;
    private Long minvalue;
    private Long maxvalue;
    private Long currentvalue;
    private String wrapcounter;

    public BaculaSqlCounters() {
        this("");
    }
    
    public BaculaSqlCounters(String counter) {
        this.counter = counter;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public Long getMinvalue() {
        return minvalue;
    }

    public void setMinvalue(Long minvalue) {
        this.minvalue = minvalue;
    }

    public Long getMaxvalue() {
        return maxvalue;
    }

    public void setMaxvalue(Long maxvalue) {
        this.maxvalue = maxvalue;
    }

    public Long getCurrentvalue() {
        return currentvalue;
    }

    public void setCurrentvalue(Long currentvalue) {
        this.currentvalue = currentvalue;
    }

    public String getWrapcounter() {
        return wrapcounter;
    }

    public void setWrapcounter(String wrapcounter) {
        this.wrapcounter = wrapcounter;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.counter);
        hash = 17 * hash + Objects.hashCode(this.minvalue);
        hash = 17 * hash + Objects.hashCode(this.maxvalue);
        hash = 17 * hash + Objects.hashCode(this.currentvalue);
        hash = 17 * hash + Objects.hashCode(this.wrapcounter);
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
        final BaculaSqlCounters other = (BaculaSqlCounters) obj;
        if (!Objects.equals(this.counter, other.counter)) {
            return false;
        }
        if (!Objects.equals(this.minvalue, other.minvalue)) {
            return false;
        }
        if (!Objects.equals(this.maxvalue, other.maxvalue)) {
            return false;
        }
        if (!Objects.equals(this.currentvalue, other.currentvalue)) {
            return false;
        }
        if (!Objects.equals(this.wrapcounter, other.wrapcounter)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaculaSqlCounters{" + "counter=" + counter + ", minvalue=" + minvalue + ", maxvalue=" + maxvalue + ", currentvalue=" + currentvalue + ", wrapcounter=" + wrapcounter + '}';
    }
}
