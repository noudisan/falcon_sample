package com.zhiyi.communitybuilding.dto;


import java.io.Serializable;
import java.util.Date;

/**
 * 小区楼栋
 */
public class DeliveryCommunityBuildingDto implements Serializable{
    private static final long serialVersionUID = 4932723737751445827L;

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

    @Override
    public String toString() {
        return "DeliveryCommunityBuildingDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", remark='" + remark + '\'' +
                ", communityId=" + communityId +
                ", communityName='" + communityName + '\'' +
                ", modifyUser='" + modifyUser + '\'' +
                ", modifyDt=" + modifyDt +
                ", createUser='" + createUser + '\'' +
                ", createDt=" + createDt +
                '}';
    }
}
