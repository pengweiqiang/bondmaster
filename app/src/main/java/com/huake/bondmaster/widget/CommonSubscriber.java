package com.huake.bondmaster.widget;

import android.text.TextUtils;

import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.BaseView;
import com.huake.bondmaster.model.http.exception.ApiException;
import com.huake.bondmaster.model.http.response.BondMasterHttpResponse;
import com.huake.bondmaster.util.LogUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

/**
 * Created by pengweiqiang on 2017/2/23.
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
        } else if (Constants.CODE_FAIL==code) {
            mView.stateMain();
        } else if (Constants.CODE_QUIT == code) {
            mView.stateMain();
        }else if (Constants.CODE_INVALID_TOKEN == code) {
            mView.showErrorMsg(mErrorMsg);
            //提示重新登陆
            mView.startLoginActivity();
        }

// else if (Constants.CODE_EMPTY_DATA.equals(code)) {
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
            LogUtil.i(((HttpException)e).code()+" code");
            if(isShowErrorState){
                mView.stateError();
            }
            mView.showErrorMsg("数据加载失败");
        }else if (e instanceof ConnectException) {
            if(isShowErrorState){
                mView.stateError();
            }
            mView.showErrorMsg("请检查网络是否正常");
        } else if (e instanceof SocketTimeoutException) {
            mView.showErrorMsg("连接超时，检查网络是否正常");
            if(isShowErrorState){
                mView.stateError();
            }
        } else {
            mView.showErrorMsg("未知错误");
            LogUtil.d(e.toString());
        }
//        if (isShowErrorState) {
//            mView.stateError();
//        }
        onError(e.toString());
    }
    //获取失败
    public void onError(String msg) {
//        mView.cancelDialog();
    }
}
