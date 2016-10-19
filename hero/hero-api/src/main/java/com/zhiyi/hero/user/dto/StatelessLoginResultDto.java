package com.zhiyi.hero.user.dto;

import com.zhiyi.common.dto.BaseDto;
import com.zhiyi.hero.role.dto.SysRoleDto;

import java.util.List;

/**
 * Created by hrs on 2014/11/17.
 */
public class StatelessLoginResultDto extends BaseDto {

    private Status status;

    private List<SysRoleDto> roles;

    private List<PermissionDto> permissions;

    public StatelessLoginResultDto() {
    }

    public StatelessLoginResultDto(Status status) {
        this.status = status;
    }

    public StatelessLoginResultDto(Status status, List<SysRoleDto> roles, List<PermissionDto> permissions) {
        this.status = status;
        this.roles = roles;
        this.permissions = permissions;
    }

    public List<SysRoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRoleDto> roles) {
        this.roles = roles;
    }

    public List<PermissionDto> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDto> permissions) {
        this.permissions = permissions;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static enum Status {

        SUCCESS("登录成功"), FAIL_1("用户名或密码错误"), FAIL_2("签名错误"),
        FAIL_3("用户已锁定"), FAIL_4("请求的平台不存在"),;

        private String message;

        Status(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


}
