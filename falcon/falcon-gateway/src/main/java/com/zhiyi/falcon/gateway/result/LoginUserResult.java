package com.zhiyi.falcon.gateway.result;

import java.io.Serializable;

/**
 * 派送员工登陆返回结果
 */
public class LoginUserResult implements Serializable {
    private Integer id;

    private String city;

    private Integer isFirstLogin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getIsFirstLogin() {
        return isFirstLogin;
    }

    public void setIsFirstLogin(Integer isFirstLogin) {
        this.isFirstLogin = isFirstLogin;
    }
}
