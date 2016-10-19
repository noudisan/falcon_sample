package com.zhiyi.common.web;


/**
 * datatables 页面查询参数
 * http://www.datatables.net/usage/server-side
 * Parameters sent to the server
 */
public class DtRequest {

    // 当前起始记录数
    private int iDisplayStart = 0;
    // 每一页显示条数
    private int iDisplayLength = 10;
    //默认查询框内容
    private String sSearch;
    // sEcho
    private int sEcho;

    //排序字段（数据库字段对应）
    private String iSortCol_0;
    //排序类型（ASC，DESC）
    private String sSortDir_0;


    public int getiDisplayStart() {
        return iDisplayStart;
    }

    public void setiDisplayStart(int iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    public int getiDisplayLength() {
        return iDisplayLength;
    }

    public void setiDisplayLength(int iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }

    public int getsEcho() {
        return sEcho;
    }

    public void setsEcho(int sEcho) {
        this.sEcho = sEcho;
    }

    public String getsSearch() {
        return sSearch;
    }

    public void setsSearch(String sSearch) {
        this.sSearch = sSearch;
    }

    public String getiSortCol_0() {
        return iSortCol_0;
    }

    public void setiSortCol_0(String iSortCol_0) {
        this.iSortCol_0 = iSortCol_0;
    }

    public String getsSortDir_0() {
        return sSortDir_0;
    }

    public void setsSortDir_0(String sSortDir_0) {
        this.sSortDir_0 = sSortDir_0;
    }

    public int currentPage(){
        return  (iDisplayStart + iDisplayLength) / iDisplayLength;
    }

    public int pageSize(){
        return  iDisplayLength;
    }
}
