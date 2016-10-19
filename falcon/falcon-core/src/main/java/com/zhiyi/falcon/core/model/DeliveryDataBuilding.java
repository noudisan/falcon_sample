package com.zhiyi.falcon.core.model;

import java.io.Serializable;
import java.util.Date;

public class DeliveryDataBuilding implements Serializable {
    private Integer id;

    private Integer buildId;

    private Integer deliveryNum;

    private Integer communityId;

    private Integer deliveryEmployeeId;

    private Integer deliveryTaskId;

    private String deliveryStatus;

    private Date deliveryDt;

    private Date beginDt;

    private Date endDt;

    private String remark;

    private String communityName;

    private String buildingName;

    private Double longitude;

    private Double latitude;

    private String deliveryCity;

    public DeliveryDataBuilding(Integer id, Integer buildId, Integer deliveryNum, Integer communityId, Integer deliveryEmployeeId, Integer deliveryTaskId, String deliveryStatus, Date deliveryDt, Date beginDt, Date endDt, String remark, String communityName, String buildingName, Double longitude, Double latitude, String deliveryCity) {
        this.id = id;
        this.buildId = buildId;
        this.deliveryNum = deliveryNum;
        this.communityId = communityId;
        this.deliveryEmployeeId = deliveryEmployeeId;
        this.deliveryTaskId = deliveryTaskId;
        this.deliveryStatus = deliveryStatus;
        this.deliveryDt = deliveryDt;
        this.beginDt = beginDt;
        this.endDt = endDt;
        this.remark = remark;
        this.communityName = communityName;
        this.buildingName = buildingName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.deliveryCity = deliveryCity;
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

    public DeliveryDataBuilding() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBuildId() {
        return buildId;
    }

    public void setBuildId(Integer buildId) {
        this.buildId = buildId;
    }

    public Integer getDeliveryNum() {
        return deliveryNum;
    }

    public void setDeliveryNum(Integer deliveryNum) {
        this.deliveryNum = deliveryNum;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getDeliveryEmployeeId() {
        return deliveryEmployeeId;
    }

    public void setDeliveryEmployeeId(Integer deliveryEmployeeId) {
        this.deliveryEmployeeId = deliveryEmployeeId;
    }

    public Integer getDeliveryTaskId() {
        return deliveryTaskId;
    }

    public void setDeliveryTaskId(Integer deliveryTaskId) {
        this.deliveryTaskId = deliveryTaskId;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Date getDeliveryDt() {
        return deliveryDt;
    }

    public void setDeliveryDt(Date deliveryDt) {
        this.deliveryDt = deliveryDt;
    }

    public Date getBeginDt() {
        return beginDt;
    }

    public void setBeginDt(Date beginDt) {
        this.beginDt = beginDt;
    }

    public Date getEndDt() {
        return endDt;
    }

    public void setEndDt(Date endDt) {
        this.endDt = endDt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }
}