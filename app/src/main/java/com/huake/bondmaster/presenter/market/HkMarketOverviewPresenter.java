package com.huake.bondmaster.presenter.market;

import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.market.HkMarketOverviewContract;
import com.huake.bondmaster.model.DataManager;
import com.huake.bondmaster.model.bean.HkMarketOverviewBean;
import com.huake.bondmaster.model.bean.PageBean;
import com.huake.bondmaster.model.http.response.BondMasterHttpResponse;
import com.huake.bondmaster.util.RxUtil;
import com.huake.bondmaster.widget.CommonSubscriber;

import javax.inject.Inject;

/**
 * @author will on 2017/8/25 11:24
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class HkMarketOverviewPresenter extends RxPresenter<HkMarketOverviewContract.View> implements HkMarketOverviewContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public HkMarketOverviewPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void getLists(String sInfoIndustrycode1, long pageNum, long pageSize) {
        addSubscribe(mDataManager.findHkMarketOverviewDataPage(sInfoIndustrycode1,pageNum,pageSize)
                .compose(RxUtil.<BondMasterHttpResponse<PageBean<HkMarketOverviewBean>>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<PageBean<HkMarketOverviewBean>>(mView, true) {
                    @Override
                    public void dataHandler(PageBean<HkMarketOverviewBean> pageBean) {
                        mView.stateMain();
                        if(pageBean!=null) {
                            mView.showContent(pageBean.getRecords(),pageBean.getPageNum()==0?1:pageBean.getPageNum(),pageBean.getTotal(),pageBean.getRows());
                        }else{
                            mView.showErrorMsg("获取结果为空");
                        }
                    }

                })
        );
    }
}
