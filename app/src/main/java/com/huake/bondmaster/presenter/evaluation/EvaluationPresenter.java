package com.huake.bondmaster.presenter.evaluation;

import android.content.Context;

import com.google.gson.Gson;
import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.evaluation.EvaluationContract;
import com.huake.bondmaster.model.DataManager;
import com.huake.bondmaster.model.bean.AreaNatureTypeBean;
import com.huake.bondmaster.model.bean.PartyBean;
import com.huake.bondmaster.model.http.response.BondMasterHttpResponse;
import com.huake.bondmaster.ui.evaluation.EvaluationInputFinanceInfoActivity;
import com.huake.bondmaster.util.LogUtil;
import com.huake.bondmaster.util.RxUtil;
import com.huake.bondmaster.widget.CommonSubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        addSubscribe(dataManager.getCompanyNameListByName(companyName)
                .compose(RxUtil.<BondMasterHttpResponse<PartyBean>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<PartyBean>(mView, true) {
                    @Override
                    public void dataHandler(PartyBean partyBean) {
                        mView.stateMain();
                        if(partyBean!=null && partyBean.getPartyList()!=null && !partyBean.getPartyList().isEmpty()) {
                            mView.setCompanyNameList(partyBean.getPartyList(),false);
                        }else{
                            List<PartyBean.PartyListBean> partyListBeanList = new ArrayList<PartyBean.PartyListBean>();
                            mView.setCompanyNameList(partyListBeanList,false);
                        }
                    }

                })
        );
    }

    @Override
    public void getCompanyNameListByUserId(String userId) {
        addSubscribe(dataManager.getCompanyNameListByUserId(userId)
                .compose(RxUtil.<BondMasterHttpResponse<PartyBean>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<PartyBean>(mView, true) {
                    @Override
                    public void dataHandler(PartyBean partyBean) {
                        mView.stateMain();
                        if(partyBean!=null && partyBean.getPartyList()!=null && !partyBean.getPartyList().isEmpty()) {
                            mView.setCompanyNameList(partyBean.getPartyList(),true);
                        }else{
                            List<PartyBean.PartyListBean> partyListBeanList = new ArrayList<PartyBean.PartyListBean>();
                            mView.setCompanyNameList(partyListBeanList,true);
                        }
                    }

                })
        );
    }

    @Override
    public void getAreaNatureTypeList(final String title) {
        addSubscribe(dataManager.getAreaNatureTypeList()
                .compose(RxUtil.<BondMasterHttpResponse<AreaNatureTypeBean>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<AreaNatureTypeBean>(mView, true) {
                    @Override
                    public void dataHandler(AreaNatureTypeBean areaNatureTypeBean) {
                        mView.stateMain();
                        if(areaNatureTypeBean!=null) {
                            mView.setAreaNatureType(areaNatureTypeBean,title);
                        }else{
                            mView.showErrorMsg("获取结果为空");
                        }
                    }

                })
        );
    }



    @Override
    public void startNext(Context context, Map<String,String> params, PartyBean.PartyListBean partyListBean) {
        LogUtil.i(new Gson().toJson(params).toString());
        EvaluationInputFinanceInfoActivity.open(context,params,partyListBean);
    }

    @Override
    public void getEnterpriseInfo(String companyName) {
        addSubscribe(dataManager.getEnterpriseInfoByName(companyName)
                .compose(RxUtil.<BondMasterHttpResponse<PartyBean.PartyListBean>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<PartyBean.PartyListBean>(mView, true) {
                    @Override
                    public void dataHandler(PartyBean.PartyListBean partyListBean) {
                        mView.stateMain();
                        mView.setEnterpriseInfo(partyListBean);
                    }

                })
        );
    }
}
