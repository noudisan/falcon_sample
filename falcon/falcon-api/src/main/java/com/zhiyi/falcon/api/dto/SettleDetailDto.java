package com.zhiyi.falcon.api.dto;

import java.io.Serializable;
import java.util.Date;

public class SettleDetailDto implements Serializable{
    private Integer id;

    private Integer settleId;

    private String sendStyle;//delivery Type

    private Integer bookNum;// 派发数量

    private Double price;//结算明细金额

    private Double settleAmount;//总额

    private Integer status;//状态

    private String communityName;//小区名称

    private Integer buildingNum;//层数

    private Integer communityUnitNum;//单元数（ 废弃）

    private Integer userId;

    private Integer taskId;

    private Integer buildingId;

    private String buildName;

    private Integer communityId;

    private String deliveryResult;//派发结果

    private Date settleDt;//结算日期

    private String settleTime;//查询日期

    private String sectionId;//板块id

    private String sectionName; //板块名称

    private Integer communityUnitId;

    public Integer getCommunityUnitId() {
        return communityUnitId;
    }

    public void setCommunityUnitId(Integer communityUnitId) {
        this.communityUnitId = communityUnitId;
    }

    public SettleDetailDto() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSettleId() {
        return settleId;
    }

    public void setSettleId(Integer settleId) {
        this.settleId = settleId;
    }

    public String getSendStyle() {
        return sendStyle;
    }

    public void setSendStyle(String sendStyle) {
        this.sendStyle = sendStyle;
    }

    public Integer getBookNum() {
        return bookNum;
    }

    public void setBookNum(Integer bookNum) {
        this.bookNum = bookNum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(Double settleAmount) {
        this.settleAmount = settleAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public Integer getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(Integer buildingNum) {
        this.buildingNum = buildingNum;
    }

    public Integer getCommunityUnitNum() {
        return communityUnitNum;
    }

    public void setCommunityUnitNum(Integer communityUnitNum) {
        this.communityUnitNum = communityUnitNum;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getDeliveryResult() {
        return deliveryResult;
    }

    public void setDeliveryResult(String deliveryResult) {
        this.deliveryResult = deliveryResult;
    }

    public Date getSettleDt() {
        return settleDt;
    }

    public void setSettleDt(Date settleDt) {
        this.settleDt = settleDt;
    }

    public String getSettleTime() {
        return settleTime;
    }

    public void setSettleTime(String settleTime) {
        this.settleTime = settleTime;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    @Override
    public String toString() {
        return "SettleDetailDto{" +
                "id=" + id +
                ", settleId=" + settleId +
                ", sendStyle='" + sendStyle + '\'' +
                ", bookNum=" + bookNum +
                ", price=" + price +
                ", settleAmount=" + settleAmount +
                ", status=" + status +
                ", communityName='" + communityName + '\'' +
                ", buildingNum=" + buildingNum +
                ", communityUnitNum=" + communityUnitNum +
                '}';
    }


    public static String getSortColumnName(Object sortColumnForInvisible, int iSortCol_0) {

        if(iSortCol_0 ==1){
            return  "ID";
        } else if(iSortCol_0 ==2){
            return  "SETTLE_ID";
        } else if(iSortCol_0 ==3){
            return  "SEND_STYLE";
        } else if(iSortCol_0 ==4){
            return  "BOOK_NUM";
        }else if(iSortCol_0 ==5){
            return  "PRICE";
        }else if(iSortCol_0 ==6){
            return  "SETTLE_AMOUNT";
        }else if(iSortCol_0 ==7){
            return  "STATUS";
        }else if(iSortCol_0 ==8){
            return  "NEIGHBORHOOD_NAME";
        }else if(iSortCol_0 ==9){
            return  "FLOOR_NUM";
        }else if(iSortCol_0 ==10){
            return  "UNIT_NUM";
        }
        return "ID";
    }

}