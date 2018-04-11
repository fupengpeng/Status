package com.fpp.status.activity.eight.one;

import java.util.List;

/**
 * @author fupengpeng
 * @description 描述
 * @data 2018/4/11 0011 8:28
 */

public class GoodsCategory {

    private String id;
    private String name;
    private String type;
    private List<Goods> goodsList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
