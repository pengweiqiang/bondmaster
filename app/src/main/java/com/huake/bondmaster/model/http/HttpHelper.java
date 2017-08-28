package com.huake.bondmaster.model.http;


import com.huake.bondmaster.model.bean.HomePageBean;
import com.huake.bondmaster.model.bean.PageBean;
import com.huake.bondmaster.model.bean.SceneBean;
import com.huake.bondmaster.model.bean.SearchBean;
import com.huake.bondmaster.model.bean.UserBean;
import com.huake.bondmaster.model.bean.VersionBean;
import com.huake.bondmaster.model.http.response.BondMasterHttpResponse;

import io.reactivex.Flowable;

/**
 * @author: Est <codeest.dev@gmail.com>
 * @date: 2017/4/21
 * @description:
 */

public interface HttpHelper {


    Flowable<BondMasterHttpResponse<VersionBean>> fetchVersionInfo();


    //首页接口
    Flowable<BondMasterHttpResponse<HomePageBean>> homePage(String scale);


    //搜索
    Flowable<BondMasterHttpResponse<SearchBean>>  search(String userId,String sInfoCustname,
                                                         String sInfoCountry, String sInfoProvince,
                                                         String sInfoComptype, String bAgencyGuarantornature,
                                                         String secIndCode1, String secIndCode2,
                                                         String totAssets, String totLiab,
                                                         String netProfit,String operRev,
                                                         String netCashFlowsOperAct, String sFaCurrent,
                                                         String threeYearNetProfitMean, String ebitInterest,
                                                         String lessIntExp, String totProfit,
                                                         String roe, String yearInterest,
                                                         String corporateBondYearInterest,String enterpriseBondYearInterest,
                                                         String accRecv);

    //场景列表
    Flowable<BondMasterHttpResponse<PageBean<SceneBean>>>  getScenceList(String userId, long pageNum,
                                                                         long pageSize, String sInfoCustname,
                                                                         String secIndCode, String bAgencyGuarantornature,
                                                                         String bInfoCreditrating);

    /**
     * 注册
     * @param mobile
     * @param password
     * @param code
     * @return
     */
    Flowable<BondMasterHttpResponse> registerUser(String mobile, String password, String code);

    /**
     * 手机短信验证码
     * @param mobile
     * @return
     */
    Flowable<BondMasterHttpResponse> sendVerificationCode(String mobile);


    /**
     * 用户名密码登陆
     * @param mobile 手机号
     * @param password 密码 6-8位
     * @return
     */
    Flowable<BondMasterHttpResponse<UserBean>> login(String mobile,String password);


    /**
     * 手机验证码登陆
     * @param mobile
     * @param code
     * @return
     */
    Flowable<BondMasterHttpResponse<UserBean>> loginByCode(String mobile,String code);


    /**
     * 密码找回
     * @param mobile
     * @param code
     * @param password
     * @return
     */
    Flowable<BondMasterHttpResponse> forgetPassword(String mobile,String code,String password);

}
