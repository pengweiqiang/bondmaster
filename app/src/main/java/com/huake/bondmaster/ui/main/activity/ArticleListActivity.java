package com.huake.bondmaster.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.App;
import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.BaseActivity;
import com.huake.bondmaster.base.contract.main.ArticleListContract;
import com.huake.bondmaster.model.bean.ArticleBean;
import com.huake.bondmaster.model.bean.UserBean;
import com.huake.bondmaster.presenter.main.ArticleListPresenter;
import com.huake.bondmaster.ui.main.adapter.ArticleAdapter;
import com.huake.bondmaster.ui.my.LoginActivity;
import com.huake.bondmaster.ui.web.WebActivity;
import com.huake.bondmaster.widget.ActionBar;
import com.huake.bondmaster.widget.CommonItemDecoration;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.huake.bondmaster.R.id.refreshLayout;

/**
 * @author will on 2017/8/31 21:22
 * @email pengweiqiang64@163.com
 * @description 时讯专栏
 * @Version
 */

public class ArticleListActivity extends BaseActivity<ArticleListPresenter> implements ArticleListContract.View {

    @BindView(R.id.action_bar)
    ActionBar mActionBar;
    @BindView(refreshLayout)
    RefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private List<ArticleBean> mList = new ArrayList<>();

    private long pageNum = 1;
    private long total = 0;

    ArticleAdapter articleAdapter;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_article_list;
    }

    @Override
    protected void initEventAndData() {
        mActionBar.setTitle("时讯专栏");
        articleAdapter = new ArticleAdapter(mContext,mList);

        mRecyclerView.addItemDecoration(new CommonItemDecoration(10,CommonItemDecoration.UNIT_DP));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mRecyclerView.setAdapter(articleAdapter);

        mRefreshLayout.autoRefresh();

        initListener();
    }

    @Override
    public void stateMain() {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadmore();
    }

    @Override
    public void stateError() {
        stateMain();
    }

    @Override
    public void showContent(long records, long pageNum, long total, List<ArticleBean> articleBeanList) {
        stateMain();
        this.total = total;
        this.pageNum = pageNum;
        if(pageNum <= 1) {
            mList.clear();
        }
        mRefreshLayout.setLoadmoreFinished(pageNum>=total);
        mList.addAll(articleBeanList);
        if(mList.isEmpty()){
            showErrorMsg("查询结果为空");
        }
        articleAdapter.notifyDataSetChanged();
    }

    @Override
    public void initListener() {
        articleAdapter.setOnItemClickListener(new ArticleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                if(position>-1 && position<mList.size()) {
                    UserBean userBean = App.getInstance().getUserBeanInstance();
                    if(userBean==null){
                        showErrorMsg("请先登录");
                        LoginActivity.open(mContext,"");
                        return;
                    }
                    ArticleBean articleBean = mList.get(position);
                    StringBuilder hotNewUrl = new StringBuilder(Constants.HOST_URL).append(Constants.ARTICLE_URL);
                    hotNewUrl.append("?id=").append(articleBean.getId()).append("&userId=").append(userBean.getId());
                    WebActivity.open(mContext,articleBean.getTitle(),hotNewUrl.toString());
                }
            }
        });

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageNum = 1;
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
    }

    public static void open(Context context){
        Intent intent = new Intent(context,ArticleListActivity.class);
        context.startActivity(intent);
    }

    private void loadData(){
        UserBean userBean = App.getInstance().getUserBeanInstance();
        String userId = "";
        if(userBean!=null){
            userId = userBean.getId();
        }
        mPresenter.getArticleList(userId,pageNum, Constants.PAGE_SIZE,"2");
    }

}
