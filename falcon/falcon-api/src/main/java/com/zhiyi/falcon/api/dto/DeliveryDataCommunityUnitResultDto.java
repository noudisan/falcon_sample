package com.zhiyi.falcon.api.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Adminstrator on 2015-07-07.
 */
public class DeliveryDataCommunityUnitResultDto implements Serializable {

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

    private String city;

    private String area;

    private String taskSampling; //抽查状态

    private Integer totalSendNum; //总派送数量

    private Integer communityDeliveryNum;  //派送小区数量

    private Integer buildDeliveryNum;     //派送楼栋数量

    private Integer unitDeliveryNum;     //派送单元数量

    private Integer unitNum;

    private Integer buildingId;


    public String getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public DeliveryDataCommunityUnitResultDto() {
        super();
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


    public Integer getTotalSendNum() {
        return totalSendNum;
    }

    public void setTotalSendNum(Integer totalSendNum) {
        this.totalSendNum = totalSendNum;
    }

    public Integer getCommunityDeliveryNum() {
        return communityDeliveryNum;
    }

    public void setCommunityDeliveryNum(Integer communityDeliveryNum) {
        this.communityDeliveryNum = communityDeliveryNum;
    }

    public Integer getBuildDeliveryNum() {
        return buildDeliveryNum;
    }

    public void setBuildDeliveryNum(Integer buildDeliveryNum) {
        this.buildDeliveryNum = buildDeliveryNum;
    }

    public Integer getUnitDeliveryNum() {
        return unitDeliveryNum;
    }

    public void setUnitDeliveryNum(Integer unitDeliveryNum) {
        this.unitDeliveryNum = unitDeliveryNum;
    }

    public String getTaskSampling() {
        return taskSampling;
    }

    public void setTaskSampling(String taskSampling) {
        this.taskSampling = taskSampling;
    }

    public Integer getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(Integer unitNum) {
        this.unitNum = unitNum;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }
}

