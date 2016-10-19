package com.zhiyi.falcon.api.enumType;

/**
 * 抽查标志
 */
public enum TaskSampling {

    SAMPLING("已抽检"),NOSAMPLING("未抽检");

    private String status;

    TaskSampling(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

    public String getStatus() {
        return status;
    }
}
