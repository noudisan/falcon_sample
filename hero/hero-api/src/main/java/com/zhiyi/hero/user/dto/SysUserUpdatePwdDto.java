package com.zhiyi.hero.user.dto;

import com.zhiyi.common.dto.BaseDto;
import com.zhiyi.common.dto.ManageDto;
import com.zhiyi.utils.DateUtils;

/**
 * Created by hrs on 2014/10/23.
 */
public class SysUserUpdatePwdDto extends BaseDto {

    private String userName;

    /**
     * 加密之后的密码
     */
    private String password;

    /**
     * 加密之后的密码
     */
    private String newPassword;

    public SysUserUpdatePwdDto() {
    }

    public SysUserUpdatePwdDto(String userName, String password, String newPassword) {
        this.userName = userName;
        this.password = password;
        this.newPassword = newPassword;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
