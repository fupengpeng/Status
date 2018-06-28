package com.fpp.status.activity.nine.entity;

import java.util.List;

/**
 * Description:
 * Author: fpp
 * Date: 2018/5/30  8:32
 */

public class ExerciseBottomBean {


    /**
     * code : 1
     * msg : ok
     * data : {"list":[{"id":293,"post_title":"新手上路","more":{"thumbnail":"https://yun.xiaojiangjiakao.com/upload/portal/20180606/fb62555566d023af5eb348608f9cea60.png","template":""},"post_content":"","update_time":1528273217},{"id":292,"post_title":"驾照挂失","more":{"thumbnail":"https://yun.xiaojiangjiakao.com/upload/portal/20180606/af3eceace7affe9d588732932b04c616.png","template":""},"post_content":"","update_time":1528273193},{"id":291,"post_title":"驾照换证","more":{"thumbnail":"https://yun.xiaojiangjiakao.com/upload/portal/20180606/d4cde008733d83eb428b012828fe3350.png","template":""},"post_content":"","update_time":1528273158},{"id":290,"post_title":"驾照年审","more":{"thumbnail":"https://yun.xiaojiangjiakao.com/upload/portal/20180606/3c89717ddea0631dbf067289b6646fc9.png","template":""},"post_content":"","update_time":1528273127},{"id":289,"post_title":"驾照领取","more":{"thumbnail":"https://yun.xiaojiangjiakao.com/upload/portal/20180606/86aa065023f995fe52786a94ef6d28f0.png","template":""},"post_content":"","update_time":1528273035}]}
     */

    private int code;
    private String msg;
    private CourseDataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CourseDataBean getData() {
        return data;
    }

    public void setData(CourseDataBean data) {
        this.data = data;
    }

    public static class CourseDataBean {
        private List<CourseBean> list;

        public List<CourseBean> getList() {
            return list;
        }

        public void setList(List<CourseBean> list) {
            this.list = list;
        }

        public static class CourseBean {
            /**
             * id : 293
             * post_title : 新手上路
             * more : {"thumbnail":"https://yun.xiaojiangjiakao.com/upload/portal/20180606/fb62555566d023af5eb348608f9cea60.png","template":""}
             * post_content :
             * update_time : 1528273217
             */

            private int id;
            private String post_title;
            private MoreBean more;
            private String post_content;
            private int update_time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPost_title() {
                return post_title;
            }

            public void setPost_title(String post_title) {
                this.post_title = post_title;
            }

            public MoreBean getMore() {
                return more;
            }

            public void setMore(MoreBean more) {
                this.more = more;
            }

            public String getPost_content() {
                return post_content;
            }

            public void setPost_content(String post_content) {
                this.post_content = post_content;
            }

            public int getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(int update_time) {
                this.update_time = update_time;
            }

            public static class MoreBean {
                /**
                 * thumbnail : https://yun.xiaojiangjiakao.com/upload/portal/20180606/fb62555566d023af5eb348608f9cea60.png
                 * template :
                 */

                private String thumbnail;
                private String template;

                public String getThumbnail() {
                    return thumbnail;
                }

                public void setThumbnail(String thumbnail) {
                    this.thumbnail = thumbnail;
                }

                public String getTemplate() {
                    return template;
                }

                public void setTemplate(String template) {
                    this.template = template;
                }
            }
        }
    }
}
