package com.zhiyi.hero.common;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hrs on 2014/8/25.
 */
public abstract class BaseController {

    protected final transient Logger logger = LoggerFactory.getLogger(this.getClass());

    protected final static Result DEL_SUCCESS = new Result(Result.Status.SUCCESS, "删除成功");

    protected final static Result UPD_SUCCESS = new Result(Result.Status.SUCCESS, "修改成功");

    protected final static Result ADD_SUCCESS = new Result(Result.Status.SUCCESS, "新增成功");

    protected final static Result SET_SUCCESS = new Result(Result.Status.SUCCESS, "设置成功");

    protected final Result showSuccess() {
        return showSuccess("");
    }

    protected final Result showSuccess(String message) {
        return new Result(Result.Status.SUCCESS, message);
    }

    protected final Result showError() {
        return showError("");
    }

    protected final Result showError(String message) {
        return new Result(Result.Status.ERROR, message);
    }

    protected final String getLoginUserName() {
        return (String) SecurityUtils.getSubject().getPrincipal();
    }

}

