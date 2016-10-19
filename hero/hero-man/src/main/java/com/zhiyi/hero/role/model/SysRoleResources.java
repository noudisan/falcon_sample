package com.zhiyi.hero.role.model;

import com.zhiyi.common.model.BaseEntity;

/**
 * Created by hrs on 2014/11/3.
 */
public class SysRoleResources extends BaseEntity {

    private Long id;

    private Long roleId;

    private Long resourcesId;

    public SysRoleResources() {
    }

    public SysRoleResources(Long roleId, Long resourcesId, String createUser, String modifyUser) {
        this.roleId = roleId;
        this.resourcesId = resourcesId;
        this.createUser = createUser;
        this.modifyUser = modifyUser;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(Long resourcesId) {
        this.resourcesId = resourcesId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
