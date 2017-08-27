package com.huake.bondmaster.ui.my;

import android.content.Context;
import android.content.Intent;

import com.huake.bondmaster.R;
import com.huake.bondmaster.base.SimpleActivity;
import com.huake.bondmaster.widget.ActionBar;

/**
 * @author will on 2017/8/27 11:06
 * @email pengweiqiang64@163.com
 * @description 意见反馈
 * @Version
 */

public class FeedBackActivity extends SimpleActivity {

    @BindView(R.id.action_bar)
    ActionBar mActionBar;

    @Override
    protected int getLayout() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void initEventAndData() {
        mActionBar.setTitle(R.string.feed_back);
    }

    public static void open(Context context){
        Intent intent = new Intent(context,FeedBackActivity.class);
        context.startActivity(intent);
    }
}
