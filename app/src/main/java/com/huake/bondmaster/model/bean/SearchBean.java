package com.huake.bondmaster.model.bean;

import java.io.Serializable;

/**
 * @author will on 2017/8/24 12:35
 * @email pengweiqiang64@163.com
 * @description 搜索结果
 * @Version
 */

public class SearchBean implements Serializable{
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
    /**
     * id : 37017
     * sInfoListcompornot :
     * bInfoCreditratingHigh : AA
     * bInfoCreditratingLow : AA-
     * medAndLongAmountact : 431836.1032
     * medAndLongCouponrateBelow : 5.5
     * medAndLongCouponrateOver : 7.5
     * cpAmountact : 431836.1032
     * cpCouponrateBelow : 7
     * cpCouponrateOver : 7
     * secIndCodes :
     * bAgencyGuarantornatures :
     * bInfoCreditratings :
     * medAndLongCouponrate : 5.5~7.5
     * cpCouponrate : 7
     */

    private int id;
    private String sInfoListcompornot;
    private String bInfoCreditratingHigh;
    private String bInfoCreditratingLow;
    private String medAndLongAmountact;
    private double medAndLongCouponrateBelow;
    private double medAndLongCouponrateOver;
    private double cpAmountact;
    private String cpCouponrateBelow;
    private String cpCouponrateOver;
    private String secIndCodes;
    private String bAgencyGuarantornatures;
    private String bInfoCreditratings;
    private String medAndLongCouponrate;
    private String cpCouponrate;


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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSInfoListcompornot() {
        return sInfoListcompornot;
    }

    public void setSInfoListcompornot(String sInfoListcompornot) {
        this.sInfoListcompornot = sInfoListcompornot;
    }

    public String getBInfoCreditratingHigh() {
        return bInfoCreditratingHigh;
    }

    public void setBInfoCreditratingHigh(String bInfoCreditratingHigh) {
        this.bInfoCreditratingHigh = bInfoCreditratingHigh;
    }

    public String getBInfoCreditratingLow() {
        return bInfoCreditratingLow;
    }

    public void setBInfoCreditratingLow(String bInfoCreditratingLow) {
        this.bInfoCreditratingLow = bInfoCreditratingLow;
    }

    public String getMedAndLongAmountact() {
        return medAndLongAmountact;
    }

    public void setMedAndLongAmountact(String medAndLongAmountact) {
        this.medAndLongAmountact = medAndLongAmountact;
    }

    public double getMedAndLongCouponrateBelow() {
        return medAndLongCouponrateBelow;
    }

    public void setMedAndLongCouponrateBelow(double medAndLongCouponrateBelow) {
        this.medAndLongCouponrateBelow = medAndLongCouponrateBelow;
    }

    public double getMedAndLongCouponrateOver() {
        return medAndLongCouponrateOver;
    }

    public void setMedAndLongCouponrateOver(double medAndLongCouponrateOver) {
        this.medAndLongCouponrateOver = medAndLongCouponrateOver;
    }

    public double getCpAmountact() {
        return cpAmountact;
    }

    public void setCpAmountact(double cpAmountact) {
        this.cpAmountact = cpAmountact;
    }

    public String getCpCouponrateBelow() {
        return cpCouponrateBelow;
    }

    public void setCpCouponrateBelow(String cpCouponrateBelow) {
        this.cpCouponrateBelow = cpCouponrateBelow;
    }

    public String getCpCouponrateOver() {
        return cpCouponrateOver;
    }

    public void setCpCouponrateOver(String cpCouponrateOver) {
        this.cpCouponrateOver = cpCouponrateOver;
    }

    public String getSecIndCodes() {
        return secIndCodes;
    }

    public void setSecIndCodes(String secIndCodes) {
        this.secIndCodes = secIndCodes;
    }

    public String getBAgencyGuarantornatures() {
        return bAgencyGuarantornatures;
    }

    public void setBAgencyGuarantornatures(String bAgencyGuarantornatures) {
        this.bAgencyGuarantornatures = bAgencyGuarantornatures;
    }

    public String getBInfoCreditratings() {
        return bInfoCreditratings;
    }

    public void setBInfoCreditratings(String bInfoCreditratings) {
        this.bInfoCreditratings = bInfoCreditratings;
    }

    public String getMedAndLongCouponrate() {
        return medAndLongCouponrate;
    }

    public void setMedAndLongCouponrate(String medAndLongCouponrate) {
        this.medAndLongCouponrate = medAndLongCouponrate;
    }

    public String getCpCouponrate() {
        return cpCouponrate;
    }

    public void setCpCouponrate(String cpCouponrate) {
        this.cpCouponrate = cpCouponrate;
    }
}
