package com.huake.bondmaster.base.contract.user;

import com.huake.bondmaster.base.BasePresenter;
import com.huake.bondmaster.base.BaseView;
import com.huake.bondmaster.model.bean.UserBean;

/**
 * @author will on 2017/8/28 10:08
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class LoginContract {

    public interface View extends BaseView{
        void loginSuccess(UserBean userBean);

        void sendVerificationCodeSuccess();

    }

    public interface Presenter extends BasePresenter<View>{
        void login(String mobile,String password);


        void loginByCode(String mobile,String code);

        void sendVerificationCode(String mobile);
    }
}
