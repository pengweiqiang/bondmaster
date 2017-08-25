package com.huake.bondmaster.base.contract.main;

import com.huake.bondmaster.base.BasePresenter;
import com.huake.bondmaster.base.BaseView;

/**
 * @author will on 2017/8/24 21:23
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface MainContract {

    interface  View extends BaseView {
        void initView();

        void initListener();
    }

    interface  Presenter extends BasePresenter<MainContract.View> {
        void init();
    }

}
