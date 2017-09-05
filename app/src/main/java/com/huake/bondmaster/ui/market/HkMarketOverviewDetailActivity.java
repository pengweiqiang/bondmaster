package com.huake.bondmaster.ui.market;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.BaseActivity;
import com.huake.bondmaster.base.contract.market.HkMarketOverviewDetailContract;
import com.huake.bondmaster.model.bean.HkMarketOverviewBean;
import com.huake.bondmaster.model.bean.HkMarketOverviewDetailBean;
import com.huake.bondmaster.presenter.market.HkMarketOverviewDetailPresenter;
import com.huake.bondmaster.widget.ActionBar;

import butterknife.BindView;

/**
 * @author will on 2017/9/4 22:19
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class HkMarketOverviewDetailActivity extends BaseActivity<HkMarketOverviewDetailPresenter> implements HkMarketOverviewDetailContract.View{
    HkMarketOverviewBean hkMarketOverviewBean;

    @BindView(R.id.action_bar)
    ActionBar mActionBar;
    @BindView(R.id.tv_comp_name)
    TextView mTvCompanyName;
    @BindView(R.id.tv_industry)
    TextView mTvIndustry;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_nature)
    TextView mTvNature;
    @BindView(R.id.tv_subject_level)
    TextView mTvSubjectLevel;
//    @BindView(R.id.tv_bond_level)
//    TextView mTvBondLevel;
    @BindView(R.id.tv_scale)
    TextView mTvScale;
    @BindView(R.id.tv_rate)
    TextView mTvRate;
    @BindView(R.id.tv_deadline)
    TextView mTvDeadLine;


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_hk_market_overview_detail;
    }

    @Override
    protected void initEventAndData() {

        hkMarketOverviewBean = (HkMarketOverviewBean)getIntent().getParcelableExtra(Constants.HK_MARKET_OVERVIEW_BEAN);
        mActionBar.setTitle(hkMarketOverviewBean.getSInfoCompname());
        showLoading("");
        mPresenter.getDetail(String.valueOf(hkMarketOverviewBean.getSInfoCompcode()),String.valueOf(hkMarketOverviewBean.getSInfoWindcode()));
    }


    @Override
    public void showContent(HkMarketOverviewDetailBean hkMarketOverviewDetailBean) {
        mTvCompanyName.setText(hkMarketOverviewDetailBean.getSInfoCompname());
        mTvAddress.setText(hkMarketOverviewDetailBean.getSInfoProvince());
        mTvIndustry.setText(hkMarketOverviewDetailBean.getSecCodeValue1());
        mTvNature.setText(hkMarketOverviewDetailBean.getNatureCodeValue());
        mTvSubjectLevel.setText(hkMarketOverviewDetailBean.getIssueCreditrating());
//        mTvBondLevel.setText("");

        double bIssueAmoutact = hkMarketOverviewDetailBean.getBIssueAmountact();
        if(bIssueAmoutact == 0d){
            mTvScale.setText("未知");
        }else{
            mTvScale.setText(bIssueAmoutact+"亿元");
        }

        double rate = hkMarketOverviewDetailBean.getBInfoCouponrate();
        if(rate == 0d){
            mTvRate.setText("未知");
        }else{
            mTvRate.setText(rate+"%");
        }

        mTvDeadLine.setText(hkMarketOverviewDetailBean.getBInfoTermYear()+"年");

    }

    @Override
    public void stateMain() {
        super.stateMain();
    }

    @Override
    public void initListener() {

    }

    public static void open(Context context, HkMarketOverviewBean hkMarketOverviewBean){
        Intent intent = new Intent(context,HkMarketOverviewDetailActivity.class);
        intent.putExtra(Constants.HK_MARKET_OVERVIEW_BEAN,hkMarketOverviewBean);
        context.startActivity(intent);
    }
}
