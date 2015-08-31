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
package br.com.uktech.bmt.bacula.lib;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public interface Constants {
    public static String SPACE = " ";
    public static String LESS_THAN = "<";
    public static String GREATHER_THAN = ">";
    public static String DOT = ".";
    public static String AT = "@";
    public static String CR = "\n";
    public static String ZERO = "0";
    public static String EQUAL = "=";
    public static String YES = "yes";
    
    interface Bmt {
        public static String APP_NAME = "Bacula Management Tool";
        public static String VERSION = "0.0.1";
        public static String AUTHOR = "UKTech";
        public static String EMAIL_AUTHOR = "contato@uktech.com.br";
    }
    
    interface Bacula {
        public static String DATE_FORMAT = "dd-MMM-yy HH:mm";
        public static String ANOTHER_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    }
    
    interface Connection {
        interface Tokens {
            public static String AUTH = "auth";
            public static String CRAM_MD5 = "cram-md5";
            public static String SSL = "ssl";
            public static String SCHEDULED_JOBS = "Scheduled Jobs:";
            public static String NO_SCHEDULED_JOBS = "No Scheduled Jobs.";
            public static String END_JOBS = "====";
            public static String RUNNING_JOBS = "Running Jobs:";
            public static String NO_RUNNING_JOBS = "No Jobs running.";
            public static String TERMINATED_JOBS = "Terminated Jobs:";
            public static String NO_TERMINATED_JOBS = "No Terminated Jobs.";
        }
        
        interface Auth {
            public static String MD5 = "MD5";
            public static String HMACMD5 = "HmacMD5";
        }

        interface Messages {
            //public static String OK = new StringBuffer("1000 OK: ").append(Constants.Bmt.APP_NAME).append(" Version: ").append(Constants.Bmt.VERSION).append("\n").toString();
            public static String HELLO = new StringBuffer("Hello *UserAgent* calling\n").toString();
            public static String AUTH_OK = "1000 OK auth\n";
            public static String YOU_HAVE_MESSAGES = "You have messages.";
        }
        
        interface DotCommands {
            public static String MESSAGES = ".messages\n";
            public static String CLIENTS = ".clients";
            public static String FILESETS = ".filesets";
            public static String JOBS = ".jobs";
            public static String LEVELS = ".levels";
            public static String POOLS = ".pools";
            public static String STORAGE = ".storage";
            public static String TYPES = ".types";
            public static String DEFAULT_JOBS = ".defaults job=";
        }

        interface Commands {
            public static String STATUS_DIRECTOR = "status dir";
            public static String STATUS_CLIENT = "status client=";
            public static String STATUS_STORAGE = "status storage=";
            public static String LLIST_CLIENTS = "llist clients";
            public static String LIST_JOBID = "list jobid=";
            public static String LLIST_JOBID = "llist jobid=";
            public static String SHOW_STORAGE = "show storage";
            public static String ESTIMATE = "estimate job=%s level=%s accurate=%s %s";
            public static String EXIT = "exit";
            public static String QUIT = "quit";
            public static String VERSION = "version";
            public static String HELP = "help";
            public static String CANCEL = ".";
        }
        
        interface ReturnCodes {
            public static int SUCCESS = 1000;
        }
    }
}