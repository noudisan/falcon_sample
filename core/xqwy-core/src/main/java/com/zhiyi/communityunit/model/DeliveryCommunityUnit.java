package com.zhiyi.communityunit.model;

import java.util.Date;

public class DeliveryCommunityUnit {
    private Long id;

    private Long buildingId;

    private Integer floorNum;

    private Integer households;

    private Integer allNum;

    private String name;

    private String modifyUser;

    private Date modifyDt;

    private String createUser;

    private Date createDt;

    private String communityName;

    private String buildingName;

    public DeliveryCommunityUnit(Long id, Long buildingId, Integer floorNum, Integer households, Integer allNum, String name, String modifyUser, Date modifyDt, String createUser, Date createDt, String communityName, String buildingName) {
        this.id = id;
        this.buildingId = buildingId;
        this.floorNum = floorNum;
        this.households = households;
        this.allNum = allNum;
        this.name = name;
        this.modifyUser = modifyUser;
        this.modifyDt = modifyDt;
        this.createUser = createUser;
        this.createDt = createDt;
        this.communityName = communityName;
        this.buildingName = buildingName;
    }

    public DeliveryCommunityUnit() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(Integer floorNum) {
        this.floorNum = floorNum;
    }

    public Integer getHouseholds() {
        return households;
    }

    public void setHouseholds(Integer households) {
        this.households = households;
    }

    public Integer getAllNum() {
        return allNum;
    }

    public void setAllNum(Integer allNum) {
        this.allNum = allNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
}