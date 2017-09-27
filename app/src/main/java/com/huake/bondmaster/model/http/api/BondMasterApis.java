package com.huake.bondmaster.model.http.api;


import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.model.bean.AreaNatureTypeBean;
import com.huake.bondmaster.model.bean.ArticleBean;
import com.huake.bondmaster.model.bean.CommentBean;
import com.huake.bondmaster.model.bean.EnterpriseInfo;
import com.huake.bondmaster.model.bean.EvaluationResultBean;
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
import com.huake.bondmaster.model.http.response.BondMasterHttpResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by pengweiqiang on 17/08/24.
 */

public interface BondMasterApis {

    /**
     * 获取最新版本信息
     * @return
     */
    @GET("version")
    Flowable<BondMasterHttpResponse<VersionBean>> getVersionInfo();


    /**
     * 首页接口
     * @param scale 2：2倍图 3：3倍图
     * @return
     */
    @GET("hk-soft-app/cmsApp/homePage")
    Flowable<BondMasterHttpResponse<HomePageBean>> homePage(@Query("scale") String scale,@Query("pageNum") long pageNum,@Query("pageSize")long pageSize);


    /**
     * 首页搜索
     * @param pageNum 当前页，默认1
     * @param pageSize 每页显示条数 默认10
     * @param sInfoCustname 筛选条件
     * @return
     */
    @GET("hk-soft-app/evaluate/hkPartyTrialCustInfoList")
    Flowable<BondMasterHttpResponse<PageBean<SearchBean>>> homeSearch(@Query("pageNum") long pageNum,@Query("pageSize")long pageSize,@Query("sInfoCustname")String sInfoCustname);

    /**
     * 开始评测
     * @param userId
     * @param sInfoCustname 公司名称
     * @param sInfoCountry 国籍
     * @param sInfoProvince 省份
     * @param sInfoComptype
     * @param bAgencyGuarantornature
     * @param secIndCode1
     * @param secIndCode2
     * @param totAssets
     * @param totLiab
     * @param netProfit
     * @param operRev
     * @param netCashFlowsOperAct
     * @param sFaCurrent
     * @param threeYearNetProfitMean
     * @param ebitInterest
     * @param lessIntExp
     * @param totProfit
     * @param roe
     * @param yearInterest
     * @param corporateBondYearInterest
     * @param enterpriseBondYearInterest
     * @param accRecv
     * @return
     */
    @POST("hk-soft-app/evaluate/startEvaluate")
    Flowable<BondMasterHttpResponse<SearchBean>>  search(@Query("userId")String userId, @Query("sInfoCustname")String sInfoCustname,
                                                         @Query("sInfoCountry")String sInfoCountry, @Query("sInfoProvince")String sInfoProvince,
                                                         @Query("sInfoComptype")String sInfoComptype, @Query("bAgencyGuarantornature")String bAgencyGuarantornature,
                                                         @Query("secIndCode1")String secIndCode1, @Query("secIndCode2")String secIndCode2,
                                                         @Query("totAssets")String totAssets, @Query("totLiab")String totLiab,
                                                         @Query("netProfit")String netProfit, @Query("operRev")String operRev,
                                                         @Query("netCashFlowsOperAct")String netCashFlowsOperAct, @Query("sFaCurrent")String sFaCurrent,
                                                         @Query("threeYearNetProfitMean")String threeYearNetProfitMean, @Query("ebitInterest")String ebitInterest,
                                                         @Query("lessIntExp")String lessIntExp, @Query("totProfit")String totProfit,
                                                         @Query("roe")String roe, @Query("yearInterest")String yearInterest,
                                                         @Query("corporateBondYearInterest")String corporateBondYearInterest, @Query("enterpriseBondYearInterest")String enterpriseBondYearInterest,
                                                         @Query("accRecv")String accRecv);


    //开始评测
    @FormUrlEncoded
    @POST("hk-soft-app/evaluate/startEvaluate")
    Flowable<BondMasterHttpResponse<EvaluationSuccessBean>> startEvaluate(@FieldMap Map<String,String> params);

