package com.zhiyi.section.dto;

import java.io.Serializable;

/**
 * 板块点的dto
 */
public class DeliverySectionPointDto implements Serializable {
	private Integer id;

	private Double lat;

	private Double lng;

	private Byte pointIndex;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Byte getPointIndex() {
		return pointIndex;
	}

	public void setPointIndex(Byte pointIndex) {
		this.pointIndex = pointIndex;
	}
}
