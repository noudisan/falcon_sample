package com.zhiyi.falcon.api.enumType;

/**
 * 小区投递状态
 */
public enum TaskSendStatusType {

    SEND_WAITING("待投递"),SEND_ING("正在进行"),SEND_COMPLETED("已完成"),SEND_FAIL("无法投递");

    private String status;

    TaskSendStatusType(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
