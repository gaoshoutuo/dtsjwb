package com.zjdt.dtsjwb.Bean;

import android.content.ContentValues;

public class DeviceBean {
    private String deviceName;
    private int deviceId;
    private String coustomerId;
    private String location;
    private String reason;

    private static ContentValues values;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getCoustomerId() {
        return coustomerId;
    }

    public void setCoustomerId(String coustomerId) {
        this.coustomerId = coustomerId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public DeviceBean(String deviceName, int deviceId, String coustomerId, String location, String reason) {
        this.deviceName = deviceName;
        this.deviceId = deviceId;
        this.coustomerId = coustomerId;
        this.location = location;
        this.reason = reason;
    }

    /**
     * 注入devicebean 生成contentvalue
     * @param deviceBean
     * @return
     */
    public static ContentValues getValue(DeviceBean deviceBean,String[] str){
        values=new ContentValues();
      /*  for(int i=1;i<str.length;i++){
            values.put();
        }*/
        values.clear();//但是static 长久的留在内存里了
        values.put(str[1],deviceBean.getDeviceName());
        values.put(str[2],deviceBean.getDeviceId());
        values.put(str[3],deviceBean.getCoustomerId());
        values.put(str[4],deviceBean.getLocation());
        values.put(str[5],deviceBean.getReason());
        return values;
    }
}
