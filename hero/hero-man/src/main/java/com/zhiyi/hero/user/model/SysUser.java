package com.zhiyi.hero.user.model;

import java.util.Date;

import com.zhiyi.common.model.BaseEntity;

/**
 * 系统用户类
 *
 * @author Administrator
 */
public class SysUser extends BaseEntity {

    private String userName;

    private String password;

    private int userFlag;

    private int statusFlag;


    public SysUser() {

    }

    public SysUser(Long userId, String userName, String password, int userFlag,
                   int statusFlag, String createUser, Date createDt,
                   String modifyUser, Date modifyDt) {
        super();
        this.id = userId;
        this.userName = userName;
        this.password = password;
        this.userFlag = userFlag;
        this.statusFlag = statusFlag;
        this.createUser = createUser;
        this.createDt = createDt;
        this.modifyUser = modifyUser;
        this.modifyDt = modifyDt;
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

}
