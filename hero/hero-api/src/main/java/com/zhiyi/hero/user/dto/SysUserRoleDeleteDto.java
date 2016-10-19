package com.zhiyi.hero.user.dto;

import java.util.List;

/**
 * Created by hrs on 2014/11/2.
 */
public class SysUserRoleDeleteDto extends SysUserRoleAssignDto {

    public SysUserRoleDeleteDto() {
    }

    public SysUserRoleDeleteDto(Long userId) {
        super(userId);
    }

    public SysUserRoleDeleteDto(Long userId, String roleIds) {
        super(userId, roleIds);
    }
}
