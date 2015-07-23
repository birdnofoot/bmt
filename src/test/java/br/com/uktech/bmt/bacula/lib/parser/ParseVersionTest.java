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

import br.com.uktech.bmt.bacula.bean.BaculaVersion;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class ParseVersionTest {
    
    public ParseVersionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of parse method, of class ParseVersion.
     */
    @Test
    public void testParse() {
        
        System.out.println("parse");
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        String input = "backup-00-dir Version: 5.2.6 (21 February 2012) i686-pc-linux-gnu ubuntu 14.04";
        BaculaVersion expResult = new BaculaVersion();
        expResult.setMajor(5);
        expResult.setMinor(2);
        expResult.setRevision(6);
        
        try {
            expResult.setRelease(sdf.parse("21 February 2012"));
        }
        catch (ParseException ex) {
            System.err.println(ex.getLocalizedMessage());
        }
        expResult.setUname("i686-pc-linux-gnu ubuntu 14.04");
        BaculaVersion result = ParseVersion.parse(input);
        
        assertNotNull(result);
        
        System.out.println("<" + expResult.hashCode()+ ">");
        System.out.println("<" + result.hashCode()+ ">");
        System.out.println("<" + expResult.getUname() + ">");
        System.out.println("<" + result.getUname() + ">");
        
        assertEquals(expResult, result);
        
        input = "backup-00-dir Version: 7.3.5 (21 November 2015) i686-pc-linux-gnu ubuntu 15.04";
        expResult = new BaculaVersion();
        expResult.setMajor(7);
        expResult.setMinor(3);
        expResult.setRevision(5);
        
        try {
            expResult.setRelease(sdf.parse("21 November 2015"));
        }
        catch (ParseException ex) {
            System.err.println(ex.getLocalizedMessage());
        }
        expResult.setUname("i686-pc-linux-gnu ubuntu 15.04");
        
        result = ParseVersion.parse(input);
        
        assertNotNull(result);
        
        System.out.println("<" + expResult.hashCode()+ ">");
        System.out.println("<" + result.hashCode()+ ">");
        System.out.println("<" + expResult.getUname() + ">");
        System.out.println("<" + result.getUname() + ">");
        
        assertEquals(expResult, result);
        
        input = "backup-00-dir sdasda: 7.3.5 (21 November 2015) i686-pc-linux-gnu ubuntu 15.04";
        
        result = ParseVersion.parse(input);
        
        assertNull(result);
        System.out.println("< result is null >");
        /*
        System.out.println("<" + expResult.hashCode()+ ">");
        System.out.println("<" + result.hashCode()+ ">");
        System.out.println("<" + expResult.getUname() + ">");
        System.out.println("<" + result.getUname() + ">");
        
        assertEquals(expResult, result);
        */
        
        
    }
    
}
