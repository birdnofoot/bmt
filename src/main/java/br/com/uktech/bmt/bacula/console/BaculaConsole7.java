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
package br.com.uktech.bmt.bacula.console;

import br.com.uktech.bmt.bacula.lib.Connection;
import br.com.uktech.bmt.bacula.BaculaConsole;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class BaculaConsole7 extends BaculaConsole5 implements BaculaConsole {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(BaculaConsole7.class);
    
    public BaculaConsole7(String directorName, Connection connection) {
        super(directorName, connection);
    }
    
}
