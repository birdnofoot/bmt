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

package br.com.uktech.bmt.dto.bacula.dot;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class BaculaDotStatusClientRunningDto implements Serializable {
    
    private Long jobId;
    private String job;
    private Long vss;
    private String level;
    private String jobType;
    private Date jobStarted;
    private Long files;
    private Long bytes;
    private Long bytesSec;
    private Integer errors;
    private Long filesExamined;
    private String processingFile;
    private Long sdReadSeqNo;
    private Long fd;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Long getVss() {
        return vss;
    }

    public void setVss(Long vss) {
        this.vss = vss;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public Date getJobStarted() {
        return jobStarted;
    }

    public void setJobStarted(Date jobStarted) {
        this.jobStarted = jobStarted;
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

    public Long getBytesSec() {
        return bytesSec;
    }

    public void setBytesSec(Long bytesSec) {
        this.bytesSec = bytesSec;
    }

    public Integer getErrors() {
        return errors;
    }

    public void setErrors(Integer errors) {
        this.errors = errors;
    }

    public Long getFilesExamined() {
        return filesExamined;
    }

    public void setFilesExamined(Long filesExamined) {
        this.filesExamined = filesExamined;
    }

    public String getProcessingFile() {
        return processingFile;
    }

    public void setProcessingFile(String processingFile) {
        this.processingFile = processingFile;
    }

    public Long getSdReadSeqNo() {
        return sdReadSeqNo;
    }

    public void setSdReadSeqNo(Long sdReadSeqNo) {
        this.sdReadSeqNo = sdReadSeqNo;
    }

    public Long getFd() {
        return fd;
    }

    public void setFd(Long fd) {
        this.fd = fd;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.jobId);
        hash = 29 * hash + Objects.hashCode(this.job);
        hash = 29 * hash + Objects.hashCode(this.vss);
        hash = 29 * hash + Objects.hashCode(this.level);
        hash = 29 * hash + Objects.hashCode(this.jobType);
        hash = 29 * hash + Objects.hashCode(this.jobStarted);
        hash = 29 * hash + Objects.hashCode(this.files);
        hash = 29 * hash + Objects.hashCode(this.bytes);
        hash = 29 * hash + Objects.hashCode(this.bytesSec);
        hash = 29 * hash + Objects.hashCode(this.errors);
        hash = 29 * hash + Objects.hashCode(this.filesExamined);
        hash = 29 * hash + Objects.hashCode(this.processingFile);
        hash = 29 * hash + Objects.hashCode(this.sdReadSeqNo);
        hash = 29 * hash + Objects.hashCode(this.fd);
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
        final BaculaDotStatusClientRunningDto other = (BaculaDotStatusClientRunningDto) obj;
        if (!Objects.equals(this.jobId, other.jobId)) {
            return false;
        }
        if (!Objects.equals(this.job, other.job)) {
            return false;
        }
        if (!Objects.equals(this.vss, other.vss)) {
            return false;
        }
        if (!Objects.equals(this.level, other.level)) {
            return false;
        }
        if (!Objects.equals(this.jobType, other.jobType)) {
            return false;
        }
        if (!Objects.equals(this.jobStarted, other.jobStarted)) {
            return false;
        }
        if (!Objects.equals(this.files, other.files)) {
            return false;
        }
        if (!Objects.equals(this.bytes, other.bytes)) {
            return false;
        }
        if (!Objects.equals(this.bytesSec, other.bytesSec)) {
            return false;
        }
        if (!Objects.equals(this.errors, other.errors)) {
            return false;
        }
        if (!Objects.equals(this.filesExamined, other.filesExamined)) {
            return false;
        }
        if (!Objects.equals(this.processingFile, other.processingFile)) {
            return false;
        }
        if (!Objects.equals(this.sdReadSeqNo, other.sdReadSeqNo)) {
            return false;
        }
        if (!Objects.equals(this.fd, other.fd)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaculaDotStatusClientRunningDto{" + "jobId=" + jobId + ", job=" + job + ", vss=" + vss + ", level=" + level + ", jobType=" + jobType + ", jobStarted=" + jobStarted + ", files=" + files + ", bytes=" + bytes + ", bytesSec=" + bytesSec + ", errors=" + errors + ", filesExamined=" + filesExamined + ", processingFile=" + processingFile + ", sdReadSeqNo=" + sdReadSeqNo + ", fd=" + fd + '}';
    }
    
}
