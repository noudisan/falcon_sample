package com.zhiyi.falcon.api.dto;

import com.zhiyi.common.dto.PageSearchDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AccountInfoDto extends PageSearchDto implements Serializable{

    private static final long serialVersionUID = -1L;

    private Integer id;

    private Integer userId;

    private String userName;

    private String phone;

    private Double accountAmount;

    private Date createDt;

    private Date midifyDt;

    private String createUser;

    private String modifyUser;

    public Integer getId() {return id;}

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

    public static String getSortColumnName(Object sortColumnForInvisible, int iSortCol_0) {

        if(iSortCol_0 ==1){
            return  "ID";
        } else if(iSortCol_0 ==2){
            return  "USER_NAME";
        } else if(iSortCol_0 ==3){
            return  "PHONE";
        } else if(iSortCol_0 ==4){
            return  "ACCOUNT_AMOUNT";
        }
        return "ID";
    }

    @Override
    public String toString() {
        return "AccountInfoDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", accountAmount=" + accountAmount +
                ", createDt=" + createDt +
                ", midifyDt=" + midifyDt +
                ", createUser='" + createUser + '\'' +
                ", modifyUser='" + modifyUser + '\'' +
                '}';
    }
}