    /**
     * 获取企业所属行业
     * @return
     */
    @GET("hk-soft-app/evaluate/listHkCodeSacIndustry")
    Flowable<BondMasterHttpResponse<List<IndustryBean>>> getIndustry();


    /**
     * 所在地区&企业性质&企业类型接口
     * @return
     */
    @GET("hk-soft-app/evaluate/listAreaNatureType")
    Flowable<BondMasterHttpResponse<AreaNatureTypeBean>> getAreaNatureTypeList();

    /**
     * 1.1.	评测页面-指标录入-用户测评企业
     * @param userId
     * @return
     */
    @GET("hk-soft-app/evaluate/listHkPartyTrialCustInfoByUserId")
    Flowable<BondMasterHttpResponse<PartyBean>>  getCompanyNameListByUserId(@Query("userId") String userId);

    @GET("hk-soft-app/evaluate/listFuzzyHkPartyCodeInfoByName")
    Flowable<BondMasterHttpResponse<PartyBean>> getCompanyNameListByName(@Query("partyName")String partyName);

    /**
     * 1.1.	场景列表
     * @param userId
     * @param pageNum 分页时必须，不传时，默认值为1
     * @param pageSize 分页时必须，不传时，默认值为10
     * @param sInfoCustname 测评公司名称
     * @param secIndCode 所属行业 多个使用分号“;”隔开
     * @param bAgencyGuarantornature 企业性质 多个使用分号“;”隔开
     * @param bInfoCreditrating 信用等级 多个使用分号“;”隔开
     * @return
     */
    @GET("hk-soft-app/scene/findPageHkPartyTrialCustInfoListByUserId")
    Flowable<BondMasterHttpResponse<PageBean<SceneBean>>>  getScenceList(@Query("userId")String userId,@Query("pageNum")long pageNum,
                                                                         @Query("pageSize")long pageSize,@Query("sInfoCustname")String sInfoCustname,
                                                                         @Query("secIndCode")String secIndCode,@Query("bAgencyGuarantornature")String bAgencyGuarantornature,
                                                                         @Query("bInfoCreditrating")String bInfoCreditrating);


    /**
     * 获取企业信息
     * @param userId
     * @param dataDate
     * @param trialCustId
     * @return
     */
    @GET("hk-soft-app/scene/getEvaluateEnterpriseInfo")
    Flowable<BondMasterHttpResponse<EnterpriseInfo>> getEnterpriseInfo(@Query("userId")String userId,@Query("dataDate")String dataDate,@Query("trialCustId")String trialCustId);


    /**
     * 评测结果
     * @param userId 用户Id
     * @param trialCustId 评测主体id
     * @param dataDate 数据日期
     * @return
     */
    @GET("hk-soft-app/evaluate/viewIssuanceEvaluationResult")
    Flowable<BondMasterHttpResponse<EvaluationResultBean>> getViewIssuanceEvaluationResult(@Query("userId")String userId, @Query("trialCustId")String trialCustId, @Query("dataDate")String dataDate);


    /**
     * 订阅管理列表
     * @param userId
     * @return
     */
    @GET("hk-soft-app/scene/subscribeList")
    Flowable<BondMasterHttpResponse<List<SubscribeBean>>> getSubscribeList(@Query("userId")String userId);


    /**
     * 融资报告
     * @param userId
     * @param trialCustId
     * @param dataDate
     * @return
     */
    @GET("hk-soft-app/scene/getFinancingPlanReportData")
    Flowable<BondMasterHttpResponse<String>> getFinancingPlanReportData(@Query("userId")String userId, @Query("trialCustId")String trialCustId, @Query("dataDate")String dataDate);


    //时讯专栏
    @GET("hk-soft-app/cmsApp/queryArticleList")
    Flowable<BondMasterHttpResponse<PageBean<ArticleBean>>> getArticleList(@Query("userId")String userId, @Query("pageNum")long pageNum, @Query("pageSize")long pageSize, @Query("categoryId")String categoryId);


    //获取评论列表
    @GET("hk-soft-app/cmsApp/queryCmsCommentList")
    Flowable<BondMasterHttpResponse<CommentBean>>  getCmsCommentList(@Query("id")String id, @Query("uerId")String userId, @Query("pageNum")long pageNum, @Query("pageSize")long pageSize);


