package com.zhiyi.section.model;

import java.util.Date;

public class Community {
    private String communityCode;

    private String communityName;

    private String pinyinCode;

    private String city;

    private String area;

    private String section;

    private String address;

    private Byte communityType;

    private String postcode;

    private String modifyYears;

    private Integer households;

    private Byte elevatorFlag;

    private Integer prices;

    private Double longitude;

    private Double latitude;

    private Integer deliveryFlag;

    private Byte tudeRemarks;

    private String remarks;

    private Byte statusFlag;

    private String modifyUser;

    private Date modifyTime;

    private Integer buildType;

    private String funType;

    public Community(String communityCode, String communityName, String pinyinCode, String city, String area, String section, String address, Byte communityType, String postcode, String modifyYears, Integer households, Byte elevatorFlag, Integer prices, Double longitude, Double latitude, Integer deliveryFlag, Byte tudeRemarks, String remarks, Byte statusFlag, String modifyUser, Date modifyTime, Integer buildType, String funType) {
        this.communityCode = communityCode;
        this.communityName = communityName;
        this.pinyinCode = pinyinCode;
        this.city = city;
        this.area = area;
        this.section = section;
        this.address = address;
        this.communityType = communityType;
        this.postcode = postcode;
        this.modifyYears = modifyYears;
        this.households = households;
        this.elevatorFlag = elevatorFlag;
        this.prices = prices;
        this.longitude = longitude;
        this.latitude = latitude;
        this.deliveryFlag = deliveryFlag;
        this.tudeRemarks = tudeRemarks;
        this.remarks = remarks;
        this.statusFlag = statusFlag;
        this.modifyUser = modifyUser;
        this.modifyTime = modifyTime;
        this.buildType = buildType;
        this.funType = funType;
    }

    public Community() {
        super();
    }

    public String getCommunityCode() {
        return communityCode;
    }

    public void setCommunityCode(String communityCode) {
        this.communityCode = communityCode;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getPinyinCode() {
        return pinyinCode;
    }

    public void setPinyinCode(String pinyinCode) {
        this.pinyinCode = pinyinCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Byte getCommunityType() {
        return communityType;
    }

    public void setCommunityType(Byte communityType) {
        this.communityType = communityType;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getModifyYears() {
        return modifyYears;
    }

    public void setModifyYears(String modifyYears) {
        this.modifyYears = modifyYears;
    }

    public Integer getHouseholds() {
        return households;
    }

    public void setHouseholds(Integer households) {
        this.households = households;
    }

    public Byte getElevatorFlag() {
        return elevatorFlag;
    }

    public void setElevatorFlag(Byte elevatorFlag) {
        this.elevatorFlag = elevatorFlag;
    }

    public Integer getPrices() {
        return prices;
    }

    public void setPrices(Integer prices) {
        this.prices = prices;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getDeliveryFlag() {
        return deliveryFlag;
    }

    public void setDeliveryFlag(Integer deliveryFlag) {
        this.deliveryFlag = deliveryFlag;
    }

    public Byte getTudeRemarks() {
        return tudeRemarks;
    }

    public void setTudeRemarks(Byte tudeRemarks) {
        this.tudeRemarks = tudeRemarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Byte getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(Byte statusFlag) {
        this.statusFlag = statusFlag;
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

    public Integer getBuildType() {
        return buildType;
    }

    public void setBuildType(Integer buildType) {
        this.buildType = buildType;
    }

    public String getFunType() {
        return funType;
    }

    public void setFunType(String funType) {
        this.funType = funType;
    }
}