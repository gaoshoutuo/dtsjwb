package com.zjdt.dtsjwb.Bean;

public class InfoBean {
    private String infoName;
    private String infoId;
    private String infoLocation;
    private String infoCompany;

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getInfoLocation() {
        return infoLocation;
    }

    public void setInfoLocation(String infoLocation) {
        this.infoLocation = infoLocation;
    }

    public String getInfoCompany() {
        return infoCompany;
    }

    public void setInfoCompany(String infoCompany) {
        this.infoCompany = infoCompany;
    }

    public InfoBean(String infoName, String infoId, String infoLocation, String infoCompany) {
        this.infoName = infoName;
        this.infoId = infoId;
        this.infoLocation = infoLocation;
        this.infoCompany = infoCompany;
    }
}
