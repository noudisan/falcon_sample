package com.zhiyi.falcon.api.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2015/6/24.
 */
public class BaseEmployeeDto implements Serializable{
    private Integer id;

    private String userName;

    private String password;

    private String sex;

    private String career;

    private String phone;

    private String idNo;

    private String cardHolder;

    private String bankName;

    private String bankNo;

    private String headFile;

    private String role;

    private String city;

    private String isLocked;//UNLOCK LOCK

    private String deviceId;

    private String modifyUser;

    private Date modifyDt;

    private String createUser;

    private Date createDt;

    private String deliveryDt;

    private String settleStatus;

    private String deliveryResult;

    private String taskSampling;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getHeadFile() {
        return headFile;
    }

    public void setHeadFile(String headFile) {
        this.headFile = headFile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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

    public String getDeliveryDt() {
        return deliveryDt;
    }

    public void setDeliveryDt(String deliveryDt) {
        this.deliveryDt = deliveryDt;
    }

    public String getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus;
    }

    public String getDeliveryResult() {
        return deliveryResult;
    }

    public void setDeliveryResult(String deliveryResult) {
        this.deliveryResult = deliveryResult;
    }

    public String getTaskSampling() {
        return taskSampling;
    }

    public void setTaskSampling(String taskSampling) {
        this.taskSampling = taskSampling;
    }

    @Override
    public String toString() {
        return "BaseEmployeeDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", career='" + career + '\'' +
                ", phone='" + phone + '\'' +
                ", idNo='" + idNo + '\'' +
                ", cardHolder='" + cardHolder + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankNo='" + bankNo + '\'' +
                ", headFile='" + headFile + '\'' +
                ", role='" + role + '\'' +
                ", city='" + city + '\'' +
                ", isLocked='" + isLocked + '\'' +
                ", deviceId=" + deviceId +
                ", modifyUser='" + modifyUser + '\'' +
                ", modifyDt=" + modifyDt +
                ", createUser='" + createUser + '\'' +
                ", createDt=" + createDt +
                '}';
    }
}
