package com.huake.bondmaster.ui.my;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.huake.bondmaster.R;
import com.huake.bondmaster.base.SimpleActivity;
import com.huake.bondmaster.widget.ActionBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/8/27 11:06
 * @email pengweiqiang64@163.com
 * @description 密码管理
 * @Version
 */

public class PasswordManageActivity extends SimpleActivity {

    @BindView(R.id.action_bar)
    ActionBar mActionBar;

    @Override
    protected int getLayout() {
        return R.layout.activity_password_manage;
    }


    @Override
    protected void initEventAndData() {
        mActionBar.setTitle(R.string.password_manage);
    }


    @OnClick(R.id.tv_update_password)
    public void onClick(View view){
        UpdatePasswordActivity.open(mContext);
    }

    public static void open(Context context){
        Intent intent = new Intent(context,PasswordManageActivity.class);
        context.startActivity(intent);
    }
}
