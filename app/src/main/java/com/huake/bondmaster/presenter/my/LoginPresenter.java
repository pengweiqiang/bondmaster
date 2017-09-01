package com.huake.bondmaster.presenter.my;

import android.text.TextUtils;

import com.huake.bondmaster.app.App;
import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.user.LoginContract;
import com.huake.bondmaster.model.DataManager;
import com.huake.bondmaster.model.bean.UserBean;
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

public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {

    DataManager dataManager;

    @Inject
    public LoginPresenter(DataManager dataManager){
        this.dataManager = dataManager;
    }

    @Override
    public void login(final String mobile, final String password) {

        addSubscribe(dataManager.getLoginRsa()
                .compose(RxUtil.<BondMasterHttpResponse<String>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<String>(mView, true) {
                    @Override
                    public void dataHandler(String rsaStr) {
                        if(TextUtils.isEmpty(rsaStr)) {
                            mView.showErrorMsg("登陆失败");
                        }else{
                            //登陆
                            try {
                                rsaStr = rsaStr.replaceAll("\n","");
                                System.out.println(rsaStr);

                                RSAPublicKey publicKey = (RSAPublicKey)RSAUtils.getPublicKey(rsaStr);
                                //公钥加密密码后的字符串
                                String passwordRSa = RSAUtils.encrypt(password,publicKey);

                                addSubscribe(dataManager.login(mobile,passwordRSa)
                                        .compose(RxUtil.<BondMasterHttpResponse<UserBean>>rxSchedulerHelper())
                                        .subscribeWith(new CommonSubscriber<UserBean>(mView, true) {
                                            @Override
                                            public void dataHandler(UserBean userBean) {
                                                if(userBean!=null) {
                                                    dataManager.setUserInstance(userBean);
                                                    //获取个人信息
                                                    addSubscribe(dataManager.getUserInfo()
                                                            .compose(RxUtil.<BondMasterHttpResponse<UserBean>>rxSchedulerHelper())
                                                            .subscribeWith(new CommonSubscriber<UserBean>(mView, true) {
                                                                @Override
                                                                public void dataHandler(UserBean userBean) {
                                                                    if(userBean!=null) {
                                                                        userBean.setToken(App.getInstance().getUserBeanInstance().getToken());
                                                                        App.getInstance().setUserInstance(userBean);
                                                                        dataManager.setUserInstance(userBean);
                                                                    }
                                                                    mView.loginSuccess(userBean);
                                                                }

                                                            })
                                                    );

                                                }else{
                                                    mView.showErrorMsg("登陆失败");
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
    public void getLoginRsa() {
        addSubscribe(dataManager.getLoginRsa()
                .compose(RxUtil.<BondMasterHttpResponse<String>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<String>(mView, true) {
                    @Override
                    public void dataHandler(String rsaStr) {
                        if(TextUtils.isEmpty(rsaStr)) {
                            mView.showErrorMsg("登陆失败");
                        }else{
                            mView.setLoginRsa(rsaStr);
                        }
                    }

                })
        );
    }

    @Override
    public void loginByCode(String mobile, String code) {
        addSubscribe(dataManager.loginByCode(mobile,code)
                .compose(RxUtil.<BondMasterHttpResponse<UserBean>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<UserBean>(mView, true) {
                    @Override
                    public void dataHandler(UserBean userBean) {
                        if(userBean!=null) {
                            mView.loginSuccess(userBean);
                            dataManager.setUserInstance(userBean);
                        }else{
                            mView.showErrorMsg("登陆失败");
                        }
                    }

                })
        );
    }

    @Override
    public void sendVerificationCode(String mobile) {
//        addSubscribe(dataManager.sendVerificationCode(mobile)
//                .compose(RxUtil.<BondMasterHttpResponse>rxSchedulerHelper())
//                .subscribeWith(new CommonSubscriber<BondMasterHttpResponse>(mView, true) {
//                    @Override
//                    public void dataHandler(BondMasterHttpResponse bondMasterHttpResponse) {
//                        if(bondMasterHttpResponse!=null && bondMasterHttpResponse.getStat()) {
//
//                        }else{
//                            mView.showErrorMsg("登陆失败");
//                        }
//                    }
//
//                })
//        );
    }

    @Override
    public void getUserInfo() {
        addSubscribe(dataManager.getUserInfo()
                .compose(RxUtil.<BondMasterHttpResponse<UserBean>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<UserBean>(mView, true) {
                    @Override
                    public void dataHandler(UserBean userBean) {
                        if(userBean!=null) {
                            mView.loginSuccess(userBean);
                            dataManager.setUserInstance(userBean);
                        }else{
                            mView.showErrorMsg("登陆失败");
                        }
                    }

                })
        );
    }
}
