package com.huake.bondmaster.ui.main.fragment;

import android.view.View;

import com.huake.bondmaster.R;
import com.huake.bondmaster.base.SimpleFragment;
import com.huake.bondmaster.ui.my.LoginActivity;
import com.huake.bondmaster.ui.my.RegisterActivity;

import butterknife.OnClick;

/**
 * @author will on 2017/8/24 13:42
 * @email pengweiqiang64@163.com
 * @description 我的-->未登录
 * @Version
 */

public class MyUnLoginFragment extends SimpleFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_login;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }



    @OnClick({R.id.btn_register,R.id.btn_login})
    public void onClickOptionItem(View view){
        switch (view.getId()){
            case R.id.btn_register:
                RegisterActivity.open(mContext);
                break;
            case R.id.btn_login:
                LoginActivity.open(mContext,"");
                break;
        }
    }

}
