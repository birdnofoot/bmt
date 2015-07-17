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

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class Parser {

    private int position;
    private String input;

    public Parser(String input) {
        this.input = input;
        position = 0;
    }

    public String getToken(String delimiter) {
        if (position >= input.length()) {
            return null;
        }
        int p = input.indexOf(delimiter, position);
        String r;
        if (p == -1) {
            r = input.substring(position);
            position = input.length();
        } else {
            p++;
            r = input.substring(position, p);
            position = p;
        }
        return r;
    }
}
