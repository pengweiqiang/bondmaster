package com.huake.bondmaster.model.http;


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
import com.huake.bondmaster.model.http.response.BondMasterHttpResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;

/**
 * @author: pengweiqiang
 * @date: 2017/4/21
 * @description:
 */

public interface HttpHelper {


    Flowable<BondMasterHttpResponse<VersionBean>> fetchVersionInfo();


    //首页接口
    Flowable<BondMasterHttpResponse<HomePageBean>> homePage(String scale);

    Flowable<BondMasterHttpResponse<PageBean<SearchBean>>> searchSceneList(long pageNum,long pageSize,String sInfoCustname);


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

    //开始评测
    Flowable<BondMasterHttpResponse<EvaluationSuccessBean>> startEvaluate(Map<String,String> params);


    //所属行业
    Flowable<BondMasterHttpResponse<List<IndustryBean>>> getIndustry();


    /**
     * 所在地区&企业性质&企业类型接口
     * @return
     */
    Flowable<BondMasterHttpResponse<AreaNatureTypeBean>> getAreaNatureTypeList();


    //评测中--输入名称进行搜索公司名称列表根据userId
    Flowable<BondMasterHttpResponse<PartyBean>>  getCompanyNameListByUserId(String userId);
    //评测中--输入名称进行搜索公司名称列表根据关键字
    Flowable<BondMasterHttpResponse<PartyBean>> getCompanyNameListByName(String partyName);


    //场景列表
    Flowable<BondMasterHttpResponse<PageBean<SceneBean>>>  getScenceList(String userId, long pageNum,
                                                                         long pageSize, String sInfoCustname,
                                                                         String secIndCode, String bAgencyGuarantornature,
                                                                         String bInfoCreditrating);

    //企业信息
    Flowable<BondMasterHttpResponse<EnterpriseInfo>> getEnterpriseInfo(String userId,String dataDate,String trialCustId);


    //时讯专栏列表
    Flowable<BondMasterHttpResponse<PageBean<ArticleBean>>> getArticleList(String userId,long pageNum, long pageSize, String categoryId);



    //获取评论列表
    Flowable<BondMasterHttpResponse<CommentBean>>  getCmsCommentList(String id,String userId,long pageNum,long pageSize);


    //保存评论
    Flowable<BondMasterHttpResponse<CommentBean>>  insertCmsComment(Map<String,String> params);



    Flowable<BondMasterHttpResponse<String>> getRegisterRsa();


    Flowable<BondMasterHttpResponse<String>> getLoginRsa();
    /**
     * 注册
     * @param mobile
     * @param password
     * @param code
     * @return
     */
    Flowable<BondMasterHttpResponse<Object>> registerUser(String mobile, String password, String code);

    /**
     * 手机短信验证码
     * @param mobile
     * @return
     */
    Flowable<BondMasterHttpResponse<Object>> sendVerificationCode(String mobile);


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
    Flowable<BondMasterHttpResponse<Object>> forgetPassword(String mobile,String code,String password);

    Flowable<BondMasterHttpResponse<String>> getForgetPwdModifyRsa();


    Flowable<BondMasterHttpResponse<String>> getPwdModifyRsa();


    Flowable<BondMasterHttpResponse<Object>> modifyPwd(String mobile,String code,String srcPwd,String newPwd);


    Flowable<BondMasterHttpResponse<UserBean>> getUserInfo();

}
