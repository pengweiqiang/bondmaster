package com.huake.bondmaster.presenter.my;

import android.text.TextUtils;

import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.my.ForgetPasswordContract;
import com.huake.bondmaster.model.DataManager;
import com.huake.bondmaster.model.http.response.BondMasterHttpResponse;
import com.huake.bondmaster.util.RSAUtils;
import com.huake.bondmaster.util.RxUtil;
import com.huake.bondmaster.widget.CommonSubscriber;

import java.security.interfaces.RSAPublicKey;

import javax.inject.Inject;

/**
 * @author will on 2017/8/28 10:10
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class ForgetPasswordPresenter extends RxPresenter<ForgetPasswordContract.View> implements ForgetPasswordContract.Presenter {

    DataManager dataManager;

    @Inject
    public ForgetPasswordPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }




    @Override
    public void getCode(String mobile) {
        addSubscribe(dataManager.sendVerificationCode(mobile)
                .compose(RxUtil.<BondMasterHttpResponse<Object>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<Object>(mView, true) {
                    @Override
                    public void dataHandler(Object object) {
                        mView.stateMain();
                        mView.getCodeSuccess();
                    }

                    @Override
                    public void onNext(BondMasterHttpResponse<Object> objectBondMasterHttpResponse) {
                        super.onNext(objectBondMasterHttpResponse);
                        if (objectBondMasterHttpResponse != null && objectBondMasterHttpResponse.getStat() == Constants.CODE_SUCCESS) {
                            mView.showErrorMsg(objectBondMasterHttpResponse.getDesc());
                        }
                    }
                })
        );
    }

    @Override
    public void forgetPassword(final String mobile,final String code, final String password) {
        addSubscribe(dataManager.getForgetPwdModifyRsa()
                .compose(RxUtil.<BondMasterHttpResponse<String>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<String>(mView, true) {
                    @Override
                    public void dataHandler(String rsaStr) {
                        if (TextUtils.isEmpty(rsaStr)) {
                            mView.showErrorMsg("获取失败");
                        } else {
                            //登陆
                            try {
                                RSAPublicKey publicKey = (RSAPublicKey) RSAUtils.getPublicKey(rsaStr);
                                //公钥加密密码后的字符串
                                String passwordRSa = RSAUtils.encrypt(password,publicKey);
                                addSubscribe(dataManager.forgetPassword(mobile, code,passwordRSa)
                                        .compose(RxUtil.<BondMasterHttpResponse<Object>>rxSchedulerHelper())
                                        .subscribeWith(new CommonSubscriber<Object>(mView, true) {
                                            @Override
                                            public void dataHandler(Object object) {
                                                mView.stateMain();
                                                mView.updateSuccess();
                                            }

                                            @Override
                                            public void onNext(BondMasterHttpResponse<Object> objectBondMasterHttpResponse) {
                                                super.onNext(objectBondMasterHttpResponse);
                                                if (objectBondMasterHttpResponse != null && objectBondMasterHttpResponse.getStat() == Constants.CODE_SUCCESS) {
                                                    mView.showErrorMsg(objectBondMasterHttpResponse.getDesc());
                                                }
                                            }
                                        })
                                );

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                })
        );
    }
}
