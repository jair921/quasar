package com.challenge.quasar.domain.alliance.services;

import com.challenge.quasar.domain.alliance.dao.TopSecretDao;
import com.challenge.quasar.domain.alliance.entities.IncomingCommunication;
import com.challenge.quasar.domain.alliance.entities.OutgoingCommunication;
import com.challenge.quasar.domain.alliance.entities.Satellite;
import com.challenge.quasar.domain.alliance.models.TopSecret;
import com.challenge.quasar.domain.message.exceptions.MessageException;
import com.challenge.quasar.domain.position.exceptions.PositionException;
import com.challenge.quasar.domain.position.services.PositionService;
import com.challenge.quasar.domain.message.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class AllianceService {

    @Autowired
    PositionService positionService;

    @Autowired
    MessageService messageService;

    @Autowired
    Environment env;

    @Autowired
    TopSecretDao topSecretDao;

    public OutgoingCommunication receiveCommunication(IncomingCommunication incomingCommunication) throws PositionException, MessageException {

        int numSatellites = Integer.parseInt(env.getProperty("satellites.size"));

        if(incomingCommunication.getSatellites().size() < numSatellites){
            throw new PositionException("You need at least " + numSatellites + " satellites to locate the spaceship.");
        }

        if(incomingCommunication.secretMessages().size() < numSatellites){
            throw new MessageException("You need at least " + numSatellites + " messages to decode the secret.");
        }

        double[] position = positionService.getLocation(incomingCommunication.distances());
        String secretMessage = messageService.getMessage(incomingCommunication.secretMessages());

        return new OutgoingCommunication(position, secretMessage);
    }

    public OutgoingCommunication receiveCommunicationSplit(Satellite satellite) throws PositionException, MessageException {

        String satellitePoint = env.getProperty("satellites." + satellite.getName() + ".point");

        if(satellitePoint == null) {
            throw new PositionException("Unknown satellite.");
        }

        if(satellite.getMessage().isEmpty()) {
            throw new MessageException("Message is needed.");
        }

        topSecretDao.save(satellite.buildTopSecret());

        return new OutgoingCommunication(null, "Store split message");
    }
}
