package com.zhiyi.falcon.core.model;

import com.zhiyi.falcon.api.dto.AccountInfoDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountInfo {
    private Integer id;

    private Integer userId;

    private String userName;

    private String phone;

    private Double accountAmount;

    private Date createDt;

    private Date midifyDt;

    private String createUser;

    private String modifyUser;


    /**
     * DTO转为Model
     * @param accountInfoDto
     * @return
     */
    public static AccountInfo dtoToModel(AccountInfoDto accountInfoDto){
        AccountInfo accountInfo = new AccountInfo();

        accountInfo.setId(accountInfoDto.getId());
        accountInfo.setUserId(accountInfoDto.getUserId());
        accountInfo.setUserName(accountInfoDto.getUserName());
        accountInfo.setPhone(accountInfoDto.getPhone());
        accountInfo.setAccountAmount(accountInfoDto.getAccountAmount());
        accountInfo.setCreateDt(accountInfoDto.getCreateDt());
        accountInfo.setMidifyDt(accountInfoDto.getMidifyDt());
        accountInfo.setCreateUser(accountInfoDto.getCreateUser());
        accountInfo.setModifyUser(accountInfoDto.getModifyUser());
        return accountInfo;
    }

    /**
     * Model转为DTO
     * @param accountInfo
     * @return
     */
    public static AccountInfoDto modelTodto(AccountInfo accountInfo){
        AccountInfoDto accountInfoDto = new AccountInfoDto();
        accountInfoDto.setId(accountInfo.getId());
        accountInfoDto.setUserId(accountInfo.getUserId());
        accountInfoDto.setUserName(accountInfo.getUserName());
        accountInfoDto.setPhone(accountInfo.getPhone());
        accountInfoDto.setAccountAmount(accountInfo.getAccountAmount());
        accountInfoDto.setCreateDt(accountInfo.getCreateDt());
        accountInfoDto.setMidifyDt(accountInfo.getMidifyDt());
        accountInfoDto.setCreateUser(accountInfo.getCreateUser());
        accountInfoDto.setModifyUser(accountInfo.getModifyUser());
        return  accountInfoDto;
    }


    /**
     * 将DTO列表转换为Model列表
     * @param accountInfoDtoList
     * @return
     */
    public static List<AccountInfo> dtoListToModelList(List<AccountInfoDto> accountInfoDtoList){
        List<AccountInfo> accountInfoList = new ArrayList<AccountInfo>();
        if( accountInfoDtoList == null){
            return null;
        }
        for( AccountInfoDto accountInfoDto : accountInfoDtoList){
            accountInfoList.add(AccountInfo.dtoToModel(accountInfoDto));
        }
        return accountInfoList;

    }


    /**
     * 将Model转换为DTO
     * @param accountInfoList
     * @return
     */
    public static List<AccountInfoDto> modelListToDtoList(List<AccountInfo> accountInfoList){
        List<AccountInfoDto> accountInfoDtoList = new ArrayList<AccountInfoDto>();
        if( accountInfoList == null){
            return null;
        }
        for( AccountInfo accountInfo : accountInfoList){
            accountInfoDtoList.add(AccountInfo.modelTodto(accountInfo));
        }
        return accountInfoDtoList;

    }

    public AccountInfo(Integer id, Integer userId, String userName, String phone, Double accountAmount, Date createDt, Date midifyDt, String createUser, String modifyUser) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.phone = phone;
        this.accountAmount = accountAmount;
        this.createDt = createDt;
        this.midifyDt = midifyDt;
        this.createUser = createUser;
        this.modifyUser = modifyUser;
    }

    public AccountInfo() {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(Double accountAmount) {
        this.accountAmount = accountAmount;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Date getMidifyDt() {
        return midifyDt;
    }

    public void setMidifyDt(Date midifyDt) {
        this.midifyDt = midifyDt;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }
}