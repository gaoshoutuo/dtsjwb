package com.zjdt.dtsjwb.Bean;

public class InfoBean {
    //老四样
    private String infoName;
    private String infoId;
    private String infoLocation;
    private String infoCompany;

    private int upsNumber;
    private int airNumber;
    private String idcName;
    private String idcId;
    private String idcLocation;
    private String idctype;

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

    public int getUpsNumber() {
        return upsNumber;
    }

    public void setUpsNumber(int upsNumber) {
        this.upsNumber = upsNumber;
    }

    public int getAirNumber() {
        return airNumber;
    }

    public void setAirNumber(int airNumber) {
        this.airNumber = airNumber;
    }

    public InfoBean(String infoName, String infoId, String infoLocation, String infoCompany) {
        this.infoName = infoName;
        this.infoId = infoId;
        this.infoLocation = infoLocation;
        this.infoCompany = infoCompany;
    }

    public InfoBean(int upsNumber, int airNumber, String idcName, String idcId, String idcLocation, String idctype) {
        this.upsNumber = upsNumber;
        this.airNumber = airNumber;
        this.idcName = idcName;
        this.idcId = idcId;
        this.idcLocation = idcLocation;
        this.idctype = idctype;
    }

    public String getIdcName() {
        return idcName;
    }

    public void setIdcName(String idcName) {
        this.idcName = idcName;
    }

    public String getIdcId() {
        return idcId;
    }

    public void setIdcId(String idcId) {
        this.idcId = idcId;
    }

    public String getIdcLocation() {
        return idcLocation;
    }

    public void setIdcLocation(String idcLocation) {
        this.idcLocation = idcLocation;
    }

    public String getIdctype() {
        return idctype;
    }

    public void setIdctype(String idctype) {
        this.idctype = idctype;
    }
}
