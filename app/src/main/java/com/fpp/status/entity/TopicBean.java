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
public class TopicBean implements Serializable {
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
    @Property
    private int type; // 类型
    private int subject;  // 科目
    private int chapter;  // 章节
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

    private boolean collect = false;  // 是否收藏

    private boolean wrong_collect = false;   // 错题收藏

    // 顺序练习数据
    private boolean finish_exercise = false; // 是否练习过
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> select_answer; // 练习过所选答案

    // 随机练习数据
    private boolean finish_exercise_random = false;
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> select_answer_random; // 练习过所选答案

    // 章节练习数据
    private boolean finish_exercise_section = false;
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> select_answer_section; // 练习过所选答案

    // 考前冲刺练习数据
    private boolean finish_exercise_drill = false;
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> select_answer_drill; // 练习过所选答案

    // 难易错题练习数据
    private boolean finish_exercise_record = false;
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> select_answer_record; // 练习过所选答案

    @Generated(hash = 1461476364)
    public TopicBean(Long id, Long tid, int type, int subject, int chapter,
                     String title, String ans_type, String analysis, String images,
                     String image_name, byte[] image_byte, List<String> answer,
                     List<String> option, boolean collect, boolean wrong_collect,
                     boolean finish_exercise, List<String> select_answer,
                     boolean finish_exercise_random, List<String> select_answer_random,
                     boolean finish_exercise_section, List<String> select_answer_section,
                     boolean finish_exercise_drill, List<String> select_answer_drill,
                     boolean finish_exercise_record, List<String> select_answer_record) {
        this.id = id;
        this.tid = tid;
        this.type = type;
        this.subject = subject;
        this.chapter = chapter;
        this.title = title;
        this.ans_type = ans_type;
        this.analysis = analysis;
        this.images = images;
        this.image_name = image_name;
        this.image_byte = image_byte;
        this.answer = answer;
        this.option = option;
        this.collect = collect;
        this.wrong_collect = wrong_collect;
        this.finish_exercise = finish_exercise;
        this.select_answer = select_answer;
        this.finish_exercise_random = finish_exercise_random;
        this.select_answer_random = select_answer_random;
        this.finish_exercise_section = finish_exercise_section;
        this.select_answer_section = select_answer_section;
        this.finish_exercise_drill = finish_exercise_drill;
        this.select_answer_drill = select_answer_drill;
        this.finish_exercise_record = finish_exercise_record;
        this.select_answer_record = select_answer_record;
    }

    @Generated(hash = 1961217991)
    public TopicBean() {
    }

//    //将数据库中的值，转化为实体Bean类对象(比如List<String>)
//    P convertToEntityProperty(D databaseValue);
//    //将实体Bean类(比如List<String>)转化为数据库中的值(比如String)
//    D convertToDatabaseValue(P entityProperty);



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

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
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

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public boolean isWrong_collect() {
        return wrong_collect;
    }

    public void setWrong_collect(boolean wrong_collect) {
        this.wrong_collect = wrong_collect;
    }

    public boolean getFinish_exercise() {
        return this.finish_exercise;
    }

    public boolean getCollect() {
        return this.collect;
    }

    public boolean getWrong_collect() {
        return this.wrong_collect;
    }

    public boolean getFinish_exercise_random() {
        return this.finish_exercise_random;
    }

    public void setFinish_exercise_random(boolean finish_exercise_random) {
        this.finish_exercise_random = finish_exercise_random;
    }

    public List<String> getSelect_answer_random() {
        return this.select_answer_random;
    }

    public void setSelect_answer_random(List<String> select_answer_random) {
        this.select_answer_random = select_answer_random;
    }

    public boolean getFinish_exercise_section() {
        return this.finish_exercise_section;
    }

    public void setFinish_exercise_section(boolean finish_exercise_section) {
        this.finish_exercise_section = finish_exercise_section;
    }

    public List<String> getSelect_answer_section() {
        return this.select_answer_section;
    }

    public void setSelect_answer_section(List<String> select_answer_section) {
        this.select_answer_section = select_answer_section;
    }

    public boolean getFinish_exercise_drill() {
        return this.finish_exercise_drill;
    }

    public void setFinish_exercise_drill(boolean finish_exercise_drill) {
        this.finish_exercise_drill = finish_exercise_drill;
    }

    public List<String> getSelect_answer_drill() {
        return this.select_answer_drill;
    }

    public void setSelect_answer_drill(List<String> select_answer_drill) {
        this.select_answer_drill = select_answer_drill;
    }

    public boolean getFinish_exercise_record() {
        return this.finish_exercise_record;
    }

    public void setFinish_exercise_record(boolean finish_exercise_record) {
        this.finish_exercise_record = finish_exercise_record;
    }

    public List<String> getSelect_answer_record() {
        return this.select_answer_record;
    }

    public void setSelect_answer_record(List<String> select_answer_record) {
        this.select_answer_record = select_answer_record;
    }

    public boolean isFinish_exercise_random() {
        return finish_exercise_random;
    }

    public boolean isFinish_exercise_section() {
        return finish_exercise_section;
    }

    public boolean isFinish_exercise_drill() {
        return finish_exercise_drill;
    }

    public boolean isFinish_exercise_record() {
        return finish_exercise_record;
    }

    public Long getTid() {
        return this.tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

}
