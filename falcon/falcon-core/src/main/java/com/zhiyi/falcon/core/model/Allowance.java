package com.zhiyi.falcon.core.model;

import com.zhiyi.falcon.api.dto.AllowanceDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Allowance {
    private Integer id;

    private Integer settleId;

    private Integer userId;

    private String userName;

    private Integer taskId;

    private String allowanceType;

    private Double allowanceAmount;

    private Date createDt;

    private String createUser;

    public Allowance(Integer id, Integer settleId, Integer userId, String userName, Integer taskId, String allowanceType, Double allowanceAmount, Date createDt, String createUser) {
        this.id = id;
        this.settleId = settleId;
        this.userId = userId;
        this.userName = userName;
        this.taskId = taskId;
        this.allowanceType = allowanceType;
        this.allowanceAmount = allowanceAmount;
        this.createDt = createDt;
        this.createUser = createUser;
    }

    public Allowance() {
        super();
    }

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

    /**
     * DTO 转换为 Model
     * @param allowanceDto
     * @return
     */
    public static Allowance dtoToModel(AllowanceDto allowanceDto) {
        Allowance allowance = new Allowance();
        allowance.setAllowanceAmount(allowanceDto.getAllowanceAmount());
        allowance.setAllowanceType(allowanceDto.getAllowanceType());
        allowance.setCreateDt(allowanceDto.getCreateDt());
        allowance.setCreateUser(allowanceDto.getCreateUser());
        allowance.setId(allowanceDto.getId());
        allowance.setSettleId(allowanceDto.getSettleId());
        allowance.setTaskId(allowanceDto.getTaskId());
        allowance.setUserId(allowanceDto.getUserId());
        allowance.setUserName(allowanceDto.getUserName());
        return allowance;
    }

    /**
     * 将 modelList 转化为DtoList
     * @param allowanceList
     * @return
     */
    public static List<AllowanceDto> modelListToDtoList(List<Allowance> allowanceList) {
        List<AllowanceDto> allowanceDtoList = new ArrayList<>();
        if( allowanceList == null){
            return allowanceDtoList;
        }
        for( Allowance allowance : allowanceList){
            allowanceDtoList.add(Allowance.modelTodto(allowance));
        }
        return allowanceDtoList;

    }

    /**
     * 将 model 转化为 Dto
     * @param allowance
     * @return
     */
    public static AllowanceDto modelTodto(Allowance allowance) {
        AllowanceDto allowanceDto = new AllowanceDto();
        allowanceDto.setId(allowance.getId());
        allowanceDto.setUserId(allowance.getUserId());
        allowanceDto.setTaskId(allowance.getTaskId());
        allowanceDto.setUserName(allowance.getUserName());
        allowanceDto.setSettleId(allowance.getSettleId());
        allowanceDto.setAllowanceAmount(allowance.getAllowanceAmount());
        allowanceDto.setAllowanceType(allowance.getAllowanceType());
        allowanceDto.setCreateDt(allowance.getCreateDt());
        allowanceDto.setCreateUser(allowance.getCreateUser());
        return allowanceDto;
    }
}