package com.zhiyi.falcon.gateway.result;

import java.io.Serializable;

/**
 * app显示用户基本信息
 */
public class UserBaseInfoResult implements Serializable{
    private String userName;
    private String imageUrl;
    private String balance;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
