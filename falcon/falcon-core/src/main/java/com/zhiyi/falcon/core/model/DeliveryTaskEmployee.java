package com.zhiyi.falcon.core.model;

import java.io.Serializable;

public class DeliveryTaskEmployee implements Serializable{
    private Integer id;

    private Integer employeeId;

    private Integer sendTaskId;

    private String taskStatus;

    public DeliveryTaskEmployee(Integer id, Integer employeeId, Integer sendTaskId, String taskStatus) {
        this.id = id;
        this.employeeId = employeeId;
        this.sendTaskId = sendTaskId;
        this.taskStatus = taskStatus;
    }

    public DeliveryTaskEmployee() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getSendTaskId() {
        return sendTaskId;
    }

    public void setSendTaskId(Integer sendTaskId) {
        this.sendTaskId = sendTaskId;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }
}