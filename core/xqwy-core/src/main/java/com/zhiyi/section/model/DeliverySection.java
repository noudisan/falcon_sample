package com.zhiyi.section.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 板块实体
 */
public class DeliverySection implements Serializable{
    private Integer id;

    private String address;

    private String city;

    private Double latitude;

    private Double longitude;

    private String sectionName;

    private String statusFlag;

    private String modifyUser;

    private Date modifyDt;

    private String createUser;

    private Date createDt;

    private List<DeliverySectionPoint> deliverySectionPoints;


    public DeliverySection(Integer id, String address, String city, Double latitude, Double longitude, String sectionName, String statusFlag, String modifyUser, Date modifyDt, String createUser, Date createDt) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sectionName = sectionName;
        this.statusFlag = statusFlag;
        this.modifyUser = modifyUser;
        this.modifyDt = modifyDt;
        this.createUser = createUser;
        this.createDt = createDt;
    }

    public DeliverySection() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(String statusFlag) {
        this.statusFlag = statusFlag;
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

    public List<DeliverySectionPoint> getDeliverySectionPoints() {return deliverySectionPoints;}

    public void setDeliverySectionPoints(List<DeliverySectionPoint> deliverySectionPoints) {this.deliverySectionPoints = deliverySectionPoints;}
}