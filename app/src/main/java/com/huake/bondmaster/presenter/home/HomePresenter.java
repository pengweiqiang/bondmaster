package com.huake.bondmaster.presenter.home;

import com.google.gson.Gson;
import com.huake.bondmaster.app.Constants;
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
    public void getHomePage(long pageNum) {
        addSubscribe(dataManager.homePage(Constants.PARAM_SCALE,pageNum,Constants.PAGE_SIZE)
                .compose(RxUtil.<BondMasterHttpResponse<HomePageBean>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<HomePageBean>(mView, pageNum<=1?true:false) {
                    @Override
                    public void dataHandler(HomePageBean homePageBean) {
                        mView.stateMain();
                        if(homePageBean!=null) {
                            mView.showContent(homePageBean);

                            dataManager.setHomePageCache(new Gson().toJson(homePageBean));
                        }else{
                            mView.showErrorMsg("获取结果为空");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.stateMain();
                    }
                })
        );
    }

    @Override
    public void search(String searchText) {

    }

    @Override
    public void getHomePageCache() {
        HomePageBean homePageBean = dataManager.getHomePageCache();
        if(homePageBean!=null){
            mView.showContent(homePageBean);
        }
    }
}
