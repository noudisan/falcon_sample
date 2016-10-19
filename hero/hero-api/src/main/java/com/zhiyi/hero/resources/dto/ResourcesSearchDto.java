package com.zhiyi.hero.resources.dto;

import com.zhiyi.common.dto.PageSearchDto;
import com.zhiyi.utils.DateUtils;

import java.util.Date;

public class ResourcesSearchDto extends PageSearchDto {

    private static final long serialVersionUID = 9206526243229111903L;

    private String name;

    private String describer;

    private Long platformId;

    private String platformName;
    
    private Long parentId;

    private String startDate;

    private String endDate;
    
	private String authorize;

    public String getName() {
        return name;
    }

    public ResourcesSearchDto() {
    }

    public ResourcesSearchDto(Long platformId) {
        this.platformId = platformId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriber() {
        return describer;
    }

    public void setDescriber(String describer) {
        this.describer = describer;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
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

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getAuthorize() {
		return authorize;
	}

	public void setAuthorize(String authorize) {
		this.authorize = authorize;
	}

	public Date getStartD() {
        return DateUtils.parse(this.startDate, "yyyy-MM-dd HH:mm:ss");
    }

    public Date getEndD() {
        return DateUtils.parse(this.endDate, "yyyy-MM-dd HH:mm:ss");
    }
}
