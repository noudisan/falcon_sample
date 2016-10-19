package com.zhiyi.hero.role.api;

import com.zhiyi.common.dto.PageSearchResultDto;
import com.zhiyi.hero.role.dto.*;

public interface ISysRoleService {

    PageSearchResultDto<SysRoleDto, Integer> search(SysRoleSearchDto searchDto);

    int searchCount(SysRoleSearchDto searchDto);

    SysRoleDto get(Long id);

    int update(SysRoleDto roleDto);

    Long save(SysRoleDto roleDto);

    PageSearchResultDto<SysRoleResourcesDto, Integer> searchResources(SysRoleResourcesSearchDto searchDto);

    int searchResourcesCount(SysRoleResourcesSearchDto searchDto);

    void assignResources(SysRoleResourcesAssignDto assignDto);

    void deleteResources(SysRoleResourcesDeleteDto deleteDto);
}
