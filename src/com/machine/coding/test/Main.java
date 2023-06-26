package com.machine.coding.test;

import com.machine.coding.test.service.SmartHomeSystem;

public class Main {

    public static void main(String[] args) {

        SmartHomeSystem smartHomeSystem = new SmartHomeSystem();

        smartHomeSystem.addInterfaceDevice("Google Home", "Living Room","Ok, Google");
        smartHomeSystem.addInterfaceDevice("Alexa", "Drawing Room","Alexa");
        smartHomeSystem.addInterfaceDevice("Alexa", "Kitchen","Alexa");

       smartHomeSystem.addSmartHomeDevice("Alexa", "Light","Drawing Room");
        smartHomeSystem.addSmartHomeDevice("Alexa", "Light","Kitchen");
        smartHomeSystem.addSmartHomeDevice("Ok, Google", "Fan", "Living Room");

        smartHomeSystem.addSmartHomeDevice("Alexa", "Smart charger",  null);
        smartHomeSystem.addSmartHomeDevice("Alexa", "Chimney",  "Kitchen");
        smartHomeSystem.connectSmartHomeDevice("Alexa","RGB Light","Drawing Room");

        smartHomeSystem.giveCommand("Alexa", "Light", "Drawing Room", "ON");
        smartHomeSystem.giveCommand("Alexa", "Light", "Drawing Room", "BLACK");
        smartHomeSystem.giveCommand("Alexa", "RGB Light", "Drawing Room", "ON");
        smartHomeSystem.giveCommand("Ok, Google", "Fan", "Living Room","ON");

        smartHomeSystem.giveCommand("Ok, Google", "Fan", "Living Room","5");
        smartHomeSystem.giveCommand("Ok, Google", "Fan", "Living Room","7");
        smartHomeSystem.giveCommand("Alexa", "Light", "Drawing Room", "8");
        smartHomeSystem.giveCommand("Alexa", "Light", "Kitchen", "ON");
        smartHomeSystem.giveCommand("Alexa", "Light", "Kitchen", "RED");
        smartHomeSystem.giveCommand("Alexa", "Chimney", "Kitchen", "ON");
        smartHomeSystem.giveCommand("Alexa", "Chimney", "Kitchen", "OFF");
        smartHomeSystem.giveCommand("Alexa", "Smart charger", null, "ON");
        smartHomeSystem.giveCommand("Alexa", "Smart charger", null, "OFF");
        smartHomeSystem.giveCommand("Alexa", "Light", "Kitchen", "OFF");
        smartHomeSystem.giveCommand("Ok, Google", "Fan", "Living Room","OFF");
        smartHomeSystem.giveCommand("Ok, Google", "Fan", "Living Room","3");

        smartHomeSystem.printConnectedDevices("Alexa", "Kitchen");


    }
}
