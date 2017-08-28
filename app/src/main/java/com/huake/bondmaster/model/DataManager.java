package com.huake.bondmaster.model;


import com.huake.bondmaster.model.bean.HomePageBean;
import com.huake.bondmaster.model.bean.PageBean;
import com.huake.bondmaster.model.bean.SceneBean;
import com.huake.bondmaster.model.bean.SearchBean;
import com.huake.bondmaster.model.bean.UserBean;
import com.huake.bondmaster.model.bean.VersionBean;
import com.huake.bondmaster.model.db.DBHelper;
import com.huake.bondmaster.model.http.HttpHelper;
import com.huake.bondmaster.model.http.response.BondMasterHttpResponse;
import com.huake.bondmaster.model.prefs.PreferencesHelper;

import io.reactivex.Flowable;

/**
 * @date: 2017/8/25
 * @desciption:
 */

public class DataManager implements HttpHelper, DBHelper, PreferencesHelper {

    HttpHelper mHttpHelper;
    DBHelper mDbHelper;
    PreferencesHelper mPreferencesHelper;

    public DataManager(HttpHelper httpHelper, DBHelper dbHelper, PreferencesHelper preferencesHelper) {
        mHttpHelper = httpHelper;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public boolean getNightModeState() {
        return mPreferencesHelper.getNightModeState();
    }

    @Override
    public void setNightModeState(boolean state) {
        mPreferencesHelper.setNightModeState(state);
    }

    @Override
    public boolean getNoImageState() {
        return mPreferencesHelper.getNoImageState();
    }

    @Override
    public void setNoImageState(boolean state) {
        mPreferencesHelper.setNoImageState(state);
    }

    @Override
    public boolean getAutoCacheState() {
        return mPreferencesHelper.getAutoCacheState();
    }

    @Override
    public void setAutoCacheState(boolean state) {
        mPreferencesHelper.setAutoCacheState(state);
    }

    @Override
    public int getCurrentItem() {
        return mPreferencesHelper.getCurrentItem();
    }

    @Override
    public void setCurrentItem(int item) {
        mPreferencesHelper.setCurrentItem(item);
    }

    @Override
    public boolean getLikePoint() {
        return mPreferencesHelper.getLikePoint();
    }

    @Override
    public void setLikePoint(boolean isFirst) {
        mPreferencesHelper.setLikePoint(isFirst);
    }

    @Override
    public boolean getVersionPoint() {
        return mPreferencesHelper.getVersionPoint();
    }

    @Override
    public void setVersionPoint(boolean isFirst) {
        mPreferencesHelper.setVersionPoint(isFirst);
    }

    @Override
    public boolean getManagerPoint() {
        return mPreferencesHelper.getManagerPoint();
    }

    @Override
    public void setManagerPoint(boolean isFirst) {
        mPreferencesHelper.setManagerPoint(isFirst);
    }

    @Override
    public void setUserInstance(UserBean userBean) {
        mPreferencesHelper.setUserInstance(userBean);
    }

    @Override
    public UserBean getUserInstance() {
        return mPreferencesHelper.getUserInstance();
    }


    @Override
    public Flowable<BondMasterHttpResponse<VersionBean>> fetchVersionInfo() {
        return mHttpHelper.fetchVersionInfo();
    }

    @Override
    public Flowable<BondMasterHttpResponse<HomePageBean>> homePage(String scale) {
        return mHttpHelper.homePage(scale);
    }

    @Override
    public Flowable<BondMasterHttpResponse<SearchBean>> search(String userId, String sInfoCustname, String sInfoCountry, String sInfoProvince, String sInfoComptype, String bAgencyGuarantornature, String secIndCode1, String secIndCode2, String totAssets, String totLiab, String netProfit, String operRev, String netCashFlowsOperAct, String sFaCurrent, String threeYearNetProfitMean, String ebitInterest, String lessIntExp, String totProfit, String roe, String yearInterest, String corporateBondYearInterest, String enterpriseBondYearInterest, String accRecv) {
        return mHttpHelper.search(userId, sInfoCustname, sInfoCountry, sInfoProvince, sInfoComptype, bAgencyGuarantornature, secIndCode1, secIndCode2, totAssets, totLiab, netProfit, operRev, netCashFlowsOperAct, sFaCurrent, threeYearNetProfitMean, ebitInterest, lessIntExp, totProfit, roe, yearInterest, corporateBondYearInterest, enterpriseBondYearInterest, accRecv);
    }

    @Override
    public Flowable<BondMasterHttpResponse<PageBean<SceneBean>>> getScenceList(String userId, long pageNum, long pageSize, String sInfoCustname, String secIndCode, String bAgencyGuarantornature, String bInfoCreditrating) {
        return mHttpHelper.getScenceList(userId, pageNum, pageSize, sInfoCustname, secIndCode, bAgencyGuarantornature, bInfoCreditrating);
    }

    @Override
    public Flowable<BondMasterHttpResponse<Object>> registerUser(String mobile, String password, String code) {
        return mHttpHelper.registerUser(mobile, password, code);
    }

    @Override
    public Flowable<BondMasterHttpResponse<Object>> sendVerificationCode(String mobile) {
        return mHttpHelper.sendVerificationCode(mobile);
    }

    @Override
    public Flowable<BondMasterHttpResponse<UserBean>> login(String mobile, String password) {
        return mHttpHelper.login(mobile, password);
    }

    @Override
    public Flowable<BondMasterHttpResponse<UserBean>> loginByCode(String mobile, String code) {
        return mHttpHelper.loginByCode(mobile, code);
    }

    @Override
    public Flowable<BondMasterHttpResponse> forgetPassword(String mobile, String code, String password) {
        return mHttpHelper.forgetPassword(mobile, code, password);
    }

}
