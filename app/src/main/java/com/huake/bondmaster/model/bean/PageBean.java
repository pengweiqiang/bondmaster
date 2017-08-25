package com.huake.bondmaster.model.bean;

import java.util.List;

/**
 * @author will on 2017/8/24 12:44
 * @email pengweiqiang64@163.com
 * @description 分页bean
 * @Version
 */

public class PageBean<T> {
    private int pageNumber;
    private int pageSize;
    private long records;
    private int total;

    private List<T> rows;


    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
