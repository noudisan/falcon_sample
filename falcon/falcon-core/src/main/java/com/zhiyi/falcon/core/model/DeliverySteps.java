package com.zhiyi.falcon.core.model;

import java.util.Date;

public class DeliverySteps {
    private Integer id;

    private Integer userId;

    private Integer taskId;

    private String steps;

    private Date startTime;

    private Date endTime;


    public DeliverySteps(Integer id, Integer userId, Integer taskId, String steps, Date startTime, Date endTime) {
        this.id = id;
        this.userId = userId;
        this.taskId = taskId;
        this.steps = steps;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DeliverySteps() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
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

}