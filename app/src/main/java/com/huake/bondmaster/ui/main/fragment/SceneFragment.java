package com.huake.bondmaster.ui.main.fragment;

import android.widget.TextView;

import com.huake.bondmaster.R;
import com.huake.bondmaster.base.RootFragment;
import com.huake.bondmaster.base.contract.main.MyContract;
import com.huake.bondmaster.presenter.main.MyPresenter;

import butterknife.BindView;

/**
 * @author will on 2017/8/24 13:42
 * @email pengweiqiang64@163.com
 * @description 场景
 * @Version
 */

public class SceneFragment extends RootFragment<MyPresenter> implements MyContract.View {

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mTvTitle.setText(getResources().getString(R.string.tab_scene));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_scene;
    }

    @Override
    public void showContent() {

    }
}
