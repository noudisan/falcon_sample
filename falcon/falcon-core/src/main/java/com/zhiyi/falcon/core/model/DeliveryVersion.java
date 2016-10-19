package com.zhiyi.falcon.core.model;

import java.util.Date;

public class DeliveryVersion {
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

    public DeliveryVersion(Integer id, Integer versionCode, String versionName, String systemType, String updateContent, String isForceUpdate, String modifyUser, Date modifyDt, String createUser, Date createDt) {
        this.id = id;
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.systemType = systemType;
        this.updateContent = updateContent;
        this.isForceUpdate = isForceUpdate;
        this.modifyUser = modifyUser;
        this.modifyDt = modifyDt;
        this.createUser = createUser;
        this.createDt = createDt;
    }

    public DeliveryVersion() {
        super();
    }

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
}