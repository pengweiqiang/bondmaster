package com.huake.bondmaster.presenter.my;

import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.user.RegisterContract;
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

public class RegisterPresenter extends RxPresenter<RegisterContract.View> implements RegisterContract.Presenter {

    DataManager dataManager;

    @Inject
    public RegisterPresenter(DataManager dataManager){
        this.dataManager = dataManager;
    }


    @Override
    public void register(String mobile, String password, String code) {
        addSubscribe(dataManager.registerUser(mobile,password,code)
                .compose(RxUtil.<BondMasterHttpResponse<Object>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<Object>(mView, true) {
                    @Override
                    public void dataHandler(Object object) {
                        mView.stateMain();
                    }

                    @Override
                    public void onNext(BondMasterHttpResponse<Object> objectBondMasterHttpResponse) {
                        super.onNext(objectBondMasterHttpResponse);
                        if(objectBondMasterHttpResponse != null && objectBondMasterHttpResponse.getStat() == Constants.CODE_SUCCESS){
                            mView.showErrorMsg(objectBondMasterHttpResponse.getDesc());
                        }
                    }
                })
        );
    }

    @Override
    public void sendVerificationCode(String mobile) {
        addSubscribe(dataManager.sendVerificationCode(mobile)
                .compose(RxUtil.<BondMasterHttpResponse<Object>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<Object>(mView, true) {
                    @Override
                    public void dataHandler(Object object) {
                        mView.stateMain();
                        mView.sendVerificationCodeSuccess();
                    }

                    @Override
                    public void onNext(BondMasterHttpResponse<Object> objectBondMasterHttpResponse) {
                        super.onNext(objectBondMasterHttpResponse);
                        if(objectBondMasterHttpResponse != null && objectBondMasterHttpResponse.getStat() == Constants.CODE_SUCCESS){
                            mView.showErrorMsg(objectBondMasterHttpResponse.getDesc());
                        }
                    }
                })
        );
    }
}
