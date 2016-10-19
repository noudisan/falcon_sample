package com.zhiyi.hero.platform.action.form;

import com.zhiyi.hero.common.BaseForm;
import com.zhiyi.hero.platform.dto.PlatformDto;
import com.zhiyi.utils.PropertiesUtils;

public class PlatformUpdateForm extends BaseForm {
	
	private Long id;

	private String name;
	
	private String url;
	
	private String secretKey;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public PlatformDto toDto(String currentUser){
    	PlatformDto dto = PropertiesUtils.copy(PlatformDto.class, this);
    	dto.setModifyUser(currentUser);
    	return dto;
    }
}
