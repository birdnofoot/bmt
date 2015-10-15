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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class ParseTableRestore {
    public String createCommand(List<BaculaDirectory> selectedDirectories, List<BaculaFile> selectedFiles, List<BaculaFileVersions> selectedFileVersions, String listJobs) {
        String jobid = " jobid=", fileid = " fileid=", dirid=" dirid=", hardlink = " hardlink=";
        String command = ".bvfs_restore path=b2123";

        List<Long> jobids = new ArrayList<>();
        List<Long> fileids = new ArrayList<>();
        List<Long> dirids = new ArrayList<>();
        List<Long> hardlinks = new ArrayList<>();

        if(selectedDirectories != null) {
            if(!selectedDirectories.isEmpty()) {
                for (BaculaDirectory next : selectedDirectories) {
                    if(!dirids.contains(next.getPathId())) {
                        dirids.add(next.getPathId());
                        String jbs[] = listJobs.split(Pattern.quote(","));
                        for (String jb : jbs) {
                            jobids.add(Long.parseLong(jb));
                        }
                    }
                }
            }
        }

        if(selectedFiles!=null) {
            if(!selectedFiles.isEmpty()) {
                for (BaculaFile next : selectedFiles) {
                    if(!jobids.contains(next.getJobId())&&!next.getJobId().equals(0)) {
                        jobids.add(next.getJobId());
                    }
                    if(!fileids.contains(next.getFileId())) {
                        fileids.add(next.getFileId());
                    }
                    if(next.getLStat().getLinkFi()!=0) {
                        if(!hardlinks.contains(next.getLStat().getLinkFi())) {
                            hardlinks.add(next.getJobId());
                            hardlinks.add(next.getLStat().getLinkFi());
                        }
                    }
                }
            }
        }

        if(selectedFileVersions != null) {
            if(!selectedFileVersions.isEmpty()) {
                for (BaculaFileVersions next : selectedFileVersions) {
                    if(!jobids.contains(next.getJobId())&&!next.getJobId().equals(0)) {
                        jobids.add(next.getJobId());
                    }
                    if(!fileids.contains(next.getFileId())) {
                        fileids.add(next.getFileId());
                    }
                    if(next.getLStat().getLinkFi()!=0) {
                        if(!hardlinks.contains(next.getLStat().getLinkFi())) {
                            hardlinks.add(next.getJobId());
                            hardlinks.add(next.getLStat().getLinkFi());
                        }
                    }
                }
            }
        }

        if(!jobids.isEmpty()) {
            for (Long job : jobids) {
                jobid += job+",";
            }
            jobid = jobid.substring(0, jobid.length()-1);
        } else {
            jobid = "";
        }

        if(!fileids.isEmpty()) {
            for (Long file : fileids) {
                fileid += file+",";
            }
            fileid = fileid.substring(0, fileid.length()-1);
        } else {
            fileid = "";
        }

        if(!dirids.isEmpty()) {
            for (Long dir : dirids) {
                dirid += dir+",";
            }
            dirid = dirid.substring(0, dirid.length()-1);
        } else {
            dirid = "";
        }

        if(!hardlinks.isEmpty()) {
            for (Long hard : hardlinks) {
                hardlink += hard+",";
            }
            hardlink = hardlink.substring(0, hardlink.length()-1);
        } else {
            hardlink = "";
        }
        
        return command+jobid+fileid+dirid+hardlink;
    }
}

