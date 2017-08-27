package com.huake.bondmaster.presenter.home;

import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.main.HomeContract;
import com.huake.bondmaster.model.DataManager;
import com.huake.bondmaster.model.bean.HomePageBean;
import com.huake.bondmaster.model.http.response.BondMasterHttpResponse;
import com.huake.bondmaster.util.RxUtil;
import com.huake.bondmaster.widget.CommonSubscriber;

import javax.inject.Inject;

/**
 * @author will on 2017/8/26 11:30
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class HomePresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter {
    private DataManager dataManager;

    @Inject
    public HomePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getHomePage() {
        addSubscribe(dataManager.homePage()
                .compose(RxUtil.<BondMasterHttpResponse<HomePageBean>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<HomePageBean>(mView, true) {
                    @Override
                    public void dataHandler(HomePageBean homePageBean) {
                        if(homePageBean!=null) {
                            mView.showContent(homePageBean);
                        }else{
                            mView.showErrorMsg("获取结果为空");
                        }
                    }

                })
        );
    }
}
