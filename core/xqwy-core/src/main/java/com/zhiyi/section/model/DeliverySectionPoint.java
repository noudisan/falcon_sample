package com.zhiyi.section.model;

import java.io.Serializable;

/**
 * 板块点实体
 */
public class DeliverySectionPoint implements Serializable{
	private Integer id;

	private Double latitude;

	private Double longitude;

	private Byte pointIndex;

	private DeliverySection deliverySection;


	public DeliverySectionPoint(Integer id, Double latitude, Double longitude, Byte pointIndex) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.pointIndex = pointIndex;
	}

	public DeliverySectionPoint() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Byte getPointIndex() {
		return pointIndex;
	}

	public void setPointIndex(Byte pointIndex) {
		this.pointIndex = pointIndex;
	}

	public DeliverySection getDeliverySection() {
		return deliverySection;
	}

	public void setDeliverySection(DeliverySection deliverySection) {
		this.deliverySection = deliverySection;
	}
}