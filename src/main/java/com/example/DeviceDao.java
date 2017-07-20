package com.example;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by JackNiu on 2017/7/20.
 */
public class DeviceDao {
    ConcurrentHashMap<String,Device> fakeDB = new ConcurrentHashMap<>();
    public DeviceDao(){
        fakeDB.put("10.11.58.163",new Device("10.11.58.163"));
        fakeDB.put("10.11.58.164",new Device("10.11.58.164"));
    }
    public Device getDevice(String ip){
        return fakeDB.get(ip);
    }
    public Device updateDevice(Device device){
        String ip = device.getIp();
        fakeDB.put(ip,device);
        return fakeDB.get(ip);
    }
}
