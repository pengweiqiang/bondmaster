package com.huake.bondmaster.model;


import com.huake.bondmaster.model.bean.AreaNatureTypeBean;
import com.huake.bondmaster.model.bean.ArticleBean;
import com.huake.bondmaster.model.bean.CommentBean;
import com.huake.bondmaster.model.bean.EnterpriseInfo;
import com.huake.bondmaster.model.bean.EvaluationSuccessBean;
import com.huake.bondmaster.model.bean.HomePageBean;
import com.huake.bondmaster.model.bean.IndustryBean;
import com.huake.bondmaster.model.bean.PageBean;
import com.huake.bondmaster.model.bean.PartyBean;
import com.huake.bondmaster.model.bean.SceneBean;
import com.huake.bondmaster.model.bean.SearchBean;
import com.huake.bondmaster.model.bean.UserBean;
import com.huake.bondmaster.model.bean.VersionBean;
import com.huake.bondmaster.model.db.DBHelper;
import com.huake.bondmaster.model.http.HttpHelper;
import com.huake.bondmaster.model.http.response.BondMasterHttpResponse;
import com.huake.bondmaster.model.prefs.PreferencesHelper;

import java.util.List;
import java.util.Map;

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
    public void setHomePageCache(String homePageCache) {
        mPreferencesHelper.setHomePageCache(homePageCache);
    }

    @Override
    public HomePageBean getHomePageCache() {
        return mPreferencesHelper.getHomePageCache();
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
    public Flowable<BondMasterHttpResponse<PageBean<SearchBean>>> searchSceneList(long pageNum, long pageSize, String sInfoCustname) {
        return mHttpHelper.searchSceneList(pageNum, pageSize, sInfoCustname);
    }

    @Override
    public Flowable<BondMasterHttpResponse<SearchBean>> search(String userId, String sInfoCustname, String sInfoCountry, String sInfoProvince, String sInfoComptype, String bAgencyGuarantornature, String secIndCode1, String secIndCode2, String totAssets, String totLiab, String netProfit, String operRev, String netCashFlowsOperAct, String sFaCurrent, String threeYearNetProfitMean, String ebitInterest, String lessIntExp, String totProfit, String roe, String yearInterest, String corporateBondYearInterest, String enterpriseBondYearInterest, String accRecv) {
        return mHttpHelper.search(userId, sInfoCustname, sInfoCountry, sInfoProvince, sInfoComptype, bAgencyGuarantornature, secIndCode1, secIndCode2, totAssets, totLiab, netProfit, operRev, netCashFlowsOperAct, sFaCurrent, threeYearNetProfitMean, ebitInterest, lessIntExp, totProfit, roe, yearInterest, corporateBondYearInterest, enterpriseBondYearInterest, accRecv);
    }

    @Override
    public Flowable<BondMasterHttpResponse<EvaluationSuccessBean>> startEvaluate(Map<String, String> params) {
        return mHttpHelper.startEvaluate(params);
    }

    @Override
    public Flowable<BondMasterHttpResponse<List<IndustryBean>>> getIndustry() {
        return mHttpHelper.getIndustry();
    }

    @Override
    public Flowable<BondMasterHttpResponse<AreaNatureTypeBean>> getAreaNatureTypeList() {
        return mHttpHelper.getAreaNatureTypeList();
    }

    @Override
    public Flowable<BondMasterHttpResponse<PartyBean>> getCompanyNameListByUserId(String userId) {
        return mHttpHelper.getCompanyNameListByUserId(userId);
    }

    @Override
    public Flowable<BondMasterHttpResponse<PartyBean>> getCompanyNameListByName(String partyName) {
        return mHttpHelper.getCompanyNameListByName(partyName);
    }

    @Override
    public Flowable<BondMasterHttpResponse<PageBean<SceneBean>>> getScenceList(String userId, long pageNum, long pageSize, String sInfoCustname, String secIndCode, String bAgencyGuarantornature, String bInfoCreditrating) {
        return mHttpHelper.getScenceList(userId, pageNum, pageSize, sInfoCustname, secIndCode, bAgencyGuarantornature, bInfoCreditrating);
    }

    @Override
    public Flowable<BondMasterHttpResponse<EnterpriseInfo>> getEnterpriseInfo(String userId, String dataDate, String trialCustId) {
        return mHttpHelper.getEnterpriseInfo(userId, dataDate, trialCustId);
    }

    @Override
    public Flowable<BondMasterHttpResponse<PageBean<ArticleBean>>> getArticleList(String userId, long pageNum, long pageSize, String categoryId) {
        return mHttpHelper.getArticleList(userId, pageNum, pageSize, categoryId);
    }

    @Override
    public Flowable<BondMasterHttpResponse<CommentBean>> getCmsCommentList(String id, String userId, long pageNum, long pageSize) {
        return mHttpHelper.getCmsCommentList(id, userId, pageNum, pageSize);
    }

    @Override
    public Flowable<BondMasterHttpResponse<CommentBean>> insertCmsComment(Map<String, String> params) {
        return mHttpHelper.insertCmsComment(params);
    }

    @Override
    public Flowable<BondMasterHttpResponse<String>> getRegisterRsa() {
        return mHttpHelper.getRegisterRsa();
    }

    @Override
    public Flowable<BondMasterHttpResponse<String>> getLoginRsa() {
        return mHttpHelper.getLoginRsa();
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
    public Flowable<BondMasterHttpResponse<Object>> forgetPassword(String mobile, String code, String password) {
        return mHttpHelper.forgetPassword(mobile, code, password);
    }

    @Override
    public Flowable<BondMasterHttpResponse<String>> getForgetPwdModifyRsa() {
        return mHttpHelper.getForgetPwdModifyRsa();
    }

    @Override
    public Flowable<BondMasterHttpResponse<String>> getPwdModifyRsa() {
        return mHttpHelper.getPwdModifyRsa();
    }

    @Override
    public Flowable<BondMasterHttpResponse<Object>> modifyPwd( String srcPwd, String newPwd) {
        return mHttpHelper.modifyPwd(srcPwd, newPwd);
    }

    @Override
    public Flowable<BondMasterHttpResponse<UserBean>> getUserInfo() {
        return mHttpHelper.getUserInfo();
    }

}
