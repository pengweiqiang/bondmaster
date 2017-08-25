package com.huake.bondmaster.model.http;


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


}
