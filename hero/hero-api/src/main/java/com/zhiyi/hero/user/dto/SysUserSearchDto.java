package com.zhiyi.hero.user.dto;

import com.zhiyi.common.dto.PageSearchDto;
import com.zhiyi.utils.DateUtils;

import java.util.Date;

/**
 * Created by hrs on 2014/10/23.
 */
public class SysUserSearchDto extends PageSearchDto {
	
	private static final long serialVersionUID = 4704001365373446256L;

	private String userName;
    
    private String startDate;
    
    private String endDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
