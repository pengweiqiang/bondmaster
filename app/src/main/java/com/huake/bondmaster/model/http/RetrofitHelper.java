package com.huake.bondmaster.model.http;


import com.huake.bondmaster.model.bean.HomePageBean;
import com.huake.bondmaster.model.bean.PageBean;
import com.huake.bondmaster.model.bean.SceneBean;
import com.huake.bondmaster.model.bean.SearchBean;
import com.huake.bondmaster.model.bean.VersionBean;
import com.huake.bondmaster.model.http.api.BondMasterApis;
import com.huake.bondmaster.model.http.response.BondMasterHttpResponse;

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
    public Flowable<BondMasterHttpResponse<HomePageBean>> homePage(String scale) {
        return mMyApiService.homePage(scale);
    }

    @Override
    public Flowable<BondMasterHttpResponse<SearchBean>> search(String userId, String sInfoCustname, String sInfoCountry, String sInfoProvince, String sInfoComptype, String bAgencyGuarantornature, String secIndCode1, String secIndCode2, String totAssets, String totLiab, String netProfit, String operRev, String netCashFlowsOperAct, String sFaCurrent, String threeYearNetProfitMean, String ebitInterest, String lessIntExp, String totProfit, String roe, String yearInterest, String corporateBondYearInterest, String enterpriseBondYearInterest, String accRecv) {
        return mMyApiService.search(userId, sInfoCustname, sInfoCountry, sInfoProvince, sInfoComptype, bAgencyGuarantornature, secIndCode1, secIndCode2, totAssets, totLiab, netProfit, operRev, netCashFlowsOperAct, sFaCurrent, threeYearNetProfitMean, ebitInterest, lessIntExp, totProfit, roe, yearInterest, corporateBondYearInterest, enterpriseBondYearInterest, accRecv);
    }

    @Override
    public Flowable<BondMasterHttpResponse<PageBean<SceneBean>>> getScenceList(String userId, long pageNum, long pageSize, String sInfoCustname, String secIndCode, String bAgencyGuarantornature, String bInfoCreditrating) {
        return mMyApiService.getScenceList(userId, pageNum, pageSize, sInfoCustname, secIndCode, bAgencyGuarantornature, bInfoCreditrating);
    }

}
