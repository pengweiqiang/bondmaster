package com.huake.bondmaster.ui.evaluation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.huake.bondmaster.R;
import com.huake.bondmaster.base.BaseActivity;
import com.huake.bondmaster.base.contract.evaluation.IndustryContract;
import com.huake.bondmaster.model.bean.IndustryBean;
import com.huake.bondmaster.presenter.evaluation.IndustryPresenter;
import com.huake.bondmaster.widget.ActionBar;

import java.util.HashMap;
import java.util.List;

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
    @BindView(R.id.et_company_profit)
    EditText mEtCompanyProfit;
    @BindView(R.id.et_enterprise_profit)
    EditText mEtEnterpriseProfit;

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



        initListener();


    }

    @OnClick(R.id.btn_next)
    public void Onclick(View view){
        EvaluationDebtInfoActivity.open(mContext,new HashMap<String, String>());
    }

    private void initListener(){

    }

    @Override
    public void showContent(List<IndustryBean> industryBeanList) {

    }

    public static void open(Context context, Bundle bundle){
        Intent intent = new Intent(context,EvaluationInputFinanceInfoActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
