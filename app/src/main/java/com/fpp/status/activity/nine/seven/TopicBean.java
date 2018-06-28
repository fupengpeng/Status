package com.fpp.status.activity.nine.seven;

/**
 * Description:  题目数据
 * Author: fp
 * Date: 2018/6/28  23:20
 */

public class TopicBean {

    private String id;
    private String title;
    private boolean is_finish;
    private boolean is_wrong;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isIs_finish() {
        return is_finish;
    }

    public void setIs_finish(boolean is_finish) {
        this.is_finish = is_finish;
    }

    public boolean isIs_wrong() {
        return is_wrong;
    }

    public void setIs_wrong(boolean is_wrong) {
        this.is_wrong = is_wrong;
    }
}
