package com.zhiyi.hero.common;

import java.util.Date;

public class BaseForm {
	
	protected String createUser;

    protected Date createDt;

    protected String modifyUser;

    protected Date modifyDt;

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Date getModifyDt() {
		return modifyDt;
	}

	public void setModifyDt(Date modifyDt) {
		this.modifyDt = modifyDt;
	}

	/**
	 * @param createUser
	 * @param createDt
	 * @param modifyUser
	 * @param modifyDt
	 */
	public BaseForm(String createUser, Date createDt, String modifyUser,
			Date modifyDt) {
		super();
		this.createUser = createUser;
		this.createDt = createDt;
		this.modifyUser = modifyUser;
		this.modifyDt = modifyDt;
	}

	/**
	 * 
	 */
	public BaseForm() {
		super();
	}
	
}
