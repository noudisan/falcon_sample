package com.zhiyi.hero.common;


/**
 * Created by hrs on 2014/8/25.
 */
public class Result {

    private Status status;

    private String message;

    Result() {
    }

    Result(Status status) {
        this.status = status;
    }

    Result(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    enum Status {
        SUCCESS, ERROR
    }
}
