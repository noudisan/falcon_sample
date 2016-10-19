package com.zhiyi.community.dto;


public enum  CommunityTudeFLag {
//    // 无
//    public static final int TUDE_FLAG_NONE = 0;
//    // 机器
//    public static final int TUDE_FLAG_ROBOT = 1;
//    // 人工
//    public static final int TUDE_FLAG_PERSON = 2;

    TUDE_FLAG_NONE("无"),TUDE_FLAG_ROBOT("机器"),TUDE_FLAG_PERSON("人工");


    private String status;

    CommunityTudeFLag(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
