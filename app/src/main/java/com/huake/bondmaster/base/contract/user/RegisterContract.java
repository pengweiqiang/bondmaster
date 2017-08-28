package com.huake.bondmaster.base.contract.user;

import com.huake.bondmaster.base.BasePresenter;
import com.huake.bondmaster.base.BaseView;

/**
 * @author will on 2017/8/28 10:08
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class RegisterContract {

    public interface View extends BaseView{
        void registerSuccess();

        void sendVerificationCodeSuccess();

    }

    public interface Presenter extends BasePresenter<View>{
        void register(String mobile, String password,String code);
        void sendVerificationCode(String mobile);
    }
}
