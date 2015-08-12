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

import br.com.uktech.bmt.bacula.bean.BaculaStorage;
import br.com.uktech.bmt.bacula.lib.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.LoggerFactory;

/**
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class ParseStorage {
    
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ParseStorage.class);
    private static final String REGEX_SHOW_STORAGE_NAME = "Storage:[\\s|\\t]*name=([\\w|-]*)[\\s|\\t]*address=([\\.|\\w|-]*)[\\s|\\t]*SDport=(\\d*)[\\s|\\t]*MaxJobs=([\\d|,]*)";
    private static final String REGEX_SHOW_STORAGE_DEVICE = "DeviceName=([\\w|-]*)[\\s|\\t]*MediaType=([\\w|-]*)[\\s|\\t]*StorageId=([\\d|,]*)";
    
    public List<BaculaStorage> parse(String input) {
        List<BaculaStorage> baculaStorage = new ArrayList<>();
        try{
            BaculaStorage storage = new BaculaStorage();
            StringBuffer sbTemp = new StringBuffer();
            String temp;
            Parser parser = new Parser(input);
            Pattern p = null;
            Matcher m = null;
            do{
                temp = parser.getToken(Constants.CR);
                if (temp != null) {
                    temp = temp.trim();
                    if(temp.matches(ParseStorage.REGEX_SHOW_STORAGE_NAME)) {
                        p = Pattern.compile(ParseStorage.REGEX_SHOW_STORAGE_NAME);
                        m = p.matcher(temp);
                        if(m.find()) {
                            storage.setName(m.group(1));
                            storage.setAddress(m.group(2));
                            storage.setSDport(Integer.parseInt(m.group(3)));
                            storage.setMaxJobs(Integer.parseInt(m.group(4)));
                        }
                        temp = parser.getToken(Constants.CR).trim();
                        if(temp.matches(ParseStorage.REGEX_SHOW_STORAGE_DEVICE)) {
                            p = Pattern.compile(ParseStorage.REGEX_SHOW_STORAGE_DEVICE);
                            m = p.matcher(temp);
                            if(m.find()) {
                                storage.setDeviceName(m.group(1));
                                storage.setMediaType(m.group(2));
                                storage.setStorageId(Long.parseLong(m.group(3)));
                            }
                        }
                        if(storage.getName()!=null&&storage.getAddress()!=null&&storage.getSDport()!=null&&storage.getMaxJobs()!=null&&storage.getDeviceName()!=null&&storage.getMediaType()!=null&&storage.getStorageId()!=null) {
                            baculaStorage.add(storage);
                        }
                    }
                }
            } while(temp!=null);
        } catch(Exception ex) {
            this.logger.error(ex.getLocalizedMessage());
        }
        return baculaStorage;
    }
}