package com.zhiyi.community.dto;


public enum CommunityElevatorFlag {
//    case 0: flagText = "未知";
//    break;
//    case 1: flagText = "无电梯";
//    break;
//    case 2: flagText = "有电梯";
//    break;
//    default : flagText = "未知";

    UNKNOWN("未知"), ELEVATOR("有电梯"), NO_ELEVATOR("无电梯");


    private String status;

    CommunityElevatorFlag(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}