package com.huake.bondmaster.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.RootActivity;
import com.huake.bondmaster.base.contract.main.SearchTrialCustInfoContract;
import com.huake.bondmaster.model.bean.SearchBean;
import com.huake.bondmaster.presenter.home.SearchTrialCustInfoPresenter;
import com.huake.bondmaster.ui.evaluation.EvaluationActivity;
import com.huake.bondmaster.ui.main.adapter.SearchAdapter;
import com.huake.bondmaster.ui.web.WebActivity;
import com.huake.bondmaster.widget.ActionBar;
import com.huake.bondmaster.widget.ClearEditText;
import com.huake.bondmaster.widget.CommonItemDecoration;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.huake.bondmaster.R.id.refreshLayout;

/**
 * @author will on 2017/8/29 14:15
 * @email pengweiqiang64@163.com
 * @description 首页搜索企业
 * @Version
 */

public class SearchTrialCustInfoActivity extends RootActivity<SearchTrialCustInfoPresenter> implements SearchTrialCustInfoContract.View {

    @BindView(R.id.action_bar)
    ActionBar mActionBar;
    @BindView(refreshLayout)
    RefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.et_search)
    ClearEditText mEtSearch;
    @BindView(R.id.btn_start_evaluate)
    Button mBtnEvaluate;

    SearchAdapter mAdapter;

    private List<SearchBean> mList = new ArrayList<>();

    private long pageNum = 1;
    private long total = 0;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_search_trial_custinfo;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mActionBar.setTitle("");


        mAdapter = new SearchAdapter(mContext,mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        CommonItemDecoration mDecoration = new CommonItemDecoration(10, CommonItemDecoration.UNIT_DP);
        mRecyclerView.addItemDecoration(mDecoration);
        initListener();

        mRefreshLayout.autoRefresh();
    }

    @OnClick(R.id.btn_start_evaluate)
    public void onClick(View view){
        if(checkIsLogin()==null){
            startLoginActivity();
            return;
        }
        EvaluationActivity.open(mContext);
    }

    private void loadData(){
//        UserBean userBean = App.getInstance().getUserBeanInstance();
//        String userId = "";
//        if(userBean!=null){
//            userId = userBean.getId();
//        }
        String searchKey = mEtSearch.getText().toString().trim();
        mPresenter.getSceneList(pageNum,searchKey);
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
    protected void reconnectNetwork() {
        super.reconnectNetwork();
        loadDataFirst();
    }

    @Override
    public void showContent(long records, long pageNum, long total, List<SearchBean> sceneBeanList) {
        stateMain();
        this.total = total;
        this.pageNum = pageNum;
        if(pageNum <= 1) {
            mList.clear();
        }
        mRefreshLayout.setLoadmoreFinished(pageNum>=total);
        mList.addAll(sceneBeanList);
        if(mList.isEmpty()){
            showErrorMsg("查询结果为空");
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void initListener() {
        mAdapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                SearchBean sceneBean = mList.get(position);
                StringBuilder sbUrl = new StringBuilder(Constants.HOST_URL+Constants.EVALUATION_RESULT);
                sbUrl.append("?userId=").append(sceneBean.getUserId())
                        .append("&trialCustId=").append(sceneBean.getTrialCustId())
                        .append("&dataDate=").append(sceneBean.getDataDate());
                WebActivity.open(mContext,"",sbUrl.toString());
//                SceneDetailActivity.open(mContext,mList.get(position));
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

        mEtSearch.setOnDeleteClickListener(new ClearEditText.OnDeleteClickListener() {
            @Override
            public void clearText() {
                loadDataFirst();
            }
        });
    }

    private void loadDataFirst(){
        pageNum = 1;
        loadData();
    }

    public static void open(Context context,String key){
        Intent intent = new Intent(context,SearchTrialCustInfoActivity.class);
        intent.putExtra(Constants.SEARCH_KEY,key);
        context.startActivity(intent);
    }

}
