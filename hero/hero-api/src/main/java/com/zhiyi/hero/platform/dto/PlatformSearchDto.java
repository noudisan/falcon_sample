package com.zhiyi.hero.platform.dto;

import com.zhiyi.common.dto.PageSearchDto;
import com.zhiyi.utils.DateUtils;

import java.util.Date;

public class PlatformSearchDto extends PageSearchDto {

	private static final long serialVersionUID = -4087606282777940443L;
	
	private String name;
	
	private String url;
	
    private String startDate;
    
    private String endDate;
    
	/**
	 * @param name
	 */
	public PlatformSearchDto(String name) {
		super();
		this.name = name;
	}
	
	public PlatformSearchDto() {
		super();
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public Date  getStartD(){
		  return DateUtils.parse(this.startDate, "yyyy-MM-dd HH:mm:ss");
	}

	public Date getEndD(){
		return DateUtils.parse(this.endDate, "yyyy-MM-dd HH:mm:ss");
	}
}
