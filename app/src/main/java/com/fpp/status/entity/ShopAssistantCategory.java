package com.fpp.status.entity;

import java.util.List;

/**
 * 门店店员分类
 */
public class ShopAssistantCategory {
    // 岗位ID
    private String postid;
    // 岗位名称
    private String postname;
    // 岗位人员列表
    private List<ShopAssistant> userlist;

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public List<ShopAssistant> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<ShopAssistant> userlist) {
        this.userlist = userlist;
    }
}
