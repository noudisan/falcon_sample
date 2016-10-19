package com.zhiyi.hero.role.dao;

import com.zhiyi.hero.role.dto.SysRoleResourcesDto;
import com.zhiyi.hero.role.dto.SysRoleResourcesSearchDto;
import com.zhiyi.hero.role.dto.SysRoleSearchDto;
import com.zhiyi.hero.role.model.SysRole;
import com.zhiyi.hero.role.model.SysRoleResources;

import java.util.List;

public interface SysRoleMapper {

    List<SysRole> search(SysRoleSearchDto searchDto);

    int searchCount(SysRoleSearchDto searchDto);

    int update(SysRole sysRole);

    SysRole get(Long id);

    Long save(SysRole sysRole);

    List<SysRoleResourcesDto> searchResources(SysRoleResourcesSearchDto searchDto);

    int searchRoleCount(SysRoleResourcesSearchDto sysRoleResourcesSearchDto);

    int deleteResources(Long roleId, Long resourcesId);

    int saveResources(SysRoleResources sysRoleResources);
}
