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
package br.com.uktech.bmt.bacula.lib.parser;

import br.com.uktech.bmt.bacula.bean.BaculaJob;
import br.com.uktech.bmt.bacula.lib.Constants;
import br.com.uktech.bmt.bacula.lib.Utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class ParseJobs {
    
    public static final String REGEX_SCHEDULED_JOB = "(Incremental|Diferencial|Full)[\\s*|\\t*]*(\\w+)[\\s*|\\t*]*(\\d+)[\\s*|\\t*]*(\\d{2}-[a-zA-Z]{3}-\\d{2} \\d{2}:\\d{2})[\\s*|\\t*]*([\\w*|-]*)[\\s{0,}|\\t{0,}]*([\\w+|\\*]+)";
    public static final String REGEX_RUNNING_JOB = "(\\d+)[\\s*|\\t*]*(Differe|Increme|Full)[\\s*|\\t*]*([\\w+|-]*.\\d{4}-\\d{2}-\\d{2}_\\d{2}.\\d{2}.\\d{2}_\\d{2})[\\s*|\\t*]*(is waiting execution\\.*|is running\\.*|is waiting for Client [\\w*|-]* to connect to Storage [\\w*|-]*\\.*)";
    public static final String REGEX_TERMINATED_JOB = "(\\d+)[\\s*|\\t*]*(Full|Incr|Diff)[\\s*|\\t*]*([\\d+|,*]*)[\\s*|\\t*]*([\\d+|\\.*]* [K|M|G|T]*)[\\s*|\\t*]*(OK|Error)[\\s*|\\t*]*(\\d{2}-[a-zA-Z]{3}-\\d{2} \\d{2}:\\d{2})[\\s*|\\t*]*(.+)";
    public static final String REGEX_PARSE_LIST_JOB = "(\\| *[\\d+|,]* *\\|.*\\|.*\\|.*\\|.*\\|.*\\| *([\\d+|,]*) *\\|.*\\|)";
    public static final String REGEX_PARSE_LLIST_JOB = "(^jobid:) *(.+)";
    public static final String REGEX_PARSE_NUMBER = " *(jobid:|clientid:|purgedfiles:|jobtdate:|volsessionid:|volsessiontime:|jobfiles:|joberrors:|jobmissingfiles:|poolid:|priorjobid:|filesetid:) *(.+)";
    public static final String REGEX_PARSE_WORD = " *(job:|name:|type:|level:|jobstatus:|[dt]time:|fileset:) +(.*)";
    
    public BaculaJob parseScheduledJob(String linha) {
        BaculaJob job = new BaculaJob();
        Pattern p = Pattern.compile(ParseJobs.REGEX_SCHEDULED_JOB);
        Matcher m = p.matcher(linha);
        while(m.find()) {
            job.setLevel(m.group(1));
            job.setType(m.group(2));
            job.setPriorjobid(Long.parseLong(m.group(3)));
            job.setScheduled(Utils.toCalendar(m.group(4)));
            job.setName(m.group(5));
            job.setVolumename(m.group(6));
            job.setTypejob("scheduled job");
        }
        return job;
    }
    
    public BaculaJob parseRunningJob(String linha) {
        BaculaJob job = new BaculaJob();
        Pattern p = Pattern.compile(ParseJobs.REGEX_RUNNING_JOB);
        Matcher m = p.matcher(linha);
        while(m.find()) {
            job.setJobid(Long.parseLong(m.group(1)));
            job.setLevel(m.group(2));
            job.setJob(m.group(3));
            job.setDirstatus(m.group(4));
            job.setTypejob("running job");
        }
        return job;
    }
    
    public BaculaJob parseTerminatedJob(String linha) {
        BaculaJob job = new BaculaJob();
        Pattern p = Pattern.compile(ParseJobs.REGEX_TERMINATED_JOB);
        Matcher m = p.matcher(linha);
        if(m.find()) {
            job.setJobid(Long.parseLong(m.group(1)));
            job.setLevel(m.group(2));
            job.setJobfiles(Integer.parseInt(m.group(3).replace(",", "")));
            //job.setBytes(m.group(4));
            job.setDirstatus(m.group(5));
            job.setRealendtime(Utils.toCalendar(m.group(6)));
            job.setName(m.group(7));
            job.setTypejob("finished job");
        }
        return job;
    }

    public void parseListJob(String receivedData, BaculaJob job) {
        String temp;
        Parser p = new Parser(receivedData);
        
        do {
            temp = p.getToken(Constants.CR);
            if(temp!=null) {
                temp = temp.trim();
                if(temp.matches(ParseJobs.REGEX_PARSE_LIST_JOB)) {
                    job.setJobbytes(parseJobbytes(temp));
                }
            }
        } while(temp != null);
    }

    public void parseLlistJob(String receivedData, BaculaJob job) {
        String temp;
        Parser p = new Parser(receivedData);
        
        do {
            temp = p.getToken(Constants.CR);
            if(temp!=null) {
                temp = temp.trim();
                if(temp.matches(ParseJobs.REGEX_PARSE_LLIST_JOB)) {
                    job.setJobid(parseNumber(temp));
                    temp = p.getToken(Constants.CR);
                    job.setJob(parseWord(temp));
                    temp = p.getToken(Constants.CR);
                    job.setName(parseWord(temp));
                    temp = p.getToken(Constants.CR);
                    job.setPurgedfiles(Integer.parseInt(""+parseNumber(temp)));
                    temp = p.getToken(Constants.CR);
                    job.setType(parseWord(temp));
                    temp = p.getToken(Constants.CR);
                    job.setLevel(parseWord(temp));
                    temp = p.getToken(Constants.CR);
                    job.setClientid(parseNumber(temp));
                    temp = p.getToken(Constants.CR);
                    job.setClientname(parseWord(temp));
                    temp = p.getToken(Constants.CR);
                    job.setJobstatus(parseWord(temp));
                    temp = p.getToken(Constants.CR);
                    job.setSchedtime(Utils.toAnotherCalendar(parseWord(temp)));
                    temp = p.getToken(Constants.CR);
                    job.setStarttime(Utils.toAnotherCalendar(parseWord(temp)));
                    temp = p.getToken(Constants.CR);
                    job.setEndtime(Utils.toAnotherCalendar(parseWord(temp)));
                    temp = p.getToken(Constants.CR);
                    job.setRealendtime(Utils.toAnotherCalendar(parseWord(temp)));
                    temp = p.getToken(Constants.CR);
                    job.setJobtdate(parseNumber(temp));
                    temp = p.getToken(Constants.CR);
                    job.setVolsessionid(parseNumber(temp));
                    temp = p.getToken(Constants.CR);
                    job.setVolsessiontime(parseNumber(temp));
                    temp = p.getToken(Constants.CR);
                    job.setJobfiles(Integer.parseInt(""+parseNumber(temp)));
                    temp = p.getToken(Constants.CR);
                    job.setJoberrors(Integer.parseInt(""+parseNumber(temp)));
                    temp = p.getToken(Constants.CR);
                    job.setJobmissingfiles(Integer.parseInt(""+parseNumber(temp)));
                    temp = p.getToken(Constants.CR);
                    job.setPoolid(parseNumber(temp));
                    temp = p.getToken(Constants.CR);
                    job.setPoolname(parseWord(temp));
                    temp = p.getToken(Constants.CR);
                    job.setPriorjobid(parseNumber(temp));
                    temp = p.getToken(Constants.CR);
                    job.setFilesetid(parseNumber(temp));
                    temp = p.getToken(Constants.CR);
                    job.setFileset(parseWord(temp));
                    temp = p.getToken(Constants.CR);
                }
            }
        } while(temp != null);
    }
    
    public Long parseJobbytes(String linha) {
        Long number = null;
        if(linha!=null) {
            Pattern p = Pattern.compile(ParseJobs.REGEX_PARSE_LIST_JOB);
            Matcher m = p.matcher(linha);
            if(m.find()) {
                number = Long.parseLong((m.group(2).replace(",", "")).replace(" ", ""));
            }
        }
        return number;
    }
    
    public Long parseNumber(String linha) {
        Long number = null;
        if(linha!=null) {
            Pattern p = Pattern.compile(ParseJobs.REGEX_PARSE_NUMBER);
            Matcher m = p.matcher(linha);
            while(m.find()) {
                number = Long.parseLong(m.group(2).replace(",", ""));
            }
        }
        return number;
    }
    
    public String parseWord(String linha) {
        String word = null;
        if(linha!=null) {
            Pattern p = Pattern.compile(ParseJobs.REGEX_PARSE_WORD);
            Matcher m = p.matcher(linha);
            while(m.find()) {
                word = m.group(2);
            }
            if(word.equals("")) {
                return null;
            }
        }
        return word;
    }
}
