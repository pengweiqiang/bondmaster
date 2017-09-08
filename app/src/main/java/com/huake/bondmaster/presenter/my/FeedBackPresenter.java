package com.huake.bondmaster.presenter.my;

import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.my.FeedBackContract;
import com.huake.bondmaster.model.DataManager;
import com.huake.bondmaster.model.http.response.BondMasterHttpResponse;
import com.huake.bondmaster.util.RxUtil;
import com.huake.bondmaster.widget.CommonSubscriber;

import javax.inject.Inject;

/**
 * @author will on 2017/8/28 10:10
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class FeedBackPresenter extends RxPresenter<FeedBackContract.View> implements FeedBackContract.Presenter {

    DataManager dataManager;

    @Inject
    public FeedBackPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }



    @Override
    public void saveFeedBack(String content, String email, String images) {
        addSubscribe(dataManager.saveFeedBack(content,email,images)
                .compose(RxUtil.<BondMasterHttpResponse<Object>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<Object>(mView, true) {
                    @Override
                    public void dataHandler(Object object) {
                        mView.stateMain();
                        mView.saveSuccess();
                    }

                    @Override
                    public void onNext(BondMasterHttpResponse<Object> objectBondMasterHttpResponse) {
                        super.onNext(objectBondMasterHttpResponse);
                        if (objectBondMasterHttpResponse != null) {
                            if(objectBondMasterHttpResponse.getStat() == Constants.CODE_SUCCESS) {
                                mView.showErrorMsg("提交成功");
                            }else{
                                mView.showErrorMsg(objectBondMasterHttpResponse.getDesc());
                            }
                        }
                    }
                })
        );
    }
}
