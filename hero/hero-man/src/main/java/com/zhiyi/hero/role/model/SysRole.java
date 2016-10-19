package com.zhiyi.hero.role.model;

import com.zhiyi.common.model.BaseEntity;

import java.util.Date;

/**
 * 用户角色类
 * 
 * @author Administrator
 *
 */
public class SysRole extends BaseEntity {
	
	private String roleName;
	
	private String roleDesc;


	/**
	 * @param roleName
	 * @param roleDesc
	 */
	public SysRole(Long roleId,String roleName, String roleDesc,String createUser, Date createDt,
            String modifyUser, Date modifyDt) {
		super();
	    this.id = roleId;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.createUser = createUser;
		this.createDt = createDt;
		this.modifyUser = modifyUser;
		this.modifyDt = modifyDt;
	}
	
	public SysRole() {
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


}
