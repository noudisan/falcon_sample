package com.zhiyi.falcon.core.model;

import java.util.Date;

public class BaseCommunity {

    private Integer id;

    private String communityCode;

    private String address;

    private String area;

    private String city;

    private String communityName;

    private Double latitude;

    private Double longitude;

    private String pinyinCode;

    private String postcode;

    private String remarks;

    private Integer deliverySectionId;

    private String statusFlag;

    private String elevatorFlag;

    private Integer households;

    private Integer prices;

    private String modifyYears;

    private String communityType;

    private String section;

    private String buildType;

    private String funType;

    private String modifyUser;

    private Date modifyDt;

    private String createUser;

    private Date createDt;

    public BaseCommunity(Integer id, String communityCode, String address, String area, String city, String communityName, Double latitude, Double longitude, String pinyinCode, String postcode, String remarks, Integer deliverySectionId, String statusFlag, String elevatorFlag, Integer households, Integer prices, String modifyYears, String communityType, String section, String buildType, String funType, String modifyUser, Date modifyDt, String createUser, Date createDt) {
        this.id = id;
        this.communityCode = communityCode;
        this.address = address;
        this.area = area;
        this.city = city;
        this.communityName = communityName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pinyinCode = pinyinCode;
        this.postcode = postcode;
        this.remarks = remarks;
        this.deliverySectionId = deliverySectionId;
        this.statusFlag = statusFlag;
        this.elevatorFlag = elevatorFlag;
        this.households = households;
        this.prices = prices;
        this.modifyYears = modifyYears;
        this.communityType = communityType;
        this.section = section;
        this.buildType = buildType;
        this.funType = funType;
        this.modifyUser = modifyUser;
        this.modifyDt = modifyDt;
        this.createUser = createUser;
        this.createDt = createDt;
    }

    public BaseCommunity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommunityCode() {
        return communityCode;
    }

    public void setCommunityCode(String communityCode) {
        this.communityCode = communityCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
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

    public String getPinyinCode() {
        return pinyinCode;
    }

    public void setPinyinCode(String pinyinCode) {
        this.pinyinCode = pinyinCode;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getDeliverySectionId() {
        return deliverySectionId;
    }

    public void setDeliverySectionId(Integer deliverySectionId) {
        this.deliverySectionId = deliverySectionId;
    }

    public String getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(String statusFlag) {
        this.statusFlag = statusFlag;
    }

    public String getElevatorFlag() {
        return elevatorFlag;
    }

    public void setElevatorFlag(String elevatorFlag) {
        this.elevatorFlag = elevatorFlag;
    }

    public Integer getHouseholds() {
        return households;
    }

    public void setHouseholds(Integer households) {
        this.households = households;
    }

    public Integer getPrices() {
        return prices;
    }

    public void setPrices(Integer prices) {
        this.prices = prices;
    }

    public String getModifyYears() {
        return modifyYears;
    }

    public void setModifyYears(String modifyYears) {
        this.modifyYears = modifyYears;
    }

    public String getCommunityType() {
        return communityType;
    }

    public void setCommunityType(String communityType) {
        this.communityType = communityType;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getBuildType() {
        return buildType;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }

    public String getFunType() {
        return funType;
    }

    public void setFunType(String funType) {
        this.funType = funType;
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