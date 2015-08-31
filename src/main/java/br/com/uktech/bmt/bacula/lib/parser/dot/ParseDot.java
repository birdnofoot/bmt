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

import br.com.uktech.bmt.bacula.lib.Constants;
import br.com.uktech.bmt.bacula.lib.parser.Parser;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public abstract class ParseDot<T> {

    public final Logger logger = LoggerFactory.getLogger(ParseDot.class);
    
    protected abstract T createObject(String value);
    
    public List<T> getReturn(String input) {
        this.logger.trace("Comando Recebido: {}", input);
        List<T> retorno = new ArrayList<>();
        Parser p = new Parser(input);
        String temp = null;
        do {
            temp = p.getToken(Constants.CR);
            this.logger.trace("Processando Linha: {}", temp);
            if (temp != null) {
                T aux = this.createObject(temp);
                retorno.add(aux);
            }
        } while(temp!=null);
        return retorno;
    }
}
