package com.zhiyi.hero.platform.model;

import java.util.Date;

import com.zhiyi.common.model.BaseEntity;

/**
 * 
 * 平台信息类
 * @author Administrator
 *
 */
public class Platform extends BaseEntity {
	
	private String name;
	
	private String url;
	
	private String secretKey;
	/**
	 * 
	 */
	public Platform() {
		super();
	}

	/**
	 * @param name
	 * @param uRL
	 */
	public Platform(Long id,String name, String url,String secretKey,String createUser, Date createDt,
            String modifyUser, Date modifyDt) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.secretKey = secretKey;
		this.createUser = createUser;
		this.createDt = createDt;
		this.modifyUser = modifyUser;
		this.modifyDt = modifyDt;
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
	
}
