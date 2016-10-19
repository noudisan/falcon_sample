package com.zhiyi.hero.role.dto;

import com.zhiyi.common.dto.ManageDto;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hrs on 2014/11/3.
 */
public class SysRoleResourcesAssignDto extends ManageDto {

    private Long roleId;

    private Long platformId;

    private List<Long> resourcesIds = new ArrayList<>();

    public SysRoleResourcesAssignDto() {
    }

    public SysRoleResourcesAssignDto(Long roleId) {
        this.roleId = roleId;
    }

    public SysRoleResourcesAssignDto(Long roleId, Long platformId, String resources) {
        this.roleId = roleId;
        this.platformId = platformId;
        if (!StringUtils.trim(resources).equals("")) {
            String[] sIds = resources.split(",");
            Long[] ids = new Long[sIds.length];
            for (int i = 0; i < sIds.length; i++) {
                resourcesIds.add(Long.valueOf(sIds[i]));
            }
        }
    }

    public List<Long> getResourcesIds() {
        return resourcesIds;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void addResourcesId(Long resourcesId) {
        this.resourcesIds.add(resourcesId);
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }
}
