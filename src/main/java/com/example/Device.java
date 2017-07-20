package com.example;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by JackNiu on 2017/7/20.
 */
@XmlRootElement(name="device")
public class Device {
    private String decicpIp;
    private int deviceStatus;
    public  Device()
    {}
    public Device(String decicpIp){
        super();
        this.decicpIp=decicpIp;
    }
    public String getIp(){
        return this.decicpIp;
    }
    public void setIp(String decicpIp){
        this.decicpIp=decicpIp;
    }
    @XmlAttribute
    public int getStatus(){
        return deviceStatus;
    }
    public void setStatus(int deviceStatus){
        this.deviceStatus=deviceStatus;
    }

}
