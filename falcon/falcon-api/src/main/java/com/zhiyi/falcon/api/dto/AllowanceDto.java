package com.zhiyi.falcon.api.dto;

import com.zhiyi.common.dto.PageSearchDto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Adminstrator on 2015-07-21.
 */
public class AllowanceDto extends PageSearchDto implements Serializable{
    private Integer id;

    private Integer settleId;

    private Integer userId;

    private String userName;

    private Integer taskId;

    private String allowanceType;

    private Double allowanceAmount;

    private Date createDt;

    private String createUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSettleId() {
        return settleId;
    }

    public void setSettleId(Integer settleId) {
        this.settleId = settleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getAllowanceType() {
        return allowanceType;
    }

    public void setAllowanceType(String allowanceType) {
        this.allowanceType = allowanceType;
    }

    public Double getAllowanceAmount() {
        return allowanceAmount;
    }

    public void setAllowanceAmount(Double allowanceAmount) {
        this.allowanceAmount = allowanceAmount;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
