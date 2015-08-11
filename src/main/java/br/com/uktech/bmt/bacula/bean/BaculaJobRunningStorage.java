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

import java.util.Objects;

/**
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class BaculaJobRunningStorage extends AbstractJobRunning{
    
    private String name;
    private String volume;
    private String pool;
    private String device;
    private Long spooling;
    private Long despooling;
    private Long despoolWait;
    private Long FDReadSeqNo;
    private Long inMsg;
    private Long outMsg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Long getSpooling() {
        return spooling;
    }

    public void setSpooling(Long spooling) {
        this.spooling = spooling;
    }

    public Long getDespooling() {
        return despooling;
    }

    public void setDespooling(Long despooling) {
        this.despooling = despooling;
    }

    public Long getDespoolWait() {
        return despoolWait;
    }

    public void setDespoolWait(Long despoolWait) {
        this.despoolWait = despoolWait;
    }

    public Long getFDReadSeqNo() {
        return FDReadSeqNo;
    }

    public void setFDReadSeqNo(Long FDReadSeqNo) {
        this.FDReadSeqNo = FDReadSeqNo;
    }

    public Long getInMsg() {
        return inMsg;
    }

    public void setInMsg(Long inMsg) {
        this.inMsg = inMsg;
    }

    public Long getOutMsg() {
        return outMsg;
    }

    public void setOutMsg(Long outMsg) {
        this.outMsg = outMsg;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.volume);
        hash = 23 * hash + Objects.hashCode(this.pool);
        hash = 23 * hash + Objects.hashCode(this.device);
        hash = 23 * hash + Objects.hashCode(this.spooling);
        hash = 23 * hash + Objects.hashCode(this.despooling);
        hash = 23 * hash + Objects.hashCode(this.despoolWait);
        hash = 23 * hash + Objects.hashCode(this.FDReadSeqNo);
        hash = 23 * hash + Objects.hashCode(this.inMsg);
        hash = 23 * hash + Objects.hashCode(this.outMsg);
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
        final BaculaJobRunningStorage other = (BaculaJobRunningStorage) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.volume, other.volume)) {
            return false;
        }
        if (!Objects.equals(this.pool, other.pool)) {
            return false;
        }
        if (!Objects.equals(this.device, other.device)) {
            return false;
        }
        if (!Objects.equals(this.spooling, other.spooling)) {
            return false;
        }
        if (!Objects.equals(this.despooling, other.despooling)) {
            return false;
        }
        if (!Objects.equals(this.despoolWait, other.despoolWait)) {
            return false;
        }
        if (!Objects.equals(this.FDReadSeqNo, other.FDReadSeqNo)) {
            return false;
        }
        if (!Objects.equals(this.inMsg, other.inMsg)) {
            return false;
        }
        if (!Objects.equals(this.outMsg, other.outMsg)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaculaJobRunningStorage{" + "name=" + name + ", volume=" + volume + ", pool=" + pool + ", device=" + device + ", spooling=" + spooling + ", despooling=" + despooling + ", despoolWait=" + despoolWait + ", FDReadSeqNo=" + FDReadSeqNo + ", inMsg=" + inMsg + ", outMsg=" + outMsg + '}';
    }
    
}
