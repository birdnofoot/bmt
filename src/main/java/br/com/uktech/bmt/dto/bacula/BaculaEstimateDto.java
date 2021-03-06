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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class BaculaEstimateDto implements Serializable{
    
    private List<BaculaFileSystemDto> fileSystem = new ArrayList<>();
    private Long files;
    private Long bytes;

    public List<BaculaFileSystemDto> getFileSystem() {
        return fileSystem;
    }

    public void setFileSystem(List<BaculaFileSystemDto> fileSystem) {
        this.fileSystem = fileSystem;
    }

    public Long getFiles() {
        return files;
    }

    public void setFiles(Long files) {
        this.files = files;
    }

    public Long getBytes() {
        return bytes;
    }

    public void setBytes(Long bytes) {
        this.bytes = bytes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.fileSystem);
        hash = 17 * hash + Objects.hashCode(this.files);
        hash = 17 * hash + Objects.hashCode(this.bytes);
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
        final BaculaEstimateDto other = (BaculaEstimateDto) obj;
        if (!Objects.equals(this.fileSystem, other.fileSystem)) {
            return false;
        }
        if (!Objects.equals(this.files, other.files)) {
            return false;
        }
        if (!Objects.equals(this.bytes, other.bytes)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String print = "";
        if (fileSystem != null) {
            for (BaculaFileSystemDto line : fileSystem) {
                print += line.toString() + "\n";
            }
        }
        print += "\n2000 OK estimate files=" + files + "bytes=" + bytes;
        return print;
    }
    
}
