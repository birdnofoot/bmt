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

import br.com.uktech.bmt.bacula.lib.Utils;
import java.util.Calendar;
import java.util.Objects;

/**
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class BaculaJobRunningClient extends AbstractJobRunning{
    
    private String job;
    private String status;
    private Calendar starttime;
    private Integer joberrors;
    private Long filesExamined;
    private String processingFile;
    private Integer SDReadSeqNo;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Calendar getStarttime() {
        return starttime;
    }

    public void setStarttime(Calendar starttime) {
        this.starttime = starttime;
    }

    public Integer getJoberrors() {
        return joberrors;
    }

    public void setJoberrors(Integer joberrors) {
        this.joberrors = joberrors;
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

    public Integer getSDReadSeqNo() {
        return SDReadSeqNo;
    }

    public void setSDReadSeqNo(Integer SDReadSeqNo) {
        this.SDReadSeqNo = SDReadSeqNo;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 29 * hash + Objects.hashCode(this.job);
        hash = 29 * hash + Objects.hashCode(this.status);
        hash = 29 * hash + Objects.hashCode(this.starttime);
        hash = 29 * hash + Objects.hashCode(this.joberrors);
        hash = 29 * hash + Objects.hashCode(this.filesExamined);
        hash = 29 * hash + Objects.hashCode(this.processingFile);
        hash = 29 * hash + Objects.hashCode(this.SDReadSeqNo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaculaJobRunningClient other = (BaculaJobRunningClient) obj;
        if (!Objects.equals(this.job, other.job)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.starttime, other.starttime)) {
            return false;
        }
        if (!Objects.equals(this.joberrors, other.joberrors)) {
            return false;
        }
        if (!Objects.equals(this.filesExamined, other.filesExamined)) {
            return false;
        }
        if (!Objects.equals(this.processingFile, other.processingFile)) {
            return false;
        }
        if (!Objects.equals(this.SDReadSeqNo, other.SDReadSeqNo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + ", job=" + job + ", status=" + status + ", starttime=" + Utils.calendarToString(starttime) + ", joberrors=" + joberrors + ", filesExamined=" + filesExamined + ", processingFile=" + processingFile + ", SDReadSeqNo=" + SDReadSeqNo;
        //return "jobid=" + super.getJobid() + ", level=" + super.getLevel() + ", type=" + super.getType() + ", jobfiles=" + super.getJobfiles() + ", jobbytes=" + super.getJobbytes() + ", rate=" + super.getRate() + ", fd=" + super.getFd()+"job=" + job + ", status=" + status + ", starttime=" + Utils.calendarToString(starttime) + ", joberrors=" + joberrors + ", filesExamined=" + filesExamined + ", processingFile=" + processingFile + ", SDReadSeqNo=" + SDReadSeqNo;
    }
    
}
