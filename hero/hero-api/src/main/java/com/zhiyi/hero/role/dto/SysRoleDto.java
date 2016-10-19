package com.zhiyi.hero.role.dto;

import com.zhiyi.common.dto.ManageDto;
import com.zhiyi.utils.DateUtils;

public class SysRoleDto extends ManageDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -693275003338825388L;

	
	private String roleName;
	
	private String roleDesc;

	/**
	 * 
	 */
	public SysRoleDto() {
		super();
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
    public String getModifyDtString(){
    	return DateUtils.format(this.modifyDt, "yyyy-MM-dd HH:mm:ss");
    }
    
	public String getCreateDtString(){
    	return DateUtils.format(this.createDt, "yyyy-MM-dd HH:mm:ss");
    }
}
