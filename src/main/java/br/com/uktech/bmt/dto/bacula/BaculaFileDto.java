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
import java.util.List;
import java.util.Objects;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class BaculaFileDto implements Serializable {
    
    private Long pathId;
    private Long fileNameId;
    private Long fileId;
    private Long jobId;
    private BaculaRestoreFileDto LStat;
    private String path;    //Nome do Arquivo ou diretório
    private Boolean selected;
    
    BaculaDirectoryDto parent;
    
    List<BaculaFileVersionsDto> fileVersions;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public BaculaDirectoryDto getParent() {
        return parent;
    }

    public void setParent(BaculaDirectoryDto parent) {
        this.parent = parent;
    }

    public List<BaculaFileVersionsDto> getFileVersions() {
        return fileVersions;
    }

    public void setFileVersions(List<BaculaFileVersionsDto> fileVersions) {
        this.fileVersions = fileVersions;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.pathId);
        hash = 89 * hash + Objects.hashCode(this.fileNameId);
        hash = 89 * hash + Objects.hashCode(this.fileId);
        hash = 89 * hash + Objects.hashCode(this.jobId);
        hash = 89 * hash + Objects.hashCode(this.LStat);
        hash = 89 * hash + Objects.hashCode(this.path);
        hash = 89 * hash + Objects.hashCode(this.parent);
        hash = 89 * hash + Objects.hashCode(this.fileVersions);
        hash = 89 * hash + Objects.hashCode(this.selected);
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
        final BaculaFileDto other = (BaculaFileDto) obj;
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
        if (!Objects.equals(this.path, other.path)) {
            return false;
        }
        if (!Objects.equals(this.parent, other.parent)) {
            return false;
        }
        if (!Objects.equals(this.fileVersions, other.fileVersions)) {
            return false;
        }
        if (!Objects.equals(this.selected, other.selected)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaculaFileDto{" + "pathId=" + pathId + ", fileNameId=" + fileNameId + ", fileId=" + fileId + ", jobId=" + jobId + ", LStat=" + LStat + ", path=" + path + ", parent=" + parent + ", fileVersions=" + fileVersions + ", selected=" + selected + '}';
    }
    
}
