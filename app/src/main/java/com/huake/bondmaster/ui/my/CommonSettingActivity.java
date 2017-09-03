package com.huake.bondmaster.ui.my;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.App;
import com.huake.bondmaster.base.SimpleActivity;
import com.huake.bondmaster.model.DataManager;
import com.huake.bondmaster.model.bean.UserBean;
import com.huake.bondmaster.util.BigDecimalUtil;
import com.huake.bondmaster.util.ToastUtil;
import com.huake.bondmaster.widget.ActionBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/8/27 11:06
 * @email pengweiqiang64@163.com
 * @description 我的--通用设置
 * @Version
 */

public class CommonSettingActivity extends SimpleActivity {

    @BindView(R.id.action_bar)
    ActionBar mActionBar;
    @BindView(R.id.tv_mobile)
    TextView mTvMobile;

    @Override
    protected int getLayout() {
        return R.layout.activity_common_setting;
    }


    @Override
    protected void initEventAndData() {
        mActionBar.setTitle(R.string.tab_my);

        UserBean userBean = App.getInstance().getUserBeanInstance();
        mTvMobile.setText(BigDecimalUtil.getsubMobileString(userBean.getMobile()));
    }


    @OnClick({R.id.tv_change_mobile,R.id.tv_password_manage,R.id.btn_logout})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_change_mobile:
                ChangeMobileActivity.open(mContext);
                break;
            case R.id.tv_password_manage:
                UpdatePasswordActivity.open(mContext);
                break;
            case R.id.btn_logout:
                logout();
                break;
        }

    }

    private void logout(){
        DataManager dataManager = App.getAppComponent().getDataManager();
        UserBean userBean = App.getInstance().getUserBeanInstance();
        String mobile =userBean.getMobile();
        dataManager.setUserInstance(null);
        App.getInstance().setUserInstance(null);
        ToastUtil.shortShow("退出登录");
        LoginActivity.open(mContext,mobile);
        finish();
    }

    public static void open(Context context){
        Intent intent = new Intent(context,CommonSettingActivity.class);
        context.startActivity(intent);
    }
}
