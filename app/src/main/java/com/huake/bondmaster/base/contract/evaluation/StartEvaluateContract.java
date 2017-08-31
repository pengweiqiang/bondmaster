package com.huake.bondmaster.base.contract.evaluation;

import android.os.Bundle;

import com.huake.bondmaster.base.BasePresenter;
import com.huake.bondmaster.base.BaseView;
import com.huake.bondmaster.model.bean.EvaluationSuccessBean;

import java.util.Map;

/**
 * @author will on 2017/8/31 14:08
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface StartEvaluateContract {
    interface View extends BaseView {
        void evaluateSuccess(EvaluationSuccessBean evaluationSuccessBean);
    }

    interface Presenter extends BasePresenter<StartEvaluateContract.View> {
        void startEvaluate(Map<String,String> params);

        Map<String,String> getParams(Bundle bundle);
    }
}
