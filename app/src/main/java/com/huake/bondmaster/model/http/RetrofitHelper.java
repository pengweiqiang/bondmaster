package com.huake.bondmaster.model.http;


import com.huake.bondmaster.model.bean.VersionBean;
import com.huake.bondmaster.model.http.api.MyApis;
import com.huake.bondmaster.model.http.response.MyHttpResponse;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by codeest on 2016/8/3.
 */
public class RetrofitHelper implements HttpHelper {


    private MyApis mMyApiService;


    @Inject
    public RetrofitHelper(MyApis myApiService) {
        this.mMyApiService = myApiService;
    }



    @Override
    public Flowable<MyHttpResponse<VersionBean>> fetchVersionInfo() {
        return mMyApiService.getVersionInfo();
    }

}
