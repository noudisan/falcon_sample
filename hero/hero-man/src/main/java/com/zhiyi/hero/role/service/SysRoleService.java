package com.zhiyi.hero.role.service;

import com.zhiyi.common.dto.PageSearchResultDto;
import com.zhiyi.hero.resources.dao.ResourcesMapper;
import com.zhiyi.hero.resources.dto.ResourcesSearchDto;
import com.zhiyi.hero.resources.model.Resources;
import com.zhiyi.hero.role.api.ISysRoleService;
import com.zhiyi.hero.role.dao.SysRoleMapper;
import com.zhiyi.hero.role.dto.*;
import com.zhiyi.hero.role.model.SysRole;
import com.zhiyi.hero.role.model.SysRoleResources;
import com.zhiyi.utils.PropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class SysRoleService implements ISysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private ResourcesMapper resourcesMapper;

    @Override
    public PageSearchResultDto<SysRoleDto, Integer> search(
            SysRoleSearchDto searchDto) {
        List<SysRole> list = roleMapper.search(searchDto);
        List<SysRoleDto> roleList = PropertiesUtils.copyList(SysRoleDto.class, list);
        return new PageSearchResultDto<>(this.searchCount(searchDto), roleList);
    }

    @Override
    public int searchCount(SysRoleSearchDto searchDto) {
        searchDto.disablePaging();
        return roleMapper.searchCount(searchDto);
    }

    @Override
    public SysRoleDto get(Long id) {
        Assert.notNull(id, "id must not null");
        return PropertiesUtils.copy(SysRoleDto.class, roleMapper.get(id));
    }

    @Override
    @Transactional
    public int update(SysRoleDto roleDto) {
        SysRole entity = PropertiesUtils.copy(SysRole.class, roleDto);
        return roleMapper.update(entity);
    }

    @Override
    @Transactional
    public Long save(SysRoleDto roleDto) {
        SysRole entity = PropertiesUtils.copy(SysRole.class, roleDto);
        roleMapper.save(entity);
        return entity.getId();
    }


    @Override
    public PageSearchResultDto<SysRoleResourcesDto, Integer> searchResources(SysRoleResourcesSearchDto searchDto) {
        SysRoleResourcesSearchDto dto = (SysRoleResourcesSearchDto) searchDto.disablePaging();
        List<SysRoleResourcesDto> roles = roleMapper.searchResources(dto);
        return new PageSearchResultDto<>(roles, this.searchResourcesCount(dto));
    }

    @Override
    public int searchResourcesCount(SysRoleResourcesSearchDto searchDto) {
        return roleMapper.searchRoleCount((SysRoleResourcesSearchDto) searchDto.disablePaging());
    }

    @Override
    @Transactional
    public void assignResources(SysRoleResourcesAssignDto assignDto) {
        Long roleId = assignDto.getRoleId();
        Long platformId = assignDto.getPlatformId();
        //删除分配的全部资源
        List<Resources> resourcesList = resourcesMapper.search((ResourcesSearchDto)
                new ResourcesSearchDto(platformId).disablePaging());
        for (Resources resource : resourcesList) {
            roleMapper.deleteResources(roleId, resource.getId());
        }
        List<Long> resourcesIds = assignDto.getResourcesIds();
        String createUser = assignDto.getCreateUser();
        String modifyUser = assignDto.getModifyUser();
        for (Long resourcesId : resourcesIds) {
            SysRoleResources sysRoleResources = new SysRoleResources(roleId, resourcesId, createUser, modifyUser);
            roleMapper.saveResources(sysRoleResources);
        }
    }


    @Override
    @Transactional
    public void deleteResources(SysRoleResourcesDeleteDto deleteDto) {
        Long roleId = deleteDto.getRoleId();
        List<Long> resourcesIds = deleteDto.getResourcesIds();
        for (Long resourcesId : resourcesIds) {
            roleMapper.deleteResources(roleId, resourcesId);
        }
    }


}
