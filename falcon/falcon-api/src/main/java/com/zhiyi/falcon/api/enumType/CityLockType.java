package com.zhiyi.falcon.api.enumType;


public enum  CityLockType {

    LOCK("锁定"),UNLOCK("未锁定");

    private String status;

    CityLockType(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
