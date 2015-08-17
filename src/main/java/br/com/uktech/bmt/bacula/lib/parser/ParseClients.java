package br.com.uktech.bmt.bacula.lib.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.LoggerFactory;

public class ParseClients {
    
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ParseClients.class);
    
    public int parseNumber(String linha) {
        this.logger.trace("Processando linha: {}", linha);
        int number = 0;
        Pattern p = Pattern.compile(" *(clientid:|autoprune:|fileretention:|jobretention:) *(.+)");
        Matcher m = p.matcher(linha);
        while(m.find()) {
            number = Integer.parseInt(m.group(2));
        }
        return number;
    }
    
    public String parseWord(String linha) {
        this.logger.trace("Processando linha: {}", linha);
        String word = "";
        Pattern p = Pattern.compile(" *([u]*name:) +(.*)");
        Matcher m = p.matcher(linha);
        while(m.find()) {
            word = m.group(2);
        }
        return word;
    }
}