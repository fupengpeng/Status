package com.fpp.status.entity;

/**
 * 加载会员信息【列表】的响应数据
 */

public class LoadMemberListResponseData {

    //用户手机号
    private String mobile;
    //所属门店
    private String shopid;
    //实体卡号
    private String membercardid;
    //昵称----
    private String truename;
    //级别----
    private String level;
    //星级别----
    private String levelname;
    //所在门店用户id
    private String mcid;
    //所属门店名----
    private String shopname;
    //用户头像----
    private String faceurl;
    //服务折扣
    private String discountservice;
    //商品折扣
    private String discountgoods;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getMembercardid() {
        return membercardid;
    }

    public void setMembercardid(String membercardid) {
        this.membercardid = membercardid;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname;
    }

    public String getMcid() {
        return mcid;
    }

    public void setMcid(String mcid) {
        this.mcid = mcid;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getFaceurl() {
        return faceurl;
    }

    public void setFaceurl(String faceurl) {
        this.faceurl = faceurl;
    }

    public String getDiscountservice() {
        return discountservice;
    }

    public void setDiscountservice(String discountservice) {
        this.discountservice = discountservice;
    }

    public String getDiscountgoods() {
        return discountgoods;
    }

    public void setDiscountgoods(String discountgoods) {
        this.discountgoods = discountgoods;
    }
}
