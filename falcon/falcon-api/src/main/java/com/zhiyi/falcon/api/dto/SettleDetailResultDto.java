package com.zhiyi.falcon.api.dto;

import java.io.Serializable;

public class SettleDetailResultDto implements Serializable{

    private String communityName;
    private String communityBuildingName;
    private Integer communityUnitNum;
    private String deliveryStatus;
    private String deliveryType;
    private Integer deliveryNum;
    private Double amount;

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getCommunityBuildingName() {
        return communityBuildingName;
    }

    public void setCommunityBuildingName(String communityBuildingName) {
        this.communityBuildingName = communityBuildingName;
    }

    public Integer getCommunityUnitNum() {
        return communityUnitNum;
    }

    public void setCommunityUnitNum(Integer communityUnitNum) {
        this.communityUnitNum = communityUnitNum;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Integer getDeliveryNum() {
        return deliveryNum;
    }

    public void setDeliveryNum(Integer deliveryNum) {
        this.deliveryNum = deliveryNum;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
