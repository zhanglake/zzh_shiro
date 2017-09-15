package com.zhang.dto;

/**
 * Created by zhenghua.zhang on 2017/9/4.
 */
public class TableRequest {
    private Integer pageNumber;
    private Integer pageSize;
    private String sortOrder;
    private String sortName;
    private String searchText;

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortName() {

        return sortName;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public Integer getPageNumber() {

        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public String getSearchText() {
        return searchText;
    }
}
