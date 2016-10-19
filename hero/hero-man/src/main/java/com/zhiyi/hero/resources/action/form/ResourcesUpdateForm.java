package com.zhiyi.hero.resources.action.form;

import com.zhiyi.hero.common.BaseForm;
import com.zhiyi.hero.resources.dto.ResourcesDto;
import com.zhiyi.utils.PropertiesUtils;
import org.apache.commons.lang.StringUtils;

public class ResourcesUpdateForm extends BaseForm {

    private Long id;

    private String name;

    private String describer;

    private Long parentId;

    private int leafFlag;

    private String linkUrl;

    private String iconCls;

    private Long platformId;

    private int orders;

    private String authorize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getAuthorize() {
        return authorize;
    }

    public void setAuthorize(String authorize) {
        this.authorize = authorize;
    }

    public ResourcesDto toDto(String currentName) {
        ResourcesDto dto = PropertiesUtils.copy(ResourcesDto.class, this);
        if (StringUtils.isBlank(authorize)) {
            dto.setAuthorize(null);
        }
        dto.setCreateUser(currentName);
        dto.setModifyUser(currentName);
        return dto;
    }
}
