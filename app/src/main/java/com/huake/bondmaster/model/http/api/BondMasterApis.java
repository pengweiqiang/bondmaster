package com.huake.bondmaster.model.http.api;


import com.huake.bondmaster.model.bean.HomePageBean;
import com.huake.bondmaster.model.bean.PageBean;
import com.huake.bondmaster.model.bean.ResultBean;
import com.huake.bondmaster.model.bean.SceneBean;
import com.huake.bondmaster.model.bean.SearchBean;
import com.huake.bondmaster.model.bean.VersionBean;
import com.huake.bondmaster.model.http.response.BondMasterHttpResponse;

import io.reactivex.Flowable;
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
     */

    @GET("hk-soft-app/cmsApp/homePage")
    Flowable<BondMasterHttpResponse<HomePageBean>> homePage();

    /**
     *
     * @param userId
     * @param sInfoCustname
     * @param sInfoCountry
     * @param sInfoProvince
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
    @GET("hk-soft-app/evaluate/startEvaluate")
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
    @GET("hk-soft-app/")
    Flowable<BondMasterHttpResponse<PageBean<SceneBean>>>  getScenceList(@Query("userId")String userId,@Query("pageNum")long pageNum,
                                                                         @Query("pageSize")long pageSize,@Query("sInfoCustname")String sInfoCustname,
                                                                         @Query("secIndCode")String secIndCode,@Query("bAgencyGuarantornature")String bAgencyGuarantornature,
                                                                         @Query("bInfoCreditrating")String bInfoCreditrating);


    /**
     * 注册用户
     * @param mobile 手机号
     * @param password 密码
     * @param imgCode 图片验证码
     * @param code 短信验证码
     * @return
     */
    @POST("hk-soft-app/")
    Flowable<BondMasterHttpResponse<ResultBean>> registerUser(@Query("mobile")String mobile, @Query("password")String password,
                                                              @Query("imgCode")String imgCode, @Query("code")String code);


    /**
     *
     * @param mobile 手机号
     * @param password 密码 6-8位
     * @param imgCode 调用接口获取
     * @return
     */
    @POST("hk-soft-app/")
    Flowable<BondMasterHttpResponse<ResultBean>> login(@Query("mobile")String mobile,@Query("password")String password,@Query("imgCode")String imgCode);




}
