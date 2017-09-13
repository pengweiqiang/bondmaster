package com.huake.bondmaster.base.contract.evaluation;

import com.huake.bondmaster.base.BasePresenter;
import com.huake.bondmaster.base.BaseView;
import com.huake.bondmaster.model.bean.EvaluationSuccessBean;
import com.huake.bondmaster.model.bean.IndustryBean;

import java.util.List;
import java.util.Map;

/**
 * @author will on 2017/8/26 11:27
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface IndustryContract {

    interface View extends BaseView{
        void showContent(List<IndustryBean> industryBeanList);

        void evaluateSuccess(EvaluationSuccessBean evaluationSuccessBean);
    }

    interface Presenter extends BasePresenter<View>{
        void getIndustryList();

        void startEvaluate(Map<String,String> params);
    }

}
