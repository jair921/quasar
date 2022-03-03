package com.challenge.quasar.domain.alliance.services;

import com.challenge.quasar.domain.location.services.LocationService;
import com.challenge.quasar.domain.message.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllianceService {

    @Autowired
    LocationService locationService;

    @Autowired
    MessageService messageService;
}
