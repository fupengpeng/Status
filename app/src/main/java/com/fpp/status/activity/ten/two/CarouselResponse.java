package com.fpp.status.activity.ten.two;

import java.io.Serializable;
import java.util.List;

/**
 * Description:  考试中心轮播请求响应
 * Author: fpp
 * Date: 2018/7/10  10:24
 */

public class CarouselResponse implements Serializable {


    /**
     * code : 1
     * msg : 该组幻灯片获取成功!
     * data : [{"id":2,"status":1,"delete_time":0,"name":"考试-科目1--首页轮播","remark":"广告尺寸00x00","items":[{"id":3,"slide_id":2,"status":1,"list_order":10000,"title":"111","image":"https://yun.xiaojiangjiakao.com/upload/admin/20180709/a9ee814239f188db152ffc275a40c9f7.jpg","url":"https://m.autohome.com.cn/","target":"","description":"","content":"","more":null},{"id":4,"slide_id":2,"status":1,"list_order":10000,"title":"222","image":"https://yun.xiaojiangjiakao.com/upload/admin/20180709/96a4c4f9977e9b510aed13f1323bbb9e.jpg","url":"https://m.autohome.com.cn/","target":"","description":"","content":"","more":null}]}]
     */

    private int code;
    private String msg;
    private List<CarouselData> data;

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

    public List<CarouselData> getData() {
        return data;
    }

    public void setData(List<CarouselData> data) {
        this.data = data;
    }

    public static class CarouselData {
        /**
         * id : 2
         * status : 1
         * delete_time : 0
         * name : 考试-科目1--首页轮播
         * remark : 广告尺寸00x00
         * items : [{"id":3,"slide_id":2,"status":1,"list_order":10000,"title":"111","image":"https://yun.xiaojiangjiakao.com/upload/admin/20180709/a9ee814239f188db152ffc275a40c9f7.jpg","url":"https://m.autohome.com.cn/","target":"","description":"","content":"","more":null},{"id":4,"slide_id":2,"status":1,"list_order":10000,"title":"222","image":"https://yun.xiaojiangjiakao.com/upload/admin/20180709/96a4c4f9977e9b510aed13f1323bbb9e.jpg","url":"https://m.autohome.com.cn/","target":"","description":"","content":"","more":null}]
         */

        private int id;
        private int status;
        private int delete_time;
        private String name;
        private String remark;
        private List<CarouselItems> items;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getDelete_time() {
            return delete_time;
        }

        public void setDelete_time(int delete_time) {
            this.delete_time = delete_time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public List<CarouselItems> getItems() {
            return items;
        }

        public void setItems(List<CarouselItems> items) {
            this.items = items;
        }

        public static class CarouselItems {
            /**
             * id : 3
             * slide_id : 2
             * status : 1
             * list_order : 10000
             * title : 111
             * image : https://yun.xiaojiangjiakao.com/upload/admin/20180709/a9ee814239f188db152ffc275a40c9f7.jpg
             * url : https://m.autohome.com.cn/
             * target :
             * description :
             * content :
             * more : null
             */

            private int id;
            private int slide_id;
            private int status;
            private int list_order;
            private String title;
            private String image;
            private String url;
            private String target;
            private String description;
            private String content;
            private Object more;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getSlide_id() {
                return slide_id;
            }

            public void setSlide_id(int slide_id) {
                this.slide_id = slide_id;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getList_order() {
                return list_order;
            }

            public void setList_order(int list_order) {
                this.list_order = list_order;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getTarget() {
                return target;
            }

            public void setTarget(String target) {
                this.target = target;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Object getMore() {
                return more;
            }

            public void setMore(Object more) {
                this.more = more;
            }
        }
    }
}
