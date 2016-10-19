package com.zhiyi.hero.role.dto;

import com.zhiyi.common.dto.PageSearchDto;
import com.zhiyi.utils.DateUtils;

import java.util.Date;

public class SysRoleSearchDto extends PageSearchDto {

	private static final long serialVersionUID = -5687779815121507664L;
	
	private String roleName;
	
	private String roleDesc;
	
    private String startDate;
    
    private String endDate;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Date  getStartD(){
		  return DateUtils.parse(this.startDate, "yyyy-MM-dd HH:mm:ss");
	}

	public Date getEndD(){
		return DateUtils.parse(this.endDate, "yyyy-MM-dd HH:mm:ss");
	}
}
