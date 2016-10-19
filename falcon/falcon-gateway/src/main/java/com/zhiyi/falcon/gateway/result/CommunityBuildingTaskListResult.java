package com.zhiyi.falcon.gateway.result;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class CommunityBuildingTaskListResult {
    private List<CommunityBuildingTaskResult> builds =new ArrayList<>();

    public List<CommunityBuildingTaskResult> getBuilds() {
        return builds;
    }

    public void setBuilds(List<CommunityBuildingTaskResult> builds) {
        this.builds = builds;
    }
}
