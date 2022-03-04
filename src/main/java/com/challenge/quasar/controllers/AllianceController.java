package com.challenge.quasar.controllers;

import com.challenge.quasar.domain.alliance.entities.IncomingCommunication;
import com.challenge.quasar.domain.alliance.services.AllianceService;
import com.challenge.quasar.domain.message.exceptions.MessageException;
import com.challenge.quasar.domain.position.exceptions.PositionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AllianceController {

    @Autowired
    private AllianceService allianceService;

    @RequestMapping(path = "/topsecret")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity topSecret(@RequestBody IncomingCommunication incomingCommunication)
    {
        try{
            return ResponseEntity.ok().body(allianceService.receiveCommunication(incomingCommunication));
        }catch (MessageException | PositionException exception){
            return ResponseEntity.notFound().build();
        }
    }
}
