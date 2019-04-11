package com.hzy.exampledemo.widget.radarview;

/**
 * @author hzy
 * @description:名字和分数实体类
 * @date :2019/4/11 14:42
 */
public class AbilityBean {

    private String abName;
    private float abScore;

    public AbilityBean() {
    }

    public AbilityBean(String abName, float abScore) {
        this.abName = abName;
        this.abScore = abScore;
    }

    public String getAbName() {
        return abName;
    }

    public void setAbName(String abName) {
        this.abName = abName;
    }

    public float getAbScore() {
        return abScore;
    }

    public void setAbScore(float abScore) {
        this.abScore = abScore;
    }
}
