package com.zhiyi.section.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 板块传输数据
 */
public class DeliverySectionDto implements Serializable {
	private Integer id;

	private String address;

	private String city;

	private Double latitude;

	private Double longitude;

	private String sectionName;

	private String statusFlag;

	private String modifyUser;

	private Date modifyDt;

	private String createUser;

	private Date createDt;

	private List<DeliverySectionPointDto> deliverySectionPoints;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
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

	public List<DeliverySectionPointDto> getDeliverySectionPoints() {
		return deliverySectionPoints;
	}

	public void setDeliverySectionPoints(List<DeliverySectionPointDto> deliverySectionPoints) {
		this.deliverySectionPoints = deliverySectionPoints;
	}

	@Override
	public String toString() {
		return "DeliverySectionDto{" +
				"id=" + id +
				", address='" + address + '\'' +
				", city='" + city + '\'' +
				", latitude=" + latitude +
				", longitude=" + longitude +
				", sectionName='" + sectionName + '\'' +
				", statusFlag='" + statusFlag + '\'' +
				", modifyUser='" + modifyUser + '\'' +
				", modifyDt=" + modifyDt +
				", createUser='" + createUser + '\'' +
				", createDt=" + createDt +
				", deliverySectionPoints=" + deliverySectionPoints +
				'}';
	}
}
