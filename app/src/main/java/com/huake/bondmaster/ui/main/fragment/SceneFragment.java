package com.huake.bondmaster.ui.main.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.App;
import com.huake.bondmaster.base.RootFragment;
import com.huake.bondmaster.base.contract.main.SceneContract;
import com.huake.bondmaster.model.bean.SceneBean;
import com.huake.bondmaster.model.bean.SearchBean;
import com.huake.bondmaster.model.bean.UserBean;
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

/**
 * @author will on 2017/8/24 13:42
 * @email pengweiqiang64@163.com
 * @description 场景
 * @Version
 */

public class SceneFragment extends RootFragment<ScenePresenter> implements SceneContract.View {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.refreshLayout)
    RefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_evaluated_company)
    TextView mTvEvaluatedCompany;
    @BindView(R.id.et_search)
    EditText mEtSearch;


    SceneAdapter mAdapter;

    private List<SceneBean> mList = new ArrayList<>();

    private long pageNum = 1;
    private long total = 0;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mTvTitle.setText(getResources().getString(R.string.tab_scene));

        showCompanyCount("0");

        mAdapter = new SceneAdapter(mContext,mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        CommonItemDecoration mDecoration = new CommonItemDecoration(10, CommonItemDecoration.UNIT_DP);
        mRecyclerView.addItemDecoration(mDecoration);
        initListener();

        mRefreshLayout.autoRefresh();
    }


    private void loadData(){
        UserBean userBean = App.getInstance().getUserBeanInstance();
        String userId = "";
        if(userBean!=null){
            userId = userBean.getId();
        }
        String searchKey = mEtSearch.getText().toString().trim();
        mPresenter.getSceneList(userId,pageNum,searchKey,"","","");
    }

    private void loadDataFirst(){
        pageNum = 1;
        loadData();
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
        stateMain();
    }

    @Override
    protected void reconnectNetwork() {
        super.reconnectNetwork();
        loadDataFirst();
    }

    @Override
    public void initListener(){

        mAdapter.setOnItemClickListener(new SceneAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                SceneBean sceneBean = mList.get(position);
                SearchBean searchBean = new SearchBean();
                searchBean.setId(sceneBean.getId());
                searchBean.setTrialCustId(String.valueOf(sceneBean.getTrialCustId()));
                searchBean.setUserId(sceneBean.getUserId());
                searchBean.setbInfoCreditrating(sceneBean.getbInfoCreditrating());
                searchBean.setSuccessProbability(sceneBean.getSuccessProbability());
                searchBean.setDataDate(sceneBean.getDataDate());

                SceneDetailActivity.open(mContext,searchBean);
            }
        });

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
            loadData();
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageNum++;
                loadData();
            }
        });

        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {//EditorInfo.IME_ACTION_SEARCH、EditorInfo.IME_ACTION_SEND等分别对应EditText的imeOptions属性
                    if(v.getText().toString().length() != 0) {
                        loadDataFirst();
                    }
                }
                return false;
            }
        });
    }



    @Override
    public void showCompanyCount(String companyCount) {
        String evaluatedCompany = "已评测企业列表（ "+companyCount+" ）";
        mTvEvaluatedCompany.setText(TextViewUtils.getSpannableStringColor(evaluatedCompany,9,9+companyCount.length(),"#ff0000"));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_scene;
    }


    @Override
    public void showContent(long records,long pageNum,long total,List<SceneBean> sceneBeanList) {
        stateMain();
        this.total = total;
        this.pageNum = pageNum;
        if(pageNum <= 1) {
            mList.clear();
        }
        mRefreshLayout.setLoadmoreFinished(pageNum>=total);
        mList.addAll(sceneBeanList);
        if(mList.isEmpty() && isAdded() && getUserVisibleHint()){
            showErrorMsg("查询结果为空");
        }
        mAdapter.notifyDataSetChanged();
        showCompanyCount(records+"");
    }


}
