package com.zjdt.dtsjwb.Bean;

public class OfflineBean implements AllBean{
    private String time;
    private String engname;
    private String idcname;
    private String buss;
    private String filename;

    //private String engname;
    private String cusId;
    private String cusName;
    private String idcId;
    private String location;
    private String upstime;
    private String airtime;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEngname() {
        return engname;
    }

    public void setEngname(String engname) {
        this.engname = engname;
    }

    public String getIdcname() {
        return idcname;
    }

    public void setIdcname(String idcname) {
        this.idcname = idcname;
    }

    public String getBuss() {
        return buss;
    }

    public void setBuss(String buss) {
        this.buss = buss;
    }

    public OfflineBean(String time, String engname, String idcname, String buss) {
        this.time = time;
        this.engname = engname;
        this.idcname = idcname;
        this.buss = buss;
    }

    public OfflineBean(String time, String engname, String idcname, String buss, String filename) {
        this.time = time;
        this.engname = engname;
        this.idcname = idcname;
        this.buss = buss;
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getIdcId() {
        return idcId;
    }

    public void setIdcId(String idcId) {
        this.idcId = idcId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUpstime() {
        return upstime;
    }

    public void setUpstime(String upstime) {
        this.upstime = upstime;
    }

    public String getAirtime() {
        return airtime;
    }

    public void setAirtime(String airtime) {
        this.airtime = airtime;
    }

    public OfflineBean(String cusId, String cusName, String idcId, String location, String upstime, String airtime) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.idcId = idcId;
        this.location = location;
        this.upstime = upstime;
        this.airtime = airtime;
    }
}
