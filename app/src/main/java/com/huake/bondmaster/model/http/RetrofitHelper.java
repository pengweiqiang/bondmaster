package com.huake.bondmaster.model.http;


import com.huake.bondmaster.model.bean.AreaNatureTypeBean;
import com.huake.bondmaster.model.bean.ArticleBean;
import com.huake.bondmaster.model.bean.CommentBean;
import com.huake.bondmaster.model.bean.EnterpriseInfo;
import com.huake.bondmaster.model.bean.EvaluationSuccessBean;
import com.huake.bondmaster.model.bean.HkMarketOverviewBean;
import com.huake.bondmaster.model.bean.HkMarketOverviewDetailBean;
import com.huake.bondmaster.model.bean.HomePageBean;
import com.huake.bondmaster.model.bean.IndustryBean;
import com.huake.bondmaster.model.bean.PageBean;
import com.huake.bondmaster.model.bean.PartyBean;
import com.huake.bondmaster.model.bean.SceneBean;
import com.huake.bondmaster.model.bean.SearchBean;
import com.huake.bondmaster.model.bean.SubscribeBean;
import com.huake.bondmaster.model.bean.UserBean;
import com.huake.bondmaster.model.bean.VersionBean;
import com.huake.bondmaster.model.http.api.BondMasterApis;
import com.huake.bondmaster.model.http.response.BondMasterHttpResponse;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by pengweiqiang on 2017/8/25.
 */
public class RetrofitHelper implements HttpHelper {


    private BondMasterApis mMyApiService;


    @Inject
    public RetrofitHelper(BondMasterApis myApiService) {
        this.mMyApiService = myApiService;
    }



    @Override
    public Flowable<BondMasterHttpResponse<VersionBean>> fetchVersionInfo() {
        return mMyApiService.getVersionInfo();
    }

    @Override
    public Flowable<BondMasterHttpResponse<HomePageBean>> homePage(String scale, long pageNum,long pageSize) {
        return mMyApiService.homePage(scale,pageNum,pageSize);
    }

    @Override
    public Flowable<BondMasterHttpResponse<PageBean<SearchBean>>> searchSceneList(long pageNum, long pageSize, String sInfoCustname) {
        return mMyApiService.homeSearch(pageNum, pageSize, sInfoCustname);
    }

    @Override
    public Flowable<BondMasterHttpResponse<SearchBean>> search(String userId, String sInfoCustname, String sInfoCountry, String sInfoProvince, String sInfoComptype, String bAgencyGuarantornature, String secIndCode1, String secIndCode2, String totAssets, String totLiab, String netProfit, String operRev, String netCashFlowsOperAct, String sFaCurrent, String threeYearNetProfitMean, String ebitInterest, String lessIntExp, String totProfit, String roe, String yearInterest, String corporateBondYearInterest, String enterpriseBondYearInterest, String accRecv) {
        return mMyApiService.search(userId, sInfoCustname, sInfoCountry, sInfoProvince, sInfoComptype, bAgencyGuarantornature, secIndCode1, secIndCode2, totAssets, totLiab, netProfit, operRev, netCashFlowsOperAct, sFaCurrent, threeYearNetProfitMean, ebitInterest, lessIntExp, totProfit, roe, yearInterest, corporateBondYearInterest, enterpriseBondYearInterest, accRecv);
    }

    @Override
    public Flowable<BondMasterHttpResponse<EvaluationSuccessBean>> startEvaluate(Map<String, String> params) {
        return mMyApiService.startEvaluate(params);
    }

    @Override
    public Flowable<BondMasterHttpResponse<List<IndustryBean>>> getIndustry() {
        return mMyApiService.getIndustry();
    }

    @Override
    public Flowable<BondMasterHttpResponse<AreaNatureTypeBean>> getAreaNatureTypeList() {
        return mMyApiService.getAreaNatureTypeList();
    }

    @Override
    public Flowable<BondMasterHttpResponse<PartyBean>> getCompanyNameListByUserId(String userId) {
        return mMyApiService.getCompanyNameListByUserId(userId);
    }

    @Override
    public Flowable<BondMasterHttpResponse<PartyBean>> getCompanyNameListByName(String partyName) {
        return mMyApiService.getCompanyNameListByName(partyName);
    }

    @Override
    public Flowable<BondMasterHttpResponse<PageBean<SceneBean>>> getScenceList(String userId, long pageNum, long pageSize, String sInfoCustname, String secIndCode, String bAgencyGuarantornature, String bInfoCreditrating) {
        return mMyApiService.getScenceList(userId, pageNum, pageSize, sInfoCustname, secIndCode, bAgencyGuarantornature, bInfoCreditrating);
    }

