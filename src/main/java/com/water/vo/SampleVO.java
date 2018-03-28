package com.water.vo;

import com.water.entity.Apply;

import java.util.Date;

/**
 * Created by Kimone.
 */
public class SampleVO {
    private String sampleID;      //样本编号
    private long bottleID;    //样品瓶编号
    private Date sampleDate;    //采样时间
    private Double volume;      //样本体积
    private String remark;      //备注
    private Integer state;      //样本状态 包括处理中1 已上传实验结果2 未收取0 初始状态-1
    private double temperature; //温度
    private String weather;     //天气
    private String ammoniaN_c;   //氨氮浓度
    private String phosphate_c;  //磷酸盐浓度
    private Apply apply;
    private String projectName;  //项目名称
    private long projectID;      //项目ID

    public String getSampleID() {
        return sampleID;
    }

    public void setSampleID(String sampleID) {
        this.sampleID = sampleID;
    }

    public long getBottleID() {
        return bottleID;
    }

    public void setBottleID(long bottleID) {
        this.bottleID = bottleID;
    }

    public Date getSampleDate() {
        return sampleDate;
    }

    public void setSampleDate(Date sampleDate) {
        this.sampleDate = sampleDate;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getAmmoniaN_c() {
        return ammoniaN_c;
    }

    public void setAmmoniaN_c(String ammoniaN_c) {
        this.ammoniaN_c = ammoniaN_c;
    }

    public String getPhosphate_c() {
        return phosphate_c;
    }

    public void setPhosphate_c(String phosphate_c) {
        this.phosphate_c = phosphate_c;
    }

    public Apply getApply() {
        return apply;
    }

    public void setApply(Apply apply) {
        this.apply = apply;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public long getProjectID() {
        return projectID;
    }

    public void setProjectID(long projectID) {
        this.projectID = projectID;
    }
}
