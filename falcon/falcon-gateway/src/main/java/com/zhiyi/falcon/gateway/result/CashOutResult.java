package com.zhiyi.falcon.gateway.result;


public class CashOutResult {

    private String bankName;

    private String tailNumber;

    private String upAmount;

    private String toDate;


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
}
