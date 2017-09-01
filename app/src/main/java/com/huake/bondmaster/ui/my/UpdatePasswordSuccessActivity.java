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
 * @description 修改密码成功
 * @Version
 */

public class UpdatePasswordSuccessActivity extends SimpleActivity {

    @BindView(R.id.action_bar)
    ActionBar mActionBar;


    @Override
    protected int getLayout() {
        return R.layout.activity_update_password_success;
    }

    @Override
    protected void initEventAndData() {
        mActionBar.setTitle("");
    }


    @OnClick(R.id.tv_back_to_login)
    public void onclick(View view){
        LoginActivity.open(mContext,"");
    }

    public static void open(Context context){
        Intent intent = new Intent(context,UpdatePasswordSuccessActivity.class);
        context.startActivity(intent);
    }
}
