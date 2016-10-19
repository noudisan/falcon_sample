package com.zhiyi.community.dto;


import java.io.Serializable;
import java.util.Date;

public class DeliveryCommunityDto implements Serializable {

    private static final long serialVersionUID = -3149172220771194420L;

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

    private CommunityStatusFlag statusFlag;//状态

    private CommunityElevatorFlag elevatorFlag;//电梯

    private Integer households;

    private Integer prices;

    private String modifyYears;

    private CommunityType communityType;//小区类型

    private String section;

    private CommunityBuildType buildType; //建筑类型

    private String funType; //用途类型

    private String modifyUser;

    private Date modifyDt;

    private String createUser;

    private Date createDt;

    private String[] funTypeArray;

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

    public CommunityStatusFlag getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(CommunityStatusFlag statusFlag) {
        this.statusFlag = statusFlag;
    }

    public CommunityElevatorFlag getElevatorFlag() {
        return elevatorFlag;
    }

    public void setElevatorFlag(CommunityElevatorFlag elevatorFlag) {
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

    public CommunityType getCommunityType() {
        return communityType;
    }

    public void setCommunityType(CommunityType communityType) {
        this.communityType = communityType;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public CommunityBuildType getBuildType() {
        return buildType;
    }

    public void setBuildType(CommunityBuildType buildType) {
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

    public String[] getFunTypeArray() {
        return funTypeArray;
    }

    public void setFunTypeArray(String[] funTypeArray) {
        this.funTypeArray = funTypeArray;
    }
}
