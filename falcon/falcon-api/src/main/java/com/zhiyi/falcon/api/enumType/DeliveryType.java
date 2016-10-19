package com.zhiyi.falcon.api.enumType;

/**
 * 派送方式
 */
public enum  DeliveryType {

    LIFT("电梯入户"),STAIRS("步梯入户"),INLETTER("内信箱"),OUTLETTER("外信箱");

    private String status;

    DeliveryType(String status) {
        this.status = status;
    }

    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
