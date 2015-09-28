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

package br.com.uktech.bmt.bacula.lib.parser.sql;

import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlClient;
import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlFileSet;
import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlJob;
import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlPool;
import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlPriorJob;
import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlVolSession;
import br.com.uktech.bmt.bacula.lib.Constants;
import br.com.uktech.bmt.bacula.lib.Utils;
import br.com.uktech.bmt.bacula.lib.parser.Parser;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class ParseSqlJob {
    
    private final Logger logger = LoggerFactory.getLogger(ParseSqlJob.class);
    
    public static final String REGEX_SQL_JOB_ID = "(\\d*)\\t([\\w|\\-|\\.]*)\\t([\\w|\\-|\\.]*)\\t(\\w*)\\t(\\w*)\\t(\\w*)\\t(\\w*)\\t([\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}\\:\\d{2}\\:\\d{2}]*)\\t([\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}\\:\\d{2}\\:\\d{2}]*)\\t([\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}\\:\\d{2}\\:\\d{2}]*)\\t([\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}\\:\\d{2}\\:\\d{2}]*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\.*)\\t";
    
    public BaculaSqlJob parseJob(String input) {
        return parseJob(input, new BaculaSqlJob());
    }
    
    public BaculaSqlJob parseJob(String input, BaculaSqlJob job) {
        
        this.logger.trace("Mensagem recebida: {}", input);
        
        job.setId(null);
        
        BaculaSqlClient client = new BaculaSqlClient();
        BaculaSqlVolSession volsession = new BaculaSqlVolSession();
        BaculaSqlPool pool = new BaculaSqlPool();
        BaculaSqlFileSet fileset = new BaculaSqlFileSet();
        BaculaSqlPriorJob priorjob = new BaculaSqlPriorJob();
        
        Parser p = new Parser(input);
        Pattern pat = null;
        Matcher mat = null;
        String temp = null;
        
        do{
            temp = p.getToken(Constants.CR);
            if(temp != null) {
                this.logger.trace("Processando Linha: {}", temp);
                if(temp.matches(ParseSqlJob.REGEX_SQL_JOB_ID)) {
                    pat = Pattern.compile(ParseSqlJob.REGEX_SQL_JOB_ID);
                    mat = pat.matcher(temp);
                    if(mat.find()) {
                        job.setId(Long.parseLong(mat.group(1)));
                        job.setJob(mat.group(2));
                        job.setName(mat.group(3));
                        job.setType(mat.group(4));
                        job.setLevel(mat.group(5));
                        client.setId(Long.parseLong(mat.group(6)));
                        logger.trace("Cliente: " + client.toString());
                        job.setClient(client);
                        job.setJobstatus(mat.group(7));
                        job.setSchedtime(Utils.toAnotherCalendar(mat.group(8)).getTime());
                        job.setStarttime(Utils.toAnotherCalendar(mat.group(9)).getTime());
                        if(!mat.group(10).trim().isEmpty()) {
                            job.setEndtime(Utils.toAnotherCalendar(mat.group(10)).getTime());
                        }
                        if(!mat.group(11).trim().isEmpty()) {
                            job.setRealendtime(Utils.toAnotherCalendar(mat.group(11)).getTime());
                        }
                        job.setJobtdate(Long.parseLong(mat.group(12)));
                        volsession.setId(Long.parseLong(mat.group(13)));
                        job.setVolsession(volsession);
                        job.setVolsessiontime(Integer.parseInt(mat.group(14)));
                        job.setJobfiles(Integer.parseInt(mat.group(15)));
                        job.setJobbytes(Long.parseLong(mat.group(16)));
                        job.setReadbytes(Long.parseLong(mat.group(17)));
                        job.setJoberrors(Integer.parseInt(mat.group(18)));
                        job.setJobmissingfiles(Integer.parseInt(mat.group(19)));
                        pool.setId(Long.parseLong(mat.group(20)));
                        job.setPool(pool);
                        fileset.setId(Long.parseLong(mat.group(21)));
                        job.setFileset(fileset);
                        priorjob.setId(Long.parseLong(mat.group(22)));
                        job.setPriorjob(priorjob);
                        job.setPurgedfiles(Integer.parseInt(mat.group(23)));
                        job.setHasbase(Integer.parseInt(mat.group(24)));
                        job.setHascache(Integer.parseInt(mat.group(25)));
                        job.setReviewed(Integer.parseInt(mat.group(26)));
                        job.setComment(mat.group(27));
                    }
                }
            }
        } while(temp != null);
        return job;
    }
    
    public List<Long> parseId(String input) {
        this.logger.trace("Mensagem recebida: {}", input);
        
        List<Long> id = new ArrayList<>();
        
        Parser p = new Parser(input.replaceAll(";", "\n"));
        Pattern pat = null;
        Matcher mat = null;
        String temp = null;
        
        do{
            temp = p.getToken(Constants.CR);
            if(temp != null) {
                temp = temp.trim();
                this.logger.trace("Processando Linha: {}", temp);
                if(temp.matches(ParseSqlClient.REGEX_SQL_ID)) {
                    pat = Pattern.compile(ParseSqlClient.REGEX_SQL_ID);
                    mat = pat.matcher(temp);
                    if(mat.find()) {
                        id.add(Long.parseLong(mat.group(1)));
                    }
                }
            }
        } while(temp != null);
        
        return id;
    }
    
}
