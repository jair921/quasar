package com.challenge.quasar.domain.alliance;

import com.challenge.quasar.domain.alliance.entities.IncomingCommunication;
import com.challenge.quasar.domain.alliance.entities.Satellite;
import com.challenge.quasar.domain.alliance.models.TopSecret;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Configurable
public class BuilderAlliance {

    public IncomingCommunication incomingCommunicationFromTopSecretList(List<TopSecret> list) {

        IncomingCommunication ic = new IncomingCommunication();
        List<Satellite> satellites = new ArrayList<Satellite>();

        for( TopSecret topSecret : list) {

            Satellite satellite = new Satellite();
            JSONArray json = new JSONArray(topSecret.getMessage());

            List<String> listMessage = new ArrayList<String>();
            for(int i = 0; i < json.getJSONArray(0).length(); i++){
                listMessage.add(json.getJSONArray(0).get(i).toString());
            }

            satellite.setName(topSecret.getSatellite());
            satellite.setDistance(topSecret.getDistance());
            satellite.setMessage(listMessage);

            satellites.add(satellite);
        }

        ic.setSatellites(satellites);
        return ic;
    }
}
