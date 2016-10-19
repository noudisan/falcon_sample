package com.zhiyi.falcon.api.enumType;

/**
 * 派送状态
 */
public enum DeliveryStatus {

    TO_DELIVERY("待派送"),IN_DELIVERY("正在派送"),COMPLETE_DELIVERY("已完成"),CANT_DELIVERY("无法派送");

    private String status;

    DeliveryStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

