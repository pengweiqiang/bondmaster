package com.huake.bondmaster.presenter.main;

import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.main.SceneContract;
import com.huake.bondmaster.model.DataManager;
import com.huake.bondmaster.model.bean.PageBean;
import com.huake.bondmaster.model.bean.SceneBean;
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

public class ScenePresenter extends RxPresenter<SceneContract.View> implements SceneContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public ScenePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void getSceneList(String userId, final long pageNum, String sInfoCustname, String secIndCode, String bAgencyGuarantornature, String bInfoCreditrating) {

//        addSubscribe(mDataManager.searchSceneList(pageNum, com.huake.bondmaster.app.Constants.PAGE_SIZE,sInfoCustname)
//                .compose(RxUtil.<BondMasterHttpResponse<PageBean<SearchBean>>>rxSchedulerHelper())
//                .subscribeWith(new CommonSubscriber<PageBean<SearchBean>>(mView, true) {
//                    @Override
//                    public void dataHandler(PageBean<SearchBean> pageBean) {
//                        if(pageBean!=null) {
//                            mView.showContent(pageBean.getRecords(),pageBean.getPageNum(),pageBean.getTotal(),pageBean.getRows());
//                        }else{
//                            mView.showErrorMsg("获取结果为空");
//                        }
//                    }
//
//                })
//        );

        addSubscribe(mDataManager.getScenceList(userId,pageNum, com.huake.bondmaster.app.Constants.PAGE_SIZE,sInfoCustname,secIndCode,bAgencyGuarantornature,bInfoCreditrating)
                .compose(RxUtil.<BondMasterHttpResponse<PageBean<SceneBean>>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<PageBean<SceneBean>>(mView, pageNum<=1?true:false) {
                    @Override
                    public void dataHandler(PageBean<SceneBean> pageBean) {
                        mView.stateMain();
                        if(pageBean!=null) {
                            mView.showContent(pageBean.getRecords(),pageBean.getPageNum()==0?1:pageBean.getPageNum(),pageBean.getTotal(),pageBean.getRows());
                        } else{
                            mView.showEmptyMsg("获取结果为空");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.stateError();
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
