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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author João Paulo Siqueira <joao.siqueira@uktech.com.br>
 */
public class BaculaLStatDecoder {
    
    private static Map<String, Integer> base64 = new HashMap<>();
    private static Map<Integer, String> invBase64 = new HashMap<>();
    
    private static void init() {
        if (BaculaLStatDecoder.base64.isEmpty()) {
            BaculaLStatDecoder.base64.put("A", 0);
            BaculaLStatDecoder.base64.put("B", 1);
            BaculaLStatDecoder.base64.put("C", 2);
            BaculaLStatDecoder.base64.put("D", 3);
            BaculaLStatDecoder.base64.put("E", 4);
            BaculaLStatDecoder.base64.put("F", 5);
            BaculaLStatDecoder.base64.put("G", 6);
            BaculaLStatDecoder.base64.put("H", 7);
            BaculaLStatDecoder.base64.put("I", 8);
            BaculaLStatDecoder.base64.put("J", 9);
            BaculaLStatDecoder.base64.put("K", 10);
            BaculaLStatDecoder.base64.put("L", 11);
            BaculaLStatDecoder.base64.put("M", 12);
            BaculaLStatDecoder.base64.put("N", 13);
            BaculaLStatDecoder.base64.put("O", 14);
            BaculaLStatDecoder.base64.put("P", 15);
            BaculaLStatDecoder.base64.put("Q", 16);
            BaculaLStatDecoder.base64.put("R", 17);
            BaculaLStatDecoder.base64.put("S", 18);
            BaculaLStatDecoder.base64.put("T", 19);
            BaculaLStatDecoder.base64.put("U", 20);
            BaculaLStatDecoder.base64.put("V", 21);
            BaculaLStatDecoder.base64.put("W", 22);
            BaculaLStatDecoder.base64.put("X", 23);
            BaculaLStatDecoder.base64.put("Y", 24);
            BaculaLStatDecoder.base64.put("Z", 25);
            BaculaLStatDecoder.base64.put("a", 26);
            BaculaLStatDecoder.base64.put("b", 27);
            BaculaLStatDecoder.base64.put("c", 28);
            BaculaLStatDecoder.base64.put("d", 29);
            BaculaLStatDecoder.base64.put("e", 30);
            BaculaLStatDecoder.base64.put("f", 31);
            BaculaLStatDecoder.base64.put("g", 32);
            BaculaLStatDecoder.base64.put("h", 33);
            BaculaLStatDecoder.base64.put("i", 34);
            BaculaLStatDecoder.base64.put("j", 35);
            BaculaLStatDecoder.base64.put("k", 36);
            BaculaLStatDecoder.base64.put("l", 37);
            BaculaLStatDecoder.base64.put("m", 38);
            BaculaLStatDecoder.base64.put("n", 39);
            BaculaLStatDecoder.base64.put("o", 40);
            BaculaLStatDecoder.base64.put("p", 41);
            BaculaLStatDecoder.base64.put("q", 42);
            BaculaLStatDecoder.base64.put("r", 43);
            BaculaLStatDecoder.base64.put("s", 44);
            BaculaLStatDecoder.base64.put("t", 45);
            BaculaLStatDecoder.base64.put("u", 46);
            BaculaLStatDecoder.base64.put("v", 47);
            BaculaLStatDecoder.base64.put("w", 48);
            BaculaLStatDecoder.base64.put("x", 49);
            BaculaLStatDecoder.base64.put("y", 50);
            BaculaLStatDecoder.base64.put("z", 51);
            BaculaLStatDecoder.base64.put("0", 52);
            BaculaLStatDecoder.base64.put("1", 53);
            BaculaLStatDecoder.base64.put("2", 54);
            BaculaLStatDecoder.base64.put("3", 55);
            BaculaLStatDecoder.base64.put("4", 56);
            BaculaLStatDecoder.base64.put("5", 57);
            BaculaLStatDecoder.base64.put("6", 58);
            BaculaLStatDecoder.base64.put("7", 59);
            BaculaLStatDecoder.base64.put("8", 60);
            BaculaLStatDecoder.base64.put("9", 61);
            BaculaLStatDecoder.base64.put("+", 62);
            BaculaLStatDecoder.base64.put("/", 63);
            
            BaculaLStatDecoder.invBase64.clear();
            for (Map.Entry<String, Integer> entrySet : base64.entrySet()) {
                String key = entrySet.getKey();
                Integer value = entrySet.getValue();
                BaculaLStatDecoder.invBase64.put(value, key);
            }
        }
    }
    
    public static Map<String, Long> decode(String lsstat) {
        BaculaLStatDecoder.init();
        Map<String, Long> out = new LinkedHashMap<>();
        int i=0;
        Long resultado;
        String campos[] = "Device Inode Mode Number_of_hard_links User_ID_of_owner Group_ID_of_owner Device_type Total_size_in_bytes Blocksize Number_of_blocks_allocated Time_of_last_access Time_of_last_modification Time_of_last_change LinkFI Flags Data".split(" ");
        String palavras[] = lsstat.split(" ");
        for (String palavra : palavras) {
            resultado = 0l;
            char letras[] = palavra.toCharArray();
            for (char letra : letras) {
                resultado = resultado << 6;
                resultado += base64.get(String.valueOf(letra));
            }
            out.put(campos[i], resultado);
            i++;
        }
        System.out.print('{');
        for (String p : palavras) {
            System.out.print(p+" ");
        }
        System.out.print('}');
        return out;
    }
    
    public static String encode(Map<String, Long> lsstat) {
        BaculaLStatDecoder.init();
        StringBuilder sb = new StringBuilder();
        List<String> resultato = new ArrayList<>();
        Long valor;
        Long aux;
        int i;
        for (Map.Entry<String, Long> entrySet : lsstat.entrySet()) {
            String key = entrySet.getKey();
            valor = entrySet.getValue();
            i=0;
            do {
                aux = valor & 63;
                resultato.add(invBase64.get(aux.intValue()));
                valor = valor >> 6;
            } while(valor != 0);
            for(i=resultato.size() - 1; i>=0; i--){
                sb.append(resultato.get(i));
            }
            sb.append(' ');
            resultato.clear();
        }
        return sb.toString();
    }
}