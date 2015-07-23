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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class ParseJobs {
    
    public BaculaJob parseRunningJob(String linha) {
        BaculaJob job = new BaculaJob();
        Pattern p = Pattern.compile("^( *(\\d+) +(Full|Incr) +(.[^ ]+) (.+))");
        Matcher m = p.matcher(linha);
        while(m.find()) {
            job.setId(Integer.parseInt(m.group(2)));
            job.setLevel(m.group(3));
            job.setName(m.group(4));
            job.setStatus(m.group(5));
        }
        return job;
    }
    
    public BaculaJob parseTerminadedJob(String linha) {
        BaculaJob job = new BaculaJob();
        Pattern p = Pattern.compile("^( *(\\d+) +(Full|Incr) +([^ ]+) +((\\d+(\\.\\d{1,3})?|0) *((G|M|K)*+)) +(Error|OK) +(\\d{2}-[a-zA-Z]{3}-\\d{2} \\d{2}:\\d{2}) (.+))");
        Matcher m = p.matcher(linha);
        while(m.find()) {
            job.setId(Integer.parseInt(m.group(2)));
            job.setLevel(m.group(3));
            job.setFiles(Integer.parseInt(m.group(4).replace(",", "")));
            job.setBytes(m.group(5));
            job.setStatus(m.group(10));
            job.setFinished(convertToCalendar(m.group(11)));
            job.setName(m.group(12));
        }
        return job;
    }
    
    public BaculaJob parseScheduledJob(String linha) {
        BaculaJob job = new BaculaJob();
        Pattern p = Pattern.compile("^( *(Full|Incremental|Differential) +(\\w+) +(\\d+) +(\\d{2}-[a-zA-Z]{3}-\\d{2} \\d{2}:\\d{2}) +(.+[^ ]) +(.+))");
        Matcher m = p.matcher(linha);
        while(m.find()) {
            job.setLevel(m.group(2));
            job.setType(m.group(3));
            job.setPriority(Integer.parseInt(m.group(4)));
            job.setScheduled(convertToCalendar(m.group(5)));
            job.setName(m.group(6));
            job.setVolume(m.group(7));
        }
        return job;
    }
    
    private Calendar convertToCalendar(String linha) {
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
