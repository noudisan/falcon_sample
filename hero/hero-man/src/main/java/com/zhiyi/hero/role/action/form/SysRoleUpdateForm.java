package com.zhiyi.hero.role.action.form;

import com.zhiyi.hero.common.BaseForm;
import com.zhiyi.hero.role.dto.SysRoleDto;
import com.zhiyi.utils.PropertiesUtils;

public class SysRoleUpdateForm extends BaseForm {
	
	private Long id;
	
	private String roleName;
	
	private String roleDesc;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public SysRoleDto toDto(String currentUser){
		SysRoleDto dto = PropertiesUtils.copy(SysRoleDto.class, this);
		dto.setCreateUser(currentUser);
		dto.setModifyUser(currentUser);
		return dto;
	}

}
