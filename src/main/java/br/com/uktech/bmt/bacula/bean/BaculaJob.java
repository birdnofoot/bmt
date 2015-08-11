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

import br.com.uktech.bmt.bacula.lib.Constants;
import br.com.uktech.bmt.bacula.lib.Utils;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class BaculaJob implements Serializable {
    
    private Long jobid;
    private String job;
    private String name;
    private Integer purgedfiles;
    private String type;
    private String level;
    private Long clientid;
    private String clientname;
    private String jobstatus;
    private Calendar schedtime;
    private Calendar starttime;
    private Calendar endtime;
    private Calendar realendtime;
    private Long jobtdate;
    private Long volsessionid;
    private Long volsessiontime;
    private Integer jobfiles;
    private Long jobbytes;
    private Integer joberrors;
    private Integer jobmissingfiles;
    private Long poolid;
    private String poolname;
    private Long priorjobid;
    private Long filesetid;
    private String fileset;
    private String typejob;     //se é um scheduledJobs, runningJobs ou terminatedJobs
    private String volumename;  //Necessário para os trabalhos agendados
    private Calendar scheduled; //Necessário para os trabalhos agendados
    private String dirstatus;   //Status do job em quando no status dir
    
    public Long getJobid() {
        return jobid;
    }

    public void setJobid(Long jobid) {
        this.jobid = jobid;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPurgedfiles() {
        return purgedfiles;
    }

    public void setPurgedfiles(Integer purgedfiles) {
        this.purgedfiles = purgedfiles;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Long getClientid() {
        return clientid;
    }

    public void setClientid(Long clientid) {
        this.clientid = clientid;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getJobstatus() {
        return jobstatus;
    }

    public void setJobstatus(String jobstatus) {
        this.jobstatus = jobstatus;
    }

    public Calendar getSchedtime() {
        return schedtime;
    }

    public void setSchedtime(Calendar schedtime) {
        this.schedtime = schedtime;
    }

    public Calendar getStarttime() {
        return starttime;
    }

    public void setStarttime(Calendar starttime) {
        this.starttime = starttime;
    }

    public Calendar getEndtime() {
        return endtime;
    }

    public void setEndtime(Calendar endtime) {
        this.endtime = endtime;
    }

    public Calendar getRealendtime() {
        return realendtime;
    }

    public void setRealendtime(Calendar realendtime) {
        this.realendtime = realendtime;
    }

    public Long getJobtdate() {
        return jobtdate;
    }

    public void setJobtdate(Long jobtdate) {
        this.jobtdate = jobtdate;
    }

    public Long getVolsessionid() {
        return volsessionid;
    }

    public void setVolsessionid(Long volsessionid) {
        this.volsessionid = volsessionid;
    }

    public Long getVolsessiontime() {
        return volsessiontime;
    }

    public void setVolsessiontime(Long volsessiontime) {
        this.volsessiontime = volsessiontime;
    }

    public Integer getJobfiles() {
        return jobfiles;
    }

    public void setJobfiles(Integer jobfiles) {
        this.jobfiles = jobfiles;
    }

    public Long getJobbytes() {
        return jobbytes;
    }

    public void setJobbytes(Long jobbytes) {
        this.jobbytes = jobbytes;
    }

    public Integer getJoberrors() {
        return joberrors;
    }

    public void setJoberrors(Integer joberrors) {
        this.joberrors = joberrors;
    }

    public Integer getJobmissingfiles() {
        return jobmissingfiles;
    }

    public void setJobmissingfiles(Integer jobmissingfiles) {
        this.jobmissingfiles = jobmissingfiles;
    }

    public Long getPoolid() {
        return poolid;
    }

    public void setPoolid(Long poolid) {
        this.poolid = poolid;
    }

    public String getPoolname() {
        return poolname;
    }

    public void setPoolname(String poolname) {
        this.poolname = poolname;
    }

    public Long getPriorjobid() {
        return priorjobid;
    }

    public void setPriorjobid(Long priorjobid) {
        this.priorjobid = priorjobid;
    }

    public Long getFilesetid() {
        return filesetid;
    }

    public void setFilesetid(Long filesetid) {
        this.filesetid = filesetid;
    }

    public String getFileset() {
        return fileset;
    }

    public void setFileset(String fileset) {
        this.fileset = fileset;
    }

    public String getTypejob() {
        return typejob;
    }

    public void setTypejob(String typejob) {
        this.typejob = typejob;
    }

    public String getVolumename() {
        return volumename;
    }

    public void setVolumename(String volumename) {
        this.volumename = volumename;
    }

    public Calendar getScheduled() {
        return scheduled;
    }

    public void setScheduled(Calendar scheduled) {
        this.scheduled = scheduled;
    }

    public String getDirstatus() {
        return dirstatus;
    }

    public void setDirstatus(String dirstatus) {
        this.dirstatus = dirstatus;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.jobid);
        hash = 37 * hash + Objects.hashCode(this.job);
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.purgedfiles);
        hash = 37 * hash + Objects.hashCode(this.type);
        hash = 37 * hash + Objects.hashCode(this.level);
        hash = 37 * hash + Objects.hashCode(this.clientid);
        hash = 37 * hash + Objects.hashCode(this.clientname);
        hash = 37 * hash + Objects.hashCode(this.jobstatus);
        hash = 37 * hash + Objects.hashCode(this.schedtime);
        hash = 37 * hash + Objects.hashCode(this.starttime);
        hash = 37 * hash + Objects.hashCode(this.endtime);
        hash = 37 * hash + Objects.hashCode(this.realendtime);
        hash = 37 * hash + Objects.hashCode(this.jobtdate);
        hash = 37 * hash + Objects.hashCode(this.volsessionid);
        hash = 37 * hash + Objects.hashCode(this.volsessiontime);
        hash = 37 * hash + Objects.hashCode(this.jobfiles);
        hash = 37 * hash + Objects.hashCode(this.jobbytes);
        hash = 37 * hash + Objects.hashCode(this.joberrors);
        hash = 37 * hash + Objects.hashCode(this.jobmissingfiles);
        hash = 37 * hash + Objects.hashCode(this.poolid);
        hash = 37 * hash + Objects.hashCode(this.poolname);
        hash = 37 * hash + Objects.hashCode(this.priorjobid);
        hash = 37 * hash + Objects.hashCode(this.filesetid);
        hash = 37 * hash + Objects.hashCode(this.fileset);
        hash = 37 * hash + Objects.hashCode(this.typejob);
        hash = 37 * hash + Objects.hashCode(this.volumename);
        hash = 37 * hash + Objects.hashCode(this.scheduled);
        hash = 37 * hash + Objects.hashCode(this.dirstatus);
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
        final BaculaJob other = (BaculaJob) obj;
        if (!Objects.equals(this.jobid, other.jobid)) {
            return false;
        }
        if (!Objects.equals(this.job, other.job)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.purgedfiles, other.purgedfiles)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.level, other.level)) {
            return false;
        }
        if (!Objects.equals(this.clientid, other.clientid)) {
            return false;
        }
        if (!Objects.equals(this.clientname, other.clientname)) {
            return false;
        }
        if (!Objects.equals(this.jobstatus, other.jobstatus)) {
            return false;
        }
        if (!Objects.equals(this.schedtime, other.schedtime)) {
            return false;
        }
        if (!Objects.equals(this.starttime, other.starttime)) {
            return false;
        }
        if (!Objects.equals(this.endtime, other.endtime)) {
            return false;
        }
        if (!Objects.equals(this.realendtime, other.realendtime)) {
            return false;
        }
        if (!Objects.equals(this.jobtdate, other.jobtdate)) {
            return false;
        }
        if (!Objects.equals(this.volsessionid, other.volsessionid)) {
            return false;
        }
        if (!Objects.equals(this.volsessiontime, other.volsessiontime)) {
            return false;
        }
        if (!Objects.equals(this.jobfiles, other.jobfiles)) {
            return false;
        }
        if (!Objects.equals(this.jobbytes, other.jobbytes)) {
            return false;
        }
        if (!Objects.equals(this.joberrors, other.joberrors)) {
            return false;
        }
        if (!Objects.equals(this.jobmissingfiles, other.jobmissingfiles)) {
            return false;
        }
        if (!Objects.equals(this.poolid, other.poolid)) {
            return false;
        }
        if (!Objects.equals(this.poolname, other.poolname)) {
            return false;
        }
        if (!Objects.equals(this.priorjobid, other.priorjobid)) {
            return false;
        }
        if (!Objects.equals(this.filesetid, other.filesetid)) {
            return false;
        }
        if (!Objects.equals(this.fileset, other.fileset)) {
            return false;
        }
        if (!Objects.equals(this.typejob, other.typejob)) {
            return false;
        }
        if (!Objects.equals(this.volumename, other.volumename)) {
            return false;
        }
        if (!Objects.equals(this.scheduled, other.scheduled)) {
            return false;
        }
        if (!Objects.equals(this.dirstatus, other.dirstatus)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jobid=" + jobid + "\njob=" + job + "\nname=" + name + "\npurgedfiles=" + purgedfiles + "\ntype=" + type + "\nlevel=" + level + "\nclientid=" + clientid + "\nclientname=" + clientname + "\njobstatus=" + jobstatus + "\nschedtime=" + Utils.calendarToString(schedtime) + "\nstarttime=" + Utils.calendarToString(starttime) + "\nendtime=" + Utils.calendarToString(endtime) + "\nrealendtime=" + Utils.calendarToString(realendtime) + "\njobtdate=" + jobtdate + "\nvolsessionid=" + volsessionid + "\nvolsessiontime=" + volsessiontime + "\njobfiles=" + jobfiles + "\njobbytes=" + jobbytes + "\njoberrors=" + joberrors + "\njobmissingfiles=" + jobmissingfiles + "\npoolid=" + poolid + "\npoolname=" + poolname + "\npriorjobid=" + priorjobid + "\nfilesetid=" + filesetid + "\nfileset=" + fileset + "\ntypejob=" + typejob + "\nvolumename=" + volumename + "\nscheduled=" + Utils.calendarToString(scheduled)+"\ndirstatus=" + dirstatus + "\n";
    }
    
    public String lineScheduledJobs() {
        String s = new String();
        s = level+"\t"+type+"\t"+priorjobid+"\t"+Utils.calendarToString(scheduled)+"\t"+name+"\t"+volumename;
        return s;
    }
    
    public String lineRunningJobs() {
        String s = new String();
        s = jobid+"\t"+level+"\t"+name+"\t"+jobstatus;
        return s;
    }
    
    public String lineTerminatedJobs() {
        String s = new String();
        s = jobid+"\t"+level+"\t"+jobfiles+"\t"+jobbytes+"\t"+dirstatus+"\t"+Utils.calendarToString(realendtime)+"\t"+name;
        return s;
    }
}