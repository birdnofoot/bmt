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

package br.com.uktech.bmt.bacula.lib.parser.dot;

import br.com.uktech.bmt.bacula.bean.dot.BaculaDotStatusClientRunning;
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
public class ParseDotStatusClient {
    
    private final Logger logger = LoggerFactory.getLogger(ParseDotStatusClient.class);
    
    //public static final String REGEX_DOT_STATUS_CLIENT = ".+=(.*)";
    
    public BaculaDotStatusClientRunning parse(String input) {
        
        this.logger.trace("Mensagem recebida: {}", input);
        
        BaculaDotStatusClientRunning statusClient = new BaculaDotStatusClientRunning();
        
        Parser p = new Parser(input);
        Pattern pat = null;
        Matcher mat = null;
        String temp = null;
        
        do{
            temp = p.getToken(Constants.CR);
            if(temp != null) {
                temp = temp.trim();
                if(temp.matches("JobId=(.*)")) {
                    this.logger.trace("Processando Linha: {}", temp);
                    pat = Pattern.compile("JobId=(.*)");
                    mat = pat.matcher(temp);
                    if(mat.find()) {
                        statusClient.setJobId(Long.parseLong(mat.group(1)));
                    }
                } else {
                    if(temp.matches("Job=(.*)")) {
                        this.logger.trace("Processando Linha: {}", temp);
                        pat = Pattern.compile("Job=(.*)");
                        mat = pat.matcher(temp);
                        if(mat.find()) {
                            statusClient.setJob(mat.group(1));
                        }
                    } else {
                        if(temp.matches("VSS=(.*)")) {
                            this.logger.trace("Processando Linha: {}", temp);
                            pat = Pattern.compile("VSS=(.*)");
                            mat = pat.matcher(temp);
                            if(mat.find()) {
                                statusClient.setVss(Long.parseLong(mat.group(1)));
                            }
                        } else {
                            if(temp.matches("Level=(.*)")) {
                                this.logger.trace("Processando Linha: {}", temp);
                                pat = Pattern.compile("Level=(.*)");
                                mat = pat.matcher(temp);
                                if(mat.find()) {
                                    statusClient.setLevel(mat.group(1));
                                }
                            } else {
                                if(temp.matches("JobType=(.*)")) {
                                    this.logger.trace("Processando Linha: {}", temp);
                                    pat = Pattern.compile("JobType=(.*)");
                                    mat = pat.matcher(temp);
                                    if(mat.find()) {
                                        statusClient.setJobType(mat.group(1));
                                    }
                                } else {
                                    if(temp.matches("JobStarted=(.*)")) {
                                        this.logger.trace("Processando Linha: {}", temp);
                                        pat = Pattern.compile("JobStarted=(.*)");
                                        mat = pat.matcher(temp);
                                        if(mat.find()) {
                                            statusClient.setJobStarted(Utils.toAnotherCalendar(mat.group(1)).getTime());
                                        }
                                    } else {
                                        if(temp.matches("Files=(.*)")) {
                                            this.logger.trace("Processando Linha: {}", temp);
                                            pat = Pattern.compile("Files=(.*)");
                                            mat = pat.matcher(temp);
                                            if(mat.find()) {
                                                statusClient.setFiles(Long.parseLong(mat.group(1)));
                                            }
                                        } else {
                                            if(temp.matches("Bytes=(.*)")) {
                                                this.logger.trace("Processando Linha: {}", temp);
                                                pat = Pattern.compile("Bytes=(.*)");
                                                mat = pat.matcher(temp);
                                                if(mat.find()) {
                                                    statusClient.setBytes(Long.parseLong(mat.group(1)));
                                                }
                                            } else {
                                                if(temp.matches("Bytes\\/sec=(.*)")) {
                                                    this.logger.trace("Processando Linha: {}", temp);
                                                    pat = Pattern.compile("Bytes\\/sec=(.*)");
                                                    mat = pat.matcher(temp);
                                                    if(mat.find()) {
                                                        statusClient.setBytesSec(Long.parseLong(mat.group(1)));
                                                    }
                                                } else {
                                                    if(temp.matches("Errors=(.*)")) {
                                                        this.logger.trace("Processando Linha: {}", temp);
                                                        pat = Pattern.compile("Errors=(.*)");
                                                        mat = pat.matcher(temp);
                                                        if(mat.find()) {
                                                            statusClient.setErrors(Integer.parseInt(mat.group(1)));
                                                        }
                                                    } else {
                                                        if(temp.matches("Files\\sExamined=(.*)")) {
                                                            this.logger.trace("Processando Linha: {}", temp);
                                                            pat = Pattern.compile("Files\\sExamined=(.*)");
                                                            mat = pat.matcher(temp);
                                                            if(mat.find()) {
                                                                statusClient.setFilesExamined(Long.parseLong(mat.group(1)));
                                                            }
                                                        } else {
                                                            if(temp.matches("Processing\\sfile=(.*)")) {
                                                                this.logger.trace("Processando Linha: {}", temp);
                                                                pat = Pattern.compile("Processing\\sfile=(.*)");
                                                                mat = pat.matcher(temp);
                                                                if(mat.find()) {
                                                                    statusClient.setProcessingFile(mat.group(1));
                                                                }
                                                            } else {
                                                                if(temp.matches("SDReadSeqNo=(.*)")) {
                                                                    this.logger.trace("Processando Linha: {}", temp);
                                                                    pat = Pattern.compile("SDReadSeqNo=(.*)");
                                                                    mat = pat.matcher(temp);
                                                                    if(mat.find()) {
                                                                        statusClient.setSdReadSeqNo(Long.parseLong(mat.group(1)));
                                                                    }
                                                                } else {
                                                                    if(temp.matches("fd=(.*)")) {
                                                                        this.logger.trace("Processando Linha: {}", temp);
                                                                        pat = Pattern.compile("fd=(.*)");
                                                                        mat = pat.matcher(temp);
                                                                        if(mat.find()) {
                                                                            statusClient.setFd(Long.parseLong(mat.group(1)));
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } while(temp != null);
        
        return statusClient;
    }
}
