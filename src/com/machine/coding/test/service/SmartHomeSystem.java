package com.machine.coding.test.service;

import com.machine.coding.test.enums.COLOURS;
import com.machine.coding.test.enums.COMMAND_VALUE;
import com.machine.coding.test.enums.SMART_DEVICE_TYPE;
import com.machine.coding.test.models.InterfaceDevice;
import com.machine.coding.test.models.SmartHomeDevice;
import com.machine.coding.test.models.smaertHomeDevices.Fan;
import com.machine.coding.test.models.smaertHomeDevices.GenericElectricalDevice;
import com.machine.coding.test.models.smaertHomeDevices.Light;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmartHomeSystem {

    private final List<InterfaceDevice> interfaceDeviceList;
    private final List<SmartHomeDevice> smartHomeDeviceList;
    private final Map<String, String> smartHomeDeviceConnectedInterfaceDevice;
    public SmartHomeSystem() {
        this.interfaceDeviceList = new ArrayList<>();
        this.smartHomeDeviceList = new ArrayList<>();
        this.smartHomeDeviceConnectedInterfaceDevice = new HashMap<>();
    }

    //add interface device
    public void addInterfaceDevice(String name, String location, String activationKeyWord) {
        InterfaceDevice interfaceDevice = new InterfaceDevice(name, location, activationKeyWord);
        interfaceDeviceList.add(interfaceDevice);
        System.out.println("ADDED");
    }

    //add smartHomeDevice
    public void addSmartHomeDevice(String activationKey, String name, String location) {
        SmartHomeDevice smartHomeDevice;

        if(name.isEmpty()) {
            System.out.println("Invalid input");
        }

        if(SMART_DEVICE_TYPE.Fan.name().equals(name)) {
                smartHomeDevice = new Fan(name, location);
        }
        else if(SMART_DEVICE_TYPE.Light.name().equals(name)) {
            smartHomeDevice = new Light(name, location);
        }
        else {
            smartHomeDevice = new GenericElectricalDevice(name, location);
        }

        String interfaceDeviceName = null;

        if(activationKey!= null) {
            for (InterfaceDevice interfaceDevice : interfaceDeviceList) {
                if (interfaceDevice.getActivationKeyWord().equals(activationKey)) {
                    interfaceDevice.addConnectedDevice(smartHomeDevice);
                    interfaceDeviceName = interfaceDevice.getName();
                    break;
                }
            }
        }
        if(interfaceDeviceName != null) {
            smartHomeDeviceConnectedInterfaceDevice.put(smartHomeDevice.getName()+smartHomeDevice.getLocation(), interfaceDeviceName);
        }
        smartHomeDeviceList.add(smartHomeDevice);
        System.out.println("ADDED");
    }

    //give command

    public void giveCommand(String activationKey, String deviceName, String deviceLocation, String commandValue) {

        SmartHomeDevice targetDevice = null;

        for(InterfaceDevice interfaceDevice : interfaceDeviceList) {

            if(interfaceDevice.getActivationKeyWord().equals(activationKey)) {

                if(deviceLocation == null) {
                    deviceLocation = "";
                }
                else if(smartHomeDeviceConnectedInterfaceDevice.size()==0 || smartHomeDeviceConnectedInterfaceDevice.get(deviceName+deviceLocation) == null) {
                    System.out.println(deviceName+ " is not connected yet to " + interfaceDevice.getName());
                    return;
                }
                for(SmartHomeDevice smartHomeDevice : smartHomeDeviceList) {

                    if(smartHomeDevice.getLocation() != null) {
                        if (smartHomeDevice.getName().equals(deviceName) && smartHomeDevice.getLocation().equals(deviceLocation)) {
                            targetDevice = smartHomeDevice;
                            break;
                        }
                    }else {
                        if (smartHomeDevice.getName().equals(deviceName)) {
                            targetDevice = smartHomeDevice;
                            break;
                        }
                    }
                }
                break;
            }
        }

        if(targetDevice != null) {

            if(targetDevice instanceof Fan) {
                Fan fan = (Fan) targetDevice;
                if(fan.isTurnedOn() && COMMAND_VALUE.ON.name().equals(commandValue) ||
                        (!fan.isTurnedOn()) && COMMAND_VALUE.OFF.name().equals(commandValue)) {
                    System.out.println("Fan already in "+ commandValue + " State");
                }
                else if(COMMAND_VALUE.ON.name().equals(commandValue) && (!fan.isTurnedOn())) {
                    fan.turnOn();
                    System.out.println("Ok, " + deviceLocation + " "+ deviceName + " Turned "+ commandValue);
                }
                else if(COMMAND_VALUE.OFF.name().equals(commandValue) && fan.isTurnedOn()){
                    fan.turnOff();
                    System.out.println("Ok, " + deviceLocation + " "+ deviceName + " Turned "+ commandValue);
                } else {

                    try {
                        int speed = Integer.parseInt(commandValue);
                        if(fan.isTurnedOn()) {
                            fan.setSpeed(speed);
                            System.out.println("Ok, " + deviceLocation + " " + deviceName + " speed set to " + speed);
                        }else {
                            System.out.println("Sorry, "+ deviceLocation+ " " + deviceName + " is not turned on");
                        }
                        return;
                    }catch (NumberFormatException e) {
                        System.out.println("Sorry, invalid command value for the speed");
                    }
                }
            }
            else if (targetDevice instanceof  Light) {
                Light light = (Light) targetDevice;

                if(light.isTurnedOn() && COMMAND_VALUE.ON.name().equals(commandValue) ||
                        (!light.isTurnedOn()) && COMMAND_VALUE.OFF.name().equals(commandValue)) {
                    System.out.println(deviceName + " already in "+ commandValue + " State");
                }
                else if(COMMAND_VALUE.ON.name().equals(commandValue) && (!light.isTurnedOn())) {
                    light.turnOn();
                    System.out.println("Ok, " + deviceLocation + " "+ deviceName + " Turned "+ commandValue);
                }
                else if(COMMAND_VALUE.OFF.name().equals(commandValue) && light.isTurnedOn()){
                    light.turnOff();
                    System.out.println("Ok, " + deviceLocation + " "+ deviceName + " Turned "+ commandValue);
                }

                else {
                    if(COLOURS.BLACK.name().equals(commandValue)) {
                        System.out.println("Sorry , " + deviceLocation + " light can not set to be black");
                        return;
                    }

                    else if(!light.isTurnedOn()) {
                        System.out.println("Sorry , " + deviceLocation + " light can not set to be "+ commandValue + " because light need to be turned on");
                        return;
                    }
                    else if(!light.getColour().equals(commandValue)) {
                        light.setColour(commandValue);
                        System.out.println("Ok, Light of " + deviceLocation+ " set colour in "+commandValue);
                        return;
                    }
                    else {
                        try {
                            int brightness = Integer.parseInt(commandValue);
                            light.setBrightness(brightness);
                        } catch (NumberFormatException e) {
                            System.out.println("Sorry, invalid command value for the brightness");
                        }
                    }
                }

            }
            else if(targetDevice instanceof GenericElectricalDevice) {
                GenericElectricalDevice genericElectricalDevice = (GenericElectricalDevice) targetDevice;
                if(genericElectricalDevice.isTurnedOn() && COMMAND_VALUE.ON.name().equals(commandValue) ||
                        (!genericElectricalDevice.isTurnedOn()) && COMMAND_VALUE.OFF.name().equals(commandValue)) {
                    System.out.println(deviceName +" already in "+ commandValue + " State");
                }
                else if(COMMAND_VALUE.ON.name().equals(commandValue) && (!genericElectricalDevice.isTurnedOn())) {
                    genericElectricalDevice.turnOn();
                    System.out.println("Ok, " + deviceLocation + " "+ deviceName + " Turned "+ commandValue);
                }
                else if(COMMAND_VALUE.OFF.name().equals(commandValue) && genericElectricalDevice.isTurnedOn()){
                    genericElectricalDevice.turnOff();
                    System.out.println("Ok, " + deviceLocation + " "+ deviceName + " Turned "+ commandValue);
                }
            }
            else {
                System.out.println("Sorry, Invalid command found for the device ");
            }
        }else {
            System.out.println("Sorry, Device not found");
        }
    }

    public void printConnectedDevices(String activationKey, String location) {
        InterfaceDevice interfaceDevice = null;

        for(InterfaceDevice interfaceDevice1 : interfaceDeviceList) {
            if(interfaceDevice1.getActivationKeyWord().equals(activationKey)) {
                interfaceDevice = interfaceDevice1;
                break;
            }
        }

        if(interfaceDevice != null) {

            List<SmartHomeDevice> connectedDevices = interfaceDevice.getSmartHomeDeviceList();
            System.out.println("Connected devices : ");

            for(SmartHomeDevice smartHomeDevice : connectedDevices) {

                if(smartHomeDevice.getLocation() != null) {
                    if (smartHomeDevice.getLocation().equals(location)) {
                        System.out.println(smartHomeDevice.getName() + "  " + smartHomeDevice.getLocation() + "  " + (smartHomeDevice.isTurnedOn() ? COMMAND_VALUE.ON.name() : COMMAND_VALUE.OFF.name()));
                    }
                }else {
                    System.out.println(smartHomeDevice.getName() + "  " + (smartHomeDevice.isTurnedOn() ? COMMAND_VALUE.ON.name() : COMMAND_VALUE.OFF.name()));
                }
            }
        }else {

            System.out.println("Sorry, Interface device not found");
        }
    }


    public void connectSmartHomeDevice(String activationKey, String name, String location) {
        SmartHomeDevice targetDevice = null;

        if(activationKey.isEmpty() || name.isEmpty()) {
            System.out.println("Invalid input");
        }
        for(SmartHomeDevice device : smartHomeDeviceList) {
            if (location != null) {
                if (device.getName().equals(name) && device.getLocation().equals(location)) {
                    targetDevice = device;
                    break;
                }
            }else {
                if (device.getName().equals(name)) {
                    targetDevice = device;
                    break;
                }
            }
        }


        if(targetDevice!= null) {

            for(InterfaceDevice interfaceDevice: interfaceDeviceList) {

                if(interfaceDevice.getActivationKeyWord().equals(activationKey)) {
                    interfaceDevice.addConnectedDevice(targetDevice);
                    interfaceDevice.addSmartHomeDeviceConnectedToMap(targetDevice.name);
                    if(location == null) {
                        location="";
                    }
                    smartHomeDeviceConnectedInterfaceDevice.put(name+location, interfaceDevice.getName());
                    System.out.println("Ok , " + name +" Connected to " + interfaceDevice.getName());
                    return;
                }
            }
            System.out.println("Sorry, Interface device not found");
        }else {
            System.out.println("Sorry, Device not found");
        }
    }


    public void disconnectSmartHomeDevice(String activationKey, String deviceName, String location) {

        SmartHomeDevice targetDevice = null;

        for(InterfaceDevice interfaceDevice : interfaceDeviceList) {

            if(interfaceDevice.getActivationKeyWord().equals(activationKey)) {

                for(SmartHomeDevice device : interfaceDevice.getSmartHomeDeviceList()) {

                    if(location!= null) {
                        if (device.getName().equals(deviceName) && device.getLocation().equals(location)) {
                            targetDevice = device;
                            break;
                        }
                    }else {
                        if (device.getName().equals(deviceName)) {
                            targetDevice = device;
                            break;
                        }
                    }
                }
                break;
            }
        }

        if(targetDevice!= null) {
            for(InterfaceDevice interfaceDevice : interfaceDeviceList) {
                interfaceDevice.removeConnectedDevice(targetDevice);
                interfaceDevice.removeSmartHomeDeviceConnectedToMap(targetDevice.getName());
            }
            System.out.println("Disconnected from all interface devices");
        }else {
            System.out.println("Sorry, Device not found");
        }
    }

}
