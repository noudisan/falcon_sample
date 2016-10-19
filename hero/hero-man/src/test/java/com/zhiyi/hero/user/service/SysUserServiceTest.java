package com.zhiyi.hero.user.service;

import com.zhiyi.common.dto.PageSearchResultDto;
import com.zhiyi.hero.HeroJdbcH2DbTestCase;
import com.zhiyi.hero.role.dto.SysRoleDto;
import com.zhiyi.hero.user.api.IHeroStatelessLoginService;
import com.zhiyi.hero.user.api.ISysUserService;
import com.zhiyi.hero.user.dto.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SysUserServiceTest extends HeroJdbcH2DbTestCase {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IHeroStatelessLoginService loginService;

    @Test
    public void testSearch() throws Exception {
        SysUserSearchDto searchDto = new SysUserSearchDto();
        PageSearchResultDto<SysUserDto, Integer> resultDto = sysUserService.search(searchDto);
        assertEquals(1, resultDto.getSize());
    }

    @Test
    public void testSearchCount() throws Exception {
        SysUserSearchDto searchDto = new SysUserSearchDto();
        assertEquals(1, sysUserService.searchCount(searchDto));
    }

    @Test
    public void testGet() throws Exception {
        SysUserDto sysUserDto = sysUserService.get(100000L);
        assertEquals("Jack", sysUserDto.getUserName());
    }

    @Test
    public void testUpdate() throws Exception {
        SysUserDto sysUserDto = new SysUserDto(100000L);
        sysUserDto.setUserName("Jack1");
        sysUserDto.setModifyUser("andy");
        int count = sysUserService.update(sysUserDto);
        assertEquals(1, count);
        sysUserDto = sysUserService.get(100000L);
        assertEquals("Jack1", sysUserDto.getUserName());
        assertEquals("andy", sysUserDto.getModifyUser());
    }

    @Test
    public void testSave() throws Exception {
        SysUserDto sysUserDto = new SysUserDto();
        sysUserDto.setUserName("Jack1");
        sysUserDto.setCreateUser("andy");
        sysUserDto.setPassword("123456");
        Long id = sysUserService.save(sysUserDto);
        assertNotNull(id);
        sysUserDto = sysUserService.get(id);
        assertEquals("Jack1", sysUserDto.getUserName());
    }


    @Test
    public void testGetByUserName() throws Exception {
        SysUserDto sysUserDto = sysUserService.getByUserName("Jack");
        assertEquals(100000L, sysUserDto.getId().longValue());
    }

    @Test
    public void testUpdatePassword() throws Exception {
        SysUserUpdatePwdDto pwdDto = new SysUserUpdatePwdDto("Jack", "123456", "888888888888");
        SysUserUpdatePwdResultDto resultDto = sysUserService.updatePassword(pwdDto);
        assertEquals(SysUserUpdatePwdResultDto.Status.SUCCESS, resultDto.getStatus());
        String password = sysUserService.getByUserName("Jack").getPassword();
        assertEquals("888888888888", password);
        new SysUserUpdatePwdDto("Jack", "123456", "888888888888");
        resultDto = sysUserService.updatePassword(pwdDto);
        assertEquals(SysUserUpdatePwdResultDto.Status.FAIL, resultDto.getStatus());
    }


    @Test
    public void testSearchRole() throws Exception {
        SysUserRoleSearchDto searchDto = new SysUserRoleSearchDto("Jack");
        PageSearchResultDto<SysUserRoleDto, Integer> resultDto = sysUserService.searchRole(searchDto);
        assertEquals(3, resultDto.getMessage().intValue());
        assertEquals(3, sysUserService.searchRoleCount(searchDto));
    }


    @Test
    public void testAssignRole() throws Exception {
        SysUserRoleAssignDto assignDto = new SysUserRoleAssignDto(100000L);
        assignDto.addRoleId(100001L);
        assignDto.addRoleId(100000L);
        sysUserService.assignRole(assignDto);
        SysUserRoleSearchDto searchDto = new SysUserRoleSearchDto("Jack");
        PageSearchResultDto<SysUserRoleDto, Integer> resultDto = sysUserService.searchRole(searchDto);
        assertEquals(3, resultDto.getMessage().intValue());
    }

    @Test
    public void testDeleteRole() throws Exception {
        SysUserRoleDeleteDto deleteDto = new SysUserRoleDeleteDto(100000L);
        deleteDto.addRoleId(100000L);
        sysUserService.deleteRole(deleteDto);
        SysUserRoleSearchDto searchDto = new SysUserRoleSearchDto("Jack");
        PageSearchResultDto<SysUserRoleDto, Integer> resultDto = sysUserService.searchRole(searchDto);
        assertEquals(2, resultDto.getMessage().intValue());
    }

    @Test
    public void testGetRoles() throws Exception {
        String userName = "Jack";
        List<SysRoleDto> roleList = sysUserService.getRoles(userName).getResults();
        assertEquals(3, roleList.size());
        assertEquals("roleName1", roleList.get(0).getRoleName());
    }


    @Test
    public void testGetPermissions() throws Exception {
        String userName = "Jack";
        String platformName = "权限管理平台";
        List<PermissionDto> resultList = sysUserService.getPermissions(userName, platformName).getResults();
        assertEquals(1, resultList.size());
        assertEquals(3, resultList.get(0).getPermissions().size());
        assertEquals("平台管理", resultList.get(0).getPermissions().get(0).getName());
    }

    @Test
    public void testStatelessLogin() throws Exception {
        StatelessLoginRequestDto requestDto = new StatelessLoginRequestDto("Jack","123456","权限管理平台");
        requestDto.setSign(requestDto.sign("123456"));
        StatelessLoginResultDto resultDto = loginService.statelessLogin(requestDto);
        assertEquals(resultDto.getStatus(), StatelessLoginResultDto.Status.SUCCESS);

        requestDto = new StatelessLoginRequestDto("Jack","1234565","权限管理平台");
        requestDto.setSign(requestDto.sign("123456"));
        resultDto = loginService.statelessLogin(requestDto);
        assertEquals(resultDto.getStatus(), StatelessLoginResultDto.Status.FAIL_1);

        requestDto = new StatelessLoginRequestDto("Jack","123456","权限管理平台1");
        requestDto.setSign(requestDto.sign("123456"));
        resultDto = loginService.statelessLogin(requestDto);
        assertEquals(resultDto.getStatus(), StatelessLoginResultDto.Status.FAIL_4);

        requestDto = new StatelessLoginRequestDto("Jack1","123456","权限管理平台");
        requestDto.setSign(requestDto.sign("123456"));
        resultDto = loginService.statelessLogin(requestDto);
        assertEquals(resultDto.getStatus(), StatelessLoginResultDto.Status.FAIL_1);
    }


}