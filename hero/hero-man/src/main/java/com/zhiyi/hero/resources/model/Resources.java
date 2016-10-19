package com.zhiyi.hero.resources.model;

import com.zhiyi.common.model.BaseEntity;

public class Resources extends BaseEntity{
	
	private String name;
	
	private String describer;
	
	private Long parentId;
	
	private String platformName;
	
	private int leafFlag;
	
	private String linkUrl;
	
	private String iconCls;
	
	private Long platformId;
	
	private int orders;
	
	private String authorize;
	
	public Resources() {
		super();
	}

	
	/**
	 * @param name
	 * @param describer
	 * @param parentId
	 * @param leafFlag
	 * @param linkUrl
	 * @param iconCls
	 * @param platformId
	 * @param orders
	 */
	public Resources(String name, String describer, Long parentId, String platformName,int leafFlag,
			String linkUrl, String iconCls, Long platformId, int orders,String authorize) {
		super();
		this.name = name;
		this.describer = describer;
		this.parentId = parentId;
		this.platformName = platformName;
		this.leafFlag = leafFlag;
		this.linkUrl = linkUrl;
		this.iconCls = iconCls;
		this.platformId = platformId;
		this.orders = orders;
		this.authorize = authorize;
	}


	public String getName() {
		return name;
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public int getLeafFlag() {
		return leafFlag;
	}

	public void setLeafFlag(int leafFlag) {
		this.leafFlag = leafFlag;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}


	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}


	public String getAuthorize() {
		return authorize;
	}


	public void setAuthorize(String authorize) {
		this.authorize = authorize;
	} 
	
}
