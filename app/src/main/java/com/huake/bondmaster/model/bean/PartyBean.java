package com.huake.bondmaster.model.bean;

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

    public static class PartyListBean {
        private int id;
        private String dataDate;
        private String userId;
        private int trialCustId;
        private String sInfoCustname;
        private String sInfoCountry;
        private String sInfoProvince;
        private String sInfoComptype;
        private Object bAgencyGuarantornature;
        private Object ifFinacingPlatform;
        private Object secIndCode1;
        private Object secIndCode2;
        private Object totAssets;
        private Object totLiab;
        private Object netProfit;
        private Object operRev;
        private Object netCashFlowsOperAct;
        private Object sFaCurrent;
        private Object ebitInterest;
        private Object lessIntExp;
        private Object threeYearNetProfitMean;
        private Object totProfit;
        private Object roe;
        private Object yearInterest;
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
        private Object corporateBondYearInterest;
        private Object enterpriseBondYearInterest;
        private Object negativeAuditTwoYears;
        private Object ifStandardSteelEnterprise;
        private Object ratingFlag;

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

        public Object getBAgencyGuarantornature() {
            return bAgencyGuarantornature;
        }

        public void setBAgencyGuarantornature(Object bAgencyGuarantornature) {
            this.bAgencyGuarantornature = bAgencyGuarantornature;
        }

        public Object getIfFinacingPlatform() {
            return ifFinacingPlatform;
        }

        public void setIfFinacingPlatform(Object ifFinacingPlatform) {
            this.ifFinacingPlatform = ifFinacingPlatform;
        }

        public Object getSecIndCode1() {
            return secIndCode1;
        }

        public void setSecIndCode1(Object secIndCode1) {
            this.secIndCode1 = secIndCode1;
        }

        public Object getSecIndCode2() {
            return secIndCode2;
        }

        public void setSecIndCode2(Object secIndCode2) {
            this.secIndCode2 = secIndCode2;
        }

        public Object getTotAssets() {
            return totAssets;
        }

        public void setTotAssets(Object totAssets) {
            this.totAssets = totAssets;
        }

        public Object getTotLiab() {
            return totLiab;
        }

        public void setTotLiab(Object totLiab) {
            this.totLiab = totLiab;
        }

        public Object getNetProfit() {
            return netProfit;
        }

        public void setNetProfit(Object netProfit) {
            this.netProfit = netProfit;
        }

        public Object getOperRev() {
            return operRev;
        }

        public void setOperRev(Object operRev) {
            this.operRev = operRev;
        }

        public Object getNetCashFlowsOperAct() {
            return netCashFlowsOperAct;
        }

        public void setNetCashFlowsOperAct(Object netCashFlowsOperAct) {
            this.netCashFlowsOperAct = netCashFlowsOperAct;
        }

        public Object getSFaCurrent() {
            return sFaCurrent;
        }

        public void setSFaCurrent(Object sFaCurrent) {
            this.sFaCurrent = sFaCurrent;
        }

        public Object getEbitInterest() {
            return ebitInterest;
        }

        public void setEbitInterest(Object ebitInterest) {
            this.ebitInterest = ebitInterest;
        }

        public Object getLessIntExp() {
            return lessIntExp;
        }

        public void setLessIntExp(Object lessIntExp) {
            this.lessIntExp = lessIntExp;
        }

        public Object getThreeYearNetProfitMean() {
            return threeYearNetProfitMean;
        }

        public void setThreeYearNetProfitMean(Object threeYearNetProfitMean) {
            this.threeYearNetProfitMean = threeYearNetProfitMean;
        }

        public Object getTotProfit() {
            return totProfit;
        }

        public void setTotProfit(Object totProfit) {
            this.totProfit = totProfit;
        }

        public Object getRoe() {
            return roe;
        }

        public void setRoe(Object roe) {
            this.roe = roe;
        }

        public Object getYearInterest() {
            return yearInterest;
        }

        public void setYearInterest(Object yearInterest) {
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

        public Object getCorporateBondYearInterest() {
            return corporateBondYearInterest;
        }

        public void setCorporateBondYearInterest(Object corporateBondYearInterest) {
            this.corporateBondYearInterest = corporateBondYearInterest;
        }

        public Object getEnterpriseBondYearInterest() {
            return enterpriseBondYearInterest;
        }

        public void setEnterpriseBondYearInterest(Object enterpriseBondYearInterest) {
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
    }
}
