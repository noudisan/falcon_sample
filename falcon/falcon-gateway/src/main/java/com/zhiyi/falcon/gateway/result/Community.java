package com.zhiyi.falcon.gateway.result;

import java.util.List;

public class Community {
    private String communityName;

    private List<Build> datas;

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public List<Build> getDatas() {
        return datas;
    }

    public void setDatas(List<Build> datas) {
        this.datas = datas;
    }
}
