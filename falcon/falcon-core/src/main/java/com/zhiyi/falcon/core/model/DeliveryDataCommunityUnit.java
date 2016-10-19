package com.zhiyi.falcon.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;

@SuppressWarnings("serial")
public class DeliveryDataCommunityUnit implements Serializable {
    private Integer id;

    private Integer communityUnitId;

    private Integer buildId;

    private Integer deliveryNum;

    private Integer deliveryEmployeeId;

    private Integer deliveryTaskId;

    private String deliveryType;

    private String deliveryResult;

    private String remark;

    private Date deliveryDt;

    private Integer communityId;

    private String communityName;

    private String buildingName;

    private String communityUnitName;

    private String settleStatus;

    private String deliveryEmployeeName;

    private String taskSampling;

    private String deliveryCity;
    
    private List<DeliveryDataCommunityUnitPicture> pictures = Lists.newArrayList();

    public DeliveryDataCommunityUnit(){

    }

    public DeliveryDataCommunityUnit(Integer id, Integer communityUnitId, Integer buildId, Integer deliveryNum,
                                     Integer deliveryEmployeeId, Integer deliveryTaskId, String deliveryType,
                                     String deliveryResult, String remark, Date deliveryDt, Integer communityId,
                                     String communityName, String buildingName, String communityUnitName,
                                     String settleStatus, String deliveryEmployeeName,
                                     String taskSampling, String deliveryCity) {
        this.id = id;
        this.communityUnitId = communityUnitId;
        this.buildId = buildId;
        this.deliveryNum = deliveryNum;
        this.deliveryEmployeeId = deliveryEmployeeId;
        this.deliveryTaskId = deliveryTaskId;
        this.deliveryType = deliveryType;
        this.deliveryResult = deliveryResult;
        this.remark = remark;
        this.deliveryDt = deliveryDt;
        this.communityId = communityId;
        this.communityName = communityName;
        this.buildingName = buildingName;
        this.communityUnitName = communityUnitName;
        this.settleStatus = settleStatus;
        this.deliveryEmployeeName = deliveryEmployeeName;
        this.taskSampling = taskSampling;
        this.deliveryCity = deliveryCity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommunityUnitId() {
        return communityUnitId;
    }

    public void setCommunityUnitId(Integer communityUnitId) {
        this.communityUnitId = communityUnitId;
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

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getDeliveryResult() {
        return deliveryResult;
    }

    public void setDeliveryResult(String deliveryResult) {
        this.deliveryResult = deliveryResult;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getDeliveryDt() {
        return deliveryDt;
    }

    public void setDeliveryDt(Date deliveryDt) {
        this.deliveryDt = deliveryDt;
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

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getCommunityUnitName() {
        return communityUnitName;
    }

    public void setCommunityUnitName(String communityUnitName) {
        this.communityUnitName = communityUnitName;
    }

    public String getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus;
    }

    public String getDeliveryEmployeeName() {
        return deliveryEmployeeName;
    }

    public void setDeliveryEmployeeName(String deliveryEmployeeName) {
        this.deliveryEmployeeName = deliveryEmployeeName;
    }

    public String getTaskSampling() {
        return taskSampling;
    }

    public void setTaskSampling(String taskSampling) {
        this.taskSampling = taskSampling;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

	public List<DeliveryDataCommunityUnitPicture> getPictures() {
		return pictures;
	}

	public void setPictures(List<DeliveryDataCommunityUnitPicture> pictures) {
		this.pictures = pictures;
	}
}