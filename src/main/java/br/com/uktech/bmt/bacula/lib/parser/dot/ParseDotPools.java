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

import br.com.uktech.bmt.bacula.bean.dot.BaculaDotPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class ParseDotPools extends ParseDot<BaculaDotPool> {

    public final Logger logger = LoggerFactory.getLogger(ParseDotLevels.class);

    @Override
    protected BaculaDotPool createObject(String value) {
        BaculaDotPool pool = new BaculaDotPool();
        pool.setPool(value);
        return pool;
    }

}
