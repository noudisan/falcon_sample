package com.zhiyi.falcon.api.dto;

import java.io.Serializable;

/**
 * 任务投递各种数量
 */
public class DeliveryNumDto implements Serializable {

    private Integer taskId;

    private Integer userId;

    //查询出派送册子的总数量
    private Integer totalDeliveryNum;
    //查询出派送小区数量
    private Integer DeliveryCommunityNum;
    //查询出派送楼栋数量
    private Integer DeliveryCommunityBuildingNum;
    //查询出派送单元数量
    private Integer DeliveryCommunityUnitNum;

    public Integer getTotalDeliveryNum() {
        return totalDeliveryNum;
    }

    public void setTotalDeliveryNum(Integer totalDeliveryNum) {
        this.totalDeliveryNum = totalDeliveryNum;
    }

    public Integer getDeliveryCommunityNum() {
        return DeliveryCommunityNum;
    }

    public void setDeliveryCommunityNum(Integer deliveryCommunityNum) {
        DeliveryCommunityNum = deliveryCommunityNum;
    }

    public Integer getDeliveryCommunityBuildingNum() {
        return DeliveryCommunityBuildingNum;
    }

    public void setDeliveryCommunityBuildingNum(Integer deliveryCommunityBuildingNum) {
        DeliveryCommunityBuildingNum = deliveryCommunityBuildingNum;
    }

    public Integer getDeliveryCommunityUnitNum() {
        return DeliveryCommunityUnitNum;
    }

    public void setDeliveryCommunityUnitNum(Integer deliveryCommunityUnitNum) {
        DeliveryCommunityUnitNum = deliveryCommunityUnitNum;
    }
}
