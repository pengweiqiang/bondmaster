package com.huake.bondmaster.model.bean;

/**
 * @author will on 2017/8/31 21:19
 * @email pengweiqiang64@163.com
 * @description 时讯专栏
 * @Version
 */

public class ArticleBean {

    /**
     * id : 061b6c8e95654ce98f7103be39f6111a
     * categoryId : 2
     * title : 贵州出台最严禁酒令 官员想喝酒只有一招
     * image : /upload/image/20170825/1708251226286602.jpg
     * createDate : 2017-08-22 19:38:35
     * hits : 0
     * collectFlag : 1
     * content :
     * copyfrom :
     * author :
     * userId :
     */

    private String id;
    private String categoryId;
    private String title;
    private String image;
    private String createDate;
    private int hits;
    private String collectFlag;
    private String content;
    private String copyfrom;
    private String author;
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCopyfrom() {
        return copyfrom;
    }

    public void setCopyfrom(String copyfrom) {
        this.copyfrom = copyfrom;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
