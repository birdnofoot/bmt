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
public class BaculaSqlMedia extends AbstractBaculaSql {
    
    private String volumename;
    private Integer slot;
    private BaculaSqlPool pool;
    private BaculaSqlMediaType mediatype;
    private Integer labeltype;
    private Date firstwritten;
    private Date lastwritten;
    private Date labeldate;
    private Integer voljobs;
    private Integer volfiles;
    private Integer volblocks;
    private Integer volmounts;
    private Long volbytes;
    private Integer volparts;
    private Integer volerrors;
    private Integer volwrites;
    private Long volcapacitybytes;
    private String volstatus;
    private Integer enabled;
    private Integer recycle;
    private Integer actiononpurge;
    private Long volretention;
    private Long voluseduration;
    private Integer maxvoljobs;
    private Integer maxvolfiles;
    private Long maxvolbytes;
    private Integer inchanger;
    private BaculaSqlStorage storage;
    private BaculaSqlDevice device;
    private Integer mediaaddressing;
    private Long volreadtime;
    private Long volwritetime;
    private Integer endfile;
    private Long endblock;
    private BaculaSqlLocation location;
    private Integer recyclecount;
    private Date initialwrite;
    private BaculaSqlScratchPool scratchpool;
    private BaculaSqlRecyclePool recyclepool;

    public BaculaSqlMedia() {
        super();
    }

    public BaculaSqlMedia(Long id) {
        super(id);
    }

    public String getVolumename() {
        return volumename;
    }

