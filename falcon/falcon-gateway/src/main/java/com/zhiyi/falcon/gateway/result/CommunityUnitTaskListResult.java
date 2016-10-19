package com.zhiyi.falcon.gateway.result;

import java.util.ArrayList;
import java.util.List;


public class CommunityUnitTaskListResult {

    private List<CommunityUnitTaskResult> unities =new ArrayList<>();


    public List<CommunityUnitTaskResult> getUnities() {
        return unities;
    }

    public void setUnities(List<CommunityUnitTaskResult> unities) {
        this.unities = unities;
    }
}
