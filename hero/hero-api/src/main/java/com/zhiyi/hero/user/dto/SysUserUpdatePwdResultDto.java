package com.zhiyi.hero.user.dto;

import com.zhiyi.common.dto.BaseDto;

/**
 * Created by hrs on 2014/10/23.
 */
public class SysUserUpdatePwdResultDto extends BaseDto {

    private Status status;

    private String message;

    public SysUserUpdatePwdResultDto() {
    }

    public SysUserUpdatePwdResultDto(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public SysUserUpdatePwdResultDto(Status status) {
        this.status = status;
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

    public static enum Status {
        SUCCESS, FAIL
    }

}
