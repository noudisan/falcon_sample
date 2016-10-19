package com.zhiyi.falcon.core.model;

import java.util.Date;

public class DeliveryDataCommunity {
    private Integer id;

    private Integer communityId;

    private Integer deliveryNum;

    private Integer sectionId;

    private Integer deliveryTaskId;

    private String deliveryStatus;

    private Date deliveryDt;

    private Date beginDt;

    private String remark;

    private String communityName;

    private String deliveryCity;

    public DeliveryDataCommunity(Integer id, Integer communityId, Integer deliveryNum, Integer sectionId, Integer deliveryTaskId, String deliveryStatus, Date deliveryDt, Date beginDt, String remark, String communityName, String deliveryCity) {
        this.id = id;
        this.communityId = communityId;
        this.deliveryNum = deliveryNum;
        this.sectionId = sectionId;
        this.deliveryTaskId = deliveryTaskId;
        this.deliveryStatus = deliveryStatus;
        this.deliveryDt = deliveryDt;
        this.beginDt = beginDt;
        this.remark = remark;
        this.communityName = communityName;
        this.deliveryCity = deliveryCity;
    }

    public DeliveryDataCommunity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getDeliveryNum() {
        return deliveryNum;
    }

    public void setDeliveryNum(Integer deliveryNum) {
        this.deliveryNum = deliveryNum;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
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

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }
}