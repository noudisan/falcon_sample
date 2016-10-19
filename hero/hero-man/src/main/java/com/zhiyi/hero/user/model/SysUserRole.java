package com.zhiyi.hero.user.model;

import com.zhiyi.common.model.BaseEntity;

/**
 * Created by hrs on 2014/11/2.
 */
public class SysUserRole extends BaseEntity {

    private Long userId;

    private Long roleId;

    public SysUserRole() {
    }

    public SysUserRole(Long userId, Long roleId, String createUser, String modifyUser) {
        this.userId = userId;
        this.roleId = roleId;
        this.createUser = createUser;
        this.modifyUser = modifyUser;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
