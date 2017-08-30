package com.huake.bondmaster.model.bean;

import java.util.List;

/**
 * @author will on 2017/8/30 15:08
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class AreaNatureTypeBean {

    /**
     * id : 北京
     * title : 北京
     */

    private List<ChinaAreasBean> chinaAreas;//所属地区
    /**
     * id : 1
     * title : 国有企业
     */

    private List<CompNaturesBean> compNatures;//企业性质
    /**
     * id : 股份有限公司
     * title : 股份有限公司
     */

    private List<CompTypesBean> compTypes;//企业类型

    public List<ChinaAreasBean> getChinaAreas() {
        return chinaAreas;
    }

    public void setChinaAreas(List<ChinaAreasBean> chinaAreas) {
        this.chinaAreas = chinaAreas;
    }

    public List<CompNaturesBean> getCompNatures() {
        return compNatures;
    }

    public void setCompNatures(List<CompNaturesBean> compNatures) {
        this.compNatures = compNatures;
    }

    public List<CompTypesBean> getCompTypes() {
        return compTypes;
    }

    public void setCompTypes(List<CompTypesBean> compTypes) {
        this.compTypes = compTypes;
    }

    public static class ChinaAreasBean {
        private String id;
        private String title;

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
    }

    public static class CompNaturesBean {
        private String id;
        private String title;

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
    }

    public static class CompTypesBean {
        private String id;
        private String title;

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
    }
}
