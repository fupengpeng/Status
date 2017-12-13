package com.fpp.status.entity;

import java.util.List;

/**
 * 服务分类
 */
public class ServiceCategory {
    // 服务分类ID
    private String ServiceCategoryID;
    // 服务分类名
    private String ServiceCategoryName;
    private String ShopID;
    private boolean IsDelete;
    private boolean IsEnable;
    // 服务项目列表
    private List<ServiceItem> ServiceItemList;

    public String getServiceCategoryID() {
        return ServiceCategoryID;
    }

    public void setServiceCategoryID(String serviceCategoryID) {
        ServiceCategoryID = serviceCategoryID;
    }

    public String getServiceCategoryName() {
        return ServiceCategoryName;
    }

    public void setServiceCategoryName(String serviceCategoryName) {
        ServiceCategoryName = serviceCategoryName;
    }

    public String getShopID() {
        return ShopID;
    }

    public void setShopID(String shopID) {
        ShopID = shopID;
    }

    public boolean isDelete() {
        return IsDelete;
    }

    public void setDelete(boolean delete) {
        IsDelete = delete;
    }

    public boolean isEnable() {
        return IsEnable;
    }

    public void setEnable(boolean enable) {
        IsEnable = enable;
    }

    public List<ServiceItem> getServiceItemList() {
        return ServiceItemList;
    }

    public void setServiceItemList(List<ServiceItem> serviceItemList) {
        ServiceItemList = serviceItemList;
    }
}
