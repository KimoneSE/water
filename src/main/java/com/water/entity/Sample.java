package com.water.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Sample {
    private long id;            //流水号
    private long sampleID;      //样本编号
    private Date sampleDate;    //采样时间
    private Double volume;      //样本体积
    private String remark;      //备注
    private long applyID;        //申请编号 对应apply表的主键
    private Integer state;      //样本状态 包括处理中1 已上传实验结果2 未收取0 初始状态-1
    private double temperature; //温度
    private String weather;     //天气

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public long getApplyID() {
        return applyID;
    }

    public void setApplyID(long applyID) {
        this.applyID = applyID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sample sample = (Sample) o;

        if (sampleID != sample.sampleID) return false;
        if (sampleDate != null ? !sampleDate.equals(sample.sampleDate) : sample.sampleDate != null) return false;
        if (volume != null ? !volume.equals(sample.volume) : sample.volume != null) return false;
        if (remark != null ? !remark.equals(sample.remark) : sample.remark != null) return false;
        if (state != null ? !state.equals(sample.state) : sample.state != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (sampleID ^ (sampleID >>> 32));
        result = 31 * result + (sampleDate != null ? sampleDate.hashCode() : 0);
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

}
