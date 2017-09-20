package com.huake.bondmaster.model.bean;

/**
 * @author will on 2017/9/15 13:42
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class SubscribeBean {
    private String sInfoCustname;
    private String successProbability;
    private String bInfoCreditrating;


    public String getsInfoCustname() {
        return sInfoCustname;
    }

    public void setsInfoCustname(String sInfoCustname) {
        this.sInfoCustname = sInfoCustname;
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
}
