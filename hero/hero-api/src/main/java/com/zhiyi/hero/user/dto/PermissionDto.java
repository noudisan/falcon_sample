package com.zhiyi.hero.user.dto;

import java.util.ArrayList;
import java.util.List;

import com.zhiyi.common.dto.BaseDto;

public class PermissionDto extends BaseDto {
	
	private static final long serialVersionUID = 4156010300443680841L;

	private Long id;
	
	private String name;

	private String linkUrl;
	
	private String authorize;

	private List<PermissionDto> permissions = new ArrayList<PermissionDto>();

	/**
	 * 
	 */
	public PermissionDto() {
		super();
	}

	/**
	 * @param name
	 * @param linkUrl
	 */
	public PermissionDto(String name, String linkUrl) {
		super();
		this.name = name;
		this.linkUrl = linkUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public List<PermissionDto> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PermissionDto> permissions) {
		this.permissions = permissions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthorize() {
		return authorize;
	}

	public void setAuthorize(String authorize) {
		this.authorize = authorize;
	}

	
}
