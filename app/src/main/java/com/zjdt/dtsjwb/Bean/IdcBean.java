package com.zjdt.dtsjwb.Bean;

import java.io.Serializable;

public class IdcBean implements Serializable{//沉没
    private String idcId;
    private String idcName;
    private String idcType;
    private String userId;
    private String userName;
    private String deepLocation;
    private String simpleLocation;

    public String getIdcName() {
        return idcName;
    }

    public void setIdcName(String idcName) {
        this.idcName = idcName;
    }

    public String getIdcType() {
        return idcType;
    }

    public void setIdcType(String idcType) {
        this.idcType = idcType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeepLocation() {
        return deepLocation;
    }

    public void setDeepLocation(String deepLocation) {
        this.deepLocation = deepLocation;
    }

    public String getSimpleLocation() {
        return simpleLocation;
    }

    public void setSimpleLocation(String simpleLocation) {
        this.simpleLocation = simpleLocation;
    }

    public String getIdcId() {
        return idcId;
    }

    public void setIdcId(String idcId) {
        this.idcId = idcId;
    }

    public IdcBean(String idcId, String idcName, String idcType, String userId, String userName, String deepLocation, String simpleLocation) {
        this.idcId = idcId;
        this.idcName = idcName;
        this.idcType = idcType;
        this.userId = userId;
        this.userName = userName;
        this.deepLocation = deepLocation;
        this.simpleLocation = simpleLocation;
    }

    public IdcBean(String idcId, String idcName, String idcType, String simpleLocation) {
        this.idcId = idcId;
        this.idcName = idcName;
        this.idcType = idcType;
        this.simpleLocation = simpleLocation;
    }
}
