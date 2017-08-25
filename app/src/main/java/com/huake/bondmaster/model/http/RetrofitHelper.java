package com.huake.bondmaster.model.http;


import com.huake.bondmaster.model.bean.VersionBean;
import com.huake.bondmaster.model.http.api.BondMasterApis;
import com.huake.bondmaster.model.http.response.BondMasterHttpResponse;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by codeest on 2016/8/3.
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

}
