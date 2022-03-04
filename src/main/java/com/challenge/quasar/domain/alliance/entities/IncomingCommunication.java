package com.challenge.quasar.domain.alliance.entities;

import java.util.ArrayList;
import java.util.List;

public class IncomingCommunication {

    private List<Satellite> satellites;

    public double[] distances() {

        double[] distances = new double[3];

        for(int i = 0; i < satellites.size(); i++){
            distances[i] = satellites.get(i).getDistance();
        }

        return distances;
    }

    public List<List<String>> secretMessages() {
        List<List<String>> secretMessages = new ArrayList<List<String>>();
        for(Satellite satellite : satellites){
            secretMessages.add(satellite.getMessage());
        }
        return secretMessages;
    }

    public List<Satellite> getSatellites() {
        return satellites;
    }

    public void setSatellites(List<Satellite> satellites) {
        this.satellites = satellites;
    }
}
