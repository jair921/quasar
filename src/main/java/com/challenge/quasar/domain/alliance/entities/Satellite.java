package com.challenge.quasar.domain.alliance.entities;

import com.challenge.quasar.domain.alliance.models.TopSecret;

import java.util.List;

public class Satellite {

    private String name;

    private double distance;

    private List<String> message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public TopSecret buildTopSecret() {

        TopSecret topSecret = new TopSecret();
        topSecret.setSatellite(getName());
        topSecret.setDistance(getDistance());
        topSecret.setMessage(String.join(",", getMessage()));

        return topSecret;

    }
}
