package com.fpp.status.entity;

/**
 * 订单详情页面展示的客户服务项实体
 */
public class ServiceItemData {

    private String name;
    private int number;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
