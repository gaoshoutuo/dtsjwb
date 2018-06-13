package com.zjdt.dtsjwb.Bean;

public class FixHistoryBean {
    private String date;
    private String business;
    private String human;
    private String textReason;

    public String imageUrl;
    public String filePath;

    public FixHistoryBean(String date, String business, String human, String textReason, String filePath) {
        this.date = date;
        this.business = business;
        this.human = human;
        this.textReason = textReason;
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

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
