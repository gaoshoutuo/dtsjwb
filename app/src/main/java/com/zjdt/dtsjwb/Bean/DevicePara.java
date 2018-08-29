package com.zjdt.dtsjwb.Bean;

import java.io.Serializable;

public class DevicePara implements Serializable{
    private String deviceN;
    private String deviceP;
    private String deviceT;
    private String deviceB;
    private String deviceNum;

    //新增加内容
    private String life;
    private String yyyy;
    private String mm;
    private String dd;

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

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }

    public String getYyyy() {
        return yyyy;
    }

    public void setYyyy(String yyyy) {
        this.yyyy = yyyy;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public DevicePara(String deviceN, String deviceP, String deviceT, String deviceB, String deviceNum) {
        this.deviceN = deviceN;
        this.deviceP = deviceP;
        this.deviceT = deviceT;
        this.deviceB = deviceB;
        this.deviceNum = deviceNum;
    }

    public DevicePara(String deviceN, String deviceP, String deviceT, String deviceB, String deviceNum, String life, String yyyy, String mm, String dd) {
        this.deviceN = deviceN;
        this.deviceP = deviceP;
        this.deviceT = deviceT;
        this.deviceB = deviceB;
        this.deviceNum = deviceNum;
        this.life = life;
        this.yyyy = yyyy;
        this.mm = mm;
        this.dd = dd;
    }
}
