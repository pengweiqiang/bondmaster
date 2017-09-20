package com.huake.bondmaster.base.contract.subscribe;

import com.huake.bondmaster.base.BasePresenter;
import com.huake.bondmaster.base.BaseView;
import com.huake.bondmaster.model.bean.SubscribeBean;

import java.util.List;

/**
 * @author will on 2017/8/26 11:27
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface SubscribeContract {

    interface View extends BaseView{
        void showContent(List<SubscribeBean> subscribeBeanList);
    }

    interface Presenter extends BasePresenter<View>{
        void getSubscirbeList(String userId);
    }

}
