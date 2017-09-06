package com.huake.bondmaster.ui.main.fragment;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.App;
import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.RootFragment;
import com.huake.bondmaster.base.contract.main.HomeContract;
import com.huake.bondmaster.model.bean.HomePageBean;
import com.huake.bondmaster.model.bean.HotNewsBean;
import com.huake.bondmaster.model.bean.UserBean;
import com.huake.bondmaster.presenter.home.HomePresenter;
import com.huake.bondmaster.ui.main.activity.ArticleDetailActivity;
import com.huake.bondmaster.ui.main.activity.SearchTrialCustInfoActivity;
import com.huake.bondmaster.ui.main.adapter.HomeAdapter;
import com.huake.bondmaster.ui.my.LoginActivity;
import com.huake.bondmaster.util.LogUtil;
import com.huake.bondmaster.widget.CommonItemDecoration;
import com.huake.bondmaster.widget.HeaderScrollingBehavior;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
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
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    private HomePageBean homePageBean;

    private List<HotNewsBean> hotNewsBeanList = new ArrayList<>();

    private HomeAdapter mHomeAdapter;

    HeaderScrollingBehavior behavior;

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
        mPresenter.getHomePageCache();

        mSmartRefreshLayout.autoRefresh();

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mRecyclerView.getLayoutParams();
        behavior = (HeaderScrollingBehavior)params.getBehavior();

        behavior.setOnChangeExpandedListener(new HeaderScrollingBehavior.OnChangeExpandedListener() {
            @Override
            public void isChanged(boolean isExpaned) {
                mSmartRefreshLayout.setEnableRefresh(!isExpaned);
            }
        });
        boolean isExpand = behavior.isExpanded();
        LogUtil.i("----------"+isExpand);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    public void showContent(HomePageBean homePageBean) {
        this.homePageBean = homePageBean;
        hotNewsBeanList = homePageBean.getHotNews();
        mSmartRefreshLayout.finishRefresh(800);
        mHomeAdapter.setData(homePageBean);
    }

    @Override
    public void initListener() {
        mEtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isExpand = behavior.isExpanded();
                LogUtil.i("----------"+isExpand);
                SearchTrialCustInfoActivity.open(mContext,"");
            }
        });
        mHomeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                if(position>-1 && position<hotNewsBeanList.size()) {
                    UserBean userBean = App.getInstance().getUserBeanInstance();
                    if(userBean==null){
                        showErrorMsg("请先登录");
                        LoginActivity.open(mContext,"");
                        return;
                    }
                    HotNewsBean hotNewsBean = hotNewsBeanList.get(position);
                    StringBuilder hotNewUrl = new StringBuilder(Constants.HOST_URL).append(Constants.ARTICLE_URL);
                    hotNewUrl.append("?id=").append(hotNewsBean.getId()).append("&userId=").append(userBean.getId());
                    ArticleDetailActivity.open(mContext,hotNewsBean.getTitle(),hotNewUrl.toString());
                }
            }
        });
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener(){

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getHomePage();
            }
        });

    }

    @Override
    public void stateError() {
//        super.stateError();
        mSmartRefreshLayout.finishRefresh();
        mSmartRefreshLayout.finishLoadmore();
    }

    @Override
    public void stateMain() {
        super.stateMain();
        mSmartRefreshLayout.finishRefresh();
        mSmartRefreshLayout.finishLoadmore();
    }

    @Override
    public void onStart() {
        super.onStart();
        mHomeAdapter.onStart();
    }

    @Override
    public void onStop() {
        mHomeAdapter.onStop();
        super.onStop();
    }

}
