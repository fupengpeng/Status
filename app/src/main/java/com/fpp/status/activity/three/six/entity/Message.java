package com.fpp.status.activity.three.six.entity;

/**
 * Created by Administrator on 2017/6/13.
 */

public class Message {
    int id;
    String fid;
    String name;

    public Message() {
    }

    ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Message(int id, String fid, String name) {
        this.id = id;
        this.fid = fid;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", fid='" + fid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }


}
