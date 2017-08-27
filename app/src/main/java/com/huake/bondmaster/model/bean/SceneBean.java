package com.huake.bondmaster.model.bean;

import java.io.Serializable;

/**
 * @author will on 2017/8/24 12:45
 * @email pengweiqiang64@163.com
 * @description 场景
 * @Version
 */

public class SceneBean implements Serializable{

    private long id;
    private String dataDate;
    private String userId;
    private long trialCustId;
    private String sInfoCustname;
    private String bInfoCreditrating;
    private String successProbability;
    private long secIndCode;
    private String secIndCodeName;
    private long bAgencyGuarantornature;
    private String bAgencyGuarantornatureName;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getTrialCustId() {
        return trialCustId;
    }

    public void setTrialCustId(long trialCustId) {
        this.trialCustId = trialCustId;
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

    public long getSecIndCode() {
        return secIndCode;
    }

    public void setSecIndCode(long secIndCode) {
        this.secIndCode = secIndCode;
    }

    public String getSecIndCodeName() {
        return secIndCodeName;
    }

    public void setSecIndCodeName(String secIndCodeName) {
        this.secIndCodeName = secIndCodeName;
    }

    public long getbAgencyGuarantornature() {
        return bAgencyGuarantornature;
    }

    public void setbAgencyGuarantornature(long bAgencyGuarantornature) {
        this.bAgencyGuarantornature = bAgencyGuarantornature;
    }

    public String getbAgencyGuarantornatureName() {
        return bAgencyGuarantornatureName;
    }

    public void setbAgencyGuarantornatureName(String bAgencyGuarantornatureName) {
        this.bAgencyGuarantornatureName = bAgencyGuarantornatureName;
    }
}
