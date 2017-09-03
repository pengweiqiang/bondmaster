package com.huake.bondmaster.presenter.my;

import android.text.TextUtils;

import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.my.UpdatePasswordContract;
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

public class UpdatePasswordPresenter extends RxPresenter<UpdatePasswordContract.View> implements UpdatePasswordContract.Presenter {

    DataManager dataManager;

    @Inject
    public UpdatePasswordPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }



    @Override
    public void updatePassword(final String mobile,final String code,final String currentPassword,final String newPassword) {
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
                                RSAPublicKey publicKey = (RSAPublicKey) RSAUtils.getPublicKey(rsaStr);
                                //公钥加密密码后的字符串
                                String currentPwdStr = RSAUtils.encrypt(currentPassword,publicKey);

                                String newPwdStr = RSAUtils.encrypt(newPassword,publicKey);

                                addSubscribe(dataManager.modifyPwd(mobile, code, currentPwdStr, newPwdStr)
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
