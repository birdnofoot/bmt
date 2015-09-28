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
import br.com.uktech.bmt.bacula.lib.Constants;
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
public class ParseSqlClient {
    
    private final Logger logger = LoggerFactory.getLogger(ParseSqlClient.class);
    
    public static final String REGEX_SQL_CLIENT_ID = "(\\d*)\\t([\\w|\\-]*)\\t###\\t([\\,|\\)|\\(|\\s|\\.|\\w|\\-]*)\\t###\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t";
    public static final String REGEX_SQL_ID = "\\#\\#\\#\\t*(\\d*)\\t*\\#\\#\\#";
    
    public BaculaSqlClient parseClient(String input) {
        return parseClient(input, new BaculaSqlClient());
    }
    
    public BaculaSqlClient parseClient(String input, BaculaSqlClient client) {
        this.logger.trace("Mensagem recebida: {}", input);
        
        client.setId(null);
        
        Parser p = new Parser(input);
        Pattern pat = null;
        Matcher mat = null;
        String temp = null;
        
        do{
            temp = p.getToken(Constants.CR);
            if(temp != null) {
                this.logger.trace("Processando Linha: {}", temp);
                if(temp.matches(ParseSqlClient.REGEX_SQL_CLIENT_ID)) {
                    pat = Pattern.compile(ParseSqlClient.REGEX_SQL_CLIENT_ID);
                    mat = pat.matcher(temp);
                    if(mat.find()) {
                        client.setId(Long.parseLong(mat.group(1)));
                        client.setName(mat.group(2));
                        client.setUname(mat.group(3));
                        client.setAutoprune(Integer.parseInt(mat.group(4)));
                        client.setFileretention(Long.parseLong(mat.group(5)));
                        client.setJobretention(Long.parseLong(mat.group(6)));
                    }
                }
            }
        } while(temp != null);
        return client;
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
