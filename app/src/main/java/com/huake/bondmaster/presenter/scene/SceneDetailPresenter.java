package com.huake.bondmaster.presenter.scene;

import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.scene.SceneDetailContract;
import com.huake.bondmaster.model.DataManager;
import com.huake.bondmaster.model.bean.EnterpriseInfo;
import com.huake.bondmaster.model.http.response.BondMasterHttpResponse;
import com.huake.bondmaster.util.RxUtil;
import com.huake.bondmaster.widget.CommonSubscriber;

import javax.inject.Inject;

/**
 * @author will on 2017/8/26 11:30
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class SceneDetailPresenter extends RxPresenter<SceneDetailContract.View> implements SceneDetailContract.Presenter {
    private DataManager dataManager;

    @Inject
    public SceneDetailPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getEnterpriseInfo(String userId, String dataDate,String trialCustId) {
        addSubscribe(dataManager.getEnterpriseInfo(userId, dataDate, trialCustId)
                .compose(RxUtil.<BondMasterHttpResponse<EnterpriseInfo>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<EnterpriseInfo>(mView, true) {
                    @Override
                    public void dataHandler(EnterpriseInfo enterpriseInfo) {
                        mView.stateMain();
                        if(enterpriseInfo!=null) {
                            mView.showContent(enterpriseInfo);
                        }else{
                            mView.showErrorMsg("获取结果为空");
                        }
                    }

                })
        );
    }
}
