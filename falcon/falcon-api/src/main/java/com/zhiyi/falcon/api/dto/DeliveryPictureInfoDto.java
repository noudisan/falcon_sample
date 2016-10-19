package com.zhiyi.falcon.api.dto;

import java.io.Serializable;

/**
 */
@SuppressWarnings("serial")
public class DeliveryPictureInfoDto implements Serializable {
	private String path;
	private boolean skipNull;
	
	public DeliveryPictureInfoDto(){}
	
	public DeliveryPictureInfoDto(String path,boolean skipNull){
		this.path = path;
		this.skipNull = skipNull;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isSkipNull() {
		return skipNull;
	}
	public void setSkipNull(boolean skipNull) {
		this.skipNull = skipNull;
	}

	
	
	
	
}