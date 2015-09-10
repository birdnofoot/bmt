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

import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlFileSet;
import br.com.uktech.bmt.bacula.lib.Constants;
import br.com.uktech.bmt.bacula.lib.Utils;
import br.com.uktech.bmt.bacula.lib.parser.Parser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class ParseSqlFileSet {
    
    private final Logger logger = LoggerFactory.getLogger(ParseSqlFileSet.class);
    
    public static final String REGEX_SQL_FILESET_ID = "(\\d*)\\t([\\w|\\s]*)\\t([\\w|\\s|\\+|\\/|\\,|\\.]*)\\t(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2})\\t";
    
    public BaculaSqlFileSet parseFileSet(String input) {
        return parseFileSet(input, new BaculaSqlFileSet());
    }
    
    public BaculaSqlFileSet parseFileSet(String input, BaculaSqlFileSet fileset) {
        
        this.logger.trace("Mensagem recebida: {}", input);
        
        fileset.setId(null);
        
        Parser p = new Parser(input);
        Pattern pat = null;
        Matcher mat = null;
        String temp = null;
        
        do{
            temp = p.getToken(Constants.CR);
            if(temp != null) {
                this.logger.trace("Processando Linha: {}", temp);
                if(temp.matches(ParseSqlFileSet.REGEX_SQL_FILESET_ID)) {
                    pat = Pattern.compile(ParseSqlFileSet.REGEX_SQL_FILESET_ID);
                    mat = pat.matcher(temp);
                    if(mat.find()) {
                        fileset.setId(Long.parseLong(mat.group(1)));
                        fileset.setFileset(mat.group(2));
                        fileset.setMd5(mat.group(3));
                        fileset.setCreatetime(Utils.toAnotherCalendar(mat.group(4)).getTime());
                    }
                }
            }
        } while(temp != null);
        return fileset;
    }
}
