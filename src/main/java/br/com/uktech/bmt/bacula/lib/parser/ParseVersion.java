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
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class ParseVersion {
    
    private static Logger logger = LoggerFactory.getLogger(ParseVersion.class);

    public static BaculaVersion parse(String input) {
        BaculaVersion version = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        Pattern p = Pattern.compile(".+ Version\\: ((\\d)\\.(\\d)\\.(\\d)) \\((.+)\\) (.+)");
        Matcher m = p.matcher(input);

        if (m.find()) {
            version = new BaculaVersion();
            version.setMajor(Integer.parseInt(m.group(2)));
            version.setMinor(Integer.parseInt(m.group(3)));
            version.setRevision(Integer.parseInt(m.group(4)));
            try {
                version.setRelease(sdf.parse(m.group(5)));
            }
            catch (ParseException ex) {
                ParseVersion.logger.error(ex.getLocalizedMessage());
                version.setRelease(Calendar.getInstance().getTime());
            }
            version.setUname(m.group(6));
        }
        
        return version;
    }
}
