package com.huake.bondmaster.presenter.main;

import com.huake.bondmaster.app.App;
import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.main.MyContract;
import com.huake.bondmaster.model.DataManager;

import javax.inject.Inject;

/**
 * @author will on 2017/8/25 11:24
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class MyPresenter extends RxPresenter<MyContract.View> implements MyContract.Presenter {
    private DataManager dataManager;

    @Inject
    public MyPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getUserInfo() {

    }

    @Override
    public void logout() {
        dataManager.setUserInstance(null);
        App.getInstance().setUserInstance(null);
    }


}
