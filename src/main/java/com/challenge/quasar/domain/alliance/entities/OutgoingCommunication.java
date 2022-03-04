package com.challenge.quasar.domain.alliance.entities;

public class OutgoingCommunication {

    private double[] position;
    private String message;

    public OutgoingCommunication(double[] location, String message)
    {
        this.position = location;
        this.message = message;
    }

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