    //保存评论
    @FormUrlEncoded
    @GET("hk-soft-app/cmsApp/insertCmsComment")
    Flowable<BondMasterHttpResponse<CommentBean>>  insertCmsComment(@FieldMap Map<String,String> params);

    /**
     * 查看债种今日新发债
     * @param sInfoIndustrycode1
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GET(Constants.HK_MARKET_OVERVIEW)
    Flowable<BondMasterHttpResponse<PageBean<HkMarketOverviewBean>>> findHkMarketOverviewDataPage(@Query("sInfoIndustrycode1") String sInfoIndustrycode1, @Query("pageNum")long pageNum, @Query("pageSize")long pageSize);


    /**
     * 查看债券详细信息
     * @param sInfoCompcode
     * @param sInfoWindcode
     * @return
     */
    @GET("hk-soft-app/marketOverview/getHkMarketOverviewDataCmpIdAndWindCode")
    Flowable<BondMasterHttpResponse<HkMarketOverviewDetailBean>> getHkMarketOverviewDataCmpIdAndWindCode(@Query("sInfoCompcode")String sInfoCompcode, @Query("sInfoWindcode")String sInfoWindcode);



    @GET("hk-soft-app/user/register/registerRsa")
    Flowable<BondMasterHttpResponse<String>> getRegisterRsa();


    @GET("hk-soft-app/loginRsa")
    Flowable<BondMasterHttpResponse<String>> getLoginRsa();

    /**
     * 注册用户
     * @param mobile 手机号
     * @param password 密码
     * @param code 短信验证码
     * @return
     */
    @POST("hk-soft-app/user/register/registerDo")
    Flowable<BondMasterHttpResponse<Object>> registerUser(@Query("mobile")String mobile, @Query("password")String password,
                                                              @Query("code")String code);

    /**
     * 手机短信验证码
     * @param mobile
     * @return
     */
    @POST("hk-soft-app/user/register/sendVerificationCode")
    Flowable<BondMasterHttpResponse<Object>> sendVerificationCode(@Query("mobile")String mobile);


    /**
     * 用户名密码登陆
     * @param mobile 手机号
     * @param password 密码 6-8位
     * @return
     */
    @POST("hk-soft-app/login")
    Flowable<BondMasterHttpResponse<UserBean>> login(@Query("mobile")String mobile, @Query("password")String password);


    /**
     * 手机验证码登陆
     * @param mobile
     * @param code
     * @return
     */
    @POST("hk-soft-app/mobileAndValidateCodeLogin")
    Flowable<BondMasterHttpResponse<UserBean>> loginByCode(@Field("mobile")String mobile,@Field("code")String code);


    /**
     * 密码找回--->获取rsa
     * @return
     */
    @GET("hk-soft-app/userForgetpwdModifyRsa")
    Flowable<BondMasterHttpResponse<String>> getForgetPwdModifyRsa();
    /**
     * 密码找回
     * @param mobile
     * @param code
     * @param password
     * @return
     */
    @POST("hk-soft-app/userForgetpwdModify")
    Flowable<BondMasterHttpResponse<Object>> forgetPassword(@Query("mobile")String mobile,@Query("code")String code,@Query("password")String password);


    /**
     * 修改密码--->获取rsa
     * @return
     */
    @GET("hk-soft-app/userPwdModifyRsa")
    Flowable<BondMasterHttpResponse<String>> getPwdModifyRsa();

    /**
     * 修改密码
     * @param srcPwd
     * @param newPwd
     * @return
     */
    @POST("hk-soft-app/userPwdModify")
    Flowable<BondMasterHttpResponse<Object>> modifyPwd(@Query("srcPwd")String srcPwd,@Query("newPwd")String newPwd);


    @POST("hk-soft-app/getUserInfo")
    Flowable<BondMasterHttpResponse<UserBean>> getUserInfo();

    @POST("hk-soft-app/feedback/save")
    Flowable<BondMasterHttpResponse<Object>> saveFeedBack(@Query("content")String content,@Query("contactEmail")String contactEmail,@Query("images")String images);
}
