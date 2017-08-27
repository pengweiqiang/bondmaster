package com.huake.bondmaster.widget;

import android.text.TextUtils;

import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.BaseView;
import com.huake.bondmaster.model.http.exception.ApiException;
import com.huake.bondmaster.model.http.response.BondMasterHttpResponse;
import com.huake.bondmaster.util.LogUtil;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

/**
 * Created by codeest on 2017/2/23.
 */

public abstract class CommonSubscriber<T> extends ResourceSubscriber<BondMasterHttpResponse<T>> {
    private BaseView mView;
    private String mErrorMsg;
    private boolean isShowErrorState = true;

    protected CommonSubscriber(BaseView view){
        this.mView = view;
    }

    protected CommonSubscriber(BaseView view, String errorMsg){
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    protected CommonSubscriber(BaseView view, boolean isShowErrorState){
        this.mView = view;
        this.isShowErrorState = isShowErrorState;
    }

    protected CommonSubscriber(BaseView view, String errorMsg, boolean isShowErrorState){
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.isShowErrorState = isShowErrorState;
    }

    @Override
    public void onNext(BondMasterHttpResponse<T> tBondMasterHttpResponse) {
        mErrorMsg = tBondMasterHttpResponse.getDesc();
        int code = tBondMasterHttpResponse.getStat();
        if (Constants.CODE_SUCCESS==code) {
            dataHandler(tBondMasterHttpResponse.getData());
            return;
        }
//        if (Constants.CODE_FAIL.equals(code)) {
//
//        } else if (Constants.CODE_EXCEPTION.equals(code)) {
//
//        } else if (Constants.CODE_INVALID_TOKEN.equals(code)) {
//            mView.showErrorMsgToast(mErrorMsg);
//            //提示重新登陆
//            mView.startLoginActivity();
//        } else if (Constants.CODE_EMPTY_DATA.equals(code)) {
//            onEmptyList();
//        }
        if (isShowErrorState) {
            mView.showErrorMsg(mErrorMsg);
        }

        onError(mErrorMsg);
    }

    @Override
    public void onComplete() {

    }

    public abstract void dataHandler(T t);

    //查询结果为空的情况
    public void onEmptyList() {

    }

    @Override
    public void onError(Throwable e) {
        if (mView == null) {
            return;
        }
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.showErrorMsg(mErrorMsg);
        } else if (e instanceof ApiException) {
            mView.showErrorMsg(e.toString());
        } else if (e instanceof HttpException) {
            mView.showErrorMsg("数据加载失败ヽ(≧Д≦)ノ");
        } else {
            mView.showErrorMsg("未知错误ヽ(≧Д≦)ノ");
            LogUtil.d(e.toString());
        }
        if (isShowErrorState) {
            mView.stateError();
        }
        onError(e.toString());
    }
    //获取失败
    public void onError(String msg) {
//        mView.cancelDialog();
    }
}
