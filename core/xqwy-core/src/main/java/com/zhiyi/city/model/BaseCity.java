package com.zhiyi.city.model;

import java.io.Serializable;
import java.util.Date;

public class BaseCity implements Serializable {
    private static final long serialVersionUID = 1456417944448408442L;

    private Integer id;

    private String province;

    private String cityName;

    private String cityAbbr;

    private String modifyUser;

    private Date modifyDt;

    private String createUser;

    private Date createDt;

    private String isLocked;

    public BaseCity(Integer id, String province, String cityName, String cityAbbr, String modifyUser, Date modifyDt, String createUser, Date createDt, String isLocked) {
        this.id = id;
        this.province = province;
        this.cityName = cityName;
        this.cityAbbr = cityAbbr;
        this.modifyUser = modifyUser;
        this.modifyDt = modifyDt;
        this.createUser = createUser;
        this.createDt = createDt;
        this.isLocked = isLocked;
    }

    public BaseCity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityAbbr() {
        return cityAbbr;
    }

    public void setCityAbbr(String cityAbbr) {
        this.cityAbbr = cityAbbr;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getModifyDt() {
        return modifyDt;
    }

    public void setModifyDt(Date modifyDt) {
        this.modifyDt = modifyDt;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }
}