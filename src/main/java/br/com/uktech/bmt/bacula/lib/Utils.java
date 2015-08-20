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

import br.com.uktech.bmt.bacula.exceptions.BaculaNoInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Carlos Alberto Cipriano Korovsky <carlos.korovsky@uktech.com.br>
 */
public class Utils {
    
    public static String convertToHexString(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; ++i) {
            sb.append(Integer.toHexString((b[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }
    
    public static byte[] convertToByteArray(int value) {
        byte[] byteArray = new byte[4];
        byteArray[0] = (byte) ((value >> 24) & 0xFF);
        byteArray[1] = (byte) ((value >> 16) & 0xFF);
        byteArray[2] = (byte) ((value >> 8) & 0xFF);
        byteArray[3] = (byte) (value & 0xFF);
        return byteArray;
    }
    
    public static Integer convertToInteger(byte byteArray[]) throws BaculaNoInteger {
        if (byteArray.length != 4) {
            throw new BaculaNoInteger();
        }
        short b;
        int result = 0;

        for (int i=0; i<4; i++) {
            b = (short)byteArray[i];
            b &= 0x00FF;
            result <<= 8;
            result |= (int)b;
        }
        return result;
    }
    
    public static Calendar toCalendar(String linha) {
        Calendar calendar = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.Bacula.DATE_FORMAT, Locale.US);
            calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(linha));
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }
        return calendar;
    }
    
    public static Calendar toAnotherCalendar(String linha) {
        Calendar calendar = null;
        try {
            if(linha!=null) {
                SimpleDateFormat sdf = new SimpleDateFormat(Constants.Bacula.ANOTHER_DATE_FORMAT, Locale.US);
                calendar = Calendar.getInstance();
                calendar.setTime(sdf.parse(linha));
            }
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }
        return calendar;
    }
    
    public static String calendarToString(Calendar calendar) {
        if(calendar!=null) {
            Date date = calendar.getTime();
            SimpleDateFormat format = new SimpleDateFormat(Constants.Bacula.DATE_FORMAT);
            return format.format(date);
        }
        return null;
    }
    
    public static String calendarToStringAnotherCalendar(Calendar calendar) {
        if(calendar!=null) {
            Date date = calendar.getTime();
            SimpleDateFormat format = new SimpleDateFormat(Constants.Bacula.ANOTHER_DATE_FORMAT, Locale.US);
            return format.format(date);
        }
        return null;
    }
}