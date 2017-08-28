package com.huake.bondmaster.model.bean;

import java.util.List;

/**
 * @author will on 2017/8/28 09:36
 * @email pengweiqiang64@163.com
 * @description 评测结果
 * @Version
 */

public class EvaluationResultBean {
    private String userId;//用户id
    private String trialCustId;//评测主体id
    private String dataDate;//数据日期
    private String trialCustName;//评测公司名称
    private String successProbability;//发债成功概率
    private String bInfoCreditrating;//主体级别预估
    private String cpAmountact;//短期可发规模
    private String cpCouponrate;//短期票面利率
    private String medAndLongAmountact;//中长期可发规模
    private String medAndLongCouponrate;//中长期票面利率
    private List<FinancingPlan> rows;//融资方案的数据集合


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

    public String getTrialCustName() {
        return trialCustName;
    }

    public void setTrialCustName(String trialCustName) {
        this.trialCustName = trialCustName;
    }

    public String getSuccessProbability() {
        return successProbability;
    }

    public void setSuccessProbability(String successProbability) {
        this.successProbability = successProbability;
    }

    public String getbInfoCreditrating() {
        return bInfoCreditrating;
    }

    public void setbInfoCreditrating(String bInfoCreditrating) {
        this.bInfoCreditrating = bInfoCreditrating;
    }

    public String getCpAmountact() {
        return cpAmountact;
    }

    public void setCpAmountact(String cpAmountact) {
        this.cpAmountact = cpAmountact;
    }

    public String getCpCouponrate() {
        return cpCouponrate;
    }

    public void setCpCouponrate(String cpCouponrate) {
        this.cpCouponrate = cpCouponrate;
    }

    public String getMedAndLongAmountact() {
        return medAndLongAmountact;
    }

    public void setMedAndLongAmountact(String medAndLongAmountact) {
        this.medAndLongAmountact = medAndLongAmountact;
    }

    public String getMedAndLongCouponrate() {
        return medAndLongCouponrate;
    }

    public void setMedAndLongCouponrate(String medAndLongCouponrate) {
        this.medAndLongCouponrate = medAndLongCouponrate;
    }

    public List<FinancingPlan> getRows() {
        return rows;
    }

    public void setRows(List<FinancingPlan> rows) {
        this.rows = rows;
    }

    class FinancingPlan {
        private String id;//债种id
        private String codeValue;//债种名称
        private String bIssueAmountact;//规模
        private String bInfoCouponrate;//利率

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCodeValue() {
            return codeValue;
        }

        public void setCodeValue(String codeValue) {
            this.codeValue = codeValue;
        }

        public String getbIssueAmountact() {
            return bIssueAmountact;
        }

        public void setbIssueAmountact(String bIssueAmountact) {
            this.bIssueAmountact = bIssueAmountact;
        }

        public String getbInfoCouponrate() {
            return bInfoCouponrate;
        }

        public void setbInfoCouponrate(String bInfoCouponrate) {
            this.bInfoCouponrate = bInfoCouponrate;
        }
    }
}
