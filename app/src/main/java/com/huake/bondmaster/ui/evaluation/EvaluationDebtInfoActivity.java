package com.huake.bondmaster.ui.evaluation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.BaseActivity;
import com.huake.bondmaster.base.contract.evaluation.IndustryContract;
import com.huake.bondmaster.model.bean.IndustryBean;
import com.huake.bondmaster.presenter.evaluation.IndustryPresenter;
import com.huake.bondmaster.widget.ActionBar;

import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author will on 2017/8/29 16:16
 * @email pengweiqiang64@163.com
 * @description 评测-->输入债务信息
 * @Version
 */

public class EvaluationDebtInfoActivity extends BaseActivity<IndustryPresenter> implements IndustryContract.View {

    @BindView(R.id.action_bar)
    ActionBar mActionBar;



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



        initListener();

    }

    private void initListener(){

    }

    @Override
    public void showContent(List<IndustryBean> industryBeanList) {

    }

    public static void open(Context context, Map<String,String> params){
        Intent intent = new Intent(context,EvaluationDebtInfoActivity.class);
        ((Activity)context).startActivityForResult(intent, Constants.SELECT_INDUSTRY_REQUEST_CODE);
    }
}
