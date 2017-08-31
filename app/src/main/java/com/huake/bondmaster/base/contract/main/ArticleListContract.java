package com.huake.bondmaster.base.contract.main;

import com.huake.bondmaster.base.BasePresenter;
import com.huake.bondmaster.base.BaseView;
import com.huake.bondmaster.model.bean.ArticleBean;

import java.util.List;

/**
 * @author will on 2017/8/25 11:23
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface ArticleListContract {
    interface View extends BaseView{


        void showContent(long records, long pageNum, long total, List<ArticleBean> articleBeanList);

        void initListener();
    }

    interface  Presenter extends BasePresenter<View>{
        void getArticleList(String userId, long pageNum, long pageSize,String mCategoryId);
    }

}
