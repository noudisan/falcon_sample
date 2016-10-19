package com.zhiyi.falcon.gateway.result;

import java.util.List;

/**
 */
public class DeliveryProcessResult {
    private String date; //当前时间
    private Integer payoutAmount;//派发册子数量

    private String startDate;//当前时间
    private String tokenTime; //总耗时
    private Integer communityAmount;//小区数
    private Integer buildingAmount;//楼栋数
    private Integer unitAmount;//单元数
    private String total; //总收入

    private List<DeliveryProcessSettleResult> settleResultList;//结算明细

    public String getTokenTime() {
        return tokenTime;
    }

    public void setTokenTime(String tokenTime) {
        this.tokenTime = tokenTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getPayoutAmount() {
        return payoutAmount;
    }

    public void setPayoutAmount(Integer payoutAmount) {
        this.payoutAmount = payoutAmount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getCommunityAmount() {
        return communityAmount;
    }

    public void setCommunityAmount(Integer communityAmount) {
        this.communityAmount = communityAmount;
    }

    public Integer getBuildingAmount() {
        return buildingAmount;
    }

    public void setBuildingAmount(Integer buildingAmount) {
        this.buildingAmount = buildingAmount;
    }

    public Integer getUnitAmount() {
        return unitAmount;
    }

    public void setUnitAmount(Integer unitAmount) {
        this.unitAmount = unitAmount;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<DeliveryProcessSettleResult> getSettleResultList() {
        return settleResultList;
    }

    public void setSettleResultList(List<DeliveryProcessSettleResult> settleResultList) {
        this.settleResultList = settleResultList;
    }
}
