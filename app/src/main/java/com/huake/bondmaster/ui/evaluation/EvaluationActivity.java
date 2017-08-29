package com.huake.bondmaster.ui.evaluation;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.huake.bondmaster.R;
import com.huake.bondmaster.base.BaseActivity;
import com.huake.bondmaster.base.contract.evaluation.EvaluationContract;
import com.huake.bondmaster.presenter.evaluation.EvaluationPresenter;
import com.huake.bondmaster.widget.ActionBar;

import butterknife.BindView;

/**
 * @author will on 2017/8/29 16:16
 * @email pengweiqiang64@163.com
 * @description 开始测评第一步
 * @Version
 */

public class EvaluationActivity extends BaseActivity<EvaluationPresenter> implements EvaluationContract.View {

    @BindView(R.id.action_bar)
    ActionBar mActionBar;

    @Override
    protected void initInject() {
        getActivityComponent().inject(EvaluationActivity.this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_evaluation_first;
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
    }

    @Override
    public void showContent() {

    }

    public static void open(Context context){
        Intent intent = new Intent(context,EvaluationActivity.class);
        context.startActivity(intent);
    }
}
