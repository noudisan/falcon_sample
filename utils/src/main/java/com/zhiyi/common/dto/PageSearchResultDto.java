package com.zhiyi.common.dto;

import java.util.List;

/**
 * Created by hrs on PageSearchResultDto2014/7/23.
 */
public class PageSearchResultDto<T, S> extends ResultDto<T>{

    private S message;

    public S getMessage() {
        return message;
    }

    public void setMessage(S message) {
        this.message = message;
    }

    public PageSearchResultDto(){
        super();
    }

    public PageSearchResultDto(S message, List<T> results) {
        this.results = results;
        this.message = message;
    }

    public PageSearchResultDto(List<T> results, S message) {
        this.results = results;
        this.message = message;
    }
}
