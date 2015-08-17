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
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class BaculaStatusClient implements Serializable {
    
    private String header;
    private List<BaculaJobRunningClient> runningJobs = new ArrayList<>();
    private List<BaculaJob> terminatedJobs = new ArrayList<>();

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<BaculaJobRunningClient> getRunningJobs() {
        return runningJobs;
    }

    public void setRunningJobs(List<BaculaJobRunningClient> runningJobs) {
        this.runningJobs = runningJobs;
    }

    public List<BaculaJob> getTerminatedJobs() {
        return terminatedJobs;
    }

    public void setTerminatedJobs(List<BaculaJob> terminatedJobs) {
        this.terminatedJobs = terminatedJobs;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.header);
        hash = 29 * hash + Objects.hashCode(this.runningJobs);
        hash = 29 * hash + Objects.hashCode(this.terminatedJobs);
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
        final BaculaStatusClient other = (BaculaStatusClient) obj;
        if (!Objects.equals(this.header, other.header)) {
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
        "Running Jobs:\n";
        if(runningJobs!=null) {
            for (int i = 0; i < runningJobs.size(); i++) {
                output += runningJobs.get(i).toString()+"\n";
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
