package com.zhiyi.hero.role.service;

import com.zhiyi.common.dto.PageSearchResultDto;
import com.zhiyi.hero.HeroJdbcH2DbTestCase;
import com.zhiyi.hero.role.api.ISysRoleService;
import com.zhiyi.hero.role.dto.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SysRoleServiceTest extends HeroJdbcH2DbTestCase {

    @Autowired
    private ISysRoleService roleService;

    @Test
    public void testSearch() throws Exception {
        SysRoleSearchDto roleSearchDto = new SysRoleSearchDto();
        roleSearchDto.setRoleName("admin");
        PageSearchResultDto<SysRoleDto, Integer> resultDto = roleService.search(roleSearchDto);
        assertEquals(1, resultDto.getSize());
    }

    @Test
    public void testSearchCount() throws Exception {
        SysRoleSearchDto searchDto = new SysRoleSearchDto();
        assertEquals(1, roleService.searchCount(searchDto));
    }

    @Test
    public void testGet() throws Exception {
        SysRoleDto roleDto = roleService.get(100L);
        assertEquals("management", roleDto.getRoleDesc());
    }

    @Test
    public void testUpdate() throws Exception {
        SysRoleDto roleDto = new SysRoleDto();
        roleDto.setId(100L);
        roleDto.setRoleName("manager");
        roleDto.setRoleDesc("custom manage");
        roleDto.setModifyUser("angla");
        int count = roleService.update(roleDto);
        assertEquals(1, count);
        SysRoleDto resultDto = roleService.get(100L);
        assertEquals("manager", resultDto.getRoleName());
        assertEquals("custom manage", resultDto.getRoleDesc());
        assertEquals("angla", resultDto.getModifyUser());
    }

    @Test
    public void testSave() throws Exception {
        SysRoleDto roleDto = new SysRoleDto();
        roleDto.setRoleName("customer");
        roleDto.setRoleDesc("customer user");
        roleDto.setCreateUser("admin");
        Long id = roleService.save(roleDto);
        assertNotNull(id);
        SysRoleDto resultDto = roleService.get(id);
        assertEquals("customer", resultDto.getRoleName());
    }


    @Test
    public void testSearchResources() throws Exception {
        SysRoleResourcesSearchDto searchDto = new SysRoleResourcesSearchDto(100L);
        PageSearchResultDto<SysRoleResourcesDto, Integer> resultDto = roleService.searchResources(searchDto);
        assertEquals(1, resultDto.getMessage().intValue());
        assertEquals(1, roleService.searchResourcesCount(searchDto));
    }


    @Test
    public void testAssignResources() throws Exception {
        SysRoleResourcesAssignDto assignDto = new SysRoleResourcesAssignDto(100L);
        assignDto.addResourcesId(224L);
        assignDto.addResourcesId(223L);
        roleService.assignResources(assignDto);
        SysRoleResourcesSearchDto searchDto = new SysRoleResourcesSearchDto(100L);
        PageSearchResultDto<SysRoleResourcesDto, Integer> resultDto = roleService.searchResources(searchDto);
        assertEquals(2, resultDto.getMessage().intValue());
    }


    @Test
    public void testDeleteResources() throws Exception{
        SysRoleResourcesDeleteDto deleteDto = new SysRoleResourcesDeleteDto(100L);
        deleteDto.addResourcesId(223L);
        roleService.deleteResources(deleteDto);
        SysRoleResourcesSearchDto searchDto = new SysRoleResourcesSearchDto(100L);
        PageSearchResultDto<SysRoleResourcesDto, Integer> resultDto = roleService.searchResources(searchDto);
        assertEquals(0, resultDto.getMessage().intValue());
    }


}
