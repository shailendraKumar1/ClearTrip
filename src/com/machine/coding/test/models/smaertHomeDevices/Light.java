package com.machine.coding.test.models.smaertHomeDevices;

import com.machine.coding.test.enums.COLOURS;
import com.machine.coding.test.models.SmartHomeDevice;

public class Light extends SmartHomeDevice {

    private int brightness;
    private String colour;

    public Light(String name, String location) {
        super(name, location);
        this.brightness = 0;
        this.colour = "WHITE";
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {

        if(brightness>=1 && brightness<=10) {
            this.brightness = brightness;
        }
        else  {
            System.out.println("Incorrect brightness found in the req "+ brightness);
        }
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        if(COLOURS.BLUE.name().equals(colour) || COLOURS.WHITE.name().equals(colour) ||
                COLOURS.RED.name().equals(colour) || COLOURS.GREEN.name().equals(colour)) {
            this.colour = colour;
        }
    }
}
