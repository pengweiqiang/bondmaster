package com.huake.bondmaster.base.contract.main;

import com.huake.bondmaster.base.BasePresenter;
import com.huake.bondmaster.base.BaseView;

/**
 * @author will on 2017/8/25 11:23
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface MyContract {
    interface View extends BaseView{


        void showContent();
    }

    interface  Presenter extends BasePresenter<View>{
        void getUserInfo();

        void logout();
    }

}
