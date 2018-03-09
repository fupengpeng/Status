package com.fpp.status.entity;

/**
 * 用户信息
 */
public class User {
    // 用户系统ID
    private String id;
    // 账号，手机号码
    private String ids;
    // 真实姓名
    private String name;
    // 平台级别
    private String level;
    // 昵称
    private String NickName;
    // 性别，0.女，1.男
    private String Gender;
    // 身份证号码
    private String IDCard;
    // 生日
    private String Birthday;
    // 微信
    private String Weixin;
    // QQ
    private String QQ;
    // Email
    private String Email;
    // 所在省
    private String Province;
    // 所在地级市
    private String City;
    // 所在县区
    private String Area;
    // 地址
    private String Address;
    // 头像
    private String PhotoUrl;
    // 注册时间
    private String regtime;
    // 说明
    private String Intro;


    // 门店店员id
    private String shopuserid;

    // 是否选定
    private boolean select;
    // 是否选定
    private boolean enable;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getWeixin() {
        return Weixin;
    }

    public void setWeixin(String weixin) {
        Weixin = weixin;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhotoUrl() {
        return PhotoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        PhotoUrl = photoUrl;
    }

    public String getRegtime() {
        return regtime;
    }

    public void setRegtime(String regtime) {
        this.regtime = regtime;
    }

    public String getIntro() {
        return Intro;
    }

    public void setIntro(String intro) {
        Intro = intro;
    }

    public String getShopuserid() {
        return shopuserid;
    }

    public void setShopuserid(String shopuserid) {
        this.shopuserid = shopuserid;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", ids='" + ids + '\'' +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", NickName='" + NickName + '\'' +
                ", Gender='" + Gender + '\'' +
                ", IDCard='" + IDCard + '\'' +
                ", Birthday='" + Birthday + '\'' +
                ", Weixin='" + Weixin + '\'' +
                ", QQ='" + QQ + '\'' +
                ", Email='" + Email + '\'' +
                ", Province='" + Province + '\'' +
                ", City='" + City + '\'' +
                ", Area='" + Area + '\'' +
                ", Address='" + Address + '\'' +
                ", PhotoUrl='" + PhotoUrl + '\'' +
                ", regtime='" + regtime + '\'' +
                ", Intro='" + Intro + '\''+
                ", shopuserid='" + shopuserid + '\'' +
                '}';
    }
}
