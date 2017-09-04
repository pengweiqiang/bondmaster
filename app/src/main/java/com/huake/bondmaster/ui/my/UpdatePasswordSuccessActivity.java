package com.huake.bondmaster.ui.my;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.SimpleActivity;
import com.huake.bondmaster.util.TextViewUtils;
import com.huake.bondmaster.widget.ActionBar;

import java.util.Timer;
import java.util.TimerTask;

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
    @BindView(R.id.tv_time_tips)
    TextView mTvTimeTips;


    private String mobile = "";


    @Override
    protected int getLayout() {
        return R.layout.activity_update_password_success;
    }

    @Override
    protected void initEventAndData() {
        mActionBar.setTitle("");

        mobile = getIntent().getStringExtra(Constants.MOBILE);

        startCountTime();
    }


    @OnClick(R.id.tv_back_to_login)
    public void onclick(View view){
        LoginActivity.open(mContext,mobile);
    }

    public static void open(Context context,String mobile){
        Intent intent = new Intent(context,UpdatePasswordSuccessActivity.class);
        context.startActivity(intent);
    }

    private Timer timer;// 计时器
    private int time = 3;//倒计时120秒

    private void startCountTime() {
        time = 3;
        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                if(handler!=null) {
                    handler.sendEmptyMessage(time--);
                }
            }
        }, 0, 1000);
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                LoginActivity.open(mContext,mobile);
                timer.cancel();
            } else {
                String tips = "系统将在2秒后自动跳转至登录页面";
                mTvTimeTips.setText(TextViewUtils.getSpannableStringColor(tips,4,5, Color.RED));
            }

        };
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer!=null){
            timer.cancel();
            timer = null;
        }
        if(handler!=null){
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }
}
