package com.zhiyi.common.web;

import java.util.List;

/**
 * datatables 分页方式服务端返回参数
 * http://www.datatables.net/usage/server-side
 * Reply from the server
 */
public class DtPagerResponse<T> {
	// 记录总数
	private int iTotalRecords = 0;
	// 需要被显示的记录总数
	private int iTotalDisplayRecords = 10;
	// datatables保留属性
	private int sEcho;
	// 数据
	private List<T> aaData;




    public DtPagerResponse(int iTotalRecords, DtRequest dtRequest, List<T> aaData) {
        this.iTotalRecords = iTotalRecords;
        this.iTotalDisplayRecords = iTotalRecords;
        this.sEcho = dtRequest.getsEcho();
        this.aaData = aaData;
    }

    public DtPagerResponse() {
		super();
	}

	public DtPagerResponse(DtRequest dtReq) {
		super();
		this.sEcho = dtReq.getsEcho();
	}
	
	/**
	 * 装配分页查询结果
	 * @param pQR
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setupResult(PagerQueryResult pQR){
		this.aaData=pQR.getDataList();
		this.iTotalRecords=pQR.getTotal();
		this.iTotalDisplayRecords=pQR.getTotal();
	}
	
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	public int getsEcho() {
		return sEcho;
	}
	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}
	public List<T> getAaData() {
		return aaData;
	}
	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}
	
}
