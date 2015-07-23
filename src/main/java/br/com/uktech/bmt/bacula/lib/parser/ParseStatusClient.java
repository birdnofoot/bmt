package br.com.uktech.bmt.bacula.lib.parser;

import br.com.uktech.bmt.bacula.lib.Constants;
import br.com.uktech.bmt.bacula.bean.BaculaJobRunning;
import br.com.uktech.bmt.bacula.bean.BaculaStatusClient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseStatusClient {
    
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
                
                if(temp.matches(" *(.+) (Version: .+) (\\(\\d{2} \\w+ \\d{4}\\)) (.+)")) {
                    header.append(temp+"\n");
                    header.append(p.getToken(Constants.CR));
                    header.append(p.getToken(Constants.CR));
                    header.append(p.getToken(Constants.CR)).append(Constants.CR);
                    statusClient.setHeader(header.toString());
                } else if(temp.matches("^( *(\\d+) +(Full|Incr) +([^ ]+) +((\\d+(\\.\\d{1,3})?|0) *((G|M|K)*+)) +(Error|OK) +(\\d{2}-[a-zA-Z]{3}-\\d{2} \\d{2}:\\d{2}) (.+))")) {
                    statusClient.getTerminatedJobs().add(parse.parseTerminadedJob(temp));
                } else if(temp.matches(" *(JobId) +(\\d+) +(Job) +(.[^ ]+) (.+)")) {
                    BaculaJobRunning jobRunning = new BaculaJobRunning();
                    
                    Pattern pa = Pattern.compile(" *(JobId) +(\\d+) +(Job) +(.[^ ]+) (.+)");
                    Matcher m = pa.matcher(temp);
                    while(m.find()) {
                        jobRunning.setId(Integer.parseInt(m.group(2)));
                        jobRunning.setName(m.group(4));
                        jobRunning.setStatus(m.group(5));
                    }
                    
                    do{
                        temp = p.getToken(Constants.CR);
                        if(temp!=null && !temp.equals("====")) {
                            temp = temp.trim();
                            if(temp.matches(" *(\\w+) +(\\w+) Job started: (\\d{2}-[a-zA-Z]{3}-\\d{2} \\d{2}:\\d{2})")) {
                                pa = Pattern.compile(" *(\\w+) +(\\w+) Job started: (\\d{2}-[a-zA-Z]{3}-\\d{2} \\d{2}:\\d{2})");
                                m = pa.matcher(temp);
                                while(m.find()) {
                                    jobRunning.setLevel(m.group(1));
                                    jobRunning.setType(m.group(2));
                                    jobRunning.setStarted(toCalendar(m.group(3)));
                                }
                            } else if(temp.matches(" *Files=(.*) +Bytes=(.*) +Bytes/sec=(.*) Errors=(.*)")) {
                                pa = Pattern.compile(" *Files=(.*) +Bytes=(.*) +Bytes/sec=(.*) Errors=(.*)");
                                m = pa.matcher(temp);
                                while(m.find()) {
                                    jobRunning.setFiles(Integer.parseInt(m.group(1).replace(",", "")));
                                    jobRunning.setBytes(Integer.parseInt(m.group(2).replace(",", "")));
                                    jobRunning.setSpeed(Integer.parseInt(m.group(3).replace(",", "")));
                                    jobRunning.setErrors(Integer.parseInt(m.group(4).replace(",", "")));
                                }
                            } else if(temp.matches(" *Files Examined=(.*)")) {
                                pa = Pattern.compile(" *Files Examined=(.*)");
                                m = pa.matcher(temp);
                                while(m.find()) {
                                    jobRunning.setFilesExamined(Integer.parseInt(m.group(1).replace(",", "")));
                                }
                            }else if(temp.matches(" *Processing file: (.*)")) {
                                pa = Pattern.compile(" *Processing file: (.*)");
                                m = pa.matcher(temp);
                                while(m.find()) {
                                    jobRunning.setProcessingFile(m.group(1));
                                }
                            } else if(temp.matches(" *SDReadSeqNo=(\\d*) fd=(\\d*)")) {
                                pa = Pattern.compile(" *SDReadSeqNo=(\\d*) fd=(\\d*)");
                                m = pa.matcher(temp);
                                while(m.find()) {
                                    jobRunning.setSDReadSeqNo(Integer.parseInt(m.group(1)));
                                    jobRunning.setFd(Integer.parseInt(m.group(2)));
                                }
                            }
                        }
                    } while(!temp.equals("===="));
                    statusClient.getRunningJobs().add(jobRunning);
                }
            }
        } while(temp != null);
        
        return statusClient;
    }
    
    private Calendar toCalendar(String linha) {
        Calendar calendar = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.Bacula.DATE_FORMAT);
            calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(linha));
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }
        return calendar;
    }
}