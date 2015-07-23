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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class BaculaStatusDirector implements Serializable {
    
    private String banner;
    private Calendar upSince;
    private int jobsRunned;
    private List<BaculaJob> scheduledJobs = new ArrayList<>();
    private List<BaculaJob> runningJobs = new ArrayList<>();
    private List<BaculaJob> terminatedJobs = new ArrayList<>();

    /**
     * @return the banner
     */
    public String getBanner() {
        return banner;
    }

    /**
     * @param banner the banner to set
     */
    public void setBanner(String banner) {
        this.banner = banner;
    }

    /**
     * @return the upSince
     */
    public Calendar getUpSince() {
        return upSince;
    }

    /**
     * @param upSince the upSince to set
     */
    public void setUpSince(Calendar upSince) {
        this.upSince = upSince;
    }

    /**
     * @return the jobsRunned
     */
    public int getJobsRunned() {
        return jobsRunned;
    }

    /**
     * @param jobsRunned the jobsRunned to set
     */
    public void setJobsRunned(int jobsRunned) {
        this.jobsRunned = jobsRunned;
    }

    /**
     * @param scheduledJobs the scheduledJobs to set
     */
    public void setScheduledJobs(List<BaculaJob> scheduledJobs) {
        this.scheduledJobs = scheduledJobs;
    }

    public List<BaculaJob> getScheduledJobs() {
        return scheduledJobs;
    }
    
    /**
     * @param runningJobs the runningJobs to set
     */
    public void setRunningJobs(List<BaculaJob> runningJobs) {
        this.runningJobs = runningJobs;
    }

    public List<BaculaJob> getRunningJobs() {
        return runningJobs;
    }

    /**
     * @param terminatedJobs the terminatedJobs to set
     */
    public void setTerminatedJobs(List<BaculaJob> terminatedJobs) {
        this.terminatedJobs = terminatedJobs;
    }
    
    public List<BaculaJob> getTerminatedJobs() {
        return terminatedJobs;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.banner);
        hash = 43 * hash + Objects.hashCode(this.upSince);
        hash = 43 * hash + this.jobsRunned;
        hash = 43 * hash + Objects.hashCode(this.scheduledJobs);
        hash = 43 * hash + Objects.hashCode(this.runningJobs);
        hash = 43 * hash + Objects.hashCode(this.terminatedJobs);
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
        final BaculaStatusDirector other = (BaculaStatusDirector) obj;
        if (!Objects.equals(this.banner, other.banner)) {
            return false;
        }
        if (!Objects.equals(this.upSince, other.upSince)) {
            return false;
        }
        if (this.jobsRunned != other.jobsRunned) {
            return false;
        }
        if (!Objects.equals(this.scheduledJobs, other.scheduledJobs)) {
            return false;
        }
        if (!Objects.equals(this.runningJobs, other.runningJobs)) {
            return false;
        }
        if (!Objects.equals(this.terminatedJobs, other.terminatedJobs)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        String output = "";
        output = banner;
        
        output +=   
        "Scheduled Jobs:\n";
        if(scheduledJobs!=null) {
            output += "Level          Type     Pri  Scheduled          Name               Volume\n" +
            "===================================================================================\n";
            for (int i = 0; i < scheduledJobs.size(); i++) {
                output += scheduledJobs.get(i).lineScheduledJobs()+"\n";
            }
        } else {
            output += "No Scheduled Jobs.\n";
        }
        output += "====\n\n";
        
        output += 
        "Running Jobs:\n";
        if(runningJobs!=null) {
            output += "JobId Level   Name                       Status\n" +
            "======================================================================\n";
            for (int i = 0; i < runningJobs.size(); i++) {
                output += runningJobs.get(i).lineRunningJobs()+"\n";
            }
        } else {
            output += "No Jobs running.\n";
        }
        output += "====\n\n";
        
        output += 
        "Terminated Jobs:\n";
        if(terminatedJobs!=null) {
            output += 
            " JobId  Level    Files      Bytes   Status   Finished        Name \n" +
            "====================================================================\n";
            for (int i = 0; i < terminatedJobs.size(); i++) {
                output += terminatedJobs.get(i).lineTerminatedJobs()+"\n";
            }
        } else {
            output += "No Terminated Jobs.\n";
        }
        output += "====\n";
        
        return output;
    }
}
