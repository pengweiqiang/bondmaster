package com.huake.bondmaster.presenter.subscribe;

import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.subscribe.SubscribeContract;
import com.huake.bondmaster.model.DataManager;
import com.huake.bondmaster.model.bean.SubscribeBean;
import com.huake.bondmaster.model.http.response.BondMasterHttpResponse;
import com.huake.bondmaster.util.RxUtil;
import com.huake.bondmaster.widget.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

/**
 * @author will on 2017/8/26 11:30
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class SubscribePresenter extends RxPresenter<SubscribeContract.View> implements SubscribeContract.Presenter {
    private DataManager dataManager;

    @Inject
    public SubscribePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void getSubscirbeList(String userId) {
        addSubscribe(dataManager.getSubscribeList(userId)
                .compose(RxUtil.<BondMasterHttpResponse<List<SubscribeBean>>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<List<SubscribeBean>>(mView, true) {
                    @Override
                    public void dataHandler(List<SubscribeBean> subscribeBeanList) {
                        mView.stateMain();
                        mView.showContent(subscribeBeanList);
                    }

                    @Override
                    public void onError(String msg) {
                        super.onError(msg);
                        mView.stateError();
                    }

                })
        );
    }
}
