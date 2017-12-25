package com.fpp.status.entity;

/**
 * 门店店员
 */
public class ShopAssistant {
    // 人员ID
    private String userid;
    // 人员姓名
    private String username;
    // 人员工号
    private String jobnumber;
    // 人员级别ID
    private String jobid;
    // 人员级别名称
    private String jobname;
    // 是否选择
    private boolean serve;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public boolean isServe() {
        return serve;
    }

    public void setServe(boolean serve) {
        this.serve = serve;
    }
}
