package com.zjdt.dtsjwb.Bean;

public class DeviceBean {
    private String deviceName;
    private int deviceId;
    private String coustomerId;
    private String location;
    private String reason;

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
}
