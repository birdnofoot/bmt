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

import br.com.uktech.bmt.bacula.bean.BaculaDirectory;
import br.com.uktech.bmt.bacula.bean.BaculaFile;
import br.com.uktech.bmt.bacula.bean.BaculaFileVersions;
import br.com.uktech.bmt.bacula.bean.BaculaRestoreFile;
import br.com.uktech.bmt.bacula.lib.BaculaLStatDecoder;
import br.com.uktech.bmt.bacula.lib.Constants;
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
public class ParseDirectories {
    
    private final Logger logger = LoggerFactory.getLogger(ParseDirectories.class);
    
    public static final String REGEX_LIST_JOBIDS = "([\\d|\\,]+)";
    public static final String REGEX_ROOT_DIRECTORY = "^(\\d+)\\t(\\d+)\\t(\\d+)\\t(\\d+)\\t([\\w|\\+|\\/|\\s]*)\\t(\\.)$";
    public static final String REGEX_PREVIOUS_DIRECTORY = "^(\\d+)\\t(\\d+)\\t(\\d+)\\t(\\d+)\\t([\\w|\\+|\\/|\\s]*)\\t(\\.\\.)$";
    public static final String REGEX_CHILD_DIRECTORY = "(\\d+)\\t(\\d+)\\t(\\d+)\\t(\\d+)\\t([\\w|\\+|\\/|\\s]*)\\t([\\w|\\/|\\.]*)";
    public static final String REGEX_CHILD_FILE = "(\\d+)\\t(\\d+)\\t(\\d+)\\t(\\d+)\\t([\\w|\\+|\\/|\\s]*)\\t([\\w|\\/|\\.|\\-]*)";
    public static final String REGEX_FILE_VERSIONS = "(\\d+)\\t(\\d+)\\t(\\d+)\\t(\\d+)\\t([\\w|\\+|\\/|\\s]*)\\t([\\w|\\/|\\.|\\-]*)\\t([\\w|\\-|\\.]*)\\t(\\d*)";
    
    public String parseListJobids(String input) {
        
        this.logger.trace("Mensagem recebida: {}", input);
        
        String listJobids = null;
        
        Parser p = new Parser(input);
        Pattern pat = null;
        Matcher mat = null;
        String temp = null;
        
        do{
            temp = p.getToken(Constants.CR);
            if(temp != null) {
                temp = temp.trim();
                this.logger.trace("Processando Linha: {}", temp);
                if(temp.matches(ParseDirectories.REGEX_LIST_JOBIDS)) {
                    pat = Pattern.compile(ParseDirectories.REGEX_LIST_JOBIDS);
                    mat = pat.matcher(temp);
                    if(mat.find()) {
                        listJobids = temp;
                    }
                }
            }
        } while(temp != null);
        
        return listJobids;
    }
    
    public BaculaDirectory parseRootDirectory(String input) {
        
        this.logger.trace("Mensagem recebida: {}", input);
        
        BaculaDirectory directory = new BaculaDirectory();
        BaculaDirectory child;
        
        Parser p = new Parser(input);
        Pattern pat = null;
        Matcher mat = null;
        String temp = null;
        
        //directory.setParent(null);
        
        do{
            temp = p.getToken(Constants.CR);
            if(temp != null) {
                temp = temp.trim();
                this.logger.trace("Processando Linha: {}", temp);
                
                if(temp.matches(ParseDirectories.REGEX_ROOT_DIRECTORY)) {
                    this.logger.trace("Parse Current Directory");
                    pat = Pattern.compile(ParseDirectories.REGEX_ROOT_DIRECTORY);
                    mat = pat.matcher(temp);
                    if(mat.find()) {
                        directory.setPathId(Long.parseLong(mat.group(1)));
                        directory.setFileNameId(Long.parseLong(mat.group(2)));
                        directory.setFileId(Long.parseLong(mat.group(3)));
                        directory.setJobId(Long.parseLong(mat.group(4)));
                        directory.setLStat(new BaculaRestoreFile(BaculaLStatDecoder.decode(mat.group(5))));
                        directory.setPath(mat.group(6));
                    }
                } else if(temp.matches(ParseDirectories.REGEX_CHILD_DIRECTORY)) {
                    this.logger.trace("Parse Child Directory");
                    pat = Pattern.compile(ParseDirectories.REGEX_CHILD_DIRECTORY);
                    mat = pat.matcher(temp);
                    child = new BaculaDirectory();
                    if(mat.find()) {
                        child.setPathId(Long.parseLong(mat.group(1)));
                        child.setFileNameId(Long.parseLong(mat.group(2)));
                        child.setFileId(Long.parseLong(mat.group(3)));
                        child.setJobId(Long.parseLong(mat.group(4)));
                        child.setLStat(new BaculaRestoreFile(BaculaLStatDecoder.decode(mat.group(5))));
                        child.setPath(mat.group(6));
                    }
                    if(directory.getChild() == null) {
                        directory.setChild(new ArrayList<>());
                    }
                    directory.getChild().add(child);
                }
            }
        } while(temp != null);
        
        return directory;
    }
    
