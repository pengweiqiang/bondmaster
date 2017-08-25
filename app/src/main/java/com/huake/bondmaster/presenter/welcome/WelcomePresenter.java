package com.huake.bondmaster.presenter.welcome;


import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.welcome.WelcomeContract;
import com.huake.bondmaster.util.RxUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

;

/**
 * @author will on 2017/6/6 09:56
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class WelcomePresenter extends RxPresenter<WelcomeContract.View> implements WelcomeContract.Presenter {

    private static final int COUNT_DOWN_TIME = 2200;//欢迎界面等待时间

    @Inject
    public WelcomePresenter() {
    }

    @Override
    public void startMainActivity() {
        startCountDown();
    }

    @Override
    public void checkIsLogin() {
//        UserBean userBean = App.getInstance().getUserBeanInstance();
        //TODO 判断是否登录成功
        if(true){
            mView.jumpToLogin();
        }else {
            mView.jumpToHome();
        }
    }

    private void startCountDown() {
        addSubscribe(Flowable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
//                        checkIsLogin();
                        mView.jumpToHome();
                    }
                }));
    }



}
