package br.com.uktech.bmt.bacula.bean;

import br.com.uktech.bmt.bacula.lib.Constants;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class BaculaJobRunning {
    
    private int id;
    private String name;
    private String status;
    private String level;
    private String type;
    private Calendar started;
    private int files;
    private int bytes;
    private int speed;
    private int errors;
    private int filesExamined;
    private String processingFile;
    private int SDReadSeqNo;
    private int fd;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Calendar getStarted() {
        return started;
    }

    public void setStarted(Calendar started) {
        this.started = started;
    }

    public int getFiles() {
        return files;
    }

    public void setFiles(int files) {
        this.files = files;
    }

    public int getBytes() {
        return bytes;
    }

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getErrors() {
        return errors;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

    public int getFilesExamined() {
        return filesExamined;
    }

    public void setFilesExamined(int filesExamined) {
        this.filesExamined = filesExamined;
    }

    public String getProcessingFile() {
        return processingFile;
    }

    public void setProcessingFile(String processingFile) {
        this.processingFile = processingFile;
    }

    public int getSDReadSeqNo() {
        return SDReadSeqNo;
    }

    public void setSDReadSeqNo(int SDReadSeqNo) {
        this.SDReadSeqNo = SDReadSeqNo;
    }

    public int getFd() {
        return fd;
    }

    public void setFd(int fd) {
        this.fd = fd;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.status);
        hash = 37 * hash + Objects.hashCode(this.level);
        hash = 37 * hash + Objects.hashCode(this.type);
        hash = 37 * hash + Objects.hashCode(this.started);
        hash = 37 * hash + this.files;
        hash = 37 * hash + this.bytes;
        hash = 37 * hash + this.speed;
        hash = 37 * hash + this.errors;
        hash = 37 * hash + this.filesExamined;
        hash = 37 * hash + Objects.hashCode(this.processingFile);
        hash = 37 * hash + this.SDReadSeqNo;
        hash = 37 * hash + this.fd;
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
        final BaculaJobRunning other = (BaculaJobRunning) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.level, other.level)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.started, other.started)) {
            return false;
        }
        if (this.files != other.files) {
            return false;
        }
        if (this.bytes != other.bytes) {
            return false;
        }
        if (this.speed != other.speed) {
            return false;
        }
        if (this.errors != other.errors) {
            return false;
        }
        if (this.filesExamined != other.filesExamined) {
            return false;
        }
        if (!Objects.equals(this.processingFile, other.processingFile)) {
            return false;
        }
        if (this.SDReadSeqNo != other.SDReadSeqNo) {
            return false;
        }
        if (this.fd != other.fd) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String str = new String();
        str = "JobId "+id+" Job "+name+" "+status+"\n"+
        level+" "+type+" Job "+calendarToString(started)+"\n"+
        "Files="+files+" Bytes="+bytes+" Bytes/sec="+speed+" Errors="+errors+"\n"+
        "Files Examined="+filesExamined+"\n"+
        "Processing file: "+processingFile+"\n"+
        "SDReadSeqNo="+SDReadSeqNo+" fd="+fd;
        return str;
    }
    
    public String calendarToString(Calendar calendar) {
        if(calendar!=null) {
            Date date = calendar.getTime();
            SimpleDateFormat format = new SimpleDateFormat(Constants.Bacula.DATE_FORMAT);
            return format.format(date);
        }
        return null;
    }
    
    
    
}
