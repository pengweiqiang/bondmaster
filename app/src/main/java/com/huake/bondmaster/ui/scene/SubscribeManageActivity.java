package com.huake.bondmaster.ui.scene;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.App;
import com.huake.bondmaster.base.RootActivity;
import com.huake.bondmaster.base.contract.subscribe.SubscribeContract;
import com.huake.bondmaster.model.bean.SubscribeBean;
import com.huake.bondmaster.model.bean.UserBean;
import com.huake.bondmaster.presenter.subscribe.SubscribePresenter;
import com.huake.bondmaster.ui.scene.adapter.SubscribeAdapter;
import com.huake.bondmaster.widget.ActionBar;
import com.huake.bondmaster.widget.CommonItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author will on 2017/9/5 11:32
 * @email pengweiqiang64@163.com
 * @description 订阅管理
 * @Version
 */

public class SubscribeManageActivity extends RootActivity<SubscribePresenter> implements SubscribeContract.View{

    @BindView(R.id.action_bar)
    ActionBar mActionBar;
    @BindView(R.id.view_main)
    RecyclerView recyclerView;
    @BindView(R.id.empty_subscribe)
    ViewStub mViewStub;

    SubscribeAdapter subscribeAdapter;

    List<SubscribeBean> subscribeBeanList = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_subscirbe_manage;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mActionBar.setTitle("订阅管理");

        subscribeAdapter = new SubscribeAdapter(mContext,subscribeBeanList);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(subscribeAdapter);
        CommonItemDecoration mDecoration = new CommonItemDecoration(10, CommonItemDecoration.UNIT_DP);
        recyclerView.addItemDecoration(mDecoration);

        UserBean userBean = App.getInstance().getUserBeanInstance();
        if(userBean==null){
            startLoginActivity();
            return;
        }
        showLoading("");
        mPresenter.getSubscirbeList(userBean.getId());
    }

    public static void open(Context context){
        Intent intent = new Intent(context,SubscribeManageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showContent(List<SubscribeBean> subscribeBeanList) {
        this.subscribeBeanList = subscribeBeanList;
        if(subscribeBeanList==null || subscribeBeanList.isEmpty()){
            View emptyView = mViewStub.inflate();
            recyclerView.setVisibility(View.GONE);
            return;
        }
        recyclerView.setVisibility(View.VISIBLE);
        subscribeAdapter.setData(subscribeBeanList
        );
    }

}
