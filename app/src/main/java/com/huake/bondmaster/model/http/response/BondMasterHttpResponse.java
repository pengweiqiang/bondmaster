package com.huake.bondmaster.model.http.response;

/**
 * Created by pengweiqiang on 17/08/24.
 */

public class BondMasterHttpResponse<T> {

    private int stat;
    private String desc;
    private T data;

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
