package com.zhiyi.common.dto;

import com.zhiyi.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hrs on 2014/7/24.
 */
public class ManageDto extends BaseDto {

    //主键
    protected Long id;

    //下面4后台人员字段
    protected String createUser;

    protected Date createDt;

    protected String modifyUser;

    protected Date modifyDt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getModifyDt() {
        return modifyDt;
    }

    public void setModifyDt(Date modifyDt) {
        this.modifyDt = modifyDt;
    }

    public String getCreateDtToString() {
        return DateUtils.format(this.createDt);
    }

    public String getModifyDtToString() {
        return DateUtils.format(this.modifyDt);
    }
}
