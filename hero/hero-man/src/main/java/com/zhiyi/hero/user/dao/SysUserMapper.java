package com.zhiyi.hero.user.dao;

import com.zhiyi.hero.resources.model.Resources;
import com.zhiyi.hero.role.model.SysRole;
import com.zhiyi.hero.user.dto.SysUserRoleDto;
import com.zhiyi.hero.user.dto.SysUserRoleSearchDto;
import com.zhiyi.hero.user.dto.SysUserSearchDto;
import com.zhiyi.hero.user.model.SysUser;
import com.zhiyi.hero.user.model.SysUserRole;

import java.util.List;
import java.util.Map;

public interface SysUserMapper {

    List<SysUser> search(SysUserSearchDto searchDto);

    int searchCount(SysUserSearchDto searchDto);

    SysUser get(Long id);

    int update(SysUser sysUser);

    Long save(SysUser sysUser);

    SysUser getByUserName(String userName);

    int updatePassword(String userName, String newPassword);

    List<SysUserRoleDto> searchRole(SysUserRoleSearchDto searchDto);

    int searchRoleCount(SysUserRoleSearchDto searchDto);

    int deleteRole(Long userId, Long roleId);

    int saveRole(SysUserRole sysUserRole);

    List<SysRole> getRoles(String userName);

    List<Resources> getPermission(Map<String, Object> paramMap);
}
