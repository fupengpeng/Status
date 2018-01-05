package com.fpp.status.entity;

import java.util.List;

/**
 * @author fupengpeng
 * @description 加载订单详情接单人及服务项等的数据
 * @data 2017/12/28 0028 16:35
 */

public class LoadOrderDetailsResponseData {

    // id
    private String id;
    // 接单人ID
    private String shopid;
    // 接单人图像
    private String shopimageurl;
    // 接单人名称
    private String shopname;
    // 接单人岗位
    private String shopgangwei;
    // 服务项是否显示
    private String enable;
    // 服务项
    private List<SelectOrderItem> opsList;
    // 商品项
    private List<SelectOrderItem> opgList;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getShopimageurl() {
        return shopimageurl;
    }

    public void setShopimageurl(String shopimageurl) {
        this.shopimageurl = shopimageurl;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getShopgangwei() {
        return shopgangwei;
    }

    public void setShopgangwei(String shopgangwei) {
        this.shopgangwei = shopgangwei;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public List<SelectOrderItem> getOpsList() {
        return opsList;
    }

    public void setOpsList(List<SelectOrderItem> opsList) {
        this.opsList = opsList;
    }

    public List<SelectOrderItem> getOpgList() {
        return opgList;
    }

    public void setOpgList(List<SelectOrderItem> opgList) {
        this.opgList = opgList;
    }
}
