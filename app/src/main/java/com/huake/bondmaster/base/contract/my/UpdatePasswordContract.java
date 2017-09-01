package com.huake.bondmaster.base.contract.my;

import com.huake.bondmaster.base.BasePresenter;
import com.huake.bondmaster.base.BaseView;

/**
 * @author will on 2017/8/28 10:08
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class UpdatePasswordContract {

    public interface View extends BaseView{
        void updateSuccess();

    }

    public interface Presenter extends BasePresenter<View>{
        void updatePassword(String currentPassword,String newPassword);
    }
}
