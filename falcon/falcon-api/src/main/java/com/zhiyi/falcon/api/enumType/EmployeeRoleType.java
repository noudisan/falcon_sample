package com.zhiyi.falcon.api.enumType;

/**
 * Created by Administrator on 2015/6/28.
 */
public enum EmployeeRoleType {
    SENDEMPLOYEE("派送员"),CHECKEMPLOYEE("抽检员");
    private String role;

    EmployeeRoleType(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
