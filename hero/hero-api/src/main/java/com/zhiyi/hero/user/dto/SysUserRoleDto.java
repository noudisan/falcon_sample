package com.zhiyi.hero.user.dto;

import com.zhiyi.common.dto.BaseDto;
import com.zhiyi.hero.role.dto.SysRoleDto;
import com.zhiyi.utils.DateUtils;

import java.util.Date;

/**
 * Created by hrs on 2014/10/31.
 */
public class SysUserRoleDto extends BaseDto {

    private SysUserDto user;

    private SysRoleDto role;

    private String assignUser;

    private Date assignDt;

    public SysUserDto getUser() {
        return user;
    }

    public void setUser(SysUserDto user) {
        this.user = user;
    }

    public SysRoleDto getRole() {
        return role;
    }

    public void setRole(SysRoleDto role) {
        this.role = role;
    }

    public String getAssignUser() {
        return assignUser;
    }

    public void setAssignUser(String assignUser) {
        this.assignUser = assignUser;
    }

    public Date getAssignDt() {
        return assignDt;
    }

    public void setAssignDt(Date assignDt) {
        this.assignDt = assignDt;
    }

    public String getAssignDts() {
        return DateUtils.format(this.getAssignDt());
    }

    public Long getKey() {
        return this.getRole().getId();
    }
}
