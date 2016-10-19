package com.zhiyi.falcon.api.dto;

import java.io.Serializable;
import java.util.Date;

import com.zhiyi.falcon.api.enumType.CityLockType;

@SuppressWarnings("serial")
public class CityDto implements Serializable {

	private Integer id;
	private String province;
	private String cityName;
	private String cityAbbr;
	private String modifyUser;
	private Date modifyDt;
	private String createUser;
	private Date createDt;
	private CityLockType isLocked;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityAbbr() {
		return cityAbbr;
	}
	public void setCityAbbr(String cityAbbr) {
		this.cityAbbr = cityAbbr;
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
	public CityLockType getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(CityLockType isLocked) {
		this.isLocked = isLocked;
	}
	
	

}
