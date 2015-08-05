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
public interface BNet {
    
    public static final int BNET_EOD            = -1,          /* End of data stream, new data may follow */
                            BNET_EOD_POLL       = -2,          /* End of data and poll all in one */
                            BNET_STATUS         = -3,          /* Send full status */
                            BNET_TERMINATE      = -4,          /* Conversation terminated, doing close() */
                            BNET_POLL           = -5,          /* Poll request, I'm hanging on a read */
                            BNET_HEARTBEAT      = -6,          /* Heartbeat Response requested */
                            BNET_HB_RESPONSE    = -7,          /* Only response permited to HB */
                            BNET_xxxxxxPROMPT   = -8,          /* No longer used -- Prompt for subcommand */
                            BNET_BTIME          = -9,          /* Send UTC btime */
                            BNET_BREAK          = -10,         /* Stop current command -- ctl-c */
                            BNET_START_SELECT   = -11,         /* Start of a selection list */
                            BNET_END_SELECT     = -12,         /* End of a select list */
                            BNET_INVALID_CMD    = -13,         /* Invalid command sent */
                            BNET_CMD_FAILED     = -14,         /* Command failed */
                            BNET_CMD_OK         = -15,         /* Command succeeded */
                            BNET_CMD_BEGIN      = -16,         /* Start command execution */
                            BNET_MSGS_PENDING   = -17,         /* Messages pending */
                            BNET_MAIN_PROMPT    = -18,         /* Server ready and waiting */
                            BNET_SELECT_INPUT   = -19,         /* Return selection input */
                            BNET_WARNING_MSG    = -20,         /* Warning message */
                            BNET_ERROR_MSG      = -21,         /* Error message -- command failed */
                            BNET_INFO_MSG       = -22,         /* Info message -- status line */
                            BNET_RUN_CMD        = -23,         /* Run command follows */
                            BNET_YESNO          = -24,         /* Request yes no response */
                            BNET_START_RTREE    = -25,         /* Start restore tree mode */
                            BNET_END_RTREE      = -26,         /* End restore tree mode */ 
                            BNET_SUB_PROMPT     = -27,         /* Indicate we are at a subprompt */
                            BNET_TEXT_INPUT     = -28;          /* Get text input from user */
    
}
