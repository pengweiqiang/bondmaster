package com.huake.bondmaster.ui.main.activity;

import android.widget.ImageView;
import android.widget.TextView;

import com.huake.bondmaster.R;
import com.huake.bondmaster.base.BaseActivity;
import com.huake.bondmaster.base.contract.welcome.WelcomeContract;
import com.huake.bondmaster.presenter.welcome.WelcomePresenter;

import butterknife.BindView;

/**
 * Created by pengweiqiang on 16/8/15.
 */

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeContract.View {

    @BindView(R.id.iv_welcome_bg)
    ImageView ivWelcomeBg;
    @BindView(R.id.tv_welcome_desc)
    TextView tvWelcomeAuthor;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initEventAndData() {
//        mPresenter.getWelcomeData();
        mPresenter.startMainActivity();
        tvWelcomeAuthor.setText("");
    }

//    @Override
//    public void showContent(WelcomeBean welcomeBean) {
//        ImageLoader.load(this, welcomeBean.getImg(), ivWelcomeBg);
//        ivWelcomeBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
//        tvWelcomeAuthor.setText(welcomeBean.getText());
//    }

//    @Override
//    protected void onDestroy() {
//        Glide.clear(ivWelcomeBg);
//        super.onDestroy();
//    }

    @Override
    public void jumpToLogin() {

    }

    @Override
    public void jumpToHome() {
        MainActivity.open(mContext);
        finish();
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
