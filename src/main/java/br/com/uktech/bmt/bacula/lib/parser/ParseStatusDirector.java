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

import br.com.uktech.bmt.bacula.bean.BaculaStatusDirector;
import br.com.uktech.bmt.bacula.lib.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class ParseStatusDirector {
    
    private final Logger logger = LoggerFactory.getLogger(ParseStatusDirector.class);
    
    public BaculaStatusDirector parse(String input) {
        this.logger.trace("Mensagem recebida: {}", input);
        BaculaStatusDirector statusDirector = new BaculaStatusDirector();
        ParseJobs parse = new ParseJobs();
        StringBuffer sbTemp = new StringBuffer();
        String temp;
        Parser p = new Parser(input);
        //Criar um Regex e um parse para o Header
        sbTemp.append(p.getToken(Constants.CR));
        sbTemp.append(p.getToken(Constants.CR));
        sbTemp.append(p.getToken(Constants.CR)).append(Constants.CR);
        statusDirector.setHeader(sbTemp.toString());
        this.logger.debug("Header: {}", sbTemp.toString());
        //Jobs
        do{
            temp = p.getToken(Constants.CR);
            this.logger.trace("Processando linha: {}", temp);
            if (temp != null)
            {
                temp = temp.trim();
                if(temp.matches(ParseJobs.REGEX_SCHEDULED_JOB)) {
                    statusDirector.getScheduledJobs().add(parse.parseScheduledJob(temp));
                } else if(temp.matches(ParseJobs.REGEX_RUNNING_JOB)) {
                    statusDirector.getRunningJobs().add(parse.parseRunningJob(temp));
                } else if(temp.matches(ParseJobs.REGEX_TERMINATED_JOB)) {
                    statusDirector.getTerminatedJobs().add(parse.parseTerminatedJob(temp));
                }
            }
        } while(temp!=null);
        return statusDirector;
    }
}
