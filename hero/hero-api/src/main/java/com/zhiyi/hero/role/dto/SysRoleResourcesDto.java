package com.zhiyi.hero.role.dto;

import com.zhiyi.common.dto.ManageDto;
import com.zhiyi.hero.resources.dto.ResourcesDto;

/**
 * Created by hrs on 2014/11/3.
 */
public class SysRoleResourcesDto extends ManageDto {

    private SysRoleDto role;

    private ResourcesDto resources;

    public ResourcesDto getResources() {
        return resources;
    }

    public void setResources(ResourcesDto resources) {
        this.resources = resources;
    }

    public SysRoleDto getRole() {
        return role;
    }

    public void setRole(SysRoleDto role) {
        this.role = role;
    }


    public Long getKey() {
        return this.getRole().getId();
    }

}
