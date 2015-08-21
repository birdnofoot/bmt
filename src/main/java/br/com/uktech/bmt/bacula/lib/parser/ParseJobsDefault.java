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

import br.com.uktech.bmt.bacula.bean.BaculaJobDefault;
import br.com.uktech.bmt.bacula.lib.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class ParseJobsDefault {
    
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ParseJobsDefault.class);
    
    public static final String REGEX_JOB_DEFAULT = "job=(.*)pool=(.*)\\w{4,5}messages=(.*)client=(.*)storage=(.*)where=(.*)level=(.*)type=(.*)fileset=(.*)enabled=(.*)catalog=(.*)\\.*";
    
    public List<BaculaJobDefault> setNames(String input) {
        this.logger.trace("Mensagem recebida: {}", input);
        List<BaculaJobDefault> jobsDefault = new ArrayList<>();
        BaculaJobDefault job = null;
        Parser p = new Parser(input);
        String temp = null;
        do {
            temp = p.getToken(Constants.CR);
            //temp = temp.replace(Constants.CR, "");
            this.logger.trace("Processando: {}", temp);
            if (temp != null) {
                //temp.trim();
                //if (temp.matches(".*")) {
                    job = new BaculaJobDefault();
                    job.setJob(temp);
                    jobsDefault.add(job);
                //} else {
                //    this.logger.error("Token inválido: {}", temp);
                //}
            }
        } while(temp!=null);
        return jobsDefault;
    }
    
    public BaculaJobDefault detailJobDefault(String input) {
        this.logger.trace("Mensagem recebida: {}", input);
        BaculaJobDefault jobDefault = null;
        Parser p = new Parser(input);
        String temp = null;
        do {
            temp = p.getToken(Constants.CR);
            if (temp != null) {
                temp.trim();
                this.logger.trace("Processando Linha: {}", temp);
                if(temp.matches(ParseJobsDefault.REGEX_JOB_DEFAULT)) {
                    this.logger.info("Entra o Parse");
                    Pattern pa = Pattern.compile(ParseJobsDefault.REGEX_JOB_DEFAULT);
                    Matcher m = pa.matcher(temp);
                    if(m.find()) {
                        jobDefault = new BaculaJobDefault();
                        jobDefault.setJob(m.group(1));
                        jobDefault.setPool(m.group(2));
                        jobDefault.setPoolmessages(m.group(3));
                        jobDefault.setClient(m.group(4));
                        jobDefault.setStorage(m.group(5));
                        jobDefault.setWhere(m.group(6));
                        jobDefault.setLevel(m.group(7));
                        jobDefault.setType(m.group(8));
                        jobDefault.setFileset(m.group(9));
                        jobDefault.setEnabled(m.group(10).contains("1"));
                        jobDefault.setCatalog(m.group(11));
                    }
                }
            }
        } while(temp!=null);
        return jobDefault;
    }
    
}
