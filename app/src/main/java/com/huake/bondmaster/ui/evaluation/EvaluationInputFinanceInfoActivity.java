package com.huake.bondmaster.ui.evaluation;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.huake.bondmaster.R;
import com.huake.bondmaster.app.App;
import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.BaseActivity;
import com.huake.bondmaster.base.contract.evaluation.IndustryContract;
import com.huake.bondmaster.model.bean.EvaluationSuccessBean;
import com.huake.bondmaster.model.bean.IndustryBean;
import com.huake.bondmaster.presenter.evaluation.IndustryPresenter;
import com.huake.bondmaster.ui.web.WebActivity;
import com.huake.bondmaster.util.LogUtil;
import com.huake.bondmaster.widget.ActionBar;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/8/29 16:16
 * @email pengweiqiang64@163.com
 * @description 评测-->输入财务信息
 * @Version
 */

public class EvaluationInputFinanceInfoActivity extends BaseActivity<IndustryPresenter> implements IndustryContract.View {

    @BindView(R.id.action_bar)
    ActionBar mActionBar;
    @BindView(R.id.btn_next)
    Button mBtnNext;
    @BindView(R.id.et_asset_total)
    EditText mEtAssetTotal;
    @BindView(R.id.et_liabilities)
    EditText mEtliabilities;
    @BindView(R.id.et_flow_rate)
    EditText mEtFlowRate;
    @BindView(R.id.et_income)
    EditText mEtIncome;
    @BindView(R.id.et_profit_total)
    EditText mEtProfitTotal;
    @BindView(R.id.et_retained_profits)
    EditText mEtRetainedProfits;
    @BindView(R.id.ev_three_avg_profit)
    EditText mEtThreeAvgProfit;
    @BindView(R.id.et_ebit)
    EditText mEtEbit;
    @BindView(R.id.et_cash)
    EditText mEtCash;
    @BindView(R.id.et_company_profit)
    EditText mEtCompanyProfit;
    @BindView(R.id.et_enterprise_profit)
    EditText mEtEnterpriseProfit;
    @BindView(R.id.et_interest)
    EditText mEtInterest;

    private Map<String,String> params ;

    @Override
    protected void initInject() {
        getActivityComponent().inject(EvaluationInputFinanceInfoActivity.this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_input_finance_info;
    }

    @Override
    protected void initEventAndData() {
        mActionBar.setTitle("评测开始了");
        mActionBar.setLeftActionButton(R.mipmap.ic_white_close, "", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        });

        params = (HashMap)getIntent().getSerializableExtra(Constants.EVALUATE_PARAMS);

        initListener();

    }

    @OnClick(R.id.btn_next)
    public void Onclick(View view){
        String assetTotal = mEtAssetTotal.getText().toString();
        if(TextUtils.isEmpty(assetTotal)){
            mEtAssetTotal.requestFocus();
            return;
        }

        String libilities = mEtliabilities.getText().toString();
        if(TextUtils.isEmpty(libilities)){
            mEtliabilities.requestFocus();
            return;
        }

        String flowRate = mEtFlowRate.getText().toString();
        if(TextUtils.isEmpty(flowRate)){
            mEtFlowRate.requestFocus();
            return;
        }
        String income = mEtIncome.getText().toString();
        if(TextUtils.isEmpty(income)){
            mEtIncome.requestFocus();
            return;
        }
        String profitTotal = mEtProfitTotal.getText().toString();
        if(TextUtils.isEmpty(profitTotal)){
            mEtProfitTotal.requestFocus();
            return;
        }
        String retainedProfits = mEtRetainedProfits.getText().toString();
        if(TextUtils.isEmpty(retainedProfits)){
            mEtRetainedProfits.requestFocus();
            return;
        }
        String threeAvgProfit = mEtThreeAvgProfit.getText().toString();
        if(TextUtils.isEmpty(threeAvgProfit)){
            mEtThreeAvgProfit.requestFocus();
            return;
        }
        String ebit = mEtEbit.getText().toString();
        if(TextUtils.isEmpty(ebit)){
            mEtEbit.requestFocus();
            return;
        }
        String cash = mEtCash.getText().toString();
        if(TextUtils.isEmpty(cash)){
            mEtCash.requestFocus();
            return;
        }

        //债务信息

        String companyProfit = mEtCompanyProfit.getText().toString().trim();
        if(TextUtils.isEmpty(companyProfit)){
            mEtCompanyProfit.requestFocus();
            return;
        }
        String enterpriseProfit = mEtEnterpriseProfit.getText().toString().trim();
        if(TextUtils.isEmpty(enterpriseProfit)){
            mEtEnterpriseProfit.requestFocus();
            return;
        }

        String yearInterest = mEtInterest.getText().toString();
        if(TextUtils.isEmpty(yearInterest)){
            mEtInterest.requestFocus();
            return;
        }


        params.put("totAssets",assetTotal);//总资产
        params.put("totLiab",libilities);//总负债
        params.put("sFaCurrent",flowRate);//流动比率
        params.put("operRev",income);//营业收入
        params.put("totProfit",profitTotal);
        params.put("netProfit",retainedProfits);//净利润
        params.put("threeYearNetProfitMean",threeAvgProfit);//近三年平均利润
        params.put("ebitInterest",ebit);//EBIT利息
        params.put("netCashFlowsOperAct",cash);//经营性现金流


        params.put("userId", App.getInstance().getUserBeanInstance().getId());
        params.put("corporateBondYearInterest",companyProfit);
        params.put("enterpriseBondYearInterest",enterpriseProfit);
        params.put("yearInterest",yearInterest);//一年利息



//        EvaluationDebtInfoActivity.open(mContext,params);
        showLoading("评测中...");
        LogUtil.i(new Gson().toJson(params).toString());
        mPresenter.startEvaluate(params);
    }


    private void initListener(){

    }

    @Override
    public void showContent(List<IndustryBean> industryBeanList) {

    }

    public static void open(Context context, Map<String,String> params){
        Intent intent = new Intent(context,EvaluationInputFinanceInfoActivity.class);
        intent.putExtra(Constants.EVALUATE_PARAMS,(Serializable) params);
        context.startActivity(intent);
    }

    @Override
    public void evaluateSuccess(EvaluationSuccessBean evaluationSuccessBean) {
        //评测成功需要跳转
        StringBuilder sbUrl = new StringBuilder(Constants.HOST_URL+Constants.EVALUATION_RESULT);
        sbUrl.append("?userId=").append(evaluationSuccessBean.getUserId())
                .append("&trialCustId=").append(evaluationSuccessBean.getTrialCustId())
                .append("&dataDate=").append(evaluationSuccessBean.getDataDate());
        WebActivity.open(mContext,"",sbUrl.toString());
        App.getInstance().removeActivity(EvaluationActivity.class);
        finish();
    }
}
