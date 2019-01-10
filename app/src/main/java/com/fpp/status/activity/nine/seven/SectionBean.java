package com.fpp.status.activity.nine.seven;

import java.util.List;

/**
 * Description:  章节数据
 * Author: fp
 * Date: 2018/6/28  23:19
 */

public class SectionBean {

    private String id;
    private String title;
    private List<TopicBean> topicBeans;

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

    public List<TopicBean> getTopicBeans() {
        return topicBeans;
    }

    public void setTopicBeans(List<TopicBean> topicBeans) {
        this.topicBeans = topicBeans;
    }
}
