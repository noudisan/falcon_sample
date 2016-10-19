package com.zhiyi.falcon.gateway.result;

import java.util.Date;
import java.util.List;


public class Section {
    private String date;

    private String balance;

    private String sectionName;

    private String section;

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
}
