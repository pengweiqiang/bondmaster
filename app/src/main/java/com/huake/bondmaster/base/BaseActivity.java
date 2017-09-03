package com.huake.bondmaster.base;

import android.support.v7.app.AppCompatDelegate;

import com.huake.bondmaster.app.App;
import com.huake.bondmaster.di.component.ActivityComponent;
import com.huake.bondmaster.di.component.DaggerActivityComponent;
import com.huake.bondmaster.di.module.ActivityModule;
import com.huake.bondmaster.model.bean.UserBean;
import com.huake.bondmaster.ui.my.LoginActivity;
import com.huake.bondmaster.util.ToastUtil;

import javax.inject.Inject;

/**
 * Created by pengweiqiang on 2016/8/2.
 * MVP activity基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity implements BaseView {

    @Inject
    protected T mPresenter;


    protected ActivityComponent getActivityComponent(){
        return  DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showErrorMsg(String msg) {
        ToastUtil.shortShow(msg);
//        SnackbarUtil.show(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), msg);
    }

    @Override
    public void useNightMode(boolean isNight) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    @Override
    public void stateError() {
        cancelDialogLoading();
    }

    @Override
    public void stateLoading() {
    }

    @Override
    public void stateMain() {
        cancelDialogLoading();
    }


    @Override
    public void startLoginActivity() {
        UserBean userBean = App.getInstance().getUserBeanInstance();
        String mobile = "";
        if(userBean!=null) {
            mobile = userBean.getMobile();
        }
        LoginActivity.open(mContext, mobile);
    }

    @Override
    public UserBean checkIsLogin() {
        return App.getInstance().getUserBeanInstance();
    }

    protected abstract void initInject();

    protected void reconnectNetwork(){}
}