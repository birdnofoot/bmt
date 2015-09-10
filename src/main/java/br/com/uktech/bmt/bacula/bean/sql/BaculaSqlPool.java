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
public class BaculaSqlPool extends AbstractBaculaSql {
    
    private String name;
    private Integer numvols;
    private Integer maxvols;
    private Integer useonce;
    private Integer usecatalog;
    private Integer acceptanyvolume;
    private Long volretention;
    private Long voluseduration;
    private Integer maxvoljobs;
    private Integer maxvolfiles;
    private Long maxvolbytes;
    private Integer autoprune;
    private Integer recycle;
    private Integer actiononpurge;
    private String pooltype;
    private Integer labeltype;
    private String labelformat;
    private Integer enabled;
    private BaculaSqlScratchPool scratchpool;
    private BaculaSqlRecyclePool recyclepool;
    private BaculaSqlNextPool nextpool;
    private Long migrationhighbytes;
    private Long migrationlowbytes;
    private Long migrationtime;

    public BaculaSqlPool() {
        super();
    }

    public BaculaSqlPool(Long id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumvols() {
        return numvols;
    }

    public void setNumvols(Integer numvols) {
        this.numvols = numvols;
    }

    public Integer getMaxvols() {
        return maxvols;
    }

    public void setMaxvols(Integer maxvols) {
        this.maxvols = maxvols;
    }

    public Integer getUseonce() {
        return useonce;
    }

    public void setUseonce(Integer useonce) {
        this.useonce = useonce;
    }

    public Integer getUsecatalog() {
        return usecatalog;
    }

    public void setUsecatalog(Integer usecatalog) {
        this.usecatalog = usecatalog;
    }

    public Integer getAcceptanyvolume() {
        return acceptanyvolume;
    }

    public void setAcceptanyvolume(Integer acceptanyvolume) {
        this.acceptanyvolume = acceptanyvolume;
    }

    public Long getVolretention() {
        return volretention;
    }

    public void setVolretention(Long volretention) {
        this.volretention = volretention;
    }

    public Long getVoluseduration() {
        return voluseduration;
    }

    public void setVoluseduration(Long voluseduration) {
        this.voluseduration = voluseduration;
    }

    public Integer getMaxvoljobs() {
        return maxvoljobs;
    }

    public void setMaxvoljobs(Integer maxvoljobs) {
        this.maxvoljobs = maxvoljobs;
    }

    public Integer getMaxvolfiles() {
        return maxvolfiles;
    }

    public void setMaxvolfiles(Integer maxvolfiles) {
        this.maxvolfiles = maxvolfiles;
    }

    public Long getMaxvolbytes() {
        return maxvolbytes;
    }

    public void setMaxvolbytes(Long maxvolbytes) {
        this.maxvolbytes = maxvolbytes;
    }

    public Integer getAutoprune() {
        return autoprune;
    }

    public void setAutoprune(Integer autoprune) {
        this.autoprune = autoprune;
    }

    public Integer getRecycle() {
        return recycle;
    }

    public void setRecycle(Integer recycle) {
        this.recycle = recycle;
    }

    public Integer getActiononpurge() {
        return actiononpurge;
    }

    public void setActiononpurge(Integer actiononpurge) {
        this.actiononpurge = actiononpurge;
    }

    public String getPooltype() {
        return pooltype;
    }

    public void setPooltype(String pooltype) {
        this.pooltype = pooltype;
    }

    public Integer getLabeltype() {
        return labeltype;
    }

    public void setLabeltype(Integer labeltype) {
        this.labeltype = labeltype;
    }

    public String getLabelformat() {
        return labelformat;
    }

    public void setLabelformat(String labelformat) {
        this.labelformat = labelformat;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public BaculaSqlScratchPool getScratchpool() {
        return scratchpool;
    }

    public void setScratchpool(BaculaSqlScratchPool scratchpool) {
        this.scratchpool = scratchpool;
    }

    public BaculaSqlRecyclePool getRecyclepool() {
        return recyclepool;
    }

    public void setRecyclepool(BaculaSqlRecyclePool recyclepool) {
        this.recyclepool = recyclepool;
    }

    public BaculaSqlNextPool getNextpool() {
        return nextpool;
    }

    public void setNextpool(BaculaSqlNextPool nextpool) {
        this.nextpool = nextpool;
    }

    public Long getMigrationhighbytes() {
        return migrationhighbytes;
    }

    public void setMigrationhighbytes(Long migrationhighbytes) {
        this.migrationhighbytes = migrationhighbytes;
    }

    public Long getMigrationlowbytes() {
        return migrationlowbytes;
    }

    public void setMigrationlowbytes(Long migrationlowbytes) {
        this.migrationlowbytes = migrationlowbytes;
    }

    public Long getMigrationtime() {
        return migrationtime;
    }

    public void setMigrationtime(Long migrationtime) {
        this.migrationtime = migrationtime;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.numvols);
        hash = 71 * hash + Objects.hashCode(this.maxvols);
        hash = 71 * hash + Objects.hashCode(this.useonce);
        hash = 71 * hash + Objects.hashCode(this.usecatalog);
        hash = 71 * hash + Objects.hashCode(this.acceptanyvolume);
        hash = 71 * hash + Objects.hashCode(this.volretention);
        hash = 71 * hash + Objects.hashCode(this.voluseduration);
        hash = 71 * hash + Objects.hashCode(this.maxvoljobs);
        hash = 71 * hash + Objects.hashCode(this.maxvolfiles);
        hash = 71 * hash + Objects.hashCode(this.maxvolbytes);
        hash = 71 * hash + Objects.hashCode(this.autoprune);
        hash = 71 * hash + Objects.hashCode(this.recycle);
        hash = 71 * hash + Objects.hashCode(this.actiononpurge);
        hash = 71 * hash + Objects.hashCode(this.pooltype);
        hash = 71 * hash + Objects.hashCode(this.labeltype);
        hash = 71 * hash + Objects.hashCode(this.labelformat);
        hash = 71 * hash + Objects.hashCode(this.enabled);
        hash = 71 * hash + Objects.hashCode(this.scratchpool);
        hash = 71 * hash + Objects.hashCode(this.recyclepool);
        hash = 71 * hash + Objects.hashCode(this.nextpool);
        hash = 71 * hash + Objects.hashCode(this.migrationhighbytes);
        hash = 71 * hash + Objects.hashCode(this.migrationlowbytes);
        hash = 71 * hash + Objects.hashCode(this.migrationtime);
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
        final BaculaSqlPool other = (BaculaSqlPool) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.numvols, other.numvols)) {
            return false;
        }
        if (!Objects.equals(this.maxvols, other.maxvols)) {
            return false;
        }
        if (!Objects.equals(this.useonce, other.useonce)) {
            return false;
        }
        if (!Objects.equals(this.usecatalog, other.usecatalog)) {
            return false;
        }
        if (!Objects.equals(this.acceptanyvolume, other.acceptanyvolume)) {
            return false;
        }
        if (!Objects.equals(this.volretention, other.volretention)) {
            return false;
        }
        if (!Objects.equals(this.voluseduration, other.voluseduration)) {
            return false;
        }
        if (!Objects.equals(this.maxvoljobs, other.maxvoljobs)) {
            return false;
        }
        if (!Objects.equals(this.maxvolfiles, other.maxvolfiles)) {
            return false;
        }
        if (!Objects.equals(this.maxvolbytes, other.maxvolbytes)) {
            return false;
        }
        if (!Objects.equals(this.autoprune, other.autoprune)) {
            return false;
        }
        if (!Objects.equals(this.recycle, other.recycle)) {
            return false;
        }
        if (!Objects.equals(this.actiononpurge, other.actiononpurge)) {
            return false;
        }
        if (!Objects.equals(this.pooltype, other.pooltype)) {
            return false;
        }
        if (!Objects.equals(this.labeltype, other.labeltype)) {
            return false;
        }
        if (!Objects.equals(this.labelformat, other.labelformat)) {
            return false;
        }
        if (!Objects.equals(this.enabled, other.enabled)) {
            return false;
        }
        if (!Objects.equals(this.scratchpool, other.scratchpool)) {
            return false;
        }
        if (!Objects.equals(this.recyclepool, other.recyclepool)) {
            return false;
        }
        if (!Objects.equals(this.nextpool, other.nextpool)) {
            return false;
        }
        if (!Objects.equals(this.migrationhighbytes, other.migrationhighbytes)) {
            return false;
        }
        if (!Objects.equals(this.migrationlowbytes, other.migrationlowbytes)) {
            return false;
        }
        if (!Objects.equals(this.migrationtime, other.migrationtime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaculaSqlPool{" + super.toString() + ", name=" + name + ", numvols=" + numvols + ", maxvols=" + maxvols + ", useonce=" + useonce + ", usecatalog=" + usecatalog + ", acceptanyvolume=" + acceptanyvolume + ", volretention=" + volretention + ", voluseduration=" + voluseduration + ", maxvoljobs=" + maxvoljobs + ", maxvolfiles=" + maxvolfiles + ", maxvolbytes=" + maxvolbytes + ", autoprune=" + autoprune + ", recycle=" + recycle + ", actiononpurge=" + actiononpurge + ", pooltype=" + pooltype + ", labeltype=" + labeltype + ", labelformat=" + labelformat + ", enabled=" + enabled + ", scratchpool=" + scratchpool + ", recyclepool=" + recyclepool + ", nextpool=" + nextpool + ", migrationhighbytes=" + migrationhighbytes + ", migrationlowbytes=" + migrationlowbytes + ", migrationtime=" + migrationtime + '}';
    }
}