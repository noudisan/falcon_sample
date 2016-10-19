package com.zhiyi.hero.user.dto;

import com.zhiyi.common.dto.BaseDto;
import com.zhiyi.common.dto.PageSearchDto;

/**
 * Created by hrs on 2014/10/31.
 */
public class SysUserRoleSearchDto extends PageSearchDto {

    private Long userId;

    private String userName;

    public SysUserRoleSearchDto() {
    }

    public SysUserRoleSearchDto(String userName) {
        this.userName = userName;
    }

    public SysUserRoleSearchDto(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
