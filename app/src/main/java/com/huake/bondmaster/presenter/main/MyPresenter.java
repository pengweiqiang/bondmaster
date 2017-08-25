package com.huake.bondmaster.presenter.main;

import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.main.MyContract;

import javax.inject.Inject;

/**
 * @author will on 2017/8/25 11:24
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class MyPresenter extends RxPresenter<MyContract.View> implements MyContract.Presenter {


    @Inject
    public MyPresenter() {

    }

    @Override
    public void getUserInfo() {

    }
}
