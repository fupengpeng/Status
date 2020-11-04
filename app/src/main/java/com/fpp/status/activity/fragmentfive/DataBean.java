package com.fpp.status.activity.fragmentfive;

public class DataBean extends CheckBean{

	/**
	 * 商品id
	 */
	int id;

	/**
	 * 商品数量
	 */
	int carNum;

	/**
	 * 商品名称
	 */
	String shopName;

	/**
	 * 商品内容
	 */
	String content;

	/**
	 * 商品价格
	 */
	double price;

	/**
	 * 是否选中
	 */
	boolean isChoose;

	public boolean isChoose() {
		return isChoose;
	}

	public void setChoose(boolean isChoose) {
		this.isChoose = isChoose;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCarNum() {
		return carNum;
	}

	public void setCarNum(int carNum) {
		this.carNum = carNum;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
