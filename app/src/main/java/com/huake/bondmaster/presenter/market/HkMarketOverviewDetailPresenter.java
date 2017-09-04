package com.huake.bondmaster.presenter.market;

import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.market.HkMarketOverviewDetailContract;
import com.huake.bondmaster.model.DataManager;
import com.huake.bondmaster.model.bean.HkMarketOverviewDetailBean;
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

public class HkMarketOverviewDetailPresenter extends RxPresenter<HkMarketOverviewDetailContract.View> implements HkMarketOverviewDetailContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public HkMarketOverviewDetailPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getDetail(String sInfoCompcode, String sInfoWindcode) {
        addSubscribe(mDataManager.getHkMarketOverviewDataCmpIdAndWindCode(sInfoCompcode,sInfoWindcode)
                .compose(RxUtil.<BondMasterHttpResponse<HkMarketOverviewDetailBean>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<HkMarketOverviewDetailBean>(mView, true) {
                    @Override
                    public void dataHandler(HkMarketOverviewDetailBean hkMarketOverviewDetailBean) {
                        if(hkMarketOverviewDetailBean!=null) {
                            mView.showContent(hkMarketOverviewDetailBean);
                        }else{
                            mView.showErrorMsg("获取结果为空");
                        }
                    }

                })
        );

    }
}
