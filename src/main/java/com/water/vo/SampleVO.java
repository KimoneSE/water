package com.water.vo;

import com.water.entity.Apply;

import java.util.Date;

/**
 * Created by Kimone.
 */
public class SampleVO {
    private long sampleID;      //样本编号
    private Date sampleDate;    //采样时间
    private Double volume;      //样本体积
    private String remark;      //备注
    private Integer state;      //样本状态 包括处理中1 已上传实验结果2 未收取0 初始状态-1
    private double temperature; //温度
    private String weather;     //天气
    private Apply apply;

    public long getSampleID() {
        return sampleID;
    }

    public void setSampleID(long sampleID) {
        this.sampleID = sampleID;
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

    public Apply getApply() {
        return apply;
    }

    public void setApply(Apply apply) {
        this.apply = apply;
    }
}
