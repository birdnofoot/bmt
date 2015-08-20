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

import br.com.uktech.bmt.bacula.bean.BaculaEstimate;
import br.com.uktech.bmt.bacula.bean.BaculaFileSystem;
import br.com.uktech.bmt.bacula.lib.Constants;
import br.com.uktech.bmt.bacula.lib.Utils;
import ch.qos.logback.classic.pattern.Util;
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
public class ParseEstimate {
    
    private final Logger logger = LoggerFactory.getLogger(ParseEstimate.class);
    
    public static final String REGEX_ESTIMATE_FILESYSTEM = "([-|\\w]*)[\\s|\\t]*([\\d|,]*)[\\s|\\t]*([\\w|-]*)[\\s|\\t]*([\\w|-]*)[\\s|\\t]*(\\d*)[\\s|\\t]*(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2})[\\s|\\t]*([\\w|/|\\.|-]*)";
    public static final String REGEX_ESTIMATE_FILES_BYTES = "2000[\\s|\\t]*OK[\\s|\\t]*estimate[\\s|\\t]*files=([\\d|,]*)[\\s|\\t]*bytes=([\\d|,]*)";
    
    public BaculaEstimate parse(String input) {
        this.logger.trace("Mensagem recebida: {}", input);
        BaculaEstimate estimate = new BaculaEstimate();
        Parser p = new Parser(input);
        BaculaFileSystem fileSystem = null;
        Pattern pat = null;
        Matcher mat = null;
        String temp = null;
        do{
            temp = p.getToken(Constants.CR);
            this.logger.trace("Processando linha: {}", temp);
            if(temp != null) {
                temp = temp.trim();
                this.logger.trace("Processando Linha: {}", temp);
                if(temp.matches(ParseEstimate.REGEX_ESTIMATE_FILESYSTEM)) {
                    fileSystem = new BaculaFileSystem();
                    pat = Pattern.compile(ParseEstimate.REGEX_ESTIMATE_FILESYSTEM);
                    mat = pat.matcher(temp);
                    if(mat.find()) {
                        fileSystem.setPermission(mat.group(1));
                        fileSystem.setType(Integer.parseInt(mat.group(2).replace(" ", "")));
                        fileSystem.setUser(mat.group(3));
                        fileSystem.setGroup(mat.group(4));
                        fileSystem.setLinks(Integer.parseInt(mat.group(5).replace(" ", "")));
                        fileSystem.setCreationDate(Utils.toAnotherCalendar(mat.group(6)));
                        fileSystem.setPath(mat.group(7));
                    }
                    //Parse do fileSystem
                    estimate.getFileSystem().add(fileSystem);
                } else if(temp.matches(ParseEstimate.REGEX_ESTIMATE_FILES_BYTES)) {
                    pat = Pattern.compile(ParseEstimate.REGEX_ESTIMATE_FILES_BYTES);
                    mat = pat.matcher(temp);
                    if(mat.find()) {
                        estimate.setFiles(Long.parseLong(mat.group(1).replace(",", "")));
                        estimate.setBytes(Long.parseLong(mat.group(2).replace(",", "")));
                    }
                }
            }
            
        } while(temp!=null);
        return estimate;
    }
}
