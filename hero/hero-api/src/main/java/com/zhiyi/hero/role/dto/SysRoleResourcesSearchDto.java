package com.zhiyi.hero.role.dto;

import com.zhiyi.common.dto.PageSearchDto;

/**
 * Created by hrs on 2014/11/3.
 */
public class SysRoleResourcesSearchDto extends PageSearchDto {

    private Long roleId;

    private Long platformId;

    public SysRoleResourcesSearchDto() {
    }

    public SysRoleResourcesSearchDto(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }
}
