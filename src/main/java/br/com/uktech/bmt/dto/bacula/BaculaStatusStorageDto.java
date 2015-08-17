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
public class BaculaStatusStorageDto implements Serializable {
    
    private String header;
    private List<BaculaJobRunningStorageDto> runningJobs = new ArrayList<>();
    private List<BaculaJobDto> jobsWaiting = new ArrayList<>();
    private List<BaculaJobDto> terminatedJobs = new ArrayList<>();
    private List<String> devices = new ArrayList<>();
    private String volumeStatus;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<BaculaJobRunningStorageDto> getRunningJobs() {
        return runningJobs;
    }

    public void setRunningJobs(List<BaculaJobRunningStorageDto> runningJobs) {
        this.runningJobs = runningJobs;
    }

    public List<BaculaJobDto> getJobsWaiting() {
        return jobsWaiting;
    }

    public void setJobsWaiting(List<BaculaJobDto> jobsWaiting) {
        this.jobsWaiting = jobsWaiting;
    }

    public List<BaculaJobDto> getTerminatedJobs() {
        return terminatedJobs;
    }

    public void setTerminatedJobs(List<BaculaJobDto> terminatedJobs) {
        this.terminatedJobs = terminatedJobs;
    }

    public List<String> getDevices() {
        return devices;
    }

    public void setDevices(List<String> devices) {
        this.devices = devices;
    }

    public String getVolumeStatus() {
        return volumeStatus;
    }

    public void setVolumeStatus(String volumeStatus) {
        this.volumeStatus = volumeStatus;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.header);
        hash = 17 * hash + Objects.hashCode(this.runningJobs);
        hash = 17 * hash + Objects.hashCode(this.jobsWaiting);
        hash = 17 * hash + Objects.hashCode(this.terminatedJobs);
        hash = 17 * hash + Objects.hashCode(this.devices);
        hash = 17 * hash + Objects.hashCode(this.volumeStatus);
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
        final BaculaStatusStorageDto other = (BaculaStatusStorageDto) obj;
        if (!Objects.equals(this.header, other.header)) {
            return false;
        }
        if (!Objects.equals(this.runningJobs, other.runningJobs)) {
            return false;
        }
        if (!Objects.equals(this.jobsWaiting, other.jobsWaiting)) {
            return false;
        }
        if (!Objects.equals(this.terminatedJobs, other.terminatedJobs)) {
            return false;
        }
        if (!Objects.equals(this.devices, other.devices)) {
            return false;
        }
        if (!Objects.equals(this.volumeStatus, other.volumeStatus)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String str;
        str = header + "\n\nRunning Jobs:";
        if(runningJobs==null) {
            str += "\nNo Jobs running.\n";
        } else {
            str += runningJobs.toString();
        }
        str +="\n====\n";
        str += "Jobs waiting to reserve a drive:";
        if(jobsWaiting!=null) {
            str += jobsWaiting.toString();
        }
        str +="\n====\n";
        if(terminatedJobs!=null) {
            str += "Terminated Jobs:\n" +
                " JobId  Level    Files      Bytes   Status   Finished        Name \n" +
                "===================================================================";
            for (BaculaJobDto job : terminatedJobs) {
                str += "\n" + job.lineTerminatedJobs();
            }
            
        } else {
            str += "\nNo Jobs Terminated.\n";
        }
        str +="\n====\n";
        str += "Device status:\n";
        if(devices!=null) {
            str += devices.toString()+"\n";
        }
        str +="\n====\n";
        str += "Used Volume status:\n====\n";
        if(volumeStatus!=null) {
            str += volumeStatus.toString()+"\n";
        }
        str +="\n====\n";
        return str;
    }
    
}
