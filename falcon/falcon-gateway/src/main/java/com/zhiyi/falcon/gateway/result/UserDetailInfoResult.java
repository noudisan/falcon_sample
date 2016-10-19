package com.zhiyi.falcon.gateway.result;

import java.io.Serializable;

/**
 * app个人详情：用户详细信息
 */
public class UserDetailInfoResult implements Serializable {
    UserDetailBankInfoResult userBankInfo;
    UserDetailBaseInfoResult userInfo;

    public UserDetailBankInfoResult getUserBankInfo() {
        return userBankInfo;
    }

    public void setUserBankInfo(UserDetailBankInfoResult userBankInfo) {
        this.userBankInfo = userBankInfo;
    }

    public UserDetailBaseInfoResult getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserDetailBaseInfoResult userInfo) {
        this.userInfo = userInfo;
    }

}
