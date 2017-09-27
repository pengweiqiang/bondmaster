package com.huake.bondmaster.presenter.scene;

import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.scene.FinaningPlanReportContract;
import com.huake.bondmaster.model.DataManager;

import javax.inject.Inject;

/**
 * @author will on 2017/8/26 11:30
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class FinaningPlanReportPresenter extends RxPresenter<FinaningPlanReportContract.View> implements FinaningPlanReportContract.Presenter {
    private DataManager dataManager;

    @Inject
    public FinaningPlanReportPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void getReportPdfUrl(String path) {
//        addSubscribe(dataManager.getFinancingPlanReportData(path)
//                .compose(RxUtil.<BondMasterHttpResponse<String>>rxSchedulerHelper())
//                .subscribeWith(new CommonSubscriber<String>(mView, true) {
//                    @Override
//                    public void dataHandler(String pdfUrl) {
//                        mView.stateMain();
//                        if(!TextUtils.isEmpty(pdfUrl)) {
//                            mView.showContent(pdfUrl);
//                        }else{
//                            mView.showErrorMsg("获取结果为空");
//                        }
//                    }
//
//                })
//        );
    }
}
