package com.huake.bondmaster.ui.my;

import android.content.Context;
import android.content.Intent;

import com.huake.bondmaster.R;
import com.huake.bondmaster.base.SimpleActivity;
import com.huake.bondmaster.widget.ActionBar;

/**
 * @author will on 2017/8/27 11:06
 * @email pengweiqiang64@163.com
 * @description 关于我们
 * @Version
 */

public class AboutUsActivity extends SimpleActivity {

    @BindView(R.id.action_bar)
    ActionBar mActionBar;

    @Override
    protected int getLayout() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initEventAndData() {
        mActionBar.setTitle(R.string.about_us);
    }

    public static void open(Context context){
        Intent intent = new Intent(context,AboutUsActivity.class);
        context.startActivity(intent);
    }
}
