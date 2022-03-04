package com.challenge.quasar.controllers;

import com.challenge.quasar.domain.alliance.BuilderAlliance;
import com.challenge.quasar.domain.alliance.dao.TopSecretDao;
import com.challenge.quasar.domain.alliance.entities.IncomingCommunication;
import com.challenge.quasar.domain.alliance.entities.OutgoingCommunication;
import com.challenge.quasar.domain.alliance.entities.Satellite;
import com.challenge.quasar.domain.alliance.services.AllianceService;
import com.challenge.quasar.domain.message.exceptions.MessageException;
import com.challenge.quasar.domain.position.exceptions.PositionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AllianceController {

    @Autowired
    private AllianceService allianceService;

    @Autowired
    private BuilderAlliance builderAlliance;

    @Autowired
    private TopSecretDao topSecretDao;

    @RequestMapping(path = "/topsecret")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity topSecret(@RequestBody IncomingCommunication incomingCommunication)
    {
        try{
            return ResponseEntity.ok().body(allianceService.receiveCommunication(incomingCommunication));
        }catch (MessageException | PositionException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @RequestMapping(path = "/topsecret_split/{satelliteName}")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity topSecret(@RequestBody Satellite satellite, @PathVariable String satelliteName)
    {
        try {
            satellite.setName(satelliteName);
            return ResponseEntity.ok().body(allianceService.receiveCommunicationSplit(satellite));
        }catch (MessageException | PositionException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new OutgoingCommunication(null, exception.getMessage()));
        }
    }

    @RequestMapping(path = "/topsecret_split")
    @GetMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity topSecret()
    {
        try{
            return ResponseEntity.ok().body(allianceService.receiveCommunication(
                    builderAlliance.incomingCommunicationFromTopSecretList( topSecretDao.list() )
            ));
        }catch (MessageException | PositionException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new OutgoingCommunication(null, exception.getMessage()));
        }
    }
}
