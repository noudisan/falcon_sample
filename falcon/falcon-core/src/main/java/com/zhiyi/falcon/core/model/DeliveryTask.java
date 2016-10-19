package com.zhiyi.falcon.core.model;

import java.io.Serializable;
import java.util.Date;

public class DeliveryTask implements Serializable{
    private Integer id;

    private String code;

    private Integer sendCount;

    private Date massTime;

    private String massAddress;

    private String leader;

    private String leaderPhoneNum;

    private String driver;

    private String driverPhoneNum;

    private String isSampling;

    private String taskDesc;

    private String status;

    private Date startTime;//指的是任务在哪天执行

    private String modifyUser;

    private Date modifyDt;

    private String createUser;

    private Date createDt;

    private String region;

    private String sectionNames;

    public DeliveryTask(Integer id, String code, Integer sendCount, Date massTime, String massAddress, String leader,
                        String leaderPhoneNum, String driver, String driverPhoneNum, String isSampling, String taskDesc,
                        String status, Date startTime, String modifyUser, Date modifyDt, String createUser,
                        Date createDt, String region, String sectionNames) {
        this.id = id;
        this.code = code;
        this.sendCount = sendCount;
        this.massTime = massTime;
        this.massAddress = massAddress;
        this.leader = leader;
        this.leaderPhoneNum = leaderPhoneNum;
        this.driver = driver;
        this.driverPhoneNum = driverPhoneNum;
        this.isSampling = isSampling;
        this.taskDesc = taskDesc;
        this.status = status;
        this.startTime = startTime;
        this.modifyUser = modifyUser;
        this.modifyDt = modifyDt;
        this.createUser = createUser;
        this.createDt = createDt;
        this.region = region;
        this.sectionNames = sectionNames;
    }

    public DeliveryTask() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSendCount() {
        return sendCount;
    }

    public void setSendCount(Integer sendCount) {
        this.sendCount = sendCount;
    }

    public Date getMassTime() {
        return massTime;
    }

    public void setMassTime(Date massTime) {
        this.massTime = massTime;
    }

    public String getMassAddress() {
        return massAddress;
    }

    public void setMassAddress(String massAddress) {
        this.massAddress = massAddress;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getLeaderPhoneNum() {
        return leaderPhoneNum;
    }

    public void setLeaderPhoneNum(String leaderPhoneNum) {
        this.leaderPhoneNum = leaderPhoneNum;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDriverPhoneNum() {
        return driverPhoneNum;
    }

    public void setDriverPhoneNum(String driverPhoneNum) {
        this.driverPhoneNum = driverPhoneNum;
    }

    public String getIsSampling() {
        return isSampling;
    }

    public void setIsSampling(String isSampling) {
        this.isSampling = isSampling;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSectionNames() {
        return sectionNames;
    }

    public void setSectionNames(String sectionNames) {
        this.sectionNames = sectionNames;
    }
}