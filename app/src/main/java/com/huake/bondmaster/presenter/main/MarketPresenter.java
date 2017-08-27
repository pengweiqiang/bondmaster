package com.huake.bondmaster.presenter.main;

import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.main.MarketContract;

import javax.inject.Inject;

/**
 * @author will on 2017/8/25 11:24
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class MarketPresenter extends RxPresenter<MarketContract.View> implements MarketContract.Presenter {


    @Inject
    public MarketPresenter() {

    }


    @Override
    public void getDataList() {

    }
}
