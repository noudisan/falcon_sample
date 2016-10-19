package com.zhiyi.common.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hrs on 2014/7/24.
 */
public class ResultDto<T> extends BaseDto {

    protected List<T> results = new ArrayList<T>();

    public ResultDto() {
    }

    public ResultDto(List<T> results) {
        this.results = results;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public int getSize(){
        return results.size();
    }
}
