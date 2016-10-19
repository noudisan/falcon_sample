package com.zhiyi.hero.resources.dto;

import com.zhiyi.common.dto.ManageDto;
import com.zhiyi.utils.DateUtils;

public class ResourcesDto extends ManageDto {

    private static final long serialVersionUID = -5496980421057894908L;

    private String name;

    private String describer;

    private ResourcesDto parent;

    private Long parentId;

    private String platformName;

    private int leafFlag;

    private String linkUrl;

    private String iconCls;

    private Long platformId;

    private int orders;
    
	private String authorize;

    public ResourcesDto() {
    }

    public ResourcesDto(Long id) {
        this.id = id;
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

    public String getAuthorize() {
		return authorize;
	}

	public void setAuthorize(String authorize) {
		this.authorize = authorize;
	}

	public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public ResourcesDto getParent() {
        return parent;
    }

    public void setParent(ResourcesDto parent) {
        this.parent = parent;
    }

    public String getModifyDtString() {
        return DateUtils.format(this.modifyDt, "yyyy-MM-dd HH:mm:ss");
    }

    public String getCreateDtString() {
        return DateUtils.format(this.createDt, "yyyy-MM-dd HH:mm:ss");
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return (parent != null) ? this.getParent().getName() : null;
    }

    public String getNamesNav() {
        String nameNav = this.getName();
        ResourcesDto parent = this.getParent();
        if (parent != null && parent.getParent() != null) {
            ResourcesDto pParent = parent.getParent();
            nameNav = pParent.getName() + "→" + parent.getName() + "→" + this.getName();
        } else if (parent != null) {
            nameNav = parent.getName() + "→" + this.getName();
        }
        return nameNav;

    }
}
