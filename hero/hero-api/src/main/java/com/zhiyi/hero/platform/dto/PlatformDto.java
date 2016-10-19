package com.zhiyi.hero.platform.dto;

import com.zhiyi.common.dto.ManageDto;
import com.zhiyi.utils.DateUtils;

public class PlatformDto extends ManageDto {

	private static final long serialVersionUID = -1314376651266428345L;

	private String name;
	
	private String url;
	
	private String secretKey;

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

	public String getModifyDtString(){
    	return DateUtils.format(this.modifyDt, "yyyy-MM-dd HH:mm:ss");
    }
	
	public String getCreateDtString(){
    	return DateUtils.format(this.createDt, "yyyy-MM-dd HH:mm:ss");
    }
}
