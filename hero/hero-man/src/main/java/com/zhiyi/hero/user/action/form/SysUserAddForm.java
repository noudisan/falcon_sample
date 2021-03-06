package com.zhiyi.hero.user.action.form;

import com.zhiyi.hero.common.BaseForm;
import com.zhiyi.hero.user.dto.SysUserDto;
import com.zhiyi.utils.PropertiesUtils;
import com.zhiyi.utils.crypto.Md5Utils;

public class SysUserAddForm extends BaseForm {

    private String userName;

    private String password;

    private int userFlag;

    private int statusFlag;

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

    public int getUserFlag() {
        return userFlag;
    }

    public void setUserFlag(int userFlag) {
        this.userFlag = userFlag;
    }

    public int getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(int statusFlag) {
        this.statusFlag = statusFlag;
    }

    public SysUserDto toDto(String currentUser) {
        SysUserDto dto = PropertiesUtils.copy(SysUserDto.class, this);
        dto.setPassword(Md5Utils.encode32(dto.getPassword()));
        dto.setModifyUser(currentUser);
        dto.setCreateUser(currentUser);
        return dto;
    }

}
