package com.zhiyi.common.web;

import java.util.List;

/**
 * 通用分页对象,解决同时返回记录总数和数据
 */
public class PagerQueryResult<T> {
	//总记录数
	private int total;
    // 数据
    private List<T> dataList ;
     
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
}
