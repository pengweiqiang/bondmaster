package com.huake.bondmaster.ui.main.fragment;

import com.huake.bondmaster.R;
import com.huake.bondmaster.base.RootFragment;
import com.huake.bondmaster.base.contract.main.MarketContract;
import com.huake.bondmaster.presenter.main.MarketPresenter;

/**
 * @author will on 2017/8/24 13:42
 * @email pengweiqiang64@163.com
 * @description 市场
 * @Version
 */

public class MarketFragment extends RootFragment<MarketPresenter> implements MarketContract.View {

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_market;
    }

    @Override
    public void showContent() {

    }
}
