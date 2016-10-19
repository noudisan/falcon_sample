package com.zhiyi.falcon.api.dto;

import com.zhiyi.common.dto.PageSearchDto;
import com.zhiyi.falcon.api.enumType.DeliveryStatus;

import java.io.Serializable;
import java.util.Date;

/**
 * 小区派送任务
 */
public class DeliveryDataCommunityDto extends PageSearchDto implements Serializable {

    private static final long serialVersionUID = 5749999915529788617L;

    public static final int SORT_COLUMN_FOR_VISIBLE = 1;
    public static final int SORT_COLUMN_FOR_INVISIBLE = 2;

    private Integer id;

    private Integer communityId;

    private Integer deliveryNum;

    private Integer sectionId;

    private Integer deliveryTaskId;

    private DeliveryStatus deliveryStatus;

    private String deliveryDt;

    private String beginDt;

    private String remark;

    private String communityName;

    private String city;

    private double latitude;

    private double longitude;

    private String deliveryCity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "DeliveryDataCommunityDto{" +
                "id=" + id +
                ", communityId=" + communityId +
                ", deliveryNum=" + deliveryNum +
                ", sectionId=" + sectionId +
                ", deliveryTaskId=" + deliveryTaskId +
                ", deliveryStatus=" + deliveryStatus +
                ", deliveryDt='" + deliveryDt + '\'' +
                ", beginDt='" + beginDt + '\'' +
                ", remark='" + remark + '\'' +
                ", communityName='" + communityName + '\'' +
                ", city='" + city + '\'' +
                '}';
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
}
