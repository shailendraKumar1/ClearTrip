package com.machine.coding.test.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InterfaceDevice {

    private final String name;
    private final String location;
    private final String activationKeyWord;
    private final List<SmartHomeDevice> smartHomeDeviceList;
    private final Map<String, Boolean> smartHomeDeviceConnectionMap;

    public InterfaceDevice(String name, String location, String activationKeyWord) {
        this.name = name;
        this.location = location;
        this.activationKeyWord = activationKeyWord;
        this.smartHomeDeviceList = new ArrayList<>();
        this.smartHomeDeviceConnectionMap = new HashMap<>();
    }

    public void addSmartHomeDeviceConnectedToMap(String name) {
        smartHomeDeviceConnectionMap.put(name, true);
    }

    public void removeSmartHomeDeviceConnectedToMap(String name) {
        smartHomeDeviceConnectionMap.remove(name, true);
    }

    public boolean checkSmartHomeDeviceIsConnected(String name) {
        if(smartHomeDeviceConnectionMap.get(name)) {
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getActivationKeyWord() {
        return activationKeyWord;
    }

    public List<SmartHomeDevice> getSmartHomeDeviceList() {
        return smartHomeDeviceList;
    }

    public void addConnectedDevice(SmartHomeDevice smartHomeDevice) {
        smartHomeDeviceList.add(smartHomeDevice);
    }

    public void removeConnectedDevice(SmartHomeDevice smartHomeDevice) {
        smartHomeDeviceList.remove(smartHomeDevice);
    }


}