    public void setVolumename(String volumename) {
        this.volumename = volumename;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public BaculaSqlPool getPool() {
        return pool;
    }

    public void setPool(BaculaSqlPool pool) {
        this.pool = pool;
    }

    public BaculaSqlMediaType getMediatype() {
        return mediatype;
    }

    public void setMediatype(BaculaSqlMediaType mediatype) {
        this.mediatype = mediatype;
    }

    public Integer getLabeltype() {
        return labeltype;
    }

    public void setLabeltype(Integer labeltype) {
        this.labeltype = labeltype;
    }

    public Date getFirstwritten() {
        return firstwritten;
    }

    public void setFirstwritten(Date firstwritten) {
        this.firstwritten = firstwritten;
    }

    public Date getLastwritten() {
        return lastwritten;
    }

    public void setLastwritten(Date lastwritten) {
        this.lastwritten = lastwritten;
    }

    public Date getLabeldate() {
        return labeldate;
    }

    public void setLabeldate(Date labeldate) {
        this.labeldate = labeldate;
    }

    public Integer getVoljobs() {
        return voljobs;
    }

    public void setVoljobs(Integer voljobs) {
        this.voljobs = voljobs;
    }

    public Integer getVolfiles() {
        return volfiles;
    }

    public void setVolfiles(Integer volfiles) {
        this.volfiles = volfiles;
    }

    public Integer getVolblocks() {
        return volblocks;
    }

    public void setVolblocks(Integer volblocks) {
        this.volblocks = volblocks;
    }

    public Integer getVolmounts() {
        return volmounts;
    }

    public void setVolmounts(Integer volmounts) {
        this.volmounts = volmounts;
    }

    public Long getVolbytes() {
        return volbytes;
    }

    public void setVolbytes(Long volbytes) {
        this.volbytes = volbytes;
    }

    public Integer getVolparts() {
        return volparts;
    }

    public void setVolparts(Integer volparts) {
        this.volparts = volparts;
    }

    public Integer getVolerrors() {
        return volerrors;
    }

    public void setVolerrors(Integer volerrors) {
        this.volerrors = volerrors;
    }

    public Integer getVolwrites() {
        return volwrites;
    }

    public void setVolwrites(Integer volwrites) {
        this.volwrites = volwrites;
    }

    public Long getVolcapacitybytes() {
        return volcapacitybytes;
    }

    public void setVolcapacitybytes(Long volcapacitybytes) {
        this.volcapacitybytes = volcapacitybytes;
    }

    public String getVolstatus() {
        return volstatus;
    }

    public void setVolstatus(String volstatus) {
        this.volstatus = volstatus;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
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

    public Integer getInchanger() {
        return inchanger;
    }

    public void setInchanger(Integer inchanger) {
        this.inchanger = inchanger;
    }

    public BaculaSqlStorage getStorage() {
        return storage;
    }

    public void setStorage(BaculaSqlStorage storage) {
        this.storage = storage;
    }

    public BaculaSqlDevice getDevice() {
        return device;
    }

    public void setDevice(BaculaSqlDevice device) {
        this.device = device;
    }

    public Integer getMediaaddressing() {
        return mediaaddressing;
    }

    public void setMediaaddressing(Integer mediaaddressing) {
        this.mediaaddressing = mediaaddressing;
    }

    public Long getVolreadtime() {
        return volreadtime;
    }

    public void setVolreadtime(Long volreadtime) {
        this.volreadtime = volreadtime;
    }

    public Long getVolwritetime() {
        return volwritetime;
    }

    public void setVolwritetime(Long volwritetime) {
        this.volwritetime = volwritetime;
    }

    public Integer getEndfile() {
        return endfile;
    }

    public void setEndfile(Integer endfile) {
        this.endfile = endfile;
    }

    public Long getEndblock() {
        return endblock;
    }

    public void setEndblock(Long endblock) {
        this.endblock = endblock;
    }

    public BaculaSqlLocation getLocation() {
        return location;
    }

    public void setLocation(BaculaSqlLocation location) {
        this.location = location;
    }

    public Integer getRecyclecount() {
        return recyclecount;
    }

    public void setRecyclecount(Integer recyclecount) {
        this.recyclecount = recyclecount;
    }

    public Date getInitialwrite() {
        return initialwrite;
    }

    public void setInitialwrite(Date initialwrite) {
        this.initialwrite = initialwrite;
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

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 83 * hash + Objects.hashCode(this.volumename);
        hash = 83 * hash + Objects.hashCode(this.slot);
        hash = 83 * hash + Objects.hashCode(this.pool);
        hash = 83 * hash + Objects.hashCode(this.mediatype);
        hash = 83 * hash + Objects.hashCode(this.labeltype);
        hash = 83 * hash + Objects.hashCode(this.firstwritten);
        hash = 83 * hash + Objects.hashCode(this.lastwritten);
        hash = 83 * hash + Objects.hashCode(this.labeldate);
        hash = 83 * hash + Objects.hashCode(this.voljobs);
        hash = 83 * hash + Objects.hashCode(this.volfiles);
        hash = 83 * hash + Objects.hashCode(this.volblocks);
        hash = 83 * hash + Objects.hashCode(this.volmounts);
        hash = 83 * hash + Objects.hashCode(this.volbytes);
        hash = 83 * hash + Objects.hashCode(this.volparts);
        hash = 83 * hash + Objects.hashCode(this.volerrors);
        hash = 83 * hash + Objects.hashCode(this.volwrites);
        hash = 83 * hash + Objects.hashCode(this.volcapacitybytes);
        hash = 83 * hash + Objects.hashCode(this.volstatus);
        hash = 83 * hash + Objects.hashCode(this.enabled);
        hash = 83 * hash + Objects.hashCode(this.recycle);
        hash = 83 * hash + Objects.hashCode(this.actiononpurge);
        hash = 83 * hash + Objects.hashCode(this.volretention);
        hash = 83 * hash + Objects.hashCode(this.voluseduration);
        hash = 83 * hash + Objects.hashCode(this.maxvoljobs);
        hash = 83 * hash + Objects.hashCode(this.maxvolfiles);
        hash = 83 * hash + Objects.hashCode(this.maxvolbytes);
        hash = 83 * hash + Objects.hashCode(this.inchanger);
        hash = 83 * hash + Objects.hashCode(this.storage);
        hash = 83 * hash + Objects.hashCode(this.device);
        hash = 83 * hash + Objects.hashCode(this.mediaaddressing);
        hash = 83 * hash + Objects.hashCode(this.volreadtime);
        hash = 83 * hash + Objects.hashCode(this.volwritetime);
        hash = 83 * hash + Objects.hashCode(this.endfile);
        hash = 83 * hash + Objects.hashCode(this.endblock);
        hash = 83 * hash + Objects.hashCode(this.location);
        hash = 83 * hash + Objects.hashCode(this.recyclecount);
        hash = 83 * hash + Objects.hashCode(this.initialwrite);
        hash = 83 * hash + Objects.hashCode(this.scratchpool);
        hash = 83 * hash + Objects.hashCode(this.recyclepool);
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
        final BaculaSqlMedia other = (BaculaSqlMedia) obj;
        if (!Objects.equals(this.volumename, other.volumename)) {
            return false;
        }
        if (!Objects.equals(this.slot, other.slot)) {
            return false;
        }
        if (!Objects.equals(this.pool, other.pool)) {
            return false;
        }
        if (!Objects.equals(this.mediatype, other.mediatype)) {
            return false;
        }
        if (!Objects.equals(this.labeltype, other.labeltype)) {
            return false;
        }
        if (!Objects.equals(this.firstwritten, other.firstwritten)) {
            return false;
        }
        if (!Objects.equals(this.lastwritten, other.lastwritten)) {
            return false;
        }
        if (!Objects.equals(this.labeldate, other.labeldate)) {
            return false;
        }
        if (!Objects.equals(this.voljobs, other.voljobs)) {
            return false;
        }
        if (!Objects.equals(this.volfiles, other.volfiles)) {
            return false;
        }
        if (!Objects.equals(this.volblocks, other.volblocks)) {
            return false;
        }
        if (!Objects.equals(this.volmounts, other.volmounts)) {
            return false;
        }
        if (!Objects.equals(this.volbytes, other.volbytes)) {
            return false;
        }
        if (!Objects.equals(this.volparts, other.volparts)) {
            return false;
        }
        if (!Objects.equals(this.volerrors, other.volerrors)) {
            return false;
        }
        if (!Objects.equals(this.volwrites, other.volwrites)) {
            return false;
        }
        if (!Objects.equals(this.volcapacitybytes, other.volcapacitybytes)) {
            return false;
        }
        if (!Objects.equals(this.volstatus, other.volstatus)) {
            return false;
        }
        if (!Objects.equals(this.enabled, other.enabled)) {
            return false;
        }
        if (!Objects.equals(this.recycle, other.recycle)) {
            return false;
        }
        if (!Objects.equals(this.actiononpurge, other.actiononpurge)) {
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
        if (!Objects.equals(this.inchanger, other.inchanger)) {
            return false;
        }
        if (!Objects.equals(this.storage, other.storage)) {
            return false;
        }
        if (!Objects.equals(this.device, other.device)) {
            return false;
        }
        if (!Objects.equals(this.mediaaddressing, other.mediaaddressing)) {
            return false;
        }
        if (!Objects.equals(this.volreadtime, other.volreadtime)) {
            return false;
        }
        if (!Objects.equals(this.volwritetime, other.volwritetime)) {
            return false;
        }
        if (!Objects.equals(this.endfile, other.endfile)) {
            return false;
        }
        if (!Objects.equals(this.endblock, other.endblock)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.recyclecount, other.recyclecount)) {
            return false;
        }
        if (!Objects.equals(this.initialwrite, other.initialwrite)) {
            return false;
        }
        if (!Objects.equals(this.scratchpool, other.scratchpool)) {
            return false;
        }
        if (!Objects.equals(this.recyclepool, other.recyclepool)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaculaSqlMedia{" + super.toString() + ", volumename=" + volumename + ", slot=" + slot + ", pool=" + pool.toString() + ", mediatype=" + mediatype.toString() + ", labeltype=" + labeltype + ", firstwritten=" + firstwritten + ", lastwritten=" + lastwritten + ", labeldate=" + labeldate + ", voljobs=" + voljobs + ", volfiles=" + volfiles + ", volblocks=" + volblocks + ", volmounts=" + volmounts + ", volbytes=" + volbytes + ", volparts=" + volparts + ", volerrors=" + volerrors + ", volwrites=" + volwrites + ", volcapacitybytes=" + volcapacitybytes + ", volstatus=" + volstatus + ", enabled=" + enabled + ", recycle=" + recycle + ", actiononpurge=" + actiononpurge + ", volretention=" + volretention + ", voluseduration=" + voluseduration + ", maxvoljobs=" + maxvoljobs + ", maxvolfiles=" + maxvolfiles + ", maxvolbytes=" + maxvolbytes + ", inchanger=" + inchanger + ", storage=" + storage.toString() + ", device=" + device.toString() + ", mediaaddressing=" + mediaaddressing + ", volreadtime=" + volreadtime + ", volwritetime=" + volwritetime + ", endfile=" + endfile + ", endblock=" + endblock + ", location=" + location.toString() + ", recyclecount=" + recyclecount + ", initialwrite=" + initialwrite + ", scratchpool=" + scratchpool.toString() + ", recyclepool=" + recyclepool.toString() + '}';
    }
}