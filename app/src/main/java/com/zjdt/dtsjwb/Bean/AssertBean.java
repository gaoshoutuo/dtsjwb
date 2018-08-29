package com.zjdt.dtsjwb.Bean;

public class AssertBean {
    private String headTitle;
    private String assertName;
    private String assertPara;
    private String assertType;
    private String assertBrand;
    private String assertNumber;
    private String mainTitle;

    private String assetLife;
    private String assetFirstTime;

    public AssertBean(String headTitle, String assertName, String assertPara, String assertType, String assertBrand, String assertNumber, String mainTitle) {
        this.headTitle = headTitle;
        this.assertName = assertName;
        this.assertPara = assertPara;
        this.assertType = assertType;
        this.assertBrand = assertBrand;
        this.assertNumber = assertNumber;
        this.mainTitle = mainTitle;
    }

    public AssertBean(String headTitle, String []arr, String mainTitle) {
        this.headTitle = headTitle;
        this.assertName = arr[0];
        this.assertPara = arr[1];
        this.assertType = arr[2];
        this.assertBrand = arr[3];
        this.assertNumber = arr[4];
        this.mainTitle = mainTitle;
    }

    public String getAssetLife() {
        return assetLife;
    }

    public void setAssetLife(String assetLife) {
        this.assetLife = assetLife;
    }

    public String getAssetFirstTime() {
        return assetFirstTime;
    }

    public void setAssetFirstTime(String assetFirstTime) {
        this.assetFirstTime = assetFirstTime;
    }

    public String getHeadTitle() {
        return headTitle;
    }

    public void setHeadTitle(String headTitle) {
        this.headTitle = headTitle;
    }

    public String getAssertName() {
        return assertName;
    }

    public void setAssertName(String assertName) {
        this.assertName = assertName;
    }

    public String getAssertPara() {
        return assertPara;
    }

    public void setAssertPara(String assertPara) {
        this.assertPara = assertPara;
    }

    public String getAssertType() {
        return assertType;
    }

    public void setAssertType(String assertType) {
        this.assertType = assertType;
    }

    public String getAssertBrand() {
        return assertBrand;
    }

    public void setAssertBrand(String assertBrand) {
        this.assertBrand = assertBrand;
    }

    public String getAssertNumber() {
        return assertNumber;
    }

    public void setAssertNumber(String assertNumber) {
        this.assertNumber = assertNumber;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }
}
