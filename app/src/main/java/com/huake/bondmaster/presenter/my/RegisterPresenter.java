package com.huake.bondmaster.presenter.my;

import android.text.TextUtils;

import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.user.RegisterContract;
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

public class RegisterPresenter extends RxPresenter<RegisterContract.View> implements RegisterContract.Presenter {

    DataManager dataManager;

    @Inject
    public RegisterPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void register(final String mobile, final String password, final String code) {

        addSubscribe(dataManager.getRegisterRsa()
                .compose(RxUtil.<BondMasterHttpResponse<String>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<String>(mView, true) {
                    @Override
                    public void dataHandler(String rsaStr) {
                        if (TextUtils.isEmpty(rsaStr)) {
                            mView.showErrorMsg("注册失败");
                        } else {
                            //登陆
                            try {
                                RSAPublicKey publicKey = (RSAPublicKey)RSAUtils.getPublicKey(rsaStr);
                                //公钥加密密码后的字符串
                                String passwordRSa = RSAUtils.encrypt(password,publicKey);
                                addSubscribe(dataManager.registerUser(mobile, passwordRSa, code)
                                        .compose(RxUtil.<BondMasterHttpResponse<Object>>rxSchedulerHelper())
                                        .subscribeWith(new CommonSubscriber<Object>(mView, true) {
                                            @Override
                                            public void dataHandler(Object object) {
                                                mView.stateMain();
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

    @Override
    public void sendVerificationCode(String mobile) {
        addSubscribe(dataManager.sendVerificationCode(mobile)
                .compose(RxUtil.<BondMasterHttpResponse<Object>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<Object>(mView, true) {
                    @Override
                    public void dataHandler(Object object) {
                        mView.stateMain();
                        mView.sendVerificationCodeSuccess();
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
}
