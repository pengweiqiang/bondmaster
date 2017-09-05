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
import com.huake.bondmaster.base.contract.evaluation.StartEvaluateContract;
import com.huake.bondmaster.model.bean.EvaluationSuccessBean;
import com.huake.bondmaster.presenter.evaluation.StartEvaluatePresenter;
import com.huake.bondmaster.ui.web.WebActivity;
import com.huake.bondmaster.util.LogUtil;
import com.huake.bondmaster.widget.ActionBar;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/8/29 16:16
 * @email pengweiqiang64@163.com
 * @description 评测-->输入债务信息
 * @Version
 */

public class EvaluationDebtInfoActivity extends BaseActivity<StartEvaluatePresenter> implements StartEvaluateContract.View {

    @BindView(R.id.action_bar)
    ActionBar mActionBar;
    @BindView(R.id.btn_next)
    Button mBtnNext;
    @BindView(R.id.et_company_profit)
    EditText mEtCompanyProfit;
    @BindView(R.id.et_enterprise_profit)
    EditText mEtEnterpriseProfit;
    @BindView(R.id.et_interest)
    EditText mEtInterest;

    Map<String,String> params;

    @Override
    protected void initInject() {
        getActivityComponent().inject(EvaluationDebtInfoActivity.this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_input_debt_info;
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
    public void onCLick(View view){
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

        params.put("userId", App.getInstance().getUserBeanInstance().getId());
        params.put("corporateBondYearInterest",companyProfit);
        params.put("enterpriseBondYearInterest",enterpriseProfit);
        params.put("yearInterest",yearInterest);//一年利息

        showLoading("");
        LogUtil.i(new Gson().toJson(params).toString());
        mPresenter.startEvaluate(params);
    }

    private void initListener(){

    }


    public static void open(Context context, Map<String,String> params){
        LogUtil.i(new Gson().toJson(params).toString());
        Intent intent = new Intent(context,EvaluationDebtInfoActivity.class);
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
        App.getInstance().removeActivity(EvaluationInputFinanceInfoActivity.class);
        finish();
    }
}
