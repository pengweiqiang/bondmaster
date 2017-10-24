package com.huake.bondmaster.model.bean;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @author will on 2017/8/30 16:02
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class PartyBean {

    /**
     * id : 41001
     * dataDate : 2017-08-30 14:57:01
     * userId : 43
     * trialCustId : 17328
     * sInfoCustname : Q
     * sInfoCountry :
     * sInfoProvince : 北京
     * sInfoComptype : 股份有限公司
     * bAgencyGuarantornature : null
     * ifFinacingPlatform : null
     * secIndCode1 : null
     * secIndCode2 : null
     * totAssets : null
     * totLiab : null
     * netProfit : null
     * operRev : null
     * netCashFlowsOperAct : null
     * sFaCurrent : null
     * ebitInterest : null
     * lessIntExp : null
     * threeYearNetProfitMean : null
     * totProfit : null
     * roe : null
     * yearInterest : null
     * cpBalance : null
     * accRecv : null
     * medNoteBalance : null
     * corporateBondBalance : null
     * enterpriseBondBalance : null
     * illdgalRecordsThreeYears : null
     * defaultRecordsThreeYears : null
     * bondDefaultRecordsCurrent : null
     * issueCreditrating : null
     * bondCreditrating : null
     * corporateBondYearInterest : null
     * enterpriseBondYearInterest : null
     * negativeAuditTwoYears : null
     * ifStandardSteelEnterprise : null
     * ratingFlag : null
     */

    private List<PartyListBean> partyList;

    public List<PartyListBean> getPartyList() {
        return partyList;
    }

    public void setPartyList(List<PartyListBean> partyList) {
        this.partyList = partyList;
    }

    public static class PartyListBean implements Serializable{
        private int id;
        private String dataDate;
        private String userId;
        private int trialCustId;
        private String sInfoCustname;
        private String sInfoCountry;
        private String sInfoProvince;
        private String sInfoComptype;
        private String bAgencyGuarantornature;
        private String bAgencyGuarantornatureName;//企业性质名称
        private Object ifFinacingPlatform;
        private String secIndCode1;
        private String secIndCode2;
        private String secIndCode2Name;//所属行业名称
        private Object sFaCurrent;
        private Object lessIntExp;
        private Object cpBalance;
        private Object accRecv;
        private Object medNoteBalance;
        private Object corporateBondBalance;
        private Object enterpriseBondBalance;
        private Object illdgalRecordsThreeYears;
        private Object defaultRecordsThreeYears;
        private Object bondDefaultRecordsCurrent;
        private Object issueCreditrating;
        private Object bondCreditrating;
        private Object negativeAuditTwoYears;
        private Object ifStandardSteelEnterprise;
        private Object ratingFlag;

        private String totAssets;
        private String totLiab;
        private String netProfit;
        private String operRev;
        private String netCashFlowsOperAct;
        private String roe;
        private String threeYearNetProfitMean;
        private String ebitInterest;
        private String totProfit;
        private String yearInterest;
        private String corporateBondYearInterest;
        private String enterpriseBondYearInterest;


        private String partyName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public int getTrialCustId() {
            return trialCustId;
        }

        public void setTrialCustId(int trialCustId) {
            this.trialCustId = trialCustId;
        }

        public String getSInfoCustname() {
            return sInfoCustname;
        }

        public void setSInfoCustname(String sInfoCustname) {
            this.sInfoCustname = sInfoCustname;
        }

        public String getSInfoCountry() {
            return sInfoCountry;
        }

        public void setSInfoCountry(String sInfoCountry) {
            this.sInfoCountry = sInfoCountry;
        }

        public String getSInfoProvince() {
            return sInfoProvince;
        }

        public void setSInfoProvince(String sInfoProvince) {
            this.sInfoProvince = sInfoProvince;
        }

        public String getSInfoComptype() {
            return sInfoComptype;
        }

        public void setSInfoComptype(String sInfoComptype) {
            this.sInfoComptype = sInfoComptype;
        }

        public String getBAgencyGuarantornature() {
            return bAgencyGuarantornature;
        }

        public void setBAgencyGuarantornature(String bAgencyGuarantornature) {
            this.bAgencyGuarantornature = bAgencyGuarantornature;
        }

        public Object getIfFinacingPlatform() {
            return ifFinacingPlatform;
        }

        public void setIfFinacingPlatform(Object ifFinacingPlatform) {
            this.ifFinacingPlatform = ifFinacingPlatform;
        }

        public String getSecIndCode1() {
            return secIndCode1;
        }

        public void setSecIndCode1(String secIndCode1) {
            this.secIndCode1 = secIndCode1;
        }

        public String getSecIndCode2() {
            return secIndCode2;
        }

        public void setSecIndCode2(String secIndCode2) {
            this.secIndCode2 = secIndCode2;
        }

        public String getTotAssets() {
            return TextUtils.isEmpty(totAssets)?"0":totAssets;
        }

        public void setTotAssets(String totAssets) {
            this.totAssets = totAssets;
        }

        public String getTotLiab() {
            return TextUtils.isEmpty(totLiab)?"0":totLiab;
        }

        public void setTotLiab(String totLiab) {
            this.totLiab = totLiab;
        }

        public String getNetProfit() {
            return TextUtils.isEmpty(netProfit)?"0":netProfit;
        }

        public void setNetProfit(String netProfit) {
            this.netProfit = netProfit;
        }

        public String getOperRev() {
            return TextUtils.isEmpty(operRev)?"0":operRev;
        }

        public void setOperRev(String operRev) {
            this.operRev = operRev;
        }

        public String getNetCashFlowsOperAct() {
            return TextUtils.isEmpty(netCashFlowsOperAct)?"0":netCashFlowsOperAct;
        }

        public void setNetCashFlowsOperAct(String netCashFlowsOperAct) {
            this.netCashFlowsOperAct = netCashFlowsOperAct;
        }

        public Object getSFaCurrent() {
            return sFaCurrent;
        }

        public void setSFaCurrent(Object sFaCurrent) {
            this.sFaCurrent = sFaCurrent;
        }

        public String getEbitInterest() {
            return TextUtils.isEmpty(ebitInterest)?"0":ebitInterest;
        }

        public void setEbitInterest(String ebitInterest) {
            this.ebitInterest = ebitInterest;
        }

        public Object getLessIntExp() {
            return lessIntExp;
        }

        public void setLessIntExp(Object lessIntExp) {
            this.lessIntExp = lessIntExp;
        }

        public String getThreeYearNetProfitMean() {
            return TextUtils.isEmpty(threeYearNetProfitMean)?"0":threeYearNetProfitMean;
        }

        public void setThreeYearNetProfitMean(String threeYearNetProfitMean) {
            this.threeYearNetProfitMean = threeYearNetProfitMean;
        }

        public String getTotProfit() {
            return TextUtils.isEmpty(totProfit)?"0":totProfit;
        }

        public void setTotProfit(String totProfit) {
            this.totProfit = totProfit;
        }

        public String getRoe() {
            return TextUtils.isEmpty(roe)?"0":roe;
        }

        public void setRoe(String roe) {
            this.roe = roe;
        }

        public String getYearInterest() {
            return yearInterest;
        }

        public void setYearInterest(String yearInterest) {
            this.yearInterest = yearInterest;
        }

        public Object getCpBalance() {
            return cpBalance;
        }

        public void setCpBalance(Object cpBalance) {
            this.cpBalance = cpBalance;
        }

        public Object getAccRecv() {
            return accRecv;
        }

        public void setAccRecv(Object accRecv) {
            this.accRecv = accRecv;
        }

        public Object getMedNoteBalance() {
            return medNoteBalance;
        }

        public void setMedNoteBalance(Object medNoteBalance) {
            this.medNoteBalance = medNoteBalance;
        }

        public Object getCorporateBondBalance() {
            return corporateBondBalance;
        }

        public void setCorporateBondBalance(Object corporateBondBalance) {
            this.corporateBondBalance = corporateBondBalance;
        }

        public Object getEnterpriseBondBalance() {
            return enterpriseBondBalance;
        }

        public void setEnterpriseBondBalance(Object enterpriseBondBalance) {
            this.enterpriseBondBalance = enterpriseBondBalance;
        }

        public Object getIlldgalRecordsThreeYears() {
            return illdgalRecordsThreeYears;
        }

        public void setIlldgalRecordsThreeYears(Object illdgalRecordsThreeYears) {
            this.illdgalRecordsThreeYears = illdgalRecordsThreeYears;
        }

        public Object getDefaultRecordsThreeYears() {
            return defaultRecordsThreeYears;
        }

        public void setDefaultRecordsThreeYears(Object defaultRecordsThreeYears) {
            this.defaultRecordsThreeYears = defaultRecordsThreeYears;
        }

        public Object getBondDefaultRecordsCurrent() {
            return bondDefaultRecordsCurrent;
        }

        public void setBondDefaultRecordsCurrent(Object bondDefaultRecordsCurrent) {
            this.bondDefaultRecordsCurrent = bondDefaultRecordsCurrent;
        }

        public Object getIssueCreditrating() {
            return issueCreditrating;
        }

        public void setIssueCreditrating(Object issueCreditrating) {
            this.issueCreditrating = issueCreditrating;
        }

        public Object getBondCreditrating() {
            return bondCreditrating;
        }

        public void setBondCreditrating(Object bondCreditrating) {
            this.bondCreditrating = bondCreditrating;
        }

        public String getCorporateBondYearInterest() {
            return TextUtils.isEmpty(corporateBondYearInterest)?"0":corporateBondYearInterest;
        }

        public void setCorporateBondYearInterest(String corporateBondYearInterest) {
            this.corporateBondYearInterest = corporateBondYearInterest;
        }

        public String getEnterpriseBondYearInterest() {
            return TextUtils.isEmpty(enterpriseBondYearInterest)?"0":enterpriseBondYearInterest;
        }

        public void setEnterpriseBondYearInterest(String enterpriseBondYearInterest) {
            this.enterpriseBondYearInterest = enterpriseBondYearInterest;
        }

        public Object getNegativeAuditTwoYears() {
            return negativeAuditTwoYears;
        }

        public void setNegativeAuditTwoYears(Object negativeAuditTwoYears) {
            this.negativeAuditTwoYears = negativeAuditTwoYears;
        }

        public Object getIfStandardSteelEnterprise() {
            return ifStandardSteelEnterprise;
        }

        public void setIfStandardSteelEnterprise(Object ifStandardSteelEnterprise) {
            this.ifStandardSteelEnterprise = ifStandardSteelEnterprise;
        }

        public Object getRatingFlag() {
            return ratingFlag;
        }

        public void setRatingFlag(Object ratingFlag) {
            this.ratingFlag = ratingFlag;
        }

        public String getPartyName() {
            return partyName==null?"":partyName;
        }

        public void setPartyName(String partyName) {
            this.partyName = partyName;
        }

        public String getbAgencyGuarantornatureName() {
            return bAgencyGuarantornatureName;
        }

        public void setbAgencyGuarantornatureName(String bAgencyGuarantornatureName) {
            this.bAgencyGuarantornatureName = bAgencyGuarantornatureName;
        }

        public String getSecIndCode2Name() {
            return secIndCode2Name;
        }

        public void setSecIndCode2Name(String secIndCode2Name) {
            this.secIndCode2Name = secIndCode2Name;
        }

        @Override
        public String toString() {
            return TextUtils.isEmpty(getPartyName())?getSInfoCustname():getPartyName();
        }
    }
}
