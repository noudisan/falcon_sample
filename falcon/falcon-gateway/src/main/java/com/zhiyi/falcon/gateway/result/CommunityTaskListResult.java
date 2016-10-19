package com.zhiyi.falcon.gateway.result;

import java.util.ArrayList;
import java.util.List;

/**
 * 小区派送结果
 */
public class CommunityTaskListResult {

    private List<CommunityTaskResult> communities =new ArrayList<>();

    public List<CommunityTaskResult> getCommunities() {
        return communities;
    }

    public void setCommunities(List<CommunityTaskResult> communities) {
        this.communities = communities;
    }
}
