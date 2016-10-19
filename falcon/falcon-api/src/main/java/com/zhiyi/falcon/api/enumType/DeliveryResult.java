package com.zhiyi.falcon.api.enumType;

/**
 * 派送结果
 */
public enum DeliveryResult {

    NOTDELIVERY("无法派送"),CANDELIVERY("可以派送");

    private String status;

    DeliveryResult(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
