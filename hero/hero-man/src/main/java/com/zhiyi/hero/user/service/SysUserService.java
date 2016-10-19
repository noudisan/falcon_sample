package com.zhiyi.hero.user.service;

import com.zhiyi.common.dto.PageSearchResultDto;
import com.zhiyi.common.dto.ResultDto;
import com.zhiyi.hero.platform.dao.PlatformMapper;
import com.zhiyi.hero.platform.dto.PlatformDto;
import com.zhiyi.hero.platform.model.Platform;
import com.zhiyi.hero.role.dto.SysRoleDto;
import com.zhiyi.hero.user.api.ISysUserService;
import com.zhiyi.hero.user.dao.SysUserMapper;
import com.zhiyi.hero.user.dto.*;
import com.zhiyi.hero.user.model.SysUser;
import com.zhiyi.hero.user.model.SysUserRole;
import com.zhiyi.utils.PropertiesUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysUserService implements ISysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private PlatformMapper platformMapper;


    @Override
    public PageSearchResultDto<SysUserDto, Integer> search(SysUserSearchDto searchDto) {
        List<SysUser> list = userMapper.search(searchDto);
        List<SysUserDto> userDto = PropertiesUtils.copyList(SysUserDto.class, list);
        return new PageSearchResultDto<>(this.searchCount(searchDto), userDto);
    }

    @Override
    public int searchCount(SysUserSearchDto searchDto) {
        searchDto.disablePaging();
        return userMapper.searchCount(searchDto);
    }

    @Override
    public SysUserDto get(Long id) {
        Assert.notNull(id, "id must not null");
        return PropertiesUtils.copy(SysUserDto.class, userMapper.get(id));
    }

    @Override
    @Transactional
    public int update(SysUserDto sysUser) {
        SysUser entity = PropertiesUtils.copy(SysUser.class, sysUser);
        return userMapper.update(entity);
    }

    @Override
    @Transactional
    public Long save(SysUserDto sysUser) {
        SysUser entity = PropertiesUtils.copy(SysUser.class, sysUser);
        userMapper.save(entity);
        return entity.getId();
    }

    @Override
    public SysUserDto getByUserName(String userName) {
        Assert.notNull(userName, "userName must not null");
        SysUser user = userMapper.getByUserName(userName);
        return PropertiesUtils.copy(SysUserDto.class, user);
    }

    @Override
    @Transactional
    public SysUserUpdatePwdResultDto updatePassword(SysUserUpdatePwdDto userDto) {
        SysUser sysUser = userMapper.getByUserName(userDto.getUserName());
        if (!StringUtils.equals(sysUser.getPassword(), userDto.getPassword())) {
            return new SysUserUpdatePwdResultDto(SysUserUpdatePwdResultDto.Status.FAIL, "原密码输入错误");
        }
        userMapper.updatePassword(userDto.getUserName(), userDto.getNewPassword());
        return new SysUserUpdatePwdResultDto(SysUserUpdatePwdResultDto.Status.SUCCESS);
    }


    @Override
    public PageSearchResultDto<SysUserRoleDto, Integer> searchRole(SysUserRoleSearchDto searchDto) {
        List<SysUserRoleDto> roles = userMapper.searchRole(searchDto);
        return new PageSearchResultDto<>(roles, this.searchRoleCount(searchDto));
    }

    @Override
    public int searchRoleCount(SysUserRoleSearchDto searchDto) {
        return userMapper.searchRoleCount((SysUserRoleSearchDto) searchDto.disablePaging());
    }

    @Override
    @Transactional
    public void assignRole(SysUserRoleAssignDto assignDto) {
        Long userId = assignDto.getUserId();
        List<Long> roleIds = assignDto.getRoleIds();
        String createUser = assignDto.getCreateUser();
        String modifyUser = assignDto.getModifyUser();
        for (Long roleId : roleIds) {
            userMapper.deleteRole(userId, roleId);
            SysUserRole sysUserRole = new SysUserRole(userId, roleId, createUser, modifyUser);
            userMapper.saveRole(sysUserRole);
        }
    }


    @Override
    @Transactional
    public void deleteRole(SysUserRoleDeleteDto deleteDto) {
        Long userId = deleteDto.getUserId();
        List<Long> roleIds = deleteDto.getRoleIds();
        for (Long roleId : roleIds) {
            userMapper.deleteRole(userId, roleId);
        }
    }

    @Override
    public ResultDto<SysRoleDto> getRoles(String userName) {
        return new ResultDto<SysRoleDto>(PropertiesUtils.copyList(SysRoleDto.class, userMapper.getRoles(userName)));
    }

    @Override
    @Transactional
    public ResultDto<PermissionDto> getPermissions(String userName,
                                                   String platformName) {

        //1.query roleIdList by userName
        List<SysUserRoleDto> userroleDto = userMapper.searchRole(new SysUserRoleSearchDto(userName));
        List<Long> roleIdList = new ArrayList<Long>();
        for (SysUserRoleDto sysUserRoleDto : userroleDto) {
            Long roleId = sysUserRoleDto.getRole().getId();
            roleIdList.add(roleId);
        }

        if (roleIdList.isEmpty()) {
            return new ResultDto<PermissionDto>();
        }

        //2.query paltformId by platformName
        PlatformDto platformDto = PropertiesUtils.copy(PlatformDto.class, platformMapper.getByName(platformName));
        Long platformId = null;
        if (platformDto != null) {
            platformId = platformDto.getId();
        }

        //3.query  permissionList  set parentId equal to zero
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("roleIdList", roleIdList);
        paramMap.put("parentId", 0L);
        paramMap.put("platformId", platformId);
        List<PermissionDto> permissionList = PropertiesUtils.copyList(PermissionDto.class, userMapper.getPermission(paramMap));

        //4.Traverse permissionList and set every son-list
        for (PermissionDto permissionDto : permissionList) {
            Long permissionId = permissionDto.getId();
            paramMap.put("parentId", permissionId);
            List<PermissionDto> sonList = PropertiesUtils.copyList(PermissionDto.class, userMapper.getPermission(paramMap));
            permissionDto.setPermissions(sonList);

            //5.Query button resources by sonList.
            for (PermissionDto buttonDto : sonList) {
                paramMap.put("parentId", buttonDto.getId());
                List<PermissionDto> buttonList = PropertiesUtils.copyList(PermissionDto.class, userMapper.getPermission(paramMap));
                buttonDto.setPermissions(buttonList);
            }

        }
        return new ResultDto<PermissionDto>(permissionList);
    }

}
