package com.zhiyi.falcon.api.dto;

import com.zhiyi.common.dto.PageSearchDto;

import java.io.Serializable;
import java.util.Date;

public class SettleDto extends PageSearchDto implements Serializable {
    private Integer id;

    private String name;

    private Integer taskId;

    private Integer sendNum;

    private Integer price;

    private Double settleAmount;

    private Date settleDate;

    private Integer caushStatus;

    private Date startTime;   //派送开始时间

    private Date endTime;

    private Long totalTime;

    private Integer communityNum;

    private Integer buildingNum;

    private Integer communityUnitNum;

    private String createUser;

    private Date createDt;

    private String modifyUser;

    private Date modifyDt;

    //因搜索添加的字段

    private Date startDate;  //开始时间

    private Date endDate;    //结束时间

    private String deviceId;

    private String version;

    private Integer userId;

    private String deliveryCity; //新增搜索条件，派发城市



    public SettleDto() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSendNum() {
        return sendNum;
    }

    public void setSendNum(Integer sendNum) {
        this.sendNum = sendNum;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }



    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public Integer getCaushStatus() {
        return caushStatus;
    }

    public void setCaushStatus(Integer caushStatus) {
        this.caushStatus = caushStatus;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(Double settleAmount) {
        this.settleAmount = settleAmount;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getCommunityNum() {
        return communityNum;
    }

    public void setCommunityNum(Integer communityNum) {
        this.communityNum = communityNum;
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getModifyDt() {
        return modifyDt;
    }

    public void setModifyDt(Date modifyDt) {
        this.modifyDt = modifyDt;
    }

    @Override
    public String toString() {
        return "SettleDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sendNum=" + sendNum +
                ", price=" + price +
                ", settleAmount=" + settleAmount +
                ", settleDate=" + settleDate +
                ", caushStatus=" + caushStatus +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", totalTime=" + totalTime +
                ", communityNum=" + communityNum +
                ", buildingNum=" + buildingNum +
                ", communityUnitNum=" + communityUnitNum +
                ", createUser='" + createUser + '\'' +
                ", createDt=" + createDt +
                ", modifyUser='" + modifyUser + '\'' +
                ", modifyDt=" + modifyDt +
                '}';
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public static String getSortColumnName(Object sortColumnForInvisible, int iSortCol_0) {

        if(iSortCol_0 ==1){
            return  "ID";
        } else if(iSortCol_0 ==2){
            return  "NAME";
        } else if(iSortCol_0 ==3){
            return  "SEND_NUM";
        } else if(iSortCol_0 ==4){
            return  "SETTLE_AMOUNT";
        } else if(iSortCol_0 ==5){
            return  "SETTLE_DATE";
        } else if(iSortCol_0 ==6){
            return  "DELIVERY_CITY";
        }else if(iSortCol_0 == 7){
            return  "START_TIME";
        } else if(iSortCol_0 ==8){
            return  "END_TIME";
        } else if(iSortCol_0 ==9){
            return  "TOTAL_TIME";
        } else if(iSortCol_0 ==10){
            return  "NEIGHBORHOODS_NUM";
        } else if(iSortCol_0 ==11){
            return  "FLOOR_NUM";
        }else if(iSortCol_0 ==12){
            return  "UNIT_NUM";
        }

        return "ID";
    }
}