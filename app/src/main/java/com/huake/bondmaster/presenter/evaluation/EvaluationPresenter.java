package com.huake.bondmaster.presenter.evaluation;

import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.evaluation.EvaluationContract;
import com.huake.bondmaster.model.DataManager;

import javax.inject.Inject;

/**
 * @author will on 2017/8/26 11:30
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class EvaluationPresenter extends RxPresenter<EvaluationContract.View> implements EvaluationContract.Presenter {
    private DataManager dataManager;

    @Inject
    public EvaluationPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void searchCompanyByName(String companyName) {

    }
}
