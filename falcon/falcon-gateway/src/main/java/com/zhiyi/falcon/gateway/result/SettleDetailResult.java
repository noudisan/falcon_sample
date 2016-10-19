package com.zhiyi.falcon.gateway.result;

import java.io.Serializable;
import java.util.List;

public class SettleDetailResult implements Serializable {
    private String date;

    private String balance;

    private String sectionName;

    private String section;

    private Double allowance; //补贴

    private List<Community> communties;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public List<Community> getCommunties() {
        return communties;
    }

    public void setCommunties(List<Community> communties) {
        this.communties = communties;
    }

    public Double getAllowance() {
        return allowance;
    }

    public void setAllowance(Double allowance) {
        this.allowance = allowance;
    }
}
