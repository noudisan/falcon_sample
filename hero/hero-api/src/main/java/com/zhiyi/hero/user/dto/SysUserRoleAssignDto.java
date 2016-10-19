package com.zhiyi.hero.user.dto;

import com.zhiyi.common.dto.BaseDto;
import com.zhiyi.common.dto.ManageDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hrs on 2014/11/2.
 */
public class SysUserRoleAssignDto extends ManageDto {

    private Long userId;

    private List<Long> roleIds = new ArrayList<>();

    public SysUserRoleAssignDto(Long userId) {
        this.userId = userId;
    }

    public SysUserRoleAssignDto() {
    }

    public SysUserRoleAssignDto(Long userId, List<Long> roleIds) {
        this.userId = userId;
        this.roleIds = roleIds;
    }

    public SysUserRoleAssignDto(Long userId, String roleIds) {
        this.userId = userId;
        String[] sIds = roleIds.split(",");
        Long[] ids = new Long[sIds.length];
        for (int i = 0; i < sIds.length; i++) {
            ids[i] = Long.valueOf(sIds[i]);
        }
        this.roleIds = Arrays.asList(ids);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public void addRoleId(Long roleId) {
        this.roleIds.add(roleId);
    }
}
