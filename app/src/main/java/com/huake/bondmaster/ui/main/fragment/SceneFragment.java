package com.huake.bondmaster.ui.main.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.huake.bondmaster.R;
import com.huake.bondmaster.base.RootFragment;
import com.huake.bondmaster.base.contract.main.SceneContract;
import com.huake.bondmaster.model.bean.SceneBean;
import com.huake.bondmaster.presenter.main.ScenePresenter;
import com.huake.bondmaster.ui.main.adapter.SceneAdapter;
import com.huake.bondmaster.ui.scene.SceneDetailActivity;
import com.huake.bondmaster.util.TextViewUtils;
import com.huake.bondmaster.widget.CommonItemDecoration;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.huake.bondmaster.R.id.refreshLayout;

/**
 * @author will on 2017/8/24 13:42
 * @email pengweiqiang64@163.com
 * @description 场景
 * @Version
 */

public class SceneFragment extends RootFragment<ScenePresenter> implements SceneContract.View {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(refreshLayout)
    RefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_evaluated_company)
    TextView mTvEvaluatedCompany;


    SceneAdapter mAdapter;

    private List<SceneBean> mList = new ArrayList<>();


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mTvTitle.setText(getResources().getString(R.string.tab_scene));


        mAdapter = new SceneAdapter(mContext,mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        CommonItemDecoration mDecoration = new CommonItemDecoration(10, CommonItemDecoration.UNIT_DP);
        mRecyclerView.addItemDecoration(mDecoration);

        initListener();


        mPresenter.getSceneList();
    }

    @Override
    public void initListener(){

        mAdapter.setOnItemClickListener(new SceneAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                SceneDetailActivity.open(mContext,mList.get(position));
            }
        });

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_scene;
    }

    @Override
    public void showContent(List<SceneBean> sceneBeanList) {
        mList.addAll(sceneBeanList);
        mAdapter.notifyDataSetChanged();

        String companyCount = String.valueOf(mList.size());
        String evaluatedCompany = "已评测企业列表（ "+companyCount+" ）";
        mTvEvaluatedCompany.setText(TextViewUtils.getSpannableStringColor(evaluatedCompany,9,9+companyCount.length(),"#ff0000"));
    }
}
