package com.zhiyi.common.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 封装的返回结果集对象
 * 
 * @param <T>
 */
public class CommonResponseResult<T> implements Serializable {
	
	private static final long serialVersionUID = -6956766231443584031L;
	
	/**
	 * 记录数
	 */
	private int count;
	
	/**
	 * 当前页
	 */
	private int currentPage;
	
	/**
	 * 数据集合
	 */
	private List<T> data;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}