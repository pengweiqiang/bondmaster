package com.huake.bondmaster.model.bean;

/**
 * @author will on 2017/8/24 12:35
 * @email pengweiqiang64@163.com
 * @description 搜索结果
 * @Version
 */

public class SearchBean {
    private String userId;//用户id
    private String trialCustId;//评测主体id
    private String dataDate;//数据日期


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTrialCustId() {
        return trialCustId;
    }

    public void setTrialCustId(String trialCustId) {
        this.trialCustId = trialCustId;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }
}
