package com.zhiyi.falcon.api.enumType;

/**
 * Created by Adminstrator on 2015-07-04.
 */
public enum  SettleStatus {

    UNBALANCED("未结算"), SUCCESS("结算成功"), FAIL("结算失败");
    private String status;

    SettleStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
