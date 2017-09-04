package com.huake.bondmaster.base.contract.market;

import com.huake.bondmaster.base.BasePresenter;
import com.huake.bondmaster.base.BaseView;
import com.huake.bondmaster.model.bean.HkMarketOverviewDetailBean;

/**
 * @author will on 2017/8/25 11:23
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface HkMarketOverviewDetailContract {
    interface View extends BaseView{

        void showContent(HkMarketOverviewDetailBean hkMarketOverviewDetailBean);

        void initListener();

    }

    interface  Presenter extends BasePresenter<View>{
        void getDetail(String sInfoCompcode,String sInfoWindcode);
    }

}
