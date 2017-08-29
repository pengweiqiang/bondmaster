package com.huake.bondmaster.base.contract.evaluation;

import com.huake.bondmaster.base.BasePresenter;
import com.huake.bondmaster.base.BaseView;

/**
 * @author will on 2017/8/26 11:27
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface EvaluationContract {

    interface View extends BaseView{
        void showContent();
    }

    interface Presenter extends BasePresenter<View>{
        void searchCompanyByName(String companyName);
    }

}
