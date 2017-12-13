package com.fpp.status.entity;

import java.util.List;

/**
 * Created by fupengpeng on 2017/12/13 0013.
 */

public class LVOne {

    private String lvOneId;
    private String lvOneName;
    private String lvOneNum;
    private boolean lvOneSelect;
    private List<LVTwo> lvTwos;


    public String getLvOneId() {
        return lvOneId;
    }

    public void setLvOneId(String lvOneId) {
        this.lvOneId = lvOneId;
    }

    public String getLvOneName() {
        return lvOneName;
    }

    public void setLvOneName(String lvOneName) {
        this.lvOneName = lvOneName;
    }

    public String getLvOneNum() {
        return lvOneNum;
    }

    public void setLvOneNum(String lvOneNum) {
        this.lvOneNum = lvOneNum;
    }

    public boolean isLvOneSelect() {
        return lvOneSelect;
    }

    public void setLvOneSelect(boolean lvOneSelect) {
        this.lvOneSelect = lvOneSelect;
    }

    public List<LVTwo> getLvTwos() {
        return lvTwos;
    }

    public void setLvTwos(List<LVTwo> lvTwos) {
        this.lvTwos = lvTwos;
    }
}
