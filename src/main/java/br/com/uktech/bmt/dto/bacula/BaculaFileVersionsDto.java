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
public class BaculaFileVersionsDto implements Serializable {
    
    private Long pathId;
    private Long fileNameId;
    private Long fileId;
    private Long jobId;
    private BaculaRestoreFileDto LStat;
    private String md5;
    private String volName;
    private Integer Inchanger;

    public Long getPathId() {
        return pathId;
    }

    public void setPathId(Long pathId) {
        this.pathId = pathId;
    }

    public Long getFileNameId() {
        return fileNameId;
    }

    public void setFileNameId(Long fileNameId) {
        this.fileNameId = fileNameId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public BaculaRestoreFileDto getLStat() {
        return LStat;
    }

    public void setLStat(BaculaRestoreFileDto LStat) {
        this.LStat = LStat;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getVolName() {
        return volName;
    }

    public void setVolName(String volName) {
        this.volName = volName;
    }

    public Integer getInchanger() {
        return Inchanger;
    }

    public void setInchanger(Integer Inchanger) {
        this.Inchanger = Inchanger;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.pathId);
        hash = 97 * hash + Objects.hashCode(this.fileNameId);
        hash = 97 * hash + Objects.hashCode(this.fileId);
        hash = 97 * hash + Objects.hashCode(this.jobId);
        hash = 97 * hash + Objects.hashCode(this.LStat);
        hash = 97 * hash + Objects.hashCode(this.md5);
        hash = 97 * hash + Objects.hashCode(this.volName);
        hash = 97 * hash + Objects.hashCode(this.Inchanger);
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
        final BaculaFileVersionsDto other = (BaculaFileVersionsDto) obj;
        if (!Objects.equals(this.pathId, other.pathId)) {
            return false;
        }
        if (!Objects.equals(this.fileNameId, other.fileNameId)) {
            return false;
        }
        if (!Objects.equals(this.fileId, other.fileId)) {
            return false;
        }
        if (!Objects.equals(this.jobId, other.jobId)) {
            return false;
        }
        if (!Objects.equals(this.LStat, other.LStat)) {
            return false;
        }
        if (!Objects.equals(this.md5, other.md5)) {
            return false;
        }
        if (!Objects.equals(this.volName, other.volName)) {
            return false;
        }
        if (!Objects.equals(this.Inchanger, other.Inchanger)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaculaFileVersionsDto{" + "pathId=" + pathId + ", fileNameId=" + fileNameId + ", fileId=" + fileId + ", jobId=" + jobId + ", LStat=" + LStat + ", md5=" + md5 + ", volName=" + volName + ", Inchanger=" + Inchanger + '}';
    }
    
}
