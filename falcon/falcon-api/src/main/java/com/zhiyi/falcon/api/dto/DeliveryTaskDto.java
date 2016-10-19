package com.zhiyi.falcon.api.dto;

import com.zhiyi.falcon.api.enumType.TaskSampling;
import com.zhiyi.falcon.api.enumType.TaskStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class DeliveryTaskDto implements Serializable{

    private Integer id;

    private String code;

    private Integer sendCount;

    private String massTime;

    private String massAddress;

    private String leader;

    private String leaderPhoneNum;

    private String driver;

    private String driverPhoneNum;

    private TaskSampling isSampling;

    private String taskDesc;

    private TaskStatus status;

    private String startTime; //指的是任务在哪天执行

    private String modifyUser;

    private String modifyDt;

    private String createUser;

    private String createDt;

    private String region;//任务区域

    private String deliveryPerson;

    private String deliverySectionName;

    private String sectionIdStr;
    private String sectionNameStr;
    private List<DeliveryTaskEmployeeDto> employeeList = new ArrayList<>();

    private String employeeIdStr;
    private String employeeNameStr;
    private List<DeliveryTaskSectionDto> sectionList = new ArrayList<>();

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

    public String getMassTime() {
        return massTime;
    }

    public void setMassTime(String massTime) {
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

    public TaskSampling getIsSampling() {
        return isSampling;
    }

    public void setIsSampling(TaskSampling isSampling) {
        this.isSampling = isSampling;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getModifyDt() {
        return modifyDt;
    }

    public void setModifyDt(String modifyDt) {
        this.modifyDt = modifyDt;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(String deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    public String getDeliverySectionName() {
        return deliverySectionName;
    }

    public void setDeliverySectionName(String deliverySectionName) {
        this.deliverySectionName = deliverySectionName;
    }

    public String getSectionIdStr() {
        return sectionIdStr;
    }

    public void setSectionIdStr(String sectionIdStr) {
        this.sectionIdStr = sectionIdStr;
    }

    public String getSectionNameStr() {
        return sectionNameStr;
    }

    public void setSectionNameStr(String sectionNameStr) {
        this.sectionNameStr = sectionNameStr;
    }

    public List<DeliveryTaskEmployeeDto> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<DeliveryTaskEmployeeDto> employeeList) {
        this.employeeList = employeeList;
    }

    public String getEmployeeIdStr() {
        return employeeIdStr;
    }

    public void setEmployeeIdStr(String employeeIdStr) {
        this.employeeIdStr = employeeIdStr;
    }

    public String getEmployeeNameStr() {
        return employeeNameStr;
    }

    public void setEmployeeNameStr(String employeeNameStr) {
        this.employeeNameStr = employeeNameStr;
    }

    public List<DeliveryTaskSectionDto> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<DeliveryTaskSectionDto> sectionList) {
        this.sectionList = sectionList;
    }

    @Override
    public String toString() {
        return "DeliveryTaskDto{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", sendCount=" + sendCount +
                ", massTime='" + massTime + '\'' +
                ", massAddress='" + massAddress + '\'' +
                ", leader='" + leader + '\'' +
                ", leaderPhoneNum='" + leaderPhoneNum + '\'' +
                ", driver='" + driver + '\'' +
                ", driverPhoneNum='" + driverPhoneNum + '\'' +
                ", isSampling=" + isSampling +
                ", taskDesc='" + taskDesc + '\'' +
                ", status=" + status +
                ", startTime='" + startTime + '\'' +
                ", modifyUser='" + modifyUser + '\'' +
                ", modifyDt='" + modifyDt + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createDt='" + createDt + '\'' +
                ", region='" + region + '\'' +
                ", deliveryPerson='" + deliveryPerson + '\'' +
                ", deliverySectionName='" + deliverySectionName + '\'' +
                ", sectionIdStr='" + sectionIdStr + '\'' +
                ", sectionNameStr='" + sectionNameStr + '\'' +
                ", employeeIdStr='" + employeeIdStr + '\'' +
                ", employeeNameStr='" + employeeNameStr + '\'' +
                '}';
    }
}
