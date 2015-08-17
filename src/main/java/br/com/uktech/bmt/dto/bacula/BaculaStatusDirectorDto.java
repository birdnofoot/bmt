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
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class BaculaStatusDirectorDto implements Serializable {
    
    private String header;
    private Calendar upSince;
    private Integer jobsRunned;
    private List<BaculaJobDto> scheduledJobs = new ArrayList<>();
    private List<BaculaJobDto> runningJobs = new ArrayList<>();
    private List<BaculaJobDto> terminatedJobs = new ArrayList<>();

    /**
     * @return the header
     */
    public String getHeader() {
        return header;
    }

    /**
     * @param header the header to set
     */
    public void setHeader(String header) {
        this.header = header;
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
    public Integer getJobsRunned() {
        return jobsRunned;
    }

    /**
     * @param jobsRunned the jobsRunned to set
     */
    public void setJobsRunned(Integer jobsRunned) {
        this.jobsRunned = jobsRunned;
    }

    /**
     * @return the scheduledJobs
     */
    public List<BaculaJobDto> getScheduledJobs() {
        return scheduledJobs;
    }

    /**
     * @return the runningJobs
     */
    public List<BaculaJobDto> getRunningJobs() {
        return runningJobs;
    }

    /**
     * @return the terminatedJobs
     */
    public List<BaculaJobDto> getTerminatedJobs() {
        return terminatedJobs;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.header);
        hash = 43 * hash + Objects.hashCode(this.upSince);
        hash = 43 * hash + Objects.hashCode(this.jobsRunned);
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
        final BaculaStatusDirectorDto other = (BaculaStatusDirectorDto) obj;
        if (!Objects.equals(this.header, other.header)) {
            return false;
        }
        if (!Objects.equals(this.upSince, other.upSince)) {
            return false;
        }
        if (!Objects.equals(this.jobsRunned, other.jobsRunned)) {
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
        output = header;
        
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