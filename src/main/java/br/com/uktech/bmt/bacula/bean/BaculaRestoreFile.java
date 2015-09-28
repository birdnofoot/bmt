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
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class BaculaRestoreFile implements Serializable{
    
    private Long device;
    private Long inode;
    private Long mode;
    private Long numberOfHardLinks;
    private Long userIdOfOwner;
    private Long groupIdOfOwner;
    private Long deviceType;
    private Long totalSizeInBytes;
    private Long blocksize;
    private Long numberOfBlocksAllocated;
    private Date timeOfLastAccess;
    private Date timeOfLastModification;
    private Date timeOfLastChange;
    private Long linkFi;
    private Long flags;
    private Long data;
    
    public BaculaRestoreFile(){
        
    }
    
    public BaculaRestoreFile(Map<String, Long> stat) {
        this.device = stat.get("Device");
        this.inode = stat.get("Inode");
        this.mode = stat.get("Mode");
        this.numberOfHardLinks = stat.get("Number_of_hard_links");
        this.userIdOfOwner = stat.get("User_ID_of_owner");
        this.groupIdOfOwner = stat.get("Group_ID_of_owner");
        this.deviceType = stat.get("Device_type");
        this.totalSizeInBytes = stat.get("Total_size_in_bytes");
        this.blocksize = stat.get("Blocksize");
        this.numberOfBlocksAllocated = stat.get("Number_of_blocks_allocated");
        this.timeOfLastAccess = new Date(stat.get("Time_of_last_access"));
        this.timeOfLastModification = new Date(stat.get("Time_of_last_modification"));
        this.timeOfLastChange = new Date(stat.get("Time_of_last_change"));
        this.linkFi = stat.get("LinkFI");
        this.flags = stat.get("Flags");
        this.data = stat.get("Data");
    }

    public Long getDevice() {
        return device;
    }

    public void setDevice(Long device) {
        this.device = device;
    }

    public Long getInode() {
        return inode;
    }

    public void setInode(Long inode) {
        this.inode = inode;
    }

    public Long getMode() {
        return mode;
    }

    public void setMode(Long mode) {
        this.mode = mode;
    }

    public Long getNumberOfHardLinks() {
        return numberOfHardLinks;
    }

    public void setNumberOfHardLinks(Long numberOfHardLinks) {
        this.numberOfHardLinks = numberOfHardLinks;
    }

    public Long getUserIdOfOwner() {
        return userIdOfOwner;
    }

    public void setUserIdOfOwner(Long userIdOfOwner) {
        this.userIdOfOwner = userIdOfOwner;
    }

    public Long getGroupIdOfOwner() {
        return groupIdOfOwner;
    }

    public void setGroupIdOfOwner(Long groupIdOfOwner) {
        this.groupIdOfOwner = groupIdOfOwner;
    }

    public Long getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Long deviceType) {
        this.deviceType = deviceType;
    }

    public Long getTotalSizeInBytes() {
        return totalSizeInBytes;
    }

    public void setTotalSizeInBytes(Long totalSizeInBytes) {
        this.totalSizeInBytes = totalSizeInBytes;
    }

    public Long getBlocksize() {
        return blocksize;
    }

    public void setBlocksize(Long blocksize) {
        this.blocksize = blocksize;
    }

    public Long getNumberOfBlocksAllocated() {
        return numberOfBlocksAllocated;
    }

    public void setNumberOfBlocksAllocated(Long numberOfBlocksAllocated) {
        this.numberOfBlocksAllocated = numberOfBlocksAllocated;
    }

    public Date getTimeOfLastAccess() {
        return timeOfLastAccess;
    }

    public void setTimeOfLastAccess(Date timeOfLastAccess) {
        this.timeOfLastAccess = timeOfLastAccess;
    }

    public Date getTimeOfLastModification() {
        return timeOfLastModification;
    }

    public void setTimeOfLastModification(Date timeOfLastModification) {
        this.timeOfLastModification = timeOfLastModification;
    }

    public Date getTimeOfLastChange() {
        return timeOfLastChange;
    }

    public void setTimeOfLastChange(Date timeOfLastChange) {
        this.timeOfLastChange = timeOfLastChange;
    }

    public Long getLinkFi() {
        return linkFi;
    }

    public void setLinkFi(Long linkFi) {
        this.linkFi = linkFi;
    }

    public Long getFlags() {
        return flags;
    }

    public void setFlags(Long flags) {
        this.flags = flags;
    }

    public Long getData() {
        return data;
    }

    public void setData(Long data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.device);
        hash = 79 * hash + Objects.hashCode(this.inode);
        hash = 79 * hash + Objects.hashCode(this.mode);
        hash = 79 * hash + Objects.hashCode(this.numberOfHardLinks);
        hash = 79 * hash + Objects.hashCode(this.userIdOfOwner);
        hash = 79 * hash + Objects.hashCode(this.groupIdOfOwner);
        hash = 79 * hash + Objects.hashCode(this.deviceType);
        hash = 79 * hash + Objects.hashCode(this.totalSizeInBytes);
        hash = 79 * hash + Objects.hashCode(this.blocksize);
        hash = 79 * hash + Objects.hashCode(this.numberOfBlocksAllocated);
        hash = 79 * hash + Objects.hashCode(this.timeOfLastAccess);
        hash = 79 * hash + Objects.hashCode(this.timeOfLastModification);
        hash = 79 * hash + Objects.hashCode(this.timeOfLastChange);
        hash = 79 * hash + Objects.hashCode(this.linkFi);
        hash = 79 * hash + Objects.hashCode(this.flags);
        hash = 79 * hash + Objects.hashCode(this.data);
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
        final BaculaRestoreFile other = (BaculaRestoreFile) obj;
        if (!Objects.equals(this.device, other.device)) {
            return false;
        }
        if (!Objects.equals(this.inode, other.inode)) {
            return false;
        }
        if (!Objects.equals(this.mode, other.mode)) {
            return false;
        }
        if (!Objects.equals(this.numberOfHardLinks, other.numberOfHardLinks)) {
            return false;
        }
        if (!Objects.equals(this.userIdOfOwner, other.userIdOfOwner)) {
            return false;
        }
        if (!Objects.equals(this.groupIdOfOwner, other.groupIdOfOwner)) {
            return false;
        }
        if (!Objects.equals(this.deviceType, other.deviceType)) {
            return false;
        }
        if (!Objects.equals(this.totalSizeInBytes, other.totalSizeInBytes)) {
            return false;
        }
        if (!Objects.equals(this.blocksize, other.blocksize)) {
            return false;
        }
        if (!Objects.equals(this.numberOfBlocksAllocated, other.numberOfBlocksAllocated)) {
            return false;
        }
        if (!Objects.equals(this.timeOfLastAccess, other.timeOfLastAccess)) {
            return false;
        }
        if (!Objects.equals(this.timeOfLastModification, other.timeOfLastModification)) {
            return false;
        }
        if (!Objects.equals(this.timeOfLastChange, other.timeOfLastChange)) {
            return false;
        }
        if (!Objects.equals(this.linkFi, other.linkFi)) {
            return false;
        }
        if (!Objects.equals(this.flags, other.flags)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaculaRestoreFile{" + "device=" + device + ", inode=" + inode + ", mode=" + mode + ", numberOfHardLinks=" + numberOfHardLinks + ", userIdOfOwner=" + userIdOfOwner + ", groupIdOfOwner=" + groupIdOfOwner + ", deviceType=" + deviceType + ", totalSizeInBytes=" + totalSizeInBytes + ", blocksize=" + blocksize + ", numberOfBlocksAllocated=" + numberOfBlocksAllocated + ", timeOfLastAccess=" + timeOfLastAccess + ", timeOfLastModification=" + timeOfLastModification + ", timeOfLastChange=" + timeOfLastChange + ", linkFi=" + linkFi + ", flags=" + flags + ", data=" + data + '}';
    }

}
