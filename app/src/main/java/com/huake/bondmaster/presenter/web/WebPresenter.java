package com.huake.bondmaster.presenter.web;

import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.web.WebContract;
import com.huake.bondmaster.model.DataManager;

import javax.inject.Inject;

/**
 * @author will on 2017/8/26 11:30
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class WebPresenter extends RxPresenter<WebContract.View> implements WebContract.Presenter {
    private DataManager dataManager;

    @Inject
    public WebPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void getUrl() {

    }
}
