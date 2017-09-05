package com.huake.bondmaster.presenter.home;

import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.main.SearchTrialCustInfoContract;
import com.huake.bondmaster.model.DataManager;
import com.huake.bondmaster.model.bean.PageBean;
import com.huake.bondmaster.model.bean.SearchBean;
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

public class SearchTrialCustInfoPresenter extends RxPresenter<SearchTrialCustInfoContract.View> implements SearchTrialCustInfoContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public SearchTrialCustInfoPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void getSceneList(long pageNum, String sInfoCustname) {

        addSubscribe(mDataManager.searchSceneList(pageNum, com.huake.bondmaster.app.Constants.PAGE_SIZE,sInfoCustname)
                .compose(RxUtil.<BondMasterHttpResponse<PageBean<SearchBean>>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<PageBean<SearchBean>>(mView, pageNum<=1?true:false) {
                    @Override
                    public void dataHandler(PageBean<SearchBean> pageBean) {
                        if(pageBean!=null) {
                            mView.showContent(pageBean.getRecords(),pageBean.getPageNum(),pageBean.getTotal(),pageBean.getRows());
                        }else{
                            mView.showErrorMsg("获取结果为空");
                        }
                    }

                })
        );

    }

}
