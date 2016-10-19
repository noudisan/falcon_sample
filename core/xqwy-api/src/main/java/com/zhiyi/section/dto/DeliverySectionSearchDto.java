package com.zhiyi.section.dto;

import com.zhiyi.common.dto.PageSearchDto;

import java.io.Serializable;

/**
 * 板块查询条件的dto
 */
public class DeliverySectionSearchDto extends PageSearchDto implements Serializable {


	private String cityName;

	private String sectionName;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
}
