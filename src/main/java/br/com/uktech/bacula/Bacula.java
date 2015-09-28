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

package br.com.uktech.bacula;

import br.com.uktech.bmt.bacula.BaculaConsole;
import br.com.uktech.bmt.bacula.BaculaConsoleFactory;
import br.com.uktech.bmt.bacula.bean.BaculaDirectory;
import br.com.uktech.bmt.bacula.bean.BaculaFile;
import br.com.uktech.bmt.bacula.bean.BaculaFileVersions;
import br.com.uktech.bmt.bacula.bean.BaculaRestoreFileDefault;
import br.com.uktech.bmt.bacula.bean.BaculaRestoreJob;
import br.com.uktech.bmt.bacula.bean.dot.BaculaDotClient;
import br.com.uktech.bmt.bacula.bean.dot.BaculaDotStatusClientRunning;
import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlClient;
import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlFileSet;
import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlJob;
import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlJobToRestore;
import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlPool;
import br.com.uktech.bmt.bacula.exceptions.BaculaAuthenticationException;
import br.com.uktech.bmt.bacula.exceptions.BaculaCommunicationException;
import br.com.uktech.bmt.bacula.exceptions.BaculaDirectorNotSupported;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class Bacula {
    
    
    public static String showDirectoryFile(BaculaDirectory dir) {
        
        String out = "PathId: " + dir.getPathId() + "   Path: " + dir.getPath() + "\n";
        if(dir.getParent() != null) {
            out += "PathId: " + dir.getParent().getPathId()+ "   Path: " + dir.getParent().getPath() + "\n";
        }
        if(dir.getChild()!=null) {
            for (int i=0;i<dir.getChild().size();i++) {
                out += "Pasta     PathId: " + dir.getChild().get(i).getPathId() + "   Path: " + dir.getChild().get(i).getPath() + "\n";
            }
        }
        if(dir.getFiles()!=null) {
            for (int i=0;i<dir.getFiles().size();i++) {
                out += "Arquivo   PathId: " + dir.getFiles().get(i).getPathId() + "   fileNameId: " + dir.getFiles().get(i).getFileNameId() +  "   fileId: " + dir.getFiles().get(i).getFileId() + "   Path: " + dir.getFiles().get(i).getPath() + "\n";
                System.out.println(dir.getFiles().get(i).getFileVersions().toString());
            }
        }
        return out;
    }
    
    public static void main(String[] args) {
        
        final org.slf4j.Logger logger = LoggerFactory.getLogger(Bacula.class);
        
        try {
            //BaculaConsole console = BaculaConsoleFactory.getFactory().getConsole("director-dir", "192.168.103.1", 9101, "H6K-Ofy8725jCAoc7S0HmrYypiPHJSJxw");
            BaculaConsole console = BaculaConsoleFactory.getFactory().getConsole("director-dir", "127.0.0.1", 9101, "BWy4h3EcfCZ9fJJj1Qcgd3sS2GAt7CW30");
            
            BaculaDirectory root;
            BaculaDirectory current;
            BaculaRestoreFileDefault restoreFileDefault;
            BaculaRestoreJob restoreJob;
            
            Map<Long, BaculaDirectory> directory = new HashMap<>();
            
            List<BaculaDotClient> dotClients = null;
            List<BaculaSqlJobToRestore> jobToRestores = null;
            List<BaculaDirectory> selectedDirectories = new ArrayList<>();
            List<BaculaFile> selectedFiles = new ArrayList<>();
            List<BaculaFileVersions> selectedFileVersions = new ArrayList<>();
            
            String listJobs = null, mensagem;
            Long pathid = 0l;
            Integer limit = 500, offset = 0;
            Boolean createTable;
            int select = 1;
            
            dotClients = console.getListDotClients();
            String client = dotClients.get(0).getClient();
            jobToRestores = console.getListSqlJobToRestore(client);
            listJobs = console.getListJobsRestore(jobToRestores.get(0).getId());
            root = console.getRootDirectory(listJobs, limit, offset);
            current = root;
            directory.put(root.getPathId(), root);
            
            System.out.println(dotClients+"\n");
            System.out.println(jobToRestores+"\n");
            System.out.println(listJobs+"\n");
            System.out.println(directory+"\n\n");
            
            do {
                mensagem = showDirectoryFile(current);
                pathid = Long.parseLong(JOptionPane.showInputDialog(mensagem+"\nDigite o PathId do diretório que deseja entrar: "));
                
                if(directory.containsKey(pathid)) {
                    current = directory.get(pathid);
                    System.out.println(pathid+"\n\n");
                } else {
                    if(pathid!=0) {
                        current = console.browseDirectory(listJobs, pathid, client, limit, offset);
                        directory.put(current.getPathId(), current);
                        System.out.println(pathid+"\n\n");
                    }
                }
                mensagem = showDirectoryFile(current);
                
                if(pathid!=0) {
                    select = JOptionPane.showConfirmDialog(null,mensagem+"Deseja selecionar algum diretório?", "Please select", JOptionPane.YES_NO_OPTION);
                }
                
                if(select==0 && pathid!=0) {
                    Long dir = Long.parseLong(JOptionPane.showInputDialog(mensagem+"\nDigite o PathId do diretório que deseja selecionar: "));
                    
                    for(int i=0; i<current.getChild().size();i++) {
                        if(dir.equals(current.getChild().get(i).getPathId())) {
                            selectedDirectories.add(current.getChild().get(i));
                            break;
                        }
                    }
                }
                
                if(pathid!=0) {
                    select = JOptionPane.showConfirmDialog(null,mensagem+"Deseja selecionar algum Arquivo?", "Please select", JOptionPane.YES_NO_OPTION);
                }
                
                if(select==0 && pathid!=0) {
                    Long file = Long.parseLong(JOptionPane.showInputDialog(mensagem+"\nDigite o fileId do arquivo que deseja selecionar: "));
                    
                    for(int i=0; i<current.getFiles().size();i++) {
                        if(file.equals(current.getFiles().get(i).getFileId())) {
                            selectedFiles.add(current.getFiles().get(i));
                            break;
                        }
                    }
                }
                
                select = JOptionPane.showConfirmDialog(null,"Deseja selecionar uma versão de um Arquivo?", "Please select", JOptionPane.YES_NO_OPTION);
                
                if(select==0&&pathid!=0) {
                    String str = "Versões dos arquivos:\n";
                    for(int i=0; i<current.getFiles().size();i++) {
                        str += "\nArquivo   PathId: " + current.getFiles().get(i).getPathId() + "   fileNameId: " + current.getFiles().get(i).getFileNameId() +  "   fileId: " + current.getFiles().get(i).getFileId() + "   Path: " + current.getFiles().get(i).getPath() + "\n";
                        for(int j=0; j<current.getFiles().get(i).getFileVersions().size();j++) {
                            str += "JobId: " + current.getFiles().get(i).getFileVersions().get(j).getJobId() + "   FileId: " + current.getFiles().get(i).getFileVersions().get(j).getFileId()+"\n";
                        }
                    }
                    select = JOptionPane.showConfirmDialog(null,str+"\nDeseja selecionar uma versão de um Arquivo?", "Please select", JOptionPane.YES_NO_OPTION);
                    if(select==0) {
                        Long version = Long.parseLong(JOptionPane.showInputDialog(str+"\nDigite o fileId da versão do arquivo que deseja selecionar: "));
                        for(int i=0; i<current.getFiles().size();i++) {
                            for(int j=0; j<current.getFiles().get(i).getFileVersions().size();j++) {
                                if(version.equals(current.getFiles().get(i).getFileVersions().get(j).getFileId())) {
                                    selectedFileVersions.add(current.getFiles().get(i).getFileVersions().get(j));
                                    break;
                                }
                            }
                        }
                    }
                }
                
            } while(pathid!=0);
            
            System.out.println(selectedDirectories+"\n\n");
            System.out.println(selectedFiles+"\n\n");
            System.out.println(selectedFileVersions+"\n\n");
            restoreFileDefault = console.getBaculaRestoreFileDefault();
            System.out.println(restoreFileDefault+"\n\n");
            createTable = console.createTableRestore(selectedDirectories, selectedFiles, selectedFileVersions, listJobs);
            System.out.println("Criando Tabela: "+createTable+"\n\n");
            restoreFileDefault.setWhen(JOptionPane.showInputDialog("Digite uma data ex: 2015-09-28 14:02:10"));
            restoreJob = console.runRestore(restoreFileDefault);
            System.out.println("Restauração: "+restoreJob+"\n\n");
            console.cleanTable();
            
            console.disconnect();
        } catch (Exception ex) {
            Logger.getLogger(Bacula.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
//String returnMensage = "";
//BaculaSqlJob job = null;
//BaculaSqlClient client = null;
//BaculaSqlPool pool = null;
//BaculaSqlFileSet fileset = null;
//BaculaDotStatusClientRunning statusClientRunning = null;
//List<BaculaSqlClient> clients = null;
//List<BaculaSqlJob> jobs = null;

//returnMensage = console.executeSql("SELECT filesetid, fileset, md5, createtime FROM fileset");
//job = console.getSqlJob(1373l);
//client = console.getSqlClient(1l);
//pool = console.getSqlPool(1l);
//fileset = console.getSqlFileSet(1l);
//statusClientRunning = console.getDotStatusClientRunning(job.getClient().getName());
//clients = console.getListSqlClient();
//jobs = console.getListSqlJob();

//System.out.println("\n\n"+returnMensage+"\n\n");
//System.out.println("\n\n"+job+"\n\n");
//System.out.println("\n\n"+client.toString()+"\n\n");
//System.out.println("\n\n"+pool.toString()+"\n\n");
//System.out.println("\n\n"+fileset.toString()+"\n\n");
//System.out.println("\n\n"+statusClientRunning+"\n\n");
//System.out.println("\n\n"+clients+"\n\n");
//System.out.println("\n\n"+jobs+"\n\n");

/*
Map<String, Long> dados = null;
String codigo;
dados = BaculaLStatDecoder.decode("gB DL+b IGg B A y A D5dZR BAA fN4 BNeR+z BNeR+7 BNeR+7 A A C");
codigo = BaculaLStatDecoder.encode(dados);
System.out.println("\n\n"+dados.toString()+"\n");
System.out.println('{'+codigo+'}');
*/