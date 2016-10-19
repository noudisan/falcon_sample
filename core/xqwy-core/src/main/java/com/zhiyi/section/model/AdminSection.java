package com.zhiyi.section.model;

import java.util.Date;

public class AdminSection {
    private String sectionCode;

    private String sectionName;

    private String area;

    private String city;

    private String modifyUser;

    private Date modifyTime;

    public AdminSection(String sectionCode, String sectionName, String area, String city, String modifyUser, Date modifyTime) {
        this.sectionCode = sectionCode;
        this.sectionName = sectionName;
        this.area = area;
        this.city = city;
        this.modifyUser = modifyUser;
        this.modifyTime = modifyTime;
    }

    public AdminSection() {
        super();
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}