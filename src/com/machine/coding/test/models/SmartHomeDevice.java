package com.machine.coding.test.models;

public class SmartHomeDevice {

    public String name;
    public String location;
    private boolean turnedOn;

    public SmartHomeDevice(String name, String location) {
        this.name = name;
        this.location = location;
        this.turnedOn = false;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public boolean isTurnedOn() {
        return turnedOn;
    }

    public void turnOn() {
        turnedOn = true;
    }

    public void turnOff() {
        turnedOn = false;
    }
}
