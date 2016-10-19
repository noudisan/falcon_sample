package com.zhiyi.falcon.gateway.result;

import com.zhiyi.falcon.api.dto.DeliveryDataBuildingDto;
import com.zhiyi.falcon.api.enumType.DeliveryStatus;

/**
 * 小区楼栋
 */
public class CommunityBuildingTaskResult {
    private Integer buildingId;
    private String buildingName;
    private Double longitude;
    private Double latitude;
    private String status;
    private String statusStr;

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }


    public static CommunityBuildingTaskResult toResult(DeliveryDataBuildingDto deliveryDataBuildingDto){
        CommunityBuildingTaskResult communityBuildingTaskResult =new CommunityBuildingTaskResult();
        communityBuildingTaskResult.setBuildingId(deliveryDataBuildingDto.getBuildId());
        communityBuildingTaskResult.setBuildingName(deliveryDataBuildingDto.getName());
        communityBuildingTaskResult.setLongitude(deliveryDataBuildingDto.getLongitude());
        communityBuildingTaskResult.setLatitude(deliveryDataBuildingDto.getLatitude());
        DeliveryStatus status1 = deliveryDataBuildingDto.getDeliveryStatus() == null ? DeliveryStatus.TO_DELIVERY : deliveryDataBuildingDto.getDeliveryStatus();
        communityBuildingTaskResult.setStatus(status1.name());
        communityBuildingTaskResult.setStatusStr(status1.getStatus());
        return communityBuildingTaskResult;
    }
}
