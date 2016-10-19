package com.zhiyi.hero.user.dto;

import com.zhiyi.common.dto.ManageDto;
import com.zhiyi.utils.DateUtils;

/**
 * Created by hrs on 2014/10/23.
 */
public class SysUserDto extends ManageDto {

	private static final long serialVersionUID = 4744937188865652639L;

    private String userName;

    private String password;

    private int userFlag;

    private int statusFlag;

    public SysUserDto() {
    }

    public SysUserDto(long id) {
        this.id = id;
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

    public Boolean getLocked() {
        return (this.getStatusFlag() == 2) ? Boolean.TRUE : Boolean.FALSE;
    }
    
    public String getStatusFlagString(){
    	return (this.statusFlag == 1)  ?  "正常" : "用户锁定";
    }
    
    public String getModifyDtString(){
    	return DateUtils.format(this.modifyDt, "yyyy-MM-dd HH:mm:ss");
    }
    
    public String getCreateDtString(){
    	return DateUtils.format(this.createDt, "yyyy-MM-dd HH:mm:ss");
    }
}
