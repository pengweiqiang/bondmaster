package com.huake.bondmaster.model.bean;

import java.util.List;

/**
 * @author will on 2017/8/24 12:44
 * @email pengweiqiang64@163.com
 * @description 分页bean
 * @Version
 */

public class PageBean<T> {
    private long pageNum;
    private long pageSize;
    private long records;
    private long total;

    private List<T> rows;


    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
