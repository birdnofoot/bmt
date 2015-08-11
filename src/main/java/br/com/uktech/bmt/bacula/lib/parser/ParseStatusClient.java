package br.com.uktech.bmt.bacula.lib.parser;

import br.com.uktech.bmt.bacula.lib.Constants;
import br.com.uktech.bmt.bacula.bean.BaculaJobRunningClient;
import br.com.uktech.bmt.bacula.bean.BaculaStatusClient;
import br.com.uktech.bmt.bacula.lib.Utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseStatusClient {
    
    public static final String REGEX_JOB_RUNNING_CLIENT_HEADER = "[\\s*|\\t*]*([\\w*|-]*)[\\s*|\\t*]*(Version: [\\.|\\d]*)[\\s*|\\t*]*(\\(\\d{2} \\w+ \\d{4}\\))[\\s*|\\t*]*(.+)";
    public static final String REGEX_JOB_RUNNING_CLIENT_ID = "JobId[\\s*|\\t*]*(\\d+)[\\s*|\\t*]*Job[\\s*|\\t*]*(.[^ ]+)[\\s*|\\t*]*(is waiting execution\\.*|is running\\.*|is waiting for Client [\\w*|-]* to connect to Storage [\\w*|-]*\\.*)";
    public static final String REGEX_JOB_RUNNING_CLIENT_LEVEL = "([Full|Diff|Incr]+)[\\s*|\\t*]*([Backup|System or Console]*)[\\s*|\\t*]*Job started:[\\s*|\\t*]*(\\d{2}-[a-zA-Z]{3}-\\d{2} \\d{2}:\\d{2})";
    public static final String REGEX_JOB_RUNNING_CLIENT_FILES = "Files=([\\d|,]*)[\\s*|\\t*]*Bytes=([\\d|,]*)[\\s*|\\t*]*Bytes/sec=([\\d|,]*)[\\s*|\\t*]*Errors=([\\d|,]*)";
    public static final String REGEX_JOB_RUNNING_CLIENT_FILES_EXAMINED = "Files Examined=([\\d|,]*)";
    public static final String REGEX_JOB_RUNNING_CLIENT_PROCESSING_FILES = "Processing file: (.*)";
    public static final String REGEX_JOB_RUNNING_CLIENT_SDREADSEQNO = "SDReadSeqNo=([\\d|,]*)[\\s|\\t]*fd=([\\d|,]*)";
    
    public BaculaStatusClient parse(String input) {
        BaculaStatusClient statusClient = new BaculaStatusClient();
        ParseJobs parse = new ParseJobs();
        StringBuffer header = new StringBuffer();
        String temp;
        Parser p = new Parser(input);
        do{
            temp = p.getToken(Constants.CR);
            if (temp != null)
            {
                temp = temp.trim();
                if(temp.matches(ParseStatusClient.REGEX_JOB_RUNNING_CLIENT_HEADER)) {
                    header.append(temp+"\n");
                    header.append(p.getToken(Constants.CR));
                    header.append(p.getToken(Constants.CR));
                    header.append(p.getToken(Constants.CR)).append(Constants.CR);
                    statusClient.setHeader(header.toString());
                } else if(temp.matches(ParseStatusClient.REGEX_JOB_RUNNING_CLIENT_ID)) {
                    BaculaJobRunningClient jobRunning = new BaculaJobRunningClient();
                    Pattern pa = Pattern.compile(ParseStatusClient.REGEX_JOB_RUNNING_CLIENT_ID);
                    Matcher m = pa.matcher(temp);
                    if(m.find()) {
                        jobRunning.setJobid(Long.parseLong(m.group(1)));
                        jobRunning.setJob(m.group(2));
                        jobRunning.setStatus(m.group(3));
                        System.err.println("Fez o regex Id");
                    }
                    
                    do{
                        temp = p.getToken(Constants.CR);
                        if(temp!=null && !temp.equals("====")) {
                            temp = temp.trim();
                            if(temp.matches(ParseStatusClient.REGEX_JOB_RUNNING_CLIENT_LEVEL)) {
                                pa = Pattern.compile(ParseStatusClient.REGEX_JOB_RUNNING_CLIENT_LEVEL);
                                m = pa.matcher(temp);
                                if(m.find()) {
                                    jobRunning.setLevel(m.group(1));
                                    jobRunning.setType(m.group(2));
                                    jobRunning.setStarttime(Utils.toCalendar(m.group(3)));
                                }
                            } else if(temp.matches(ParseStatusClient.REGEX_JOB_RUNNING_CLIENT_FILES)) {
                                pa = Pattern.compile(ParseStatusClient.REGEX_JOB_RUNNING_CLIENT_FILES);
                                m = pa.matcher(temp);
                                if(m.find()) {
                                    jobRunning.setJobfiles(Long.parseLong(m.group(1).replace(",", "")));
                                    jobRunning.setJobbytes(Long.parseLong(m.group(2).replace(",", "")));
                                    jobRunning.setRate(Long.parseLong(m.group(3).replace(",", "")));
                                    jobRunning.setJoberrors(Integer.parseInt(m.group(4).replace(",", "")));
                                }
                            } else if(temp.matches(ParseStatusClient.REGEX_JOB_RUNNING_CLIENT_FILES_EXAMINED)) {
                                pa = Pattern.compile(ParseStatusClient.REGEX_JOB_RUNNING_CLIENT_FILES_EXAMINED);
                                m = pa.matcher(temp);
                                if(m.find()) {
                                    jobRunning.setFilesExamined(Long.parseLong(m.group(1).replace(",", "")));
                                }
                            }else if(temp.matches(ParseStatusClient.REGEX_JOB_RUNNING_CLIENT_PROCESSING_FILES)) {
                                pa = Pattern.compile(ParseStatusClient.REGEX_JOB_RUNNING_CLIENT_PROCESSING_FILES);
                                m = pa.matcher(temp);
                                if(m.find()) {
                                    jobRunning.setProcessingFile(m.group(1));
                                }
                            } else if(temp.matches(ParseStatusClient.REGEX_JOB_RUNNING_CLIENT_SDREADSEQNO)) {
                                pa = Pattern.compile(ParseStatusClient.REGEX_JOB_RUNNING_CLIENT_SDREADSEQNO);
                                m = pa.matcher(temp);
                                if(m.find()) {
                                    jobRunning.setSDReadSeqNo(Integer.parseInt(m.group(1)));
                                    jobRunning.setFd(Integer.parseInt(m.group(2)));
                                }
                            }
                        }
                    } while(!temp.equals("===="));
                    statusClient.getRunningJobs().add(jobRunning);
                } if(temp.matches(ParseJobs.REGEX_TERMINATED_JOB)) {
                    statusClient.getTerminatedJobs().add(parse.parseTerminatedJob(temp));
                }
            }
        } while(temp != null);
        return statusClient;
    }
}