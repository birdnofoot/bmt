package br.com.uktech.bmt.bacula.lib.parser;

import br.com.uktech.bmt.bacula.lib.Constants;
import br.com.uktech.bmt.bacula.bean.BaculaClient;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
//import br.com.uktech.bmt.bacula.bean.ListClient;

public class ParseListClient {
    
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ParseListClient.class);
    
    public List<BaculaClient> parse(String input) {
        this.logger.info("Realiza o parse do ListClient");
        List<BaculaClient> clients = new ArrayList<>();
        
        String temp;
        Parser p = new Parser(input);
        
        do {
            temp = p.getToken(Constants.CR);
            if(temp!=null) {
                temp = temp.trim();
                if(temp.matches(" *(clientid:) *(.+)")) {
                    BaculaClient c = new BaculaClient();
                    c.setId(new ParseClients().parseNumber(temp));
                    temp = p.getToken(Constants.CR);
                    c.setName(new ParseClients().parseWord(temp));
                    temp = p.getToken(Constants.CR);
                    c.setUname(new ParseClients().parseWord(temp));
                    temp = p.getToken(Constants.CR);
                    c.setAutoprune(new ParseClients().parseNumber(temp)==1);
                    temp = p.getToken(Constants.CR);
                    c.setFileretention(new ParseClients().parseNumber(temp.replace(",", "")));
                    temp = p.getToken(Constants.CR);
                    c.setJobretention(new ParseClients().parseNumber(temp.replace(",", "")));
                    if(c.getId()!=0 && c.getName()!=null && c.getUname() != null && c.getAutoprune() != null && c.getFileretention() != 0 && c.getJobretention() != 0) {
                        clients.add(c);
                    }
                }
            }
        } while(temp != null);
        
        return clients;
    }
}
