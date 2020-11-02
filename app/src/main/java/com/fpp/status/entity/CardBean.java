package com.fpp.status.entity;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;
import java.util.List;

/**
 * Description:  题目
 * Author: fpp
 * Date: 2018/6/5  10:59
 */
@Entity
public class CardBean implements Serializable {
    static final long serialVersionUID = 42L;
    /**
     * id : 1
     * type : 1
     * subject : 1
     * chapter : 1
     * title : 驾驶机动车在道路上违反道路交通安全法的行为，属于什么行为？
     * answer : ["B"]
     * ans_type : 2
     * analysis : “违反道路交通安全法”，明显的是违法，它都暗示了是“违法”行为。
     * images : -1
     * option : ["违章行为","违法行为","过失行为","违规行为"]
     */

    @Id(autoincrement=true)
    private Long id;
    private Long tid;//这个就是外键 就是SectionBean的id

    private String atr;
    private String atrr;
    private String artt;
    private String aBoolean;
    private String anInt;
    private String aLong;

    @Property
    private int type; // 类型
    private String title;  // 问题
    private String ans_type;  // 题类型  1:判断  2：单选  3：多选
    private String analysis;  // 官方解释
    private String images;  // 图片
    private String image_name;  // 图片名称
    private byte[] image_byte;  // 图片字节数组
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> answer;  // 答案
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> option;  // 选项
    // 顺序练习数据
    private boolean finish_exercise = false; // 是否练习过
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> select_answer; // 练习过所选答案








    public CardBean(String atr, String atrr, String artt, String  aBoolean, String  anInt, String  aLong) {
        this.atr = atr;
        this.atrr = atrr;
        this.artt = artt;
        this.aBoolean = aBoolean;
        this.anInt = anInt;
        this.aLong = aLong;
    }



    @Generated(hash = 1056388161)
    public CardBean(Long id, Long tid, String atr, String atrr, String artt, String aBoolean, String anInt,
            String aLong, int type, String title, String ans_type, String analysis, String images,
            String image_name, byte[] image_byte, List<String> answer, List<String> option,
            boolean finish_exercise, List<String> select_answer) {
        this.id = id;
        this.tid = tid;
        this.atr = atr;
        this.atrr = atrr;
        this.artt = artt;
        this.aBoolean = aBoolean;
        this.anInt = anInt;
        this.aLong = aLong;
        this.type = type;
        this.title = title;
        this.ans_type = ans_type;
        this.analysis = analysis;
        this.images = images;
        this.image_name = image_name;
        this.image_byte = image_byte;
        this.answer = answer;
        this.option = option;
        this.finish_exercise = finish_exercise;
        this.select_answer = select_answer;
    }



    @Generated(hash = 516506924)
    public CardBean() {
    }



//    //将数据库中的值，转化为实体Bean类对象(比如List<String>)
//    P convertToEntityProperty(D databaseValue);
//    //将实体Bean类(比如List<String>)转化为数据库中的值(比如String)
//    D convertToDatabaseValue(P entityProperty);

    public String getAtr() {
        return atr;
    }

    public void setAtr(String atr) {
        this.atr = atr;
    }

    public String getAtrr() {
        return atrr;
    }

    public void setAtrr(String atrr) {
        this.atrr = atrr;
    }

    public String getArtt() {
        return artt;
    }

    public void setArtt(String artt) {
        this.artt = artt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAns_type() {
        return ans_type;
    }

    public void setAns_type(String ans_type) {
        this.ans_type = ans_type;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public byte[] getImage_byte() {
        return image_byte;
    }

    public void setImage_byte(byte[] image_byte) {
        this.image_byte = image_byte;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    public List<String> getOption() {
        return option;
    }

    public void setOption(List<String> option) {
        this.option = option;
    }

    public boolean isFinish_exercise() {
        return finish_exercise;
    }

    public void setFinish_exercise(boolean finish_exercise) {
        this.finish_exercise = finish_exercise;
    }

    public List<String> getSelect_answer() {
        return select_answer;
    }

    public void setSelect_answer(List<String> select_answer) {
        this.select_answer = select_answer;
    }

    public boolean getFinish_exercise() {
        return this.finish_exercise;
    }

    public Long getTid() {
        return this.tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(String aBoolean) {
        this.aBoolean = aBoolean;
    }

    public void setAnInt(String anInt) {
        this.anInt = anInt;
    }

    public void setaLong(String aLong) {
        this.aLong = aLong;
    }



    public String getABoolean() {
        return this.aBoolean;
    }



    public void setABoolean(String aBoolean) {
        this.aBoolean = aBoolean;
    }



    public String getAnInt() {
        return this.anInt;
    }



    public String getALong() {
        return this.aLong;
    }



    public void setALong(String aLong) {
        this.aLong = aLong;
    }
}
