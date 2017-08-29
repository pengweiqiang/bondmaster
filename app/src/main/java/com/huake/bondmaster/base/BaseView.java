package com.huake.bondmaster.base;

import com.huake.bondmaster.model.bean.UserBean;

/**
 * Created by pengweiqiang on 2016/8/2.
 * View基类
 */
public interface BaseView {

    void showErrorMsg(String msg);

    void useNightMode(boolean isNight);

    //=======  State  =======
    void stateError();

    void stateLoading();

    void stateMain();

    void showLoading(String msg);

    void cancelDialogLoading();

    void startLoginActivity();

    UserBean checkIsLogin();
}
