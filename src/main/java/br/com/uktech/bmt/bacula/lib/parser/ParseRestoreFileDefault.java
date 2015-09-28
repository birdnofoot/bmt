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

import br.com.uktech.bmt.bacula.bean.BaculaRestoreFileDefault;
import br.com.uktech.bmt.bacula.lib.Constants;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class ParseRestoreFileDefault {
    
    private final Logger logger = LoggerFactory.getLogger(ParseRestoreFileDefault.class);
    
    public static final String REGEX_RESTORE_FILE_DEFAULT = "job=(\\w*)pool=(\\w*)messages=(\\w*)client=([\\w|\\-]*)storage=(\\w*)where=([\\w|\\/|\\-]*)level=(\\w*)type=(\\w*)fileset=([\\w|\\s]*)enabled=(\\d*)catalog=(\\w*)";
    
    public BaculaRestoreFileDefault parse(String input) {
        
        this.logger.trace("Mensagem recebida: {}", input);
        
        BaculaRestoreFileDefault restoreFileDefault = new BaculaRestoreFileDefault();
        
        Parser p = new Parser(input.replaceAll("###", "\n"));
        Pattern pat = null;
        Matcher mat = null;
        String temp = null;
        
        do{
            temp = p.getToken(Constants.CR);
            if(temp != null) {
                temp = temp.trim();
                this.logger.trace("Processando Linha: {}", temp);
                if(temp.matches(ParseRestoreFileDefault.REGEX_RESTORE_FILE_DEFAULT)) {
                    pat = Pattern.compile(ParseRestoreFileDefault.REGEX_RESTORE_FILE_DEFAULT);
                    mat = pat.matcher(temp);
                    if(mat.find()) {
                        restoreFileDefault.setJob(mat.group(1));
                        restoreFileDefault.setPool(mat.group(2));
                        restoreFileDefault.setMessages(mat.group(3));
                        restoreFileDefault.setClient(mat.group(4));
                        restoreFileDefault.setStorage(mat.group(5));
                        restoreFileDefault.setWhere(mat.group(6));
                        restoreFileDefault.setLevel(mat.group(7));
                        restoreFileDefault.setType(mat.group(8));
                        restoreFileDefault.setFileset(mat.group(9));
                        restoreFileDefault.setEnabled(mat.group(10).equals("1"));
                        restoreFileDefault.setCatalog(mat.group(11));
                        restoreFileDefault.setWhen("");
                    }
                }
            }
        } while(temp != null);
        
        return restoreFileDefault;
    }
}
