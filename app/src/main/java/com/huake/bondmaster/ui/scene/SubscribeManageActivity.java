package com.huake.bondmaster.ui.scene;

import android.content.Context;
import android.content.Intent;

import com.huake.bondmaster.R;
import com.huake.bondmaster.base.SimpleActivity;
import com.huake.bondmaster.widget.ActionBar;

import butterknife.BindView;

/**
 * @author will on 2017/9/5 11:32
 * @email pengweiqiang64@163.com
 * @description 订阅管理
 * @Version
 */

public class SubscribeManageActivity extends SimpleActivity{

    @BindView(R.id.action_bar)
    ActionBar mActionBar;
    @Override
    protected int getLayout() {
        return R.layout.activity_subscirbe_manage;
    }

    @Override
    protected void initEventAndData() {
        mActionBar.setTitle("订阅管理");
    }

    public static void open(Context context){
        Intent intent = new Intent(context,SubscribeManageActivity.class);
        context.startActivity(intent);
    }
}
