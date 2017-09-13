package com.huake.bondmaster.model.bean;

import android.text.TextUtils;

/**
 * @author will on 2017/8/29 11:25
 * @email pengweiqiang64@163.com
 * @description 企业信息
 * @Version
 */

public class EnterpriseInfo {

    /**
     * trialCustId : 3232
     * dataDate : 2017-08-28 00:00:00
     * bInfoCreditrating : AA--AA
     * successProbability : 77.6000
     * EvaluateCount : 3
     * sInfoCustname : 上海紫江企业集团股份有限公司
     * userId : hkData
     */

    private int trialCustId;
    private String dataDate;
    private String bInfoCreditrating;
    private String successProbability;
    private int evaluateCount;
    private String sInfoCustname;
    private String userId;

    public int getTrialCustId() {
        return trialCustId;
    }

    public void setTrialCustId(int trialCustId) {
        this.trialCustId = trialCustId;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }

    public String getBInfoCreditrating() {
        return bInfoCreditrating;
    }

    public void setBInfoCreditrating(String bInfoCreditrating) {
        this.bInfoCreditrating = bInfoCreditrating;
    }

    public String getSuccessProbability() {
        return TextUtils.isEmpty(successProbability)?"0":successProbability;
    }

    public void setSuccessProbability(String successProbability) {
        this.successProbability = successProbability;
    }

    public int getEvaluateCount() {
        return evaluateCount;
    }

    public void setEvaluateCount(int EvaluateCount) {
        this.evaluateCount = evaluateCount;
    }

    public String getSInfoCustname() {
        return sInfoCustname;
    }

    public void setSInfoCustname(String sInfoCustname) {
        this.sInfoCustname = sInfoCustname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
