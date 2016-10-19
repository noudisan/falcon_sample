package com.zhiyi.falcon.core.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class DeliveryDataCommunityUnitPicture implements Serializable {

	private Integer deliveryDataCommunityUnitId;
	private Integer sequence;
	private String path;
	private Date saveDate;
	
	public Integer getDeliveryDataCommunityUnitId() {
		return deliveryDataCommunityUnitId;
	}
	public void setDeliveryDataCommunityUnitId(Integer deliveryDataCommunityUnitId) {
		this.deliveryDataCommunityUnitId = deliveryDataCommunityUnitId;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getSaveDate() {
		return saveDate;
	}
	public void setSaveDate(Date saveDate) {
		this.saveDate = saveDate;
	}
	
}