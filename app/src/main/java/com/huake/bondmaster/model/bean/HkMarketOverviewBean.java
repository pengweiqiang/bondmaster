package com.huake.bondmaster.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author will on 2017/9/4 19:50
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class HkMarketOverviewBean implements Parcelable{

    /**
     * sInfoCompcode : 10
     * sInfoCompname : 中华人民共和国财政部
     * sInfoWindcode : 698
     * bInfoFullname : 2017年第一期储蓄国债(电子式)
     * bIssueFirstissue : 2017-04-10
     * bInfoCouponrate : 3.8
     * bInfoCarrydate : 20170410
     * bInfoMaturitydate : 20200410
     * bIssueAmountact : 230
     * sInfoIndustrycode1 : hk100001
     * codeValue1 : 国债
     * sInfoIndustrycode2 : hk10000101
     * codeValue2 : 国债
     * secIndCode1 :
     * secCodeValue1 :
     * secIndCode2 :
     * secCodeValue2 :
     * nature :
     * natureCodeValue :
     * sInfoProvince : 北京
     * provinceId : 110000
     * issueCreditrating :
     * issueRatingNum :
     * bInfoTermYear : 3
     */

    private int sInfoCompcode;
    private String sInfoCompname;
    private int sInfoWindcode;
    private String bInfoFullname;
    private String bIssueFirstissue;
    private double bInfoCouponrate;
    private String bInfoCarrydate;
    private String bInfoMaturitydate;
    private double bIssueAmountact;
    private String sInfoIndustrycode1;
    private String codeValue1;
    private String sInfoIndustrycode2;
    private String codeValue2;
    private String secIndCode1;
    private String secCodeValue1;
    private String secIndCode2;
    private String secCodeValue2;
    private String nature;
    private String natureCodeValue;
    private String sInfoProvince;
    private String provinceId;
    private String issueCreditrating;
    private String issueRatingNum;
    private double bInfoTermYear;

    public int getSInfoCompcode() {
        return sInfoCompcode;
    }

    public void setSInfoCompcode(int sInfoCompcode) {
        this.sInfoCompcode = sInfoCompcode;
    }

    public String getSInfoCompname() {
        return sInfoCompname;
    }

    public void setSInfoCompname(String sInfoCompname) {
        this.sInfoCompname = sInfoCompname;
    }

    public int getSInfoWindcode() {
        return sInfoWindcode;
    }

    public void setSInfoWindcode(int sInfoWindcode) {
        this.sInfoWindcode = sInfoWindcode;
    }

    public String getBInfoFullname() {
        return bInfoFullname;
    }

    public void setBInfoFullname(String bInfoFullname) {
        this.bInfoFullname = bInfoFullname;
    }

    public String getBIssueFirstissue() {
        return bIssueFirstissue;
    }

    public void setBIssueFirstissue(String bIssueFirstissue) {
        this.bIssueFirstissue = bIssueFirstissue;
    }

    public double getBInfoCouponrate() {
        return bInfoCouponrate;
    }

    public void setBInfoCouponrate(double bInfoCouponrate) {
        this.bInfoCouponrate = bInfoCouponrate;
    }

    public String getBInfoCarrydate() {
        return bInfoCarrydate;
    }

    public void setBInfoCarrydate(String bInfoCarrydate) {
        this.bInfoCarrydate = bInfoCarrydate;
    }

    public String getBInfoMaturitydate() {
        return bInfoMaturitydate;
    }

    public void setBInfoMaturitydate(String bInfoMaturitydate) {
        this.bInfoMaturitydate = bInfoMaturitydate;
    }

    public double getBIssueAmountact() {
        return bIssueAmountact;
    }

    public void setBIssueAmountact(double bIssueAmountact) {
        this.bIssueAmountact = bIssueAmountact;
    }

    public String getSInfoIndustrycode1() {
        return sInfoIndustrycode1;
    }

    public void setSInfoIndustrycode1(String sInfoIndustrycode1) {
        this.sInfoIndustrycode1 = sInfoIndustrycode1;
    }

    public String getCodeValue1() {
        return codeValue1;
    }

    public void setCodeValue1(String codeValue1) {
        this.codeValue1 = codeValue1;
    }

    public String getSInfoIndustrycode2() {
        return sInfoIndustrycode2;
    }

    public void setSInfoIndustrycode2(String sInfoIndustrycode2) {
        this.sInfoIndustrycode2 = sInfoIndustrycode2;
    }

    public String getCodeValue2() {
        return codeValue2;
    }

    public void setCodeValue2(String codeValue2) {
        this.codeValue2 = codeValue2;
    }

    public String getSecIndCode1() {
        return secIndCode1;
    }

    public void setSecIndCode1(String secIndCode1) {
        this.secIndCode1 = secIndCode1;
    }

    public String getSecCodeValue1() {
        return secCodeValue1;
    }

    public void setSecCodeValue1(String secCodeValue1) {
        this.secCodeValue1 = secCodeValue1;
    }

    public String getSecIndCode2() {
        return secIndCode2;
    }

    public void setSecIndCode2(String secIndCode2) {
        this.secIndCode2 = secIndCode2;
    }

    public String getSecCodeValue2() {
        return secCodeValue2;
    }

    public void setSecCodeValue2(String secCodeValue2) {
        this.secCodeValue2 = secCodeValue2;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getNatureCodeValue() {
        return natureCodeValue;
    }

    public void setNatureCodeValue(String natureCodeValue) {
        this.natureCodeValue = natureCodeValue;
    }

    public String getSInfoProvince() {
        return sInfoProvince;
    }

    public void setSInfoProvince(String sInfoProvince) {
        this.sInfoProvince = sInfoProvince;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getIssueCreditrating() {
        return issueCreditrating;
    }

    public void setIssueCreditrating(String issueCreditrating) {
        this.issueCreditrating = issueCreditrating;
    }

    public String getIssueRatingNum() {
        return issueRatingNum;
    }

    public void setIssueRatingNum(String issueRatingNum) {
        this.issueRatingNum = issueRatingNum;
    }

    public double getBInfoTermYear() {
        return bInfoTermYear;
    }

    public void setBInfoTermYear(double bInfoTermYear) {
        this.bInfoTermYear = bInfoTermYear;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.sInfoCompcode);
        dest.writeString(this.sInfoCompname);
        dest.writeInt(this.sInfoWindcode);
        dest.writeString(this.bInfoFullname);
        dest.writeString(this.bIssueFirstissue);
        dest.writeDouble(this.bInfoCouponrate);
        dest.writeString(this.bInfoCarrydate);
        dest.writeString(this.bInfoMaturitydate);
        dest.writeDouble(this.bIssueAmountact);
        dest.writeString(this.sInfoIndustrycode1);
        dest.writeString(this.codeValue1);
        dest.writeString(this.sInfoIndustrycode2);
        dest.writeString(this.codeValue2);
        dest.writeString(this.secIndCode1);
        dest.writeString(this.secCodeValue1);
        dest.writeString(this.secIndCode2);
        dest.writeString(this.secCodeValue2);
        dest.writeString(this.nature);
        dest.writeString(this.natureCodeValue);
        dest.writeString(this.sInfoProvince);
        dest.writeString(this.provinceId);
        dest.writeString(this.issueCreditrating);
        dest.writeString(this.issueRatingNum);
        dest.writeDouble(this.bInfoTermYear);
    }

    public HkMarketOverviewBean() {
    }

    protected HkMarketOverviewBean(Parcel in) {
        this.sInfoCompcode = in.readInt();
        this.sInfoCompname = in.readString();
        this.sInfoWindcode = in.readInt();
        this.bInfoFullname = in.readString();
        this.bIssueFirstissue = in.readString();
        this.bInfoCouponrate = in.readDouble();
        this.bInfoCarrydate = in.readString();
        this.bInfoMaturitydate = in.readString();
        this.bIssueAmountact = in.readDouble();
        this.sInfoIndustrycode1 = in.readString();
        this.codeValue1 = in.readString();
        this.sInfoIndustrycode2 = in.readString();
        this.codeValue2 = in.readString();
        this.secIndCode1 = in.readString();
        this.secCodeValue1 = in.readString();
        this.secIndCode2 = in.readString();
        this.secCodeValue2 = in.readString();
        this.nature = in.readString();
        this.natureCodeValue = in.readString();
        this.sInfoProvince = in.readString();
        this.provinceId = in.readString();
        this.issueCreditrating = in.readString();
        this.issueRatingNum = in.readString();
        this.bInfoTermYear = in.readDouble();
    }

    public static final Creator<HkMarketOverviewBean> CREATOR = new Creator<HkMarketOverviewBean>() {
        @Override
        public HkMarketOverviewBean createFromParcel(Parcel source) {
            return new HkMarketOverviewBean(source);
        }

        @Override
        public HkMarketOverviewBean[] newArray(int size) {
            return new HkMarketOverviewBean[size];
        }
    };
}
