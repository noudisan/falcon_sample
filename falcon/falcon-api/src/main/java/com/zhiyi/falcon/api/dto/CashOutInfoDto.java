package com.zhiyi.falcon.api.dto;

import com.zhiyi.common.dto.PageSearchDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CashOutInfoDto extends PageSearchDto implements Serializable{
    private Integer id;

    private Integer userId;

    private String name;

    private Double cashAmount;

    private Date cashDate;

    private String openBank;

    private String cashCard;

    private Integer dealStatus;

    private String resultComent;

    private Date createDt;

    private Date midifyDt;

    private String createUser;

    private String modifyUser;


    private Date startDate;


    private Date endDate;




    private String bankName;   //银行名称

    private String tailNumber;  //尾号

    private String upAmount;  //最多提现金额

    private String toDate;   //预计到账时间

    private Integer code;  //返回结果码

    private String msg; //消息




    public CashOutInfoDto() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(Double cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Date getCashDate() {
        return cashDate;
    }

    public void setCashDate(Date cashDate) {
        this.cashDate = cashDate;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    public String getCashCard() {
        return cashCard;
    }

    public void setCashCard(String cashCard) {
        this.cashCard = cashCard;
    }

    public Integer getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(Integer dealStatus) {
        this.dealStatus = dealStatus;
    }

    public String getResultComent() {
        return resultComent;
    }

    public void setResultComent(String resultComent) {
        this.resultComent = resultComent;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getTailNumber() {
        return tailNumber;
    }

    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }

    public String getUpAmount() {
        return upAmount;
    }

    public void setUpAmount(String upAmount) {
        this.upAmount = upAmount;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public static String getSortColumnName(Object sortColumnForInvisible, int iSortCol_0) {

        if(iSortCol_0 ==1){
            return  "ID";
        } else if(iSortCol_0 ==2){
            return  "NAME";
        } else if(iSortCol_0 ==3){
            return  "CASH_AMOUNT";
        } else if(iSortCol_0 ==4){
            return  "OPEN_BANK";
        } else if(iSortCol_0 ==5){
            return  "CASH_CARD";
        }else if(iSortCol_0 ==6){
            return  "DEAL_STATUS";
        }else if(iSortCol_0 ==7){
            return  "RESULT_COMENT";
        }else if(iSortCol_0 ==8){
            return  "CASH_DATE";
        }
        return "ID";
    }
}