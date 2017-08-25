package com.huake.bondmaster.model.bean;

/**
 * @author will on 2017/8/24 12:08
 * @email pengweiqiang64@163.com
 * @description 资讯数据
 * @Version
 */

public class HotNewsBean {
    private String id ;
    private String title;//专栏标题
    private String image;//专栏标题前缩略图，若无缩略图，显示默认图标
    private String createDate;//格式 yyyy-MM-dd hh:mm:ss
    private int hits;//点击次数
    private String collectFlag;//0否 1是

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public String getCollectFlag() {
        return collectFlag;
    }

    public void setCollectFlag(String collectFlag) {
        this.collectFlag = collectFlag;
    }
}
