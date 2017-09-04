package com.huake.bondmaster.ui.market;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.BaseActivity;
import com.huake.bondmaster.base.contract.market.HkMarketOverviewContract;
import com.huake.bondmaster.model.bean.HkMarketOverviewBean;
import com.huake.bondmaster.presenter.market.HkMarketOverviewPresenter;
import com.huake.bondmaster.ui.market.adapter.HkMarketOverviewAdapter;
import com.huake.bondmaster.widget.ActionBar;
import com.huake.bondmaster.widget.CommonItemDecoration;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author will on 2017/8/29 14:15
 * @email pengweiqiang64@163.com
 * @description 短期融资券
 * @Version
 */

public class HkMarketOverviewActivity extends BaseActivity<HkMarketOverviewPresenter> implements HkMarketOverviewContract.View {

    @BindView(R.id.action_bar)
    ActionBar mActionBar;
    @BindView(R.id.smart_refresh_layout)
    RefreshLayout mRefreshLayout;
    @BindView(R.id.view_main)
    RecyclerView mRecyclerView;

    HkMarketOverviewAdapter mAdapter;

    private List<HkMarketOverviewBean> mList = new ArrayList<>();

    private long pageNum = 1;
    private long total = 0;

    String urlKey = "";

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_hk_market_overview;
    }

    @Override
    protected void initEventAndData() {
        mActionBar.setTitle("短期融资券");
        urlKey = getIntent().getStringExtra(Constants.URL_KEY);

        mAdapter = new HkMarketOverviewAdapter(mContext,mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        CommonItemDecoration mDecoration = new CommonItemDecoration(1, CommonItemDecoration.UNIT_DP);
        mRecyclerView.addItemDecoration(mDecoration);
        initListener();

        mRefreshLayout.autoRefresh();
    }


    private void loadData(){
        urlKey = urlKey.substring(urlKey.indexOf("?")+1);
        String params[] = urlKey.split("=");
        if(params.length>=2) {
            mPresenter.getLists(params[1], pageNum, Constants.PAGE_SIZE);
        }
    }

    @Override
    public void stateMain() {
        super.stateMain();
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
    }

    @Override
    public void stateError() {
        super.stateError();
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
    }


    @Override
    public void showContent(long records, long pageNum, long total, List<HkMarketOverviewBean> hkMarketOverviewBeanList) {
        stateMain();
        this.total = total;
        this.pageNum = pageNum;
        if(pageNum <= 1) {
            mList.clear();
        }
        mRefreshLayout.setLoadmoreFinished(pageNum>=total);
        mList.addAll(hkMarketOverviewBeanList);
        if(mList.isEmpty()){
            showErrorMsg("查询结果为空");
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void initListener() {
        mAdapter.setOnItemClickListener(new HkMarketOverviewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                HkMarketOverviewDetailActivity.open(mContext,mList.get(position));
            }
        });

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadDataFirst();
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageNum++;
                loadData();
            }
        });

    }

    private void loadDataFirst(){
        pageNum = 1;
        loadData();
    }

    public static void open(Context context,String url){
        Intent intent = new Intent(context,HkMarketOverviewActivity.class);
        intent.putExtra(Constants.URL_KEY,url);
        context.startActivity(intent);
    }

}
