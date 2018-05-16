package com.zjdt.dtsjwb.Bean;

import java.io.Serializable;

public class DevicePara implements Serializable{
    private String deviceN;
    private String deviceP;
    private String deviceT;
    private String deviceB;
    private String deviceNum;

    public String getDeviceN() {
        return deviceN;
    }

    public void setDeviceN(String deviceN) {
        this.deviceN = deviceN;
    }

    public String getDeviceP() {
        return deviceP;
    }

    public void setDeviceP(String deviceP) {
        this.deviceP = deviceP;
    }

    public String getDeviceT() {
        return deviceT;
    }

    public void setDeviceT(String deviceT) {
        this.deviceT = deviceT;
    }

    public String getDeviceB() {
        return deviceB;
    }

    public void setDeviceB(String deviceB) {
        this.deviceB = deviceB;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public DevicePara() {

    }

    public DevicePara(String deviceN, String deviceP, String deviceT, String deviceB, String deviceNum) {
        this.deviceN = deviceN;
        this.deviceP = deviceP;
        this.deviceT = deviceT;
        this.deviceB = deviceB;
        this.deviceNum = deviceNum;
    }
}
