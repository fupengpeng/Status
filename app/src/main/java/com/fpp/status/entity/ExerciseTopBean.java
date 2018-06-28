package com.fpp.status.entity;

import java.util.List;

/**
 * Description:  科目二、三，拿证顶部内容
 * Author: fpp
 * Date: 2018/5/30  8:24
 */

public class ExerciseTopBean {

    /**
     * code : 1
     * msg : ok
     * data : {"list":[{"id":10,"post_title":"直角转弯","more":{"thumbnail":"https://yun.xiaojiangjiakao.com/upload/portal/20180526/8cfcf58505bfdbd2a8ecf0167a208898.jpg","template":""}},{"id":6,"post_title":"考试流程2","more":{"thumbnail":"https://yun.xiaojiangjiakao.com/upload/portal/20180526/4580441f18fd95b4049382cb30e5f530.jpg","template":""}},{"id":7,"post_title":"坡道定点停车和起步","more":{"thumbnail":"","template":""}},{"id":8,"post_title":"侧方停车","more":{"thumbnail":"","template":""}}]}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<CourseList> list;  // 教程列表

        public List<CourseList> getList() {
            return list;
        }

        public void setList(List<CourseList> list) {
            this.list = list;
        }

        public static class CourseList {
            /**
             * id : 10
             * post_title : 直角转弯
             * more : {"thumbnail":"https://yun.xiaojiangjiakao.com/upload/portal/20180526/8cfcf58505bfdbd2a8ecf0167a208898.jpg","template":""}
             */

            private int id;  // id
            private String post_title;  // 教程标题
            private MoreMsg more;  // 教程更多信息

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

            public MoreMsg getMore() {
                return more;
            }

            public void setMore(MoreMsg more) {
                this.more = more;
            }

            public static class MoreMsg {
                /**
                 * thumbnail : https://yun.xiaojiangjiakao.com/upload/portal/20180526/8cfcf58505bfdbd2a8ecf0167a208898.jpg
                 * template :
                 */

                private String thumbnail;  // 教程缩略图
                private String template;    // 模板

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
