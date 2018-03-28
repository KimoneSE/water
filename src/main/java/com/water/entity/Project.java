package com.water.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Project {
    private long idProject;       //项目编号 自增
    private String name;          //项目名称
    private String description;      //项目描述
    private Integer state;          //项目状态
    private String report;          //项目报告pdf的名称
    private Date date;          //日期
    private Integer isPrivate;  //项目是否公开
    private Double lngMax;             //经度范围最大值
    private Double lngMin;             //经度范围最小值
    private Double latMax;              //纬度范围最大值
    private Double latMin;              //纬度范围最小值

    @Id
    @GeneratedValue
    @Column(name = "idProject", nullable = false)
    public long getIdProject() {
        return idProject;
    }

    public void setIdProject(long idProject) {
        this.idProject = idProject;
    }

    @Basic
    @Column(name = "name", nullable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Lob
    @Column(name = "description", nullable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "state", nullable = true)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "report", nullable = true)
    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Integer isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Double getLngMax() {
        return lngMax;
    }

    public void setLngMax(Double lngMax) {
        this.lngMax = lngMax;
    }

    public Double getLngMin() {
        return lngMin;
    }

    public void setLngMin(Double lngMin) {
        this.lngMin = lngMin;
    }

    public Double getLatMax() {
        return latMax;
    }

    public void setLatMax(Double latMax) {
        this.latMax = latMax;
    }

    public Double getLatMin() {
        return latMin;
    }

    public void setLatMin(Double latMin) {
        this.latMin = latMin;
    }
}
