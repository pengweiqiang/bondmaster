package com.huake.bondmaster.presenter.my;

import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.my.UpdatePasswordContract;
import com.huake.bondmaster.model.DataManager;

import javax.inject.Inject;

/**
 * @author will on 2017/8/28 10:10
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class UpdatePasswordPresenter extends RxPresenter<UpdatePasswordContract.View> implements UpdatePasswordContract.Presenter {

    DataManager dataManager;

    @Inject
    public UpdatePasswordPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }



    @Override
    public void updatePassword(String currentPassword, String newPassword) {
//        addSubscribe(dataManager.up
//                .compose(RxUtil.<BondMasterHttpResponse<Object>>rxSchedulerHelper())
//                .subscribeWith(new CommonSubscriber<Object>(mView, true) {
//                    @Override
//                    public void dataHandler(Object object) {
//                        mView.stateMain();
//                        mView.sendVerificationCodeSuccess();
//                    }
//
//                    @Override
//                    public void onNext(BondMasterHttpResponse<Object> objectBondMasterHttpResponse) {
//                        super.onNext(objectBondMasterHttpResponse);
//                        if (objectBondMasterHttpResponse != null && objectBondMasterHttpResponse.getStat() == Constants.CODE_SUCCESS) {
//                            mView.showErrorMsg(objectBondMasterHttpResponse.getDesc());
//                        }
//                    }
//                })
//        );
    }
}
