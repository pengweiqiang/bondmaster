package com.huake.bondmaster.ui.main.fragment;

import com.huake.bondmaster.R;
import com.huake.bondmaster.base.RootFragment;
import com.huake.bondmaster.base.contract.main.MyContract;
import com.huake.bondmaster.presenter.main.MyPresenter;

/**
 * @author will on 2017/8/24 13:42
 * @email pengweiqiang64@163.com
 * @description 我的
 * @Version
 */

public class MyFragment extends RootFragment<MyPresenter> implements MyContract.View {

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
}
