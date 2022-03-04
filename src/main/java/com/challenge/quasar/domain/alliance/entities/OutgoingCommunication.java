package com.challenge.quasar.domain.alliance.entities;

public class OutgoingCommunication {

    private double[] position;

    public OutgoingCommunication(double[] location)
    {
        this.position = location;
    }

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }
}
