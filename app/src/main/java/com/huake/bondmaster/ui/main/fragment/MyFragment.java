package com.huake.bondmaster.ui.main.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huake.bondmaster.R;
import com.huake.bondmaster.base.RootFragment;
import com.huake.bondmaster.base.contract.main.MyContract;
import com.huake.bondmaster.presenter.main.MyPresenter;
import com.huake.bondmaster.ui.my.AboutUsActivity;
import com.huake.bondmaster.ui.my.FeedBackActivity;
import com.huake.bondmaster.widget.ToggleButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/8/24 13:42
 * @email pengweiqiang64@163.com
 * @description 我的
 * @Version
 */

public class MyFragment extends RootFragment<MyPresenter> implements MyContract.View {

    @BindView(R.id.toggle_message_notify)
    ToggleButton mToggleMessageNofity;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_user_mobile)
    TextView mTvUserMobile;
    @BindView(R.id.iv_user_head)
    ImageView mIvUserHead;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void showContent() {

    }

    @OnClick({R.id.rl_feed_back,R.id.rl_share_app,R.id.rl_about_us})
    public void onClickOptionItem(View view){
        switch (view.getId()){
            case R.id.rl_feed_back:
                FeedBackActivity.open(mContext);
                break;
            case R.id.rl_share_app:

                break;
            case R.id.rl_about_us:
                AboutUsActivity.open(mContext);
                break;
        }
    }

}
