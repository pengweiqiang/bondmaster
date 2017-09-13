package com.huake.bondmaster.presenter.evaluation;

import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.evaluation.IndustryContract;
import com.huake.bondmaster.model.DataManager;
import com.huake.bondmaster.model.bean.EvaluationSuccessBean;
import com.huake.bondmaster.model.bean.IndustryBean;
import com.huake.bondmaster.model.http.response.BondMasterHttpResponse;
import com.huake.bondmaster.util.RxUtil;
import com.huake.bondmaster.widget.CommonSubscriber;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * @author will on 2017/8/26 11:30
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class IndustryPresenter extends RxPresenter<IndustryContract.View> implements IndustryContract.Presenter {
    private DataManager dataManager;

    @Inject
    public IndustryPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }



    @Override
    public void getIndustryList() {
        addSubscribe(dataManager.getIndustry()
                .compose(RxUtil.<BondMasterHttpResponse<List<IndustryBean>>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<List<IndustryBean>>(mView, true) {
                    @Override
                    public void dataHandler(List<IndustryBean> industryBeanList) {
                        mView.stateMain();
                        if(industryBeanList!=null && !industryBeanList.isEmpty()) {
                            mView.showContent(industryBeanList);
                        }else{
                            mView.showErrorMsg("获取结果为空");
                        }
                    }

                })
        );

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
}
