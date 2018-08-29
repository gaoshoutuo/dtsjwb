package com.zjdt.dtsjwb.Bean;

public class OfflineBean implements AllBean{
    private String time;
    private String engname;
    private String idcname;
    private String buss;
    private String filename;

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
}
