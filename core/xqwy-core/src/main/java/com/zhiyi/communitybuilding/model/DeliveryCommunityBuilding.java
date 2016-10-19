package com.zhiyi.communitybuilding.model;

import java.util.Date;

public class DeliveryCommunityBuilding {
    private Integer id;

    private String name;

    private Double latitude;

    private Double longitude;

    private String remark;

    private Integer communityId;

    private String communityName;

    private String modifyUser;

    private Date modifyDt;

    private String createUser;

    private Date createDt;

    public DeliveryCommunityBuilding(Integer id, String name, Double latitude, Double longitude, String remark, Integer communityId, String communityName, String modifyUser, Date modifyDt, String createUser, Date createDt) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.remark = remark;
        this.communityId = communityId;
        this.communityName = communityName;
        this.modifyUser = modifyUser;
        this.modifyDt = modifyDt;
        this.createUser = createUser;
        this.createDt = createDt;
    }

    public DeliveryCommunityBuilding() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
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