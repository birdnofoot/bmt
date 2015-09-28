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

import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlJobToRestore;
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
public class ParseSqlJobToRestore {
    
    private final Logger logger = LoggerFactory.getLogger(ParseSqlJobToRestore.class);
    
    public static final String REGEX_SQL_JOB = "(\\d*)\\t(\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}\\:\\d{2}\\:\\d{2})\\t(\\w*)\\t([\\w|\\-]*)";
    
    public List<BaculaSqlJobToRestore> parseJobs(String input) {
        
        this.logger.trace("Mensagem recebida: {}", input);
        
        List<BaculaSqlJobToRestore> jobs = new ArrayList<>();
        BaculaSqlJobToRestore job = new BaculaSqlJobToRestore();
        
        Parser p = new Parser(input.replaceAll("###", "\n"));
        Pattern pat = null;
        Matcher mat = null;
        String temp = null;
        
        do{
            temp = p.getToken(Constants.CR);
            if(temp != null) {
                temp = temp.trim();
                this.logger.trace("Processando Linha: {}", temp);
                if(temp.matches(ParseSqlJobToRestore.REGEX_SQL_JOB)) {
                    pat = Pattern.compile(ParseSqlJobToRestore.REGEX_SQL_JOB);
                    mat = pat.matcher(temp);
                    if(mat.find()) {
                        job = new BaculaSqlJobToRestore();
                        job.setId(Long.parseLong(mat.group(1)));
                        job.setStarttime(Utils.toAnotherCalendar(mat.group(2)).getTime());
                        job.setLevel(mat.group(3));
                        job.setName(mat.group(4));
                        jobs.add(job);
                    }
                }
            }
        } while(temp != null);
        
        return jobs;
    }
    
}
