package com.huake.bondmaster.presenter.my;

import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.user.LoginContract;
import com.huake.bondmaster.model.DataManager;
import com.huake.bondmaster.model.bean.UserBean;
import com.huake.bondmaster.model.http.response.BondMasterHttpResponse;
import com.huake.bondmaster.util.RxUtil;
import com.huake.bondmaster.widget.CommonSubscriber;

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
    public void login(String mobile, String password) {
        addSubscribe(dataManager.login(mobile,password)
                .compose(RxUtil.<BondMasterHttpResponse<UserBean>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<UserBean>(mView, true) {
                    @Override
                    public void dataHandler(UserBean userBean) {
                        if(userBean!=null) {
                            dataManager.setUserInstance(userBean);
                            mView.loginSuccess(userBean);
                        }else{
                            mView.showErrorMsg("登陆失败");
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
}
