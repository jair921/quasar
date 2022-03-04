package com.challenge.quasar.domain.message.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    public String getMessage(List<List<String>> messageLst) {

        List<String> decodeMessage = new ArrayList<String>();

        for(int i= 0; i < messageLst.get(0).size(); i++) {
            for(List<String> message : messageLst) {
                if(! message.get(i).isEmpty()){
                    decodeMessage.add(i, message.get(i));

                    break;
                }
            }
        }
        return String.join(" ", decodeMessage);
    }
}
