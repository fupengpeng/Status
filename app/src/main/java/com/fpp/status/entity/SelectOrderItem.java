package com.fpp.status.entity;

/**
 * 选择的品项
 */
public class SelectOrderItem {
    // ID
    private String id;
    // 订单id
    private String oid;
    // 项目类型：1.商品,2.服务
    private int ptype;
    // 项目ID
    private String pid;
    // 数量
    private int num;
    // 原价
    private float cost;
    // 折扣
    private float discount;
    // 售价
    private float price;
    // 计次，如果是计次消费的话
    private int times;
    // 计次ID
    private String timesid;
    // 所属门店，可忽略
    private String shopid;
    // 服务状态
    private String state;
    // 服务名称
    private String serviceitemname;
    // 商品名称
    private String goodsname;
    // 编码
    private String hotkey;
    // 操作人
    private String acuid;
    // 操作状态
    private String acstate;
    // 操作人姓名
    private String acuname;
    // 是否选定
    private boolean enable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getPtype() {
        return ptype;
    }

    public void setPtype(int ptype) {
        this.ptype = ptype;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getTimesid() {
        return timesid;
    }

    public void setTimesid(String timesid) {
        this.timesid = timesid;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getServiceitemname() {
        return serviceitemname;
    }

    public void setServiceitemname(String serviceitemname) {
        this.serviceitemname = serviceitemname;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getHotkey() {
        return hotkey;
    }

    public void setHotkey(String hotkey) {
        this.hotkey = hotkey;
    }

    public String getAcuid() {
        return acuid;
    }

    public void setAcuid(String acuid) {
        this.acuid = acuid;
    }

    public String getAcstate() {
        return acstate;
    }

    public void setAcstate(String acstate) {
        this.acstate = acstate;
    }

    public String getAcuname() {
        return acuname;
    }

    public void setAcuname(String acuname) {
        this.acuname = acuname;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
