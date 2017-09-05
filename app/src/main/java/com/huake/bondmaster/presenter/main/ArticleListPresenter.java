package com.huake.bondmaster.presenter.main;

import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.main.ArticleListContract;
import com.huake.bondmaster.model.DataManager;
import com.huake.bondmaster.model.bean.ArticleBean;
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

public class ArticleListPresenter extends RxPresenter<ArticleListContract.View> implements ArticleListContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public ArticleListPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }



    @Override
    public void getArticleList(String userId, long pageNum, long pageSize, String mCategoryId) {
        addSubscribe(mDataManager.getArticleList(userId,pageNum, com.huake.bondmaster.app.Constants.PAGE_SIZE,mCategoryId)
                .compose(RxUtil.<BondMasterHttpResponse<PageBean<ArticleBean>>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<PageBean<ArticleBean>>(mView, pageNum<=1?true:false) {
                    @Override
                    public void dataHandler(PageBean<ArticleBean> pageBean) {
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
