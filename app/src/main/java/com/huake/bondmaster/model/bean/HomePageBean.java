package com.huake.bondmaster.model.bean;

import java.util.List;

/**
 * @author will on 2017/8/24 12:06
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class HomePageBean {


    /**
     * imgUrl : /1.jpg
     * sort : 1
     */

    private List<ImgsBean> imgs;

    private List<HotNewsBean> hotNews;

    public List<ImgsBean> getImgs() {
        return imgs;
    }

    public void setImgs(List<ImgsBean> imgs) {
        this.imgs = imgs;
    }

    public List<HotNewsBean> getHotNews() {
        return hotNews;
    }

    public void setHotNews(List<HotNewsBean> hotNews) {
        this.hotNews = hotNews;
    }

    public static class ImgsBean {
        private String imgUrl;
        private String sort;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }
    }

    @Override
    public String toString() {
        return "HomePageBean{" +
                "imgs=" + imgs +
                ", hotNews=" + hotNews +
                '}';
    }
}
