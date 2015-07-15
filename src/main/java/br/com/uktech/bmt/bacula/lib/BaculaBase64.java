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
public class BaculaBase64 {
    
    private static char base64Digits[] = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
    };
    
    public static String binToBase64(byte data[]) {
        StringBuffer buf = new StringBuffer();

        int reg, save, mask;
        int rem, i;

        reg = 0;
        rem = 0;

        int binlen = data.length;

        for (i=0; i < binlen; ) {
            if (rem < 6) {
                reg <<= 8;
                reg |= data[i++];
                rem += 8;
            }
            save = reg;
            reg >>= (rem - 6);
            buf.append(base64Digits[(byte)reg & 0x3F]);
            reg = save;
            rem -= 6;
        }
        if (rem != 0) {
            mask = (1 << rem) - 1;
            buf.append(base64Digits[reg & mask]);
        }
        buf.append((char)0);
        return buf.toString();
    }
}
