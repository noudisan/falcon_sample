package com.zhiyi.falcon.gateway.result;

import java.io.Serializable;

/**
 * app个人详情：显示银行信息
 */
public class UserDetailBankInfoResult implements Serializable{
    private String idNumber;//身份证

    private String cardHolder;//持卡人

    private String bankName;//银行名称

    private String cardNo;//卡号

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
