package com.zhiyi.community.dto;


public enum CommunityBuildType {

//    case 0: flagText = "小区住宅";
//    break;
//    case 1: flagText = "非小区住宅";
//    break;
//    case 2: flagText = "商业";
//    break;
//    case 3: flagText = "学校";
//    break;
//

    COMMUNITY_HOUSE("小区住宅"), NON_COMMUNITY_HOUSE("非小区住宅"), BUSINESS("商业"), SCHOOL("学校");


    private String status;

    CommunityBuildType(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


}
