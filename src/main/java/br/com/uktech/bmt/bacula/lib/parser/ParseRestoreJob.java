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

import br.com.uktech.bmt.bacula.bean.BaculaRestoreJob;
import br.com.uktech.bmt.bacula.lib.Constants;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class ParseRestoreJob {
    
    private final Logger logger = LoggerFactory.getLogger(ParseRestoreJob.class);
    
    public static final String REGEX_RESTORE_JOB_LIST = "^\\w*\\s{8,}\\w*\\s{8,}\\w*$";
    public static final String REGEX_RESTORE_JOB_FILE_NUMBERS = "(\\d*)\\sfiles\\sselected\\sto\\sbe\\srestored\\.";
    public static final String REGEX_RESTORE_JOB_JOB_QUEUED = "Job\\squeued\\.\\sJobId=(\\d*)";
    
    public BaculaRestoreJob parse(String input) {
        
        this.logger.trace("Mensagem recebida: {}", input);
        
        BaculaRestoreJob baculaRestoreJob = new BaculaRestoreJob();
        
        Parser p = new Parser(input);
        Pattern pat = null;
        Matcher mat = null;
        String temp = null;
        
        do{
            temp = p.getToken(Constants.CR);
            if(temp != null) {
                temp = temp.trim();
                this.logger.trace("Processando Linha: {}", temp);
                
                if(temp.matches(ParseRestoreJob.REGEX_RESTORE_JOB_LIST)) {
                    pat = Pattern.compile(ParseRestoreJob.REGEX_RESTORE_JOB_LIST);
                    mat = pat.matcher(temp);
                    if(mat.find()) {
                        baculaRestoreJob.getVolumes().add(mat.group(1));
                        baculaRestoreJob.getStorages().add(mat.group(2));
                        baculaRestoreJob.getSdDevices().add(mat.group(3));
                    }
                } else if(temp.matches(ParseRestoreJob.REGEX_RESTORE_JOB_FILE_NUMBERS)) {
                    pat = Pattern.compile(ParseRestoreJob.REGEX_RESTORE_JOB_FILE_NUMBERS);
                    mat = pat.matcher(temp);
                    if(mat.find()) {
                        baculaRestoreJob.setFileNumbers(Long.parseLong(mat.group(1)));
                    }
                } else if(temp.matches(ParseRestoreJob.REGEX_RESTORE_JOB_JOB_QUEUED)) {
                    pat = Pattern.compile(ParseRestoreJob.REGEX_RESTORE_JOB_JOB_QUEUED);
                    mat = pat.matcher(temp);
                    if(mat.find()) {
                        baculaRestoreJob.setJobQueued(Long.parseLong(mat.group(1)));
                    }
                }
            }
        } while(temp != null);
        
        return baculaRestoreJob;
    }
}

