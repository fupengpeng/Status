package com.fpp.status.activity.one;

class OrderInfo {

    private String orderDate;
    private int amount;
    private String payType;
    private boolean isSelect;

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderDate='" + orderDate + '\'' +
                ", amount=" + amount +
                ", payType='" + payType + '\'' +
                ", isSelect='" + isSelect + '\'' +
                '}';
    }
}
