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
    private String sInfoCustname;//企业名称
    private String bInfoCreditrating;//主体级别预估
    private String successProbability;//发债成功概率
    private String secIndCode;//所属行业编码
    private String secIndCodeName;//所属行业名称
    private String bAgencyGuarantornature;//企业性质编码
    private String bAgencyGuarantornatureName;//企业性质名称



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

    public String getsInfoCustname() {
        return sInfoCustname;
    }

    public void setsInfoCustname(String sInfoCustname) {
        this.sInfoCustname = sInfoCustname;
    }

    public String getbInfoCreditrating() {
        return bInfoCreditrating;
    }

    public void setbInfoCreditrating(String bInfoCreditrating) {
        this.bInfoCreditrating = bInfoCreditrating;
    }

    public String getSuccessProbability() {
        return successProbability;
    }

    public void setSuccessProbability(String successProbability) {
        this.successProbability = successProbability;
    }

    public String getSecIndCode() {
        return secIndCode;
    }

    public void setSecIndCode(String secIndCode) {
        this.secIndCode = secIndCode;
    }

    public String getSecIndCodeName() {
        return secIndCodeName;
    }

    public void setSecIndCodeName(String secIndCodeName) {
        this.secIndCodeName = secIndCodeName;
    }

    public String getbAgencyGuarantornature() {
        return bAgencyGuarantornature;
    }

    public void setbAgencyGuarantornature(String bAgencyGuarantornature) {
        this.bAgencyGuarantornature = bAgencyGuarantornature;
    }

    public String getbAgencyGuarantornatureName() {
        return bAgencyGuarantornatureName;
    }

    public void setbAgencyGuarantornatureName(String bAgencyGuarantornatureName) {
        this.bAgencyGuarantornatureName = bAgencyGuarantornatureName;
    }
}
