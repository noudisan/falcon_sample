package com.zhiyi.falcon.api.dto;

import com.zhiyi.falcon.api.enumType.TaskStatus;

import java.io.Serializable;

/**
 * 任务-派送员 关联表
 */
public class DeliveryTaskEmployeeDto implements Serializable{
    private Integer id;

    private Integer employeeId;

    private Integer sendTaskId;

    private TaskStatus taskStatus;

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

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
