package com.zjdt.dtsjwb.Bean;

import com.zjdt.dtsjwb.Adapter.UnionBean;

public class FDBean implements UnionBean {
    private int imageUrl;
    private String text;

    public FDBean(int imageUrl, String text) {
        this.imageUrl = imageUrl;
        this.text = text;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
