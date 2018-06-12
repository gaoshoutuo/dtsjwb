package com.zjdt.dtsjwb.Bean;

public class FixHistoryBean {
    private String date;
    private String business;
    private String human;
    private String textReason;

    public String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getHuman() {
        return human;
    }

    public void setHuman(String human) {
        this.human = human;
    }

    public String getTextReason() {
        return textReason;
    }

    public void setTextReason(String textReason) {
        this.textReason = textReason;
    }
}
