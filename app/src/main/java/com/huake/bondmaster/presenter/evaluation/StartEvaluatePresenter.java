package com.huake.bondmaster.presenter.evaluation;

import android.os.Bundle;

import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.evaluation.StartEvaluateContract;
import com.huake.bondmaster.model.DataManager;
import com.huake.bondmaster.model.bean.EvaluationSuccessBean;
import com.huake.bondmaster.model.http.response.BondMasterHttpResponse;
import com.huake.bondmaster.util.RxUtil;
import com.huake.bondmaster.widget.CommonSubscriber;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * @author will on 2017/8/26 11:30
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class StartEvaluatePresenter extends RxPresenter<StartEvaluateContract.View> implements StartEvaluateContract.Presenter {
    private DataManager dataManager;

    @Inject
    public StartEvaluatePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void startEvaluate(Map<String, String> params) {

        addSubscribe(dataManager.startEvaluate(params)
                .compose(RxUtil.<BondMasterHttpResponse<EvaluationSuccessBean>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<EvaluationSuccessBean>(mView, true) {
                    @Override
                    public void dataHandler(EvaluationSuccessBean evaluationSuccessBean) {
                        mView.stateMain();
                        mView.evaluateSuccess(evaluationSuccessBean);
                    }

                })
        );
    }

    @Override
    public Map<String, String> getParams(Bundle bundle) {
        Map<String,String> params = new HashMap<>();


        return params;
    }

}
