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

package br.com.uktech.bmt.bacula.lib.parser.sql;

import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlNextPool;
import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlPool;
import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlRecyclePool;
import br.com.uktech.bmt.bacula.bean.sql.BaculaSqlScratchPool;
import br.com.uktech.bmt.bacula.lib.Constants;
import br.com.uktech.bmt.bacula.lib.parser.Parser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class ParseSqlPool {
    
    private final Logger logger = LoggerFactory.getLogger(ParseSqlPool.class);
    
    public static final String REGEX_SQL_POOL_ID = "(\\d*)\\t([\\*|\\w|\\-|\\.]*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t([\\*|\\w|\\-|\\.]*)\\t(\\d*)\\t([\\*|\\w|\\-|\\.]*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t(\\d*)\\t";
    
    public BaculaSqlPool parsePool(String input) {
        return parsePool(input, new BaculaSqlPool());
    }
    
    public BaculaSqlPool parsePool(String input, BaculaSqlPool pool) {
        
        this.logger.trace("Mensagem recebida: {}", input);
        
        pool.setId(null);
        
        BaculaSqlScratchPool scratchpool = new BaculaSqlScratchPool();
        BaculaSqlRecyclePool recyclepool = new BaculaSqlRecyclePool();
        BaculaSqlNextPool nextpool = new BaculaSqlNextPool();
        
        Parser p = new Parser(input);
        Pattern pat = null;
        Matcher mat = null;
        String temp = null;
        
        do{
            temp = p.getToken(Constants.CR);
            if(temp != null) {
                this.logger.trace("Processando Linha: {}", temp);
                if(temp.matches(ParseSqlPool.REGEX_SQL_POOL_ID)) {
                    pat = Pattern.compile(ParseSqlPool.REGEX_SQL_POOL_ID);
                    mat = pat.matcher(temp);
                    if(mat.find()) {
                        this.logger.trace("Fazendo o Parse");
                        pool.setId(Long.parseLong(mat.group(1)));
                        pool.setName(mat.group(2));
                        pool.setNumvols(Integer.parseInt(mat.group(3)));
                        pool.setMaxvols(Integer.parseInt(mat.group(4)));
                        pool.setUseonce(Integer.parseInt(mat.group(5)));
                        pool.setUsecatalog(Integer.parseInt(mat.group(6)));
                        pool.setAcceptanyvolume(Integer.parseInt(mat.group(7)));
                        pool.setVolretention(Long.parseLong(mat.group(8)));
                        pool.setVoluseduration(Long.parseLong(mat.group(9)));
                        pool.setMaxvoljobs(Integer.parseInt(mat.group(10)));
                        pool.setMaxvolfiles(Integer.parseInt(mat.group(11)));
                        pool.setMaxvolbytes(Long.parseLong(mat.group(12)));
                        pool.setAutoprune(Integer.parseInt(mat.group(13)));
                        pool.setRecycle(Integer.parseInt(mat.group(14)));
                        pool.setActiononpurge(Integer.parseInt(mat.group(15)));
                        pool.setPooltype(mat.group(16));
                        pool.setLabeltype(Integer.parseInt(mat.group(17)));
                        pool.setLabelformat(mat.group(18));
                        pool.setEnabled(Integer.parseInt(mat.group(19)));
                        scratchpool.setId(Long.parseLong(mat.group(20)));
                        pool.setScratchpool(scratchpool);
                        recyclepool.setId(Long.parseLong(mat.group(21)));
                        pool.setRecyclepool(recyclepool);
                        nextpool.setId(Long.parseLong(mat.group(22)));
                        pool.setNextpool(nextpool);
                        pool.setMigrationhighbytes(Long.parseLong(mat.group(23)));
                        pool.setMigrationlowbytes(Long.parseLong(mat.group(24)));
                        pool.setMigrationtime(Long.parseLong(mat.group(25)));
                    }
                }
            }
        } while(temp != null);
        this.logger.trace("Saindo do Parse");
        return pool;
    }
}
