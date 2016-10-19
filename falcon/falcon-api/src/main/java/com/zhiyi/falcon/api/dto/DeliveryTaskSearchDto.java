package com.zhiyi.falcon.api.dto;

import com.zhiyi.common.dto.PageSearchDto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DeliveryTaskSearchDto extends PageSearchDto implements Serializable {

    public static final int SORT_COLUMN_FOR_VISIBLE = 1;
    public static final int SORT_COLUMN_FOR_INVISIBLE = 2;

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

    private Date startTime;

    private String modifyUser;

    private Date modifyDt;

    private String createUser;

    private Date createDt;

    private String region;

    private List<Integer> taskIdList;

    private Date usefulTask;//可用任务

    public List<Integer> getTaskIdList() {
        return taskIdList;
    }

    public void setTaskIdList(List<Integer> taskIdList) {
        this.taskIdList = taskIdList;
    }

    public Date getUsefulTask() {
        return usefulTask;
    }

    public void setUsefulTask(Date usefulTask) {
        this.usefulTask = usefulTask;
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

    public static String getSortColumnName(Object sortColumnForInvisible, int iSortCol_0) {
        if(iSortCol_0 ==1){
            return  "SEND_COUNT";
        } else if(iSortCol_0 ==2){
            return  "USER_NAME";
        } else if(iSortCol_0 ==3){
            return  "PHONE";
        } else if(iSortCol_0 ==4){
            return  "ROLE";
        }else if(iSortCol_0 ==5){
            return  "CITY";
        }else if(iSortCol_0 ==6){
            return  "IS_LOCKED";
        }
        return "ID";
    }

}
