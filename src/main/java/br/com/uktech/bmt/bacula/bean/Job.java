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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class Job {
    
    private int id;
    private String name;
    private String level;
    private String type;
    private int priority;
    private String volume;
    private int files;
    private String bytes;
    private String status;
    private Calendar finished;
    private Calendar scheduled;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * @return the volume
     */
    public String getVolume() {
        return volume;
    }

    /**
     * @param volume the volume to set
     */
    public void setVolume(String volume) {
        this.volume = volume;
    }

    /**
     * @return the files
     */
    public int getFiles() {
        return files;
    }

    /**
     * @param files the files to set
     */
    public void setFiles(int files) {
        this.files = files;
    }

    /**
     * @return the bytes
     */
    public String getBytes() {
        return bytes;
    }

    /**
     * @param bytes the bytes to set
     */
    public void setBytes(String bytes) {
        this.bytes = bytes;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the finished
     */
    public Calendar getFinished() {
        return finished;
    }

    /**
     * @param finished the finished to set
     */
    public void setFinished(Calendar finished) {
        this.finished = finished;
    }

    /**
     * @return the scheduled
     */
    public Calendar getScheduled() {
        return scheduled;
    }

    /**
     * @param scheduled the scheduled to set
     */
    public void setScheduled(Calendar scheduled) {
        this.scheduled = scheduled;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.level);
        hash = 29 * hash + Objects.hashCode(this.type);
        hash = 29 * hash + this.priority;
        hash = 29 * hash + Objects.hashCode(this.volume);
        hash = 29 * hash + this.files;
        hash = 29 * hash + Objects.hashCode(this.bytes);
        hash = 29 * hash + Objects.hashCode(this.status);
        hash = 29 * hash + Objects.hashCode(this.finished);
        hash = 29 * hash + Objects.hashCode(this.scheduled);
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
        final Job other = (Job) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.level, other.level)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (this.priority != other.priority) {
            return false;
        }
        if (!Objects.equals(this.volume, other.volume)) {
            return false;
        }
        if (this.files != other.files) {
            return false;
        }
        if (!Objects.equals(this.bytes, other.bytes)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.finished, other.finished)) {
            return false;
        }
        if (!Objects.equals(this.scheduled, other.scheduled)) {
            return false;
        }
        return true;
    }
    
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("id: [").append(id).append("]\n")
        .append("name: [").append(name).append("]\n")
        .append("level: [").append(level).append("]\n")
        .append("type: [").append(type).append("]\n")
        .append("priority: [").append(priority).append("]\n")
        .append("volume: [").append(volume).append("]\n")
        .append("files: [").append(files).append("]\n")
        .append("bytes: [").append(bytes).append("]\n")
        .append("status: [").append(status).append("]\n")
        .append("finished: [").append(calendarToString(finished)).append("]\n")
        .append("scheduled: [").append(calendarToString(scheduled)).append("]\n");
        return s.toString();
    }
    
    public String calendarToString(Calendar calendar) {
        if(calendar!=null) {
            Date date = calendar.getTime();
            SimpleDateFormat format = new SimpleDateFormat(Constants.Bacula.DATE_FORMAT);
            return format.format(date);
        }
        return null;
    }
    
    public String lineScheduledJobs() {
        String s = new String();
        s = level+"\t"+type+"\t"+priority+"\t"+calendarToString(scheduled)+"\t"+name+"\t"+volume;
        return s;
    }
    
    public String lineRunningJobs() {
        String s = new String();
        s = id+"\t"+level+"\t"+name+"\t"+status;
        return s;
    }
    
    public String lineTerminatedJobs() {
        String s = new String();
        s = id+"\t"+level+"\t"+files+"\t"+bytes+"\t"+status+"\t"+calendarToString(finished)+"\t"+name;
        return s;
    }
}
