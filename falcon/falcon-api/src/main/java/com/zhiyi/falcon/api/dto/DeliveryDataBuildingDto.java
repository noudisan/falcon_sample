package com.zhiyi.falcon.api.dto;

import com.zhiyi.common.dto.PageSearchDto;
import com.zhiyi.falcon.api.enumType.DeliveryStatus;

import java.io.Serializable;

/**
 * 楼栋投递信息
 */
public class DeliveryDataBuildingDto extends PageSearchDto implements Serializable {

    private static final long serialVersionUID = 5749999915529788617L;

    private Integer id;

    private Integer buildId;

    private Integer deliveryNum;

    private Integer communityId;

    private Integer deliveryEmployeeId;

    private Integer deliveryTaskId;

    private DeliveryStatus deliveryStatus;

    private String deliveryDt;

    private String beginDt;

    private String endDt;

    private String remark;

    private String name;//楼栋名称

    private String communityName;//小区名称

    private String userName;//派送人员

    private Double longitude;

    private Double latitude;

    private String deliveryCity;


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

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryDt() {
        return deliveryDt;
    }

    public void setDeliveryDt(String deliveryDt) {
        this.deliveryDt = deliveryDt;
    }

    public String getBeginDt() {
        return beginDt;
    }

    public void setBeginDt(String beginDt) {
        this.beginDt = beginDt;
    }

    public String getEndDt() {
        return endDt;
    }

    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "DeliveryDataBuildingDto{" +
                "id=" + id +
                ", buildId=" + buildId +
                ", deliveryNum=" + deliveryNum +
                ", communityId=" + communityId +
                ", deliveryEmployeeId=" + deliveryEmployeeId +
                ", deliveryTaskId=" + deliveryTaskId +
                ", deliveryStatus=" + deliveryStatus +
                ", deliveryDt='" + deliveryDt + '\'' +
                ", beginDt='" + beginDt + '\'' +
                ", endDt='" + endDt + '\'' +
                ", remark='" + remark + '\'' +
                ", name='" + name + '\'' +
                ", communityName='" + communityName + '\'' +
                ", userName='" + userName + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }
}
