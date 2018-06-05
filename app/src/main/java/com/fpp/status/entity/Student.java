package com.fpp.status.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Description:
 * Author: fpp
 * Date: 2018/5/31  14:19
 */

@Entity
public class Student {
    //Id 默认为主键，自增方式
    @Id
    private Long id;
    private String name;
    private int age;
    private String sex;
    private String grade;

    @Generated(hash = 1612785574)
    public Student(Long id, String name, int age, String sex, String grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.grade = grade;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
