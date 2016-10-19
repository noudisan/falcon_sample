package com.zhiyi.community.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by yangjj on 2015/12/11.
 */
public class CommunityExportSearchDto implements Serializable {

	private final static double LT_RADIUS = 102834.74258026089786013677476285;
	private final static double GT_RADIUS = 111712.69150641055729984301412873;
	private double gtLongOffset;

	private double ltLongOffset;

	private double gtLatOffset;

	private double ltLatOffset;

	private Integer distance;
	private Double longitude;
	private Double latitude;

	public double getGtLongOffset() {
		return gtLongOffset;
	}

	public void setGtLongOffset(double gtLongOffset) {
		this.gtLongOffset = gtLongOffset;
	}

	public double getLtLongOffset() {
		return ltLongOffset;
	}

	public void setLtLongOffset(double ltLongOffset) {
		this.ltLongOffset = ltLongOffset;
	}

	public double getGtLatOffset() {
		return gtLatOffset;
	}

	public void setGtLatOffset(double gtLatOffset) {
		this.gtLatOffset = gtLatOffset;
	}

	public double getLtLatOffset() {
		return ltLatOffset;
	}

	public void setLtLatOffset(double ltLatOffset) {
		this.ltLatOffset = ltLatOffset;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void initOffset() {
		double gtOffset = distance / GT_RADIUS;
		double ltOffset = distance / LT_RADIUS;
		gtLongOffset = this.longitude + gtOffset;
		ltLongOffset = this.longitude - ltOffset;
		gtLatOffset = this.latitude + gtOffset;
		ltLatOffset = this.latitude - ltOffset;
	}
}
