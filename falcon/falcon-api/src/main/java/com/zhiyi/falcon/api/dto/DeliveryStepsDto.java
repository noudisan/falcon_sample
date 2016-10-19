package com.zhiyi.falcon.api.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by renfj on 2015/6/30.
 */
public class DeliveryStepsDto implements Serializable{
    private Integer id;

    private Integer userId;

    private Integer taskId;

    private String steps;

    private Date startTime;

    private Date endTime;


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


    @Override
    public String toString() {
        return "DeliveryStepsDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", taskId=" + taskId +
                ", steps='" + steps + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
