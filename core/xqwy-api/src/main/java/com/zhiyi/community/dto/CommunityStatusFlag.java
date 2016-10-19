package com.zhiyi.community.dto;


public enum  CommunityStatusFlag {
//    public static final int STATUS_FLAG_NORMAL = 0;//正常
//    public static final int STATUS_FLAG_INVISIABLE_NONE = 1;//无此小区
//    public static final int STATUS_FLAG_INVISIABLE_WRONG = 2;//非小区
//    public static final int STATUS_FLAG_INVISIABLE_REPEAT = 3;//重复小区

    STATUS_FLAG_NORMAL("正常"),STATUS_FLAG_INVISIABLE_NONE("无此小区"),
    STATUS_FLAG_INVISIABLE_WRONG("非小区"), STATUS_FLAG_INVISIABLE_REPEAT("重复小区");

    private String status;

    CommunityStatusFlag(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}