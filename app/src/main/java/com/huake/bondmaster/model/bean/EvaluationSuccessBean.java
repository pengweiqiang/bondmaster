package com.huake.bondmaster.model.bean;

/**
 * @author will on 2017/8/31 15:42
 * @email pengweiqiang64@163.com
 * @description 评测成功
 * @Version
 */

public class EvaluationSuccessBean {

    /**
     * trialCustId : 17336
     * dataDate : 2017-08-31 15:39:50
     * userId : 43
     */

    private String trialCustId;
    private String dataDate;
    private String userId;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
