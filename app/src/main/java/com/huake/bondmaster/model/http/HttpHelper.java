package com.huake.bondmaster.model.http;


import com.huake.bondmaster.model.bean.HomePageBean;
import com.huake.bondmaster.model.bean.PageBean;
import com.huake.bondmaster.model.bean.SceneBean;
import com.huake.bondmaster.model.bean.SearchBean;
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
    Flowable<BondMasterHttpResponse<HomePageBean>> homePage();


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

}
