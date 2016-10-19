package com.zhiyi.common.dto;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * mybatis拦截器 对应分页dto
 */
public class PageSearchDto extends SearchDto {

    //每页记录数
    protected int pageSize = 20;

    //当前第几页
    protected int currentPage = 1;

    //总记录数
    protected int totalSize;

    //总页数
    protected int totalPage;

    //排序字段
    protected List<String> sortColumn = new ArrayList<String>();

    protected List<String> sortDirection = new ArrayList<String>();

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<String> getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(List<String> sortColumn) {
        this.sortColumn = sortColumn;
    }

    public List<String> getSortDirection() {
        return sortDirection;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public void setSortDirection(List<String> sortDirection) {
        this.sortDirection = sortDirection;
    }

    public void addSortCriteria(String property, String direction) {
        getSortColumn().add(property);
        getSortDirection().add(direction);
    }

    protected String getSortableSql() {
        if (getSortColumn().size() == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(" order by ");
        for (int i = 0; i < sortColumn.size(); i++) {
            stringBuilder.append(sortColumn.get(i)).append(" " + sortDirection.get(i) + ", ");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 2);
    }

    protected String getPageableSql() {
        StringBuilder stringBuilder = new StringBuilder("");
        if (currentPage <= 1 && pageSize != -1) {
            stringBuilder.append("limit ").append("0," + pageSize);
        } else if (currentPage > 1) {
            int start = (currentPage - 1) * pageSize;
            stringBuilder.append("limit ").append(start + "," + pageSize);
        }

        return stringBuilder.toString();
    }

    public void clearSort() {
        if (null != sortColumn) {
            sortColumn.clear();
        }
        if (null != sortDirection) {
            sortDirection.clear();
        }
    }

    public void resetPagination(int currentPage, int pageSize, String property, String direction) {
        this.clearSort();
        this.setCurrentPage(currentPage);
        this.setPageSize(pageSize);
        if (StringUtils.isNotBlank(property) || StringUtils.isNotBlank(direction)) {
            this.addSortCriteria(property, direction);
        }
    }

    public String getPageableAndSortableSqlString() {
        return getSortableSql() + " " + getPageableSql();
    }

    public int calTotalPage(int totalSize) {
        this.totalSize = totalSize;
        int page = totalSize / pageSize;
        this.totalPage = (totalSize % pageSize) == 0 ? page : page + 1;
        return totalPage;
    }

    //设置不分页
    public Object disablePaging() {
        this.setTotalSize(-1);
        return this;
    }

    public Object openPaging() {
        this.setTotalSize(0);
        return this;
    }
}

