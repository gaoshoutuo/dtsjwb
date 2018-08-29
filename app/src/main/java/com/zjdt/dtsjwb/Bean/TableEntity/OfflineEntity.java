package com.zjdt.dtsjwb.Bean.TableEntity;

import com.zjdt.dtsjwb.Bean.AllBean;

public class OfflineEntity implements AllBean{

    // public static final String []DTSJOFFLINEMSG={"dtsjofflinemsg","id","timerecord","json_1","json_2","idc_id","idc_name","idc_type","idc_location"
    //            ,"user_id","eng_id","bussiness_type","iswatch","eng_name","blank_1","blank_2","blank_3"};

    private String timerecord;
    private String json1;
    private String json2;

    private String idcName;
    private String idcId;
    private String idcType;
    private String idcLocation;

    private String userId;
    private String engId;
    private String bussinessType;
    private String iswatch;
    private String engName;
    private String blank1;
    private String blank2;
    private String blank3;

    public String getTimerecord() {
        return timerecord;
    }

    public void setTimerecord(String timerecord) {
        this.timerecord = timerecord;
    }

    public String getJson1() {
        return json1;
    }

    public void setJson1(String json1) {
        this.json1 = json1;
    }

    public String getJson2() {
        return json2;
    }

    public void setJson2(String json2) {
        this.json2 = json2;
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

    public String getIdcType() {
        return idcType;
    }

    public void setIdcType(String idcType) {
        this.idcType = idcType;
    }

    public String getIdcLocation() {
        return idcLocation;
    }

    public void setIdcLocation(String idcLocation) {
        this.idcLocation = idcLocation;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEngId() {
        return engId;
    }

    public void setEngId(String engId) {
        this.engId = engId;
    }

    public String getBussinessType() {
        return bussinessType;
    }

    public void setBussinessType(String bussinessType) {
        this.bussinessType = bussinessType;
    }

    public String getIswatch() {
        return iswatch;
    }

    public void setIswatch(String iswatch) {
        this.iswatch = iswatch;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getBlank1() {
        return blank1;
    }

    public void setBlank1(String blank1) {
        this.blank1 = blank1;
    }

    public String getBlank2() {
        return blank2;
    }

    public void setBlank2(String blank2) {
        this.blank2 = blank2;
    }

    public String getBlank3() {
        return blank3;
    }

    public void setBlank3(String blank3) {
        this.blank3 = blank3;
    }

    public OfflineEntity(String timerecord, String json1, String json2, String idcName,
                         String idcId, String idcType, String idcLocation, String userId,
                         String engId, String bussinessType, String iswatch, String engName,
                         String blank1, String blank2, String blank3) {
        this.timerecord = timerecord;
        this.json1 = json1;
        this.json2 = json2;
        this.idcName = idcName;
        this.idcId = idcId;
        this.idcType = idcType;
        this.idcLocation = idcLocation;
        this.userId = userId;
        this.engId = engId;
        this.bussinessType = bussinessType;
        this.iswatch = iswatch;
        this.engName = engName;
        this.blank1 = blank1;
        this.blank2 = blank2;
        this.blank3 = blank3;
    }
}
