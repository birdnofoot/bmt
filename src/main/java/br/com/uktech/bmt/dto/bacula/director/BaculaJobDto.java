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
package br.com.uktech.bmt.dto.bacula.director;

import br.com.uktech.bmt.bacula.lib.Constants;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class BaculaJobDto {
    
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public int getFiles() {
        return files;
    }

    public void setFiles(int files) {
        this.files = files;
    }

    public String getBytes() {
        return bytes;
    }

    public void setBytes(String bytes) {
        this.bytes = bytes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Calendar getFinished() {
        return finished;
    }

    public void setFinished(Calendar finished) {
        this.finished = finished;
    }

    public Calendar getScheduled() {
        return scheduled;
    }

    public void setScheduled(Calendar scheduled) {
        this.scheduled = scheduled;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.level);
        hash = 79 * hash + Objects.hashCode(this.type);
        hash = 79 * hash + this.priority;
        hash = 79 * hash + Objects.hashCode(this.volume);
        hash = 79 * hash + this.files;
        hash = 79 * hash + Objects.hashCode(this.bytes);
        hash = 79 * hash + Objects.hashCode(this.status);
        hash = 79 * hash + Objects.hashCode(this.finished);
        hash = 79 * hash + Objects.hashCode(this.scheduled);
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
        final BaculaJobDto other = (BaculaJobDto) obj;
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
