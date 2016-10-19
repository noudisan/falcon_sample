package com.zhiyi.community.dto;

import org.omg.CORBA.UNKNOWN;


public enum  CommunityType {
//    case 0: flagText = "未知";
//    break;
//    case 1: flagText = "低密度豪宅";
//    break;
//    case 2: flagText = "高密度成熟小区";
//    break;
//    case 3: flagText = "公寓/老公房";
//    break;
//    default : flagText = "未知";
//    break;

    UNKNOWN("未知"),LOW_DENSITY("低密度豪宅"),HIGH_DENSITY("高密度成熟小区"),APARTMENT_HOUSE("公寓/老公房");

    private String status;

    CommunityType(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


}
