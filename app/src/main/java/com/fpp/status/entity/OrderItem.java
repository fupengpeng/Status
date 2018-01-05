package com.fpp.status.entity;

import java.util.List;

/**
 * 订单项
 */
public class OrderItem {
    // ID
    private String id;
    // 订单类型：参见配置
    private String type;
    // 订单GUID
    private String guidvalue;
    // 订单编号
    private String ids;
    // 所属门店
    private String shopid;
    // 创建日期
    private String date;
    // 创建时间
    private String time;
    // 状态
    private String state;
    // 操作人类型：员工or会员
    private String acut;
    // 操作人ID
    private String acuid;
    // 会员所在门店
    private String memberbelongsshopid;
    // 门店会员ID
    private String membershopid;
    // 会员原始ID
    private String memberid;
    // 订单级别
    private String level;
    // 异店消费
    private String isos;
    // 预约
    private String maa;
    // 预约日期
    private String maadate;
    // 预约时间
    private String maatime;
    // 预约发型师
    private String maauid;
    // 接单人
    private String takinguid;
    // 接单时间
    private String takingtime;
    // 转单历史
    private String transfer;
    // 会员升级
    private String mup;
    // 人员业绩
    private String employee;
    // 会员头像
    private String faceurl;
    // 会员昵称
    private String nickname;
    // 会员真实姓名
    private String truename;
    // 会员性别
    private String gender;
    // 会员类型
    private String mtype;
    // 会员手机号
    private String mobile;
    // 门店名称
    private String shopname;
    // 结单时间
    private String endtime;
    // 进行中的服务项目
    private String endservname;
    // 服务中的人员
    private String endservuser;
    // 选择的服务集合
    private List<SelectOrderItem> opslist;
    // 选择的商品集合
    private List<SelectOrderItem> opglist;

    // 接单人及接单项目集合
    private List<LoadOrderDetailsResponseData> shopList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGuidvalue() {
        return guidvalue;
    }

    public void setGuidvalue(String guidvalue) {
        this.guidvalue = guidvalue;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAcut() {
        return acut;
    }

    public void setAcut(String acut) {
        this.acut = acut;
    }

    public String getAcuid() {
        return acuid;
    }

    public void setAcuid(String acuid) {
        this.acuid = acuid;
    }

    public String getMemberbelongsshopid() {
        return memberbelongsshopid;
    }

    public void setMemberbelongsshopid(String memberbelongsshopid) {
        this.memberbelongsshopid = memberbelongsshopid;
    }

    public String getMembershopid() {
        return membershopid;
    }

    public void setMembershopid(String membershopid) {
        this.membershopid = membershopid;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getIsos() {
        return isos;
    }

    public void setIsos(String isos) {
        this.isos = isos;
    }

    public String getMaa() {
        return maa;
    }

    public void setMaa(String maa) {
        this.maa = maa;
    }

    public String getMaadate() {
        return maadate;
    }

    public void setMaadate(String maadate) {
        this.maadate = maadate;
    }

    public String getMaatime() {
        return maatime;
    }

    public void setMaatime(String maatime) {
        this.maatime = maatime;
    }

    public String getMaauid() {
        return maauid;
    }

    public void setMaauid(String maauid) {
        this.maauid = maauid;
    }

    public String getTakinguid() {
        return takinguid;
    }

    public void setTakinguid(String takinguid) {
        this.takinguid = takinguid;
    }

    public String getTakingtime() {
        return takingtime;
    }

    public void setTakingtime(String takingtime) {
        this.takingtime = takingtime;
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    public String getMup() {
        return mup;
    }

    public void setMup(String mup) {
        this.mup = mup;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getFaceurl() {
        return faceurl;
    }

    public void setFaceurl(String faceurl) {
        this.faceurl = faceurl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMtype() {
        return mtype;
    }

    public void setMtype(String mtype) {
        this.mtype = mtype;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getEndservname() {
        return endservname;
    }

    public void setEndservname(String endservname) {
        this.endservname = endservname;
    }

    public String getEndservuser() {
        return endservuser;
    }

    public void setEndservuser(String endservuser) {
        this.endservuser = endservuser;
    }

    public List<SelectOrderItem> getOpslist() {
        return opslist;
    }

    public void setOpslist(List<SelectOrderItem> opslist) {
        this.opslist = opslist;
    }

    public List<SelectOrderItem> getOpglist() {
        return opglist;
    }

    public void setOpglist(List<SelectOrderItem> opglist) {
        this.opglist = opglist;
    }
}
