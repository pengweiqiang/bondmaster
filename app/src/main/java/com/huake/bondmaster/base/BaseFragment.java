package com.huake.bondmaster.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.huake.bondmaster.app.App;
import com.huake.bondmaster.di.component.DaggerFragmentComponent;
import com.huake.bondmaster.di.component.FragmentComponent;
import com.huake.bondmaster.di.module.FragmentModule;
import com.huake.bondmaster.model.bean.UserBean;
import com.huake.bondmaster.ui.my.LoginActivity;
import com.huake.bondmaster.util.ToastUtil;

import javax.inject.Inject;

/**
 * Created by pengweiqiang on 2016/8/2.
 * MVP Fragment基类
 */
public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView {

    @Inject
    protected T mPresenter;


    protected FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule(){
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) mPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void showErrorMsg(String msg) {
        ToastUtil.shortShow(msg);
//        SnackbarUtil.show(((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0), msg);
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }


    @Override
    public void startLoginActivity() {
        String mobile = "";
        try {
            UserBean userBean = App.getInstance().getUserBeanInstance();
            if(userBean!=null) {
                mobile = userBean.getMobile();
            }
            App.getAppComponent().getDataManager().setUserInstance(null);
            App.getInstance().setUserInstance(null);
        }catch (Exception e){

        }finally {
            LoginActivity.open(mContext, mobile);
        }
    }

    @Override
    public UserBean checkIsLogin() {
        return App.getInstance().getUserBeanInstance();
    }

    protected abstract void initInject();

    protected abstract void reconnectNetwork();
}