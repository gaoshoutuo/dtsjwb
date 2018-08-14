package com.zjdt.dtsjwb.Bean;

import org.json.JSONObject;

public class JsonUtilBean {
    private String key;
    private String value;
    private JSONObject obj;

    public JsonUtilBean(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public JsonUtilBean(String key, JSONObject obj) {
        this.key = key;
        this.obj = obj;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public JSONObject getObj() {
        return obj;
    }

    public void setObj(JSONObject obj) {
        this.obj = obj;
    }
}
