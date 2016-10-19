package com.zhiyi.falcon.api.dto.bMap;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/12/12.
 */
public class BMapNearbyResultDto implements Serializable {
    private Integer status;
    private String message;
    private Integer total;
    private List<BMapNearbyDto> results;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<BMapNearbyDto> getResults() {
        return results;
    }

    public void setResults(List<BMapNearbyDto> results) {
        this.results = results;
    }

    public boolean isOk() {
        return status == 0;
    }

    @Override
    public String toString() {
        return "BMapNearbyResultDto{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", total=" + total +
                ", results=" + results +
                '}';
    }
}
