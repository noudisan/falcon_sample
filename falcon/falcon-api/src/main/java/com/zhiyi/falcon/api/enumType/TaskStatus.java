package com.zhiyi.falcon.api.enumType;

/**
 * 任务状态
 */
public enum TaskStatus {

    TASKBEGIN("任务开始"),TASKEND("任务结束"),TASKTOBEGIN("任务即将开始");

    private String status;

    TaskStatus(String status) {
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
