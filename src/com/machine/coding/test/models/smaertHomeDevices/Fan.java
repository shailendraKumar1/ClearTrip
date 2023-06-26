package com.machine.coding.test.models.smaertHomeDevices;

import com.machine.coding.test.models.SmartHomeDevice;

public class Fan extends SmartHomeDevice {

    private int speed;

    public Fan(String name, String location) {
        super(name, location);
        this.speed = 0;
    }



    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if(speed >=1 && speed <=5) {
            this.speed = speed;
        }else {
            System.out.println("Sorry, can not set fan speed "+ speed);
        }
    }
}
