package com.zhiyi.falcon.api.dto;

import com.zhiyi.common.dto.PageSearchDto;
import com.zhiyi.falcon.api.enumType.DeliveryStatus;

import java.io.Serializable;
import java.util.Date;


public class DeliveryDataBuildingSearchDto extends PageSearchDto implements Serializable {


    public static final int SORT_COLUMN_FOR_VISIBLE = 1;
    public static final int SORT_COLUMN_FOR_INVISIBLE = 2;

    private Integer id;

    private Integer buildId;

    private Integer deliveryNum;

    private Integer communityId;

    private Integer deliveryEmployeeId;

    private Integer deliveryTaskId;

    private String deliveryStatus;

    private String deliveryDt;

    private String beginDt;

    private String endDt;

    private String remark;

    private String userName;

    private String userRole;

    private String userSex;

    private String name;//楼栋名称

    private String communityName;//小区名称

    private String deliveryCity;

    private Date startDate;

    private Date endDate;

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

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

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
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

    public static String getSortColumnName(Object sortColumnForInvisible, int iSortCol_0) {

        if(iSortCol_0 == 1){
            return "DELIVERY_NUM";
        }else if(iSortCol_0 ==2){
            return "DELIVERY_TYPE";
        }else if(iSortCol_0 ==3){
            return "DELIVERY_RESULT";
        }else if(iSortCol_0 == 4){
            return "RMARK";
        }else if(iSortCol_0 == 5){
            return "DELIVERY_DT";
        }
        return "ID";
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
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
}
