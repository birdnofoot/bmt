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
import br.com.uktech.bmt.bacula.bean.BaculaJobRunningStorage;
import br.com.uktech.bmt.bacula.bean.BaculaStatusStorage;
import br.com.uktech.bmt.bacula.lib.Constants;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class ParseStatusStorage {
    
    private final Logger logger = LoggerFactory.getLogger(ParseStatusStorage.class);
    
    public static final String REGEX_JOB_RUNNING_STORAGE_LEVEL = "\\w+:[\\s|\\t]*([Full|Diff|Incr]+)[\\s|\\t]*(Backup)[\\s|\\t]*job[\\s|\\t]*([\\w|-]*)[\\s|\\t]*JobId=(\\d+)[\\s|\\t]*Volume=\"([\\w|-]*)\"";
    public static final String REGEX_JOB_RUNNING_STORAGE_POOL = "pool=(.*)[\\s|\\t]*device=(.*)";
    public static final String REGEX_JOB_RUNNING_STORAGE_SPOOLING = "spooling=([\\d|,]*)[\\s|\\t]*despooling=([\\d|,]*)[\\s|\\t]*despool_wait=([\\d|,]*)";
    public static final String REGEX_JOB_RUNNING_STORAGE_FILES = "Files=([\\d|,]*)[\\s|\\t]*Bytes=([\\d|,]*)[\\s|\\t]*Bytes/sec=([\\d|,]*)";
    public static final String REGEX_JOB_RUNNING_STORAGE_FDREADSEQNO = "FDReadSeqNo=([\\d|,]*)[\\s|\\t]*in_msg=([\\d|,]*)[\\s|\\t]*out_msg=([\\d|,]*)[\\s|\\t]*fd=([\\d|,]*)";
    public static final String REGEX_JOB_RUNNING_STORAGE_DEVICE = "(Device[\\s|\\t]*\".*)";
    
    public BaculaStatusStorage parse(String input) {
        this.logger.trace("Mensagem Recebiga: {}", input);
        BaculaStatusStorage statusStorage = new BaculaStatusStorage();
        ParseJobs parse = new ParseJobs();
        StringBuffer header = new StringBuffer();
        String temp;
        Parser p = new Parser(input);
        do{
            temp = p.getToken(Constants.CR);
            if (temp != null)
            {
                temp = temp.trim();
                this.logger.trace("Processando Linha: {}", temp);
                if(temp.matches(ParseStatusClient.REGEX_JOB_RUNNING_CLIENT_HEADER)){
                    header.append(temp+"\n");
                    header.append(p.getToken(Constants.CR));
                    header.append(p.getToken(Constants.CR));
                    header.append(p.getToken(Constants.CR)).append(Constants.CR);
                    statusStorage.setHeader(header.toString());
                } else if(temp.matches(ParseStatusStorage.REGEX_JOB_RUNNING_STORAGE_LEVEL)) {
                    BaculaJobRunningStorage jobRunning = new BaculaJobRunningStorage();
                    Pattern pa = Pattern.compile(ParseStatusStorage.REGEX_JOB_RUNNING_STORAGE_LEVEL);
                    Matcher m = pa.matcher(temp);
                    if(m.find()) {
                        jobRunning.setLevel(m.group(1));
                        jobRunning.setType(m.group(2));
                        jobRunning.setName(m.group(3));
                        jobRunning.setJobid(Long.parseLong(m.group(4)));
                        jobRunning.setVolume(m.group(5));
                    }
                    do{
                        temp = p.getToken(Constants.CR);
                        if(temp!=null && !temp.equals("====")) {
                            temp = temp.trim();
                            if(temp.matches(ParseStatusStorage.REGEX_JOB_RUNNING_STORAGE_POOL)) {
                                pa = Pattern.compile(ParseStatusStorage.REGEX_JOB_RUNNING_STORAGE_POOL);
                                m = pa.matcher(temp);
                                if(m.find()) {
                                    jobRunning.setPool(m.group(1));
                                    jobRunning.setDevice(m.group(2));
                                }
                            }
                        } else if(temp.matches(ParseStatusStorage.REGEX_JOB_RUNNING_STORAGE_SPOOLING)) {
                            pa = Pattern.compile(ParseStatusStorage.REGEX_JOB_RUNNING_STORAGE_SPOOLING);
                            m = pa.matcher(temp);
                            if(m.find()) {
                                jobRunning.setSpooling(Long.parseLong(m.group(1)));
                                jobRunning.setDespooling(Long.parseLong(m.group(2)));
                                jobRunning.setDespoolWait(Long.parseLong(m.group(3)));
                            }
                        } else if(temp.matches(ParseStatusStorage.REGEX_JOB_RUNNING_STORAGE_FILES)) {
                            pa = Pattern.compile(ParseStatusStorage.REGEX_JOB_RUNNING_STORAGE_FILES);
                            m = pa.matcher(temp);
                            if(m.find()) {
                                jobRunning.setJobfiles(Long.parseLong(m.group(1)));
                                jobRunning.setJobbytes(Long.parseLong(m.group(2)));
                                jobRunning.setRate(Long.parseLong(m.group(3)));
                            }
                        } else if(temp.matches(ParseStatusStorage.REGEX_JOB_RUNNING_STORAGE_FDREADSEQNO)) {
                            pa = Pattern.compile(ParseStatusStorage.REGEX_JOB_RUNNING_STORAGE_FDREADSEQNO);
                            m = pa.matcher(temp);
                            if(m.find()) {
                                jobRunning.setFDReadSeqNo(Long.parseLong(m.group(1)));
                                jobRunning.setInMsg(Long.parseLong(m.group(2)));
                                jobRunning.setOutMsg(Long.parseLong(m.group(3)));
                                jobRunning.setFd(Integer.parseInt(m.group(4)));
                            }
                        }
                        
                    } while(!temp.equals("===="));
                    statusStorage.getRunningJobs().add(jobRunning);
                } else if(temp.equalsIgnoreCase("Jobs waiting to reserve a drive:")) {
                    BaculaJob job = new BaculaJob();
                    job.setJobid(5000l);
                    statusStorage.getJobsWaiting().add(job);
                    //Jobs waiting to reserve a drive:
                    //====
                } else if(temp.matches(ParseJobs.REGEX_TERMINATED_JOB)) {
                    statusStorage.getTerminatedJobs().add(parse.parseTerminatedJob(temp));
                } else if(temp.matches(ParseStatusStorage.REGEX_JOB_RUNNING_STORAGE_DEVICE)) {
                    Pattern pa = Pattern.compile(ParseStatusStorage.REGEX_JOB_RUNNING_STORAGE_DEVICE);
                    Matcher m = pa.matcher(temp);
                    if(m.find()) {
                        statusStorage.getDevices().add(temp);
                    }
                } else if(temp.equalsIgnoreCase("Used Volume status:")) {
                    statusStorage.setVolumeStatus("");
                }
            }
        } while(temp != null);
        return statusStorage;
    }
}
