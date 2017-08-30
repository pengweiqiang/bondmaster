package com.huake.bondmaster.presenter.evaluation;

import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.evaluation.EvaluationContract;
import com.huake.bondmaster.model.DataManager;
import com.huake.bondmaster.model.bean.AreaNatureTypeBean;
import com.huake.bondmaster.model.bean.PartyBean;
import com.huake.bondmaster.model.http.response.BondMasterHttpResponse;
import com.huake.bondmaster.util.RxUtil;
import com.huake.bondmaster.widget.CommonSubscriber;

import java.util.List;

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

    @Override
    public void getAreaNatureTypeList() {
        addSubscribe(dataManager.getAreaNatureTypeList()
                .compose(RxUtil.<BondMasterHttpResponse<AreaNatureTypeBean>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<AreaNatureTypeBean>(mView, true) {
                    @Override
                    public void dataHandler(AreaNatureTypeBean areaNatureTypeBean) {
                        mView.stateMain();
                        if(areaNatureTypeBean!=null) {
                            mView.setAreaNatureType(areaNatureTypeBean);
                        }else{
                            mView.showErrorMsg("获取结果为空");
                        }
                    }

                })
        );
    }

    @Override
    public void getCompanyNameList(String userId) {
        addSubscribe(dataManager.getCompanyNameListByUserId(userId)
                .compose(RxUtil.<BondMasterHttpResponse<List<PartyBean>>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<List<PartyBean>>(mView, true) {
                    @Override
                    public void dataHandler(List<PartyBean> partyBeanList) {
                        mView.stateMain();
                        if(partyBeanList!=null) {
                            mView.setCompanyNameList(partyBeanList);
                        }else{
                            mView.showErrorMsg("获取结果为空");
                        }
                    }

                })
        );
    }
}
