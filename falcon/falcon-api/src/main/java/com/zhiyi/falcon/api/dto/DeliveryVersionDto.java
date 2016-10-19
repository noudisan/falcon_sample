package com.zhiyi.falcon.api.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2015/6/29.
 */
public class DeliveryVersionDto implements Serializable{
    private Integer id;

    private Integer versionCode;

    private String versionName;

    private String systemType;

    private String updateContent;

    private String isForceUpdate;

    private String modifyUser;

    private Date modifyDt;

    private String createUser;

    private Date createDt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public String getIsForceUpdate() {
        return isForceUpdate;
    }

    public void setIsForceUpdate(String isForceUpdate) {
        this.isForceUpdate = isForceUpdate;
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

    @Override
    public String toString() {
        return "DeliveryVersionDto{" +
                "id=" + id +
                ", versionCode=" + versionCode +
                ", versionName='" + versionName + '\'' +
                ", systimeType='" + systemType + '\'' +
                ", updateContent='" + updateContent + '\'' +
                ", isForceUpdate='" + isForceUpdate + '\'' +
                ", modifyUser='" + modifyUser + '\'' +
                ", modifyDt=" + modifyDt +
                ", createUser='" + createUser + '\'' +
                ", createDt=" + createDt +
                '}';
    }
}
