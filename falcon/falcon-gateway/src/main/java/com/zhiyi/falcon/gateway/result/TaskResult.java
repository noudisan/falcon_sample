package com.zhiyi.falcon.gateway.result;

import com.zhiyi.falcon.api.dto.DeliveryTaskDto;

import java.io.Serializable;

/**
 * Created by lirenguan on 7/4/15.
 */

public class TaskResult implements Serializable{

    private Integer taskId;
    private String region;//任务区域
    private String plateNumber;//板块编号
    private Double latitude;
    private Double longitude;
    private String taskdate;
    private Integer payoutAmount;
    private String  gatheringPlace;
    private String gatheringTime;
    private Integer status; // 1 TASKBEGIN  任务开始    0   TASKTOBEGIN 等待开始  -1 任务结束
    private String statusStr; // 1 TASKBEGIN  任务开始    0   TASKTOBEGIN 等待开始  TASKEDN 任务结束
    private String teamLeadername;
    private String teamLeaderphone;
    private String teamdriverName;
    private String teamdriverphone;

    public static  TaskResult transferDto(DeliveryTaskDto deliveryTaskDto){
        TaskResult taskResult =new TaskResult();
        if(deliveryTaskDto ==null){
            return taskResult;
        }
        taskResult.setTaskId(deliveryTaskDto.getId());
        taskResult.setTeamdriverName(deliveryTaskDto.getDriver());
        taskResult.setTeamdriverphone(deliveryTaskDto.getDriverPhoneNum());
        taskResult.setTeamLeadername(deliveryTaskDto.getLeader());
        taskResult.setTeamLeaderphone(deliveryTaskDto.getLeaderPhoneNum());
        taskResult.setGatheringPlace(deliveryTaskDto.getMassAddress());
        taskResult.setRegion(deliveryTaskDto.getRegion());
        taskResult.setPayoutAmount(deliveryTaskDto.getSendCount());

        taskResult.setPlateNumber(deliveryTaskDto.getSectionNameStr());

        return taskResult;
    }



    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getTaskdate() {
        return taskdate;
    }

    public void setTaskdate(String taskdate) {
        this.taskdate = taskdate;
    }

    public Integer getPayoutAmount() {
        return payoutAmount;
    }

    public void setPayoutAmount(Integer payoutAmount) {
        this.payoutAmount = payoutAmount;
    }

    public String getGatheringPlace() {
        return gatheringPlace;
    }

    public void setGatheringPlace(String gatheringPlace) {
        this.gatheringPlace = gatheringPlace;
    }

    public String getGatheringTime() {
        return gatheringTime;
    }

    public void setGatheringTime(String gatheringTime) {
        this.gatheringTime = gatheringTime;
    }

    public String getTeamLeadername() {
        return teamLeadername;
    }

    public void setTeamLeadername(String teamLeadername) {
        this.teamLeadername = teamLeadername;
    }

    public String getTeamLeaderphone() {
        return teamLeaderphone;
    }

    public void setTeamLeaderphone(String teamLeaderphone) {
        this.teamLeaderphone = teamLeaderphone;
    }

    public String getTeamdriverName() {
        return teamdriverName;
    }

    public void setTeamdriverName(String teamdriverName) {
        this.teamdriverName = teamdriverName;
    }

    public String getTeamdriverphone() {
        return teamdriverphone;
    }

    public void setTeamdriverphone(String teamdriverphone) {
        this.teamdriverphone = teamdriverphone;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }
}
