package com.huake.bondmaster.ui.main.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.huake.bondmaster.R;
import com.huake.bondmaster.base.RootFragment;
import com.huake.bondmaster.base.contract.main.HomeContract;
import com.huake.bondmaster.model.bean.HomePageBean;
import com.huake.bondmaster.model.bean.HotNewsBean;
import com.huake.bondmaster.presenter.home.HomePresenter;
import com.huake.bondmaster.ui.main.adapter.HomeAdapter;
import com.huake.bondmaster.widget.CommonItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @author will on 2017/8/24 13:42
 * @email pengweiqiang64@163.com
 * @description 首页
 * @Version
 */

public class HomeFragment extends RootFragment<HomePresenter> implements HomeContract.View {

    @BindView(R.id.view_main)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private HomePageBean homePageBean;

    private List<HotNewsBean> hotNewsBeanList = new ArrayList<>();

    private HomeAdapter mHomeAdapter;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }


    @Override
    protected void initEventAndData() {
        super.initEventAndData();

        mHomeAdapter = new HomeAdapter(mContext,homePageBean);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mHomeAdapter);
        CommonItemDecoration mDecoration = new CommonItemDecoration(1, 0,0,CommonItemDecoration.UNIT_DP);
        mDecoration.setIsShowHeader(false);
        mRecyclerView.addItemDecoration(mDecoration);

        initListener();
        mSmartRefreshLayout.setEnableLoadmore(false);
        mSmartRefreshLayout.autoRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    public void showContent(HomePageBean homePageBean) {
        this.homePageBean = homePageBean;
        hotNewsBeanList = homePageBean.getHotNews();
        mHomeAdapter.setData(homePageBean);
        mSmartRefreshLayout.finishRefresh(800);
    }

    @Override
    public void initListener() {
        mHomeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                if(position>-1 && position<hotNewsBeanList.size()) {
                    HotNewsBean hotNewsBean = hotNewsBeanList.get(position);
                    showErrorMsg(hotNewsBean.getTitle());
                }
            }
        });
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener(){

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getHomePage();
            }
        });

        mSmartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

            }
        });
    }

    @Override
    public void stateError() {
//        super.stateError();
        mSmartRefreshLayout.finishRefresh();
        mSmartRefreshLayout.finishLoadmore();
    }

}
