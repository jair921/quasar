package com.challenge.quasar.domain.message.services;

import com.challenge.quasar.domain.message.exceptions.MessageException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    public String getMessage(List<List<String>> messageLst) throws MessageException {

        List<String> decodeMessage = new ArrayList<String>();

        try{
            for(int i= 0; i < messageLst.get(0).size(); i++) {
                for(List<String> message : messageLst) {
                    if(! message.get(i).isEmpty()){
                        decodeMessage.add(i, message.get(i));
                        break;
                    }
                }
            }
        } catch (IndexOutOfBoundsException exception){
            throw new MessageException("One of the messages has the wrong length.");
        }

        return String.join(" ", decodeMessage);
    }
}