    public void parseFiles(String input, BaculaDirectory directory) {
        
        this.logger.trace("Mensagem recebida: {}", input);
        
        BaculaFile file;
        directory.setFiles(new ArrayList<>());
        
        Parser p = new Parser(input);
        Pattern pat = null;
        Matcher mat = null;
        String temp = null;
        
        do{
            temp = p.getToken(Constants.CR);
            if(temp != null) {
                temp = temp.trim();
                this.logger.trace("Processando Linha: {}", temp);
                if(temp.matches(ParseDirectories.REGEX_CHILD_FILE)) {
                    pat = Pattern.compile(ParseDirectories.REGEX_CHILD_FILE);
                    mat = pat.matcher(temp);
                    if(mat.find()) {
                        
                        file = new BaculaFile();
                        
                        file.setPathId(Long.parseLong(mat.group(1)));
                        file.setFileNameId(Long.parseLong(mat.group(2)));
                        file.setFileId(Long.parseLong(mat.group(3)));
                        file.setJobId(Long.parseLong(mat.group(4)));
                        file.setLStat(new BaculaRestoreFile(BaculaLStatDecoder.decode(mat.group(5))));
                        file.setPath(mat.group(6));
                        
                        directory.getFiles().add(file);
                    }
                }
            }
        } while(temp != null);
    }
    
    public BaculaDirectory parseDirectories(String input) {
        
        this.logger.trace("Mensagem recebida: {}", input);
        
        BaculaDirectory directory = new BaculaDirectory();
        BaculaDirectory child;
        
        Parser p = new Parser(input);
        Pattern pat = null;
        Matcher mat = null;
        String temp = null;
        
        do{
            temp = p.getToken(Constants.CR);
            if(temp != null) {
                temp = temp.trim();
                this.logger.trace("Processando Linha: {}", temp);
                
                if(temp.matches(ParseDirectories.REGEX_ROOT_DIRECTORY)) {
                    pat = Pattern.compile(ParseDirectories.REGEX_ROOT_DIRECTORY);
                    mat = pat.matcher(temp);
                    if(mat.find()) {
                        directory.setPathId(Long.parseLong(mat.group(1)));
                        directory.setFileNameId(Long.parseLong(mat.group(2)));
                        directory.setFileId(Long.parseLong(mat.group(3)));
                        directory.setJobId(Long.parseLong(mat.group(4)));
                        directory.setLStat(new BaculaRestoreFile(BaculaLStatDecoder.decode(mat.group(5))));
                        directory.setPath(mat.group(6));
                    }
                } else if(temp.matches(ParseDirectories.REGEX_PREVIOUS_DIRECTORY)) {
                    pat = Pattern.compile(ParseDirectories.REGEX_PREVIOUS_DIRECTORY);
                    mat = pat.matcher(temp);
                    
                    directory.setParent(new BaculaDirectory());
                    if(mat.find()) {
                        directory.getParent().setPathId(Long.parseLong(mat.group(1)));
                        directory.getParent().setFileNameId(Long.parseLong(mat.group(2)));
                        directory.getParent().setFileId(Long.parseLong(mat.group(3)));
                        directory.getParent().setJobId(Long.parseLong(mat.group(4)));
                        directory.getParent().setLStat(new BaculaRestoreFile(BaculaLStatDecoder.decode(mat.group(5))));
                        directory.getParent().setPath(mat.group(6));
                    }
                    
                } else if(temp.matches(ParseDirectories.REGEX_CHILD_DIRECTORY)) {
                    pat = Pattern.compile(ParseDirectories.REGEX_CHILD_DIRECTORY);
                    mat = pat.matcher(temp);
                    child = new BaculaDirectory();
                    if(mat.find()) {
                        child.setPathId(Long.parseLong(mat.group(1)));
                        child.setFileNameId(Long.parseLong(mat.group(2)));
                        child.setFileId(Long.parseLong(mat.group(3)));
                        child.setJobId(Long.parseLong(mat.group(4)));
                        child.setLStat(new BaculaRestoreFile(BaculaLStatDecoder.decode(mat.group(5))));
                        child.setPath(mat.group(6));
                    }
                    if(directory.getChild() == null) {
                        directory.setChild(new ArrayList<>());
                    }
                    directory.getChild().add(child);
                }
            }
        } while(temp != null);
        
        return directory;
    }
    
    public List<BaculaFileVersions> parseFileVersions(String input) {
        
        this.logger.trace("Mensagem recebida: {}", input);
        
        List<BaculaFileVersions> fileVersions = new ArrayList<>();
        BaculaFileVersions version;
        
        Parser p = new Parser(input);
        Pattern pat = null;
        Matcher mat = null;
        String temp = null;
        
        do{
            temp = p.getToken(Constants.CR);
            if(temp != null) {
                temp = temp.trim();
                this.logger.trace("Processando Linha: {}", temp);
                if(temp.matches(ParseDirectories.REGEX_FILE_VERSIONS)) {
                    pat = Pattern.compile(ParseDirectories.REGEX_FILE_VERSIONS);
                    mat = pat.matcher(temp);
                    if(mat.find()) {
                        version = new BaculaFileVersions();
                        version.setPathId(Long.parseLong(mat.group(1)));
                        version.setFileNameId(Long.parseLong(mat.group(2)));
                        version.setFileId(Long.parseLong(mat.group(3)));
                        version.setJobId(Long.parseLong(mat.group(4)));
                        version.setLStat(new BaculaRestoreFile(BaculaLStatDecoder.decode(mat.group(5))));
                        version.setMd5(mat.group(6));
                        version.setVolName(mat.group(7));
                        version.setInchanger(Integer.parseInt(mat.group(8)));
                        fileVersions.add(version);
                    }
                }
            }
        } while(temp != null);
        
        return fileVersions;
    }
}
