package com.huake.bondmaster.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author will on 2017/8/30 15:05
 * @email pengweiqiang64@163.com
 * @description 企业行业
 * @Version
 */

public class IndustryBean implements Serializable{

    /**
     * subList : [{"id":"1201010000","title":"农业"},{"id":"1201020000","title":"林业"},{"id":"1201030000","title":"畜牧业"},{"id":"1201040000","title":"渔业"},{"id":"1201050000","title":"农、林、牧、渔服务业"}]
     * id : 1201
     * title : 农、林、牧、渔业
     */

    private String id;
    private String title;
    /**
     * id : 1201010000
     * title : 农业
     */

    private List<IndustryBean> subList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<IndustryBean> getSubList() {
        return subList;
    }

    public void setSubList(List<IndustryBean> subList) {
        this.subList = subList;
    }

}