    @Override
    public Flowable<BondMasterHttpResponse<EnterpriseInfo>> getEnterpriseInfo(String userId, String dataDate, String trialCustId) {
        return mMyApiService.getEnterpriseInfo(userId, dataDate, trialCustId);
    }

    @Override
    public Flowable<BondMasterHttpResponse<PartyBean.PartyListBean>> getEnterpriseInfoByName(String companyName) {
        return mMyApiService.getEnterpriseInfoByName(companyName);
    }

    @Override
    public Flowable<BondMasterHttpResponse<List<SubscribeBean>>> getSubscribeList(String userId) {
        return mMyApiService.getSubscribeList(userId);
    }

    @Override
    public Flowable<BondMasterHttpResponse<PageBean<ArticleBean>>> getArticleList(String userId, long pageNum, long pageSize, String categoryId) {
        return mMyApiService.getArticleList(userId, pageNum, pageSize, categoryId);
    }

    @Override
    public Flowable<BondMasterHttpResponse<String>> getFinancingPlanReportData(String userId, String trialCustId, String dataDate) {
        return mMyApiService.getFinancingPlanReportData(userId, trialCustId, dataDate);
    }

    @Override
    public Flowable<BondMasterHttpResponse<CommentBean>> getCmsCommentList(String id, String userId, long pageNum, long pageSize) {
        return mMyApiService.getCmsCommentList(id, userId, pageNum, pageSize);
    }

    @Override
    public Flowable<BondMasterHttpResponse<CommentBean>> insertCmsComment(Map<String, String> params) {
        return mMyApiService.insertCmsComment(params);
    }

    @Override
    public Flowable<BondMasterHttpResponse<PageBean<HkMarketOverviewBean>>> findHkMarketOverviewDataPage(String sInfoIndustrycode1, long pageNum, long pageSize) {
        return mMyApiService.findHkMarketOverviewDataPage(sInfoIndustrycode1, pageNum, pageSize);
    }

    @Override
    public Flowable<BondMasterHttpResponse<HkMarketOverviewDetailBean>> getHkMarketOverviewDataCmpIdAndWindCode(String sInfoCompcode, String sInfoWindcode) {
        return mMyApiService.getHkMarketOverviewDataCmpIdAndWindCode(sInfoCompcode, sInfoWindcode);
    }

    @Override
    public Flowable<BondMasterHttpResponse<String>> getRegisterRsa() {
        return mMyApiService.getRegisterRsa();
    }

    @Override
    public Flowable<BondMasterHttpResponse<String>> getLoginRsa() {
        return mMyApiService.getLoginRsa();
    }

    @Override
    public Flowable<BondMasterHttpResponse<Object>> registerUser(String mobile, String password, String code) {
        return mMyApiService.registerUser(mobile, password, code);
    }

    @Override
    public Flowable<BondMasterHttpResponse<Object>> sendVerificationCode(String mobile) {
        return mMyApiService.sendVerificationCode(mobile);
    }

    @Override
    public Flowable<BondMasterHttpResponse<UserBean>> login(String mobile, String password) {
        return mMyApiService.login(mobile, password);
    }

    @Override
    public Flowable<BondMasterHttpResponse<UserBean>> loginByCode(String mobile, String code) {
        return mMyApiService.loginByCode(mobile, code);
    }

    @Override
    public Flowable<BondMasterHttpResponse<Object>> forgetPassword(String mobile, String code, String password) {
        return mMyApiService.forgetPassword(mobile, code, password);
    }

    @Override
    public Flowable<BondMasterHttpResponse<String>> getForgetPwdModifyRsa() {
        return mMyApiService.getForgetPwdModifyRsa();
    }

    @Override
    public Flowable<BondMasterHttpResponse<String>> getPwdModifyRsa() {
        return mMyApiService.getPwdModifyRsa();
    }

    @Override
    public Flowable<BondMasterHttpResponse<Object>> modifyPwd(String srcPwd, String newPwd) {
        return mMyApiService.modifyPwd(srcPwd, newPwd);
    }

    @Override
    public Flowable<BondMasterHttpResponse<UserBean>> getUserInfo() {
        return mMyApiService.getUserInfo();
    }

    @Override
    public Flowable<BondMasterHttpResponse<Object>> saveFeedBack(String content, String contactEmail, String images) {
        return mMyApiService.saveFeedBack(content, contactEmail, images);
    }

}
