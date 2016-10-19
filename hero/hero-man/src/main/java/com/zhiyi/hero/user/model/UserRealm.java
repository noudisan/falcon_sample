package com.zhiyi.hero.user.model;

import com.zhiyi.hero.role.dto.SysRoleDto;
import com.zhiyi.hero.user.api.ISysUserService;
import com.zhiyi.hero.user.dto.PermissionDto;
import com.zhiyi.hero.user.dto.SysUserDto;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 登录验证
 */
public class UserRealm extends AuthorizingRealm {


    @Autowired
    private ISysUserService sysUserService;

    public void setSysUserService(ISysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(getRoles(username));
        authorizationInfo.setStringPermissions(getPermissions(username));
        return authorizationInfo;
    }


    /**
     * 认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        String username = usernamePasswordToken.getUsername();

        SysUserDto user = sysUserService.getByUserName(username);
        if (user == null) {
            throw new UnknownAccountException();//没找到帐号
        }
        if (Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException(); //帐号锁定
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUserName(), //用户名
                user.getPassword(), //密码
                getName() //realm name

        );
        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }


    private Set<String> getRoles(String username) {
        List<SysRoleDto> roles = sysUserService.getRoles(username).getResults();
        Set<String> setRoles = new HashSet<>();
        for (SysRoleDto dto : roles) {
            setRoles.add(dto.getRoleName());
        }
        return setRoles;
    }

    private Set<String> getPermissions(String username) {
        List<PermissionDto> permissions = sysUserService.getPermissions(username, "权限管理平台").getResults();
        Set<String> setPermissions = new HashSet<String>();
        for (PermissionDto dto : permissions) {
            this.addPermission(setPermissions, dto.getAuthorize());
            List<PermissionDto> subPermission = dto.getPermissions();
            for (PermissionDto subDto : subPermission) {
                this.addPermission(setPermissions, subDto.getAuthorize());
                List<PermissionDto> btnPermission = subDto.getPermissions();
                for (PermissionDto subBtnDto : btnPermission) {
                    this.addPermission(setPermissions, subBtnDto.getAuthorize());
                }
            }
        }
        return setPermissions;
    }

    private void addPermission(Set<String> setPermissions, String authorize) {
        if (StringUtils.isNotBlank(authorize)) {
            setPermissions.add(authorize);
        }
    }

}
