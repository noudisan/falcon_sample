package com.zhiyi.hero.user.api;


import com.zhiyi.common.dto.PageSearchResultDto;
import com.zhiyi.common.dto.ResultDto;
import com.zhiyi.hero.role.dto.SysRoleDto;
import com.zhiyi.hero.user.dto.*;

/**
 * Created by hrs on 2014/10/23.
 */
public interface ISysUserService {

    PageSearchResultDto<SysUserDto, Integer> search(SysUserSearchDto searchDto);

    int searchCount(SysUserSearchDto searchDto);

    SysUserDto get(Long id);

    int update(SysUserDto sysUser);

    Long save(SysUserDto sysUser);

    SysUserDto getByUserName(String userName);

    SysUserUpdatePwdResultDto updatePassword(SysUserUpdatePwdDto userDto);

    PageSearchResultDto<SysUserRoleDto, Integer> searchRole(SysUserRoleSearchDto searchDto);

    int searchRoleCount(SysUserRoleSearchDto searchDto);

    void assignRole(SysUserRoleAssignDto assignDto);

    void deleteRole(SysUserRoleDeleteDto deleteDto);
    
    ResultDto<SysRoleDto> getRoles(String userName);
    
    ResultDto<PermissionDto> getPermissions(String userName, String platformName);
}
