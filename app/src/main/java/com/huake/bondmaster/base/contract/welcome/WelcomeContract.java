package com.huake.bondmaster.base.contract.welcome;


import com.huake.bondmaster.base.BasePresenter;
import com.huake.bondmaster.base.BaseView;

/**
 * @author will on 2017/5/4 09:52
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface WelcomeContract {

    interface  View extends BaseView {
//        void showContent();


        void jumpToLogin();

        void jumpToHome();
    }

    interface  Presenter extends BasePresenter<View> {
//        void getWelcomeData();
        void startMainActivity();
        void checkIsLogin();
    }

}
