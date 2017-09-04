package com.huake.bondmaster.base.contract.market;

import com.huake.bondmaster.base.BasePresenter;
import com.huake.bondmaster.base.BaseView;
import com.huake.bondmaster.model.bean.HkMarketOverviewBean;

import java.util.List;

/**
 * @author will on 2017/8/25 11:23
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface HkMarketOverviewContract {
    interface View extends BaseView{

        void showContent(long records, long pageNum, long total, List<HkMarketOverviewBean> hkMarketOverviewBeanList);

        void initListener();

    }

    interface  Presenter extends BasePresenter<View>{
        void getLists(String sInfoIndustrycode1, long pageNum,long pageSize);
    }

}
