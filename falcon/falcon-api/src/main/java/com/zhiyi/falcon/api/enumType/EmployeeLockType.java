package com.zhiyi.falcon.api.enumType;

/**
 * Created by Administrator on 2015/6/26.
 */
public enum EmployeeLockType {
    LOCK("锁定"),UNLOCK("未锁定");

    private String status;

    EmployeeLockType(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
