package com.challenge.quasar.domain.alliance.services;

import com.challenge.quasar.domain.alliance.entities.IncomingCommunication;
import com.challenge.quasar.domain.alliance.entities.OutgoingCommunication;
import com.challenge.quasar.domain.position.services.PositionService;
import com.challenge.quasar.domain.message.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllianceService {

    @Autowired
    PositionService positionService;

    @Autowired
    MessageService messageService;

    public OutgoingCommunication receiveCommunication(IncomingCommunication incomingCommunication)
    {
        double[] position = positionService.getLocation(incomingCommunication.distances());

        return new OutgoingCommunication(position);
    }
}
