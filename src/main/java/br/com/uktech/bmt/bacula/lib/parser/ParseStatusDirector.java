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

import br.com.uktech.bmt.bacula.bean.StatusDirector;
import br.com.uktech.bmt.bacula.lib.Constants;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class ParseStatusDirector {
    public StatusDirector parse(String input) {
        StatusDirector statusDirector = new StatusDirector();
        ParseJobs parse = new ParseJobs();
        StringBuffer sbTemp = new StringBuffer();
        String temp;
        Parser p = new Parser(input);
        
        //Banner
        sbTemp.append(p.getToken(Constants.CR));
        sbTemp.append(p.getToken(Constants.CR));
        sbTemp.append(p.getToken(Constants.CR)).append(Constants.CR);
        statusDirector.setBanner(sbTemp.toString());
        //System.err.println("antes do do while");
        do{
            temp = p.getToken(Constants.CR);
            if (temp != null)
            {
                temp = temp.trim();
                if(temp.matches("^( *(Full|Incremental|Differential) +(\\w+) +(\\d+) +(\\d{2}-[a-zA-Z]{3}-\\d{2} \\d{2}:\\d{2}) +(.+[^ ]) +(.+))")) {
                    statusDirector.getScheduledJobs().add(parse.parseScheduledJob(temp));
                } else if(temp.matches("^( *(\\d+) +(Full|Incr) +([^ ]+) +((\\d+(\\.\\d{1,3})?|0) *((G|M|K)*+)) +(Error|OK) +(\\d{2}-[a-zA-Z]{3}-\\d{2} \\d{2}:\\d{2}) (.+))")) {
                    statusDirector.getTerminatedJobs().add(parse.parseTerminadedJob(temp));
                } else if(temp.matches("^( *(\\d+) +(Full|Incr) +(.[^ ]+) (.+))")) {
                    statusDirector.getRunningJobs().add(parse.parseRunningJob(temp));
                }
            }
        } while(temp!=null);
        //System.err.println("fim do parse");
        return statusDirector;
    }
}
