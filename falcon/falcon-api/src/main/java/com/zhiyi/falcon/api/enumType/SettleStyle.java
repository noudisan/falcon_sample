package com.zhiyi.falcon.api.enumType;

import java.io.Serializable;

/**
 * Created by Adminstrator on 2015-07-20.
 */
public enum SettleStyle implements Serializable{
    LIFT("电梯入户"),STAIRS("步梯入户"),INLETTER("内信箱"),OUTLETTER("外信箱"),
    FOOD("饭补"), BAD_WEATHER("恶劣天气补贴");

    private String status;

    SettleStyle(String status) {
        this.status = status;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
