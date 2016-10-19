package com.zhiyi.falcon.api.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Lists;
import com.zhiyi.falcon.api.enumType.DeliveryResult;
import com.zhiyi.falcon.api.enumType.DeliveryType;
import com.zhiyi.falcon.api.enumType.SettleStatus;
import com.zhiyi.falcon.api.enumType.TaskSampling;

/**
 */
public class DeliveryDataCommunityUnitDto implements Serializable {

    private static final long serialVersionUID = 5749999915529788617L;

    private Integer id;

    private Integer communityUnitId;

    private Integer buildId;

    private Integer deliveryNum;//投递数量

    private Integer deliveryEmployeeId;

    private Integer deliveryTaskId;

    private DeliveryType deliveryType;

    private DeliveryResult deliveryResult;

    private String remark;

    private String deliveryDt;

    private Integer communityId;

    private String communityName;

    private String buildingName;

    private String communityUnitName;

    private SettleStatus settleStatus;

    private String city;

    private String area;

    private String userName;

    private TaskSampling taskSampling;//抽查数据

    private String deliveryCity;
    
    private List<DeliveryDataCommunityUnitPictureDto> pictures = Lists.newArrayList();


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

    public String getDeliveryDt() {
        return deliveryDt;
    }

    public void setDeliveryDt(String deliveryDt) {
        this.deliveryDt = deliveryDt;
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

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public DeliveryResult getDeliveryResult() {
        return deliveryResult;
    }

    public void setDeliveryResult(DeliveryResult deliveryResult) {
        this.deliveryResult = deliveryResult;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public SettleStatus getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(SettleStatus settleStatus) {
        this.settleStatus = settleStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public TaskSampling getTaskSampling() {
        return taskSampling;
    }

    public void setTaskSampling(TaskSampling taskSampling) {
        this.taskSampling = taskSampling;
    }

    @Override
    public String toString() {
        return "DeliveryDataCommunityUnitDto{" +
                "id=" + id +
                ", communityUnitId=" + communityUnitId +
                ", buildId=" + buildId +
                ", deliveryNum=" + deliveryNum +
                ", deliveryEmployeeId=" + deliveryEmployeeId +
                ", deliveryTaskId=" + deliveryTaskId +
                ", deliveryType=" + deliveryType +
                ", deliveryResult=" + deliveryResult +
                ", remark='" + remark + '\'' +
                ", deliveryDt='" + deliveryDt + '\'' +
                ", communityId=" + communityId +
                ", communityName='" + communityName + '\'' +
                ", buildingName='" + buildingName + '\'' +
                ", communityUnitName='" + communityUnitName + '\'' +
                ", settleStatus=" + settleStatus +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", userName='" + userName + '\'' +
                ", taskSampling=" + taskSampling +
                '}';
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

	public List<DeliveryDataCommunityUnitPictureDto> getPictures() {
		return pictures;
	}

	public void setPictures(List<DeliveryDataCommunityUnitPictureDto> pictures) {
		this.pictures = pictures;
	}
	
	public void addPicture(DeliveryDataCommunityUnitPictureDto picture) {
		if( CollectionUtils.isEmpty(this.pictures) ){
			this.pictures = Lists.newArrayList();
		}
		this.pictures.add(picture);
	}
}
