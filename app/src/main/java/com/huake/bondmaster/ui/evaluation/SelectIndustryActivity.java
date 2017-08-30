package com.huake.bondmaster.ui.evaluation;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.huake.bondmaster.R;
import com.huake.bondmaster.base.BaseActivity;
import com.huake.bondmaster.base.contract.evaluation.IndustryContract;
import com.huake.bondmaster.model.bean.IndustryBean;
import com.huake.bondmaster.presenter.evaluation.IndustryPresenter;
import com.huake.bondmaster.widget.ActionBar;

import java.util.List;

import butterknife.BindView;

/**
 * @author will on 2017/8/29 16:16
 * @email pengweiqiang64@163.com
 * @description 选择行业
 * @Version
 */

public class SelectIndustryActivity extends BaseActivity<IndustryPresenter> implements IndustryContract.View {

    @BindView(R.id.action_bar)
    ActionBar mActionBar;

    @Override
    protected void initInject() {
        getActivityComponent().inject(SelectIndustryActivity.this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_evaluation_first;
    }

    @Override
    protected void initEventAndData() {
        mActionBar.setTitle("选择行业");
        mActionBar.setLeftActionButton(R.mipmap.ic_white_close, "", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        });

        showLoading("");
        mPresenter.getIndustryList();
    }


    @Override
    public void showContent(List<IndustryBean> industryBeanList) {

    }

    public static void open(Context context){
        Intent intent = new Intent(context,SelectIndustryActivity.class);
        context.startActivity(intent);
    }
}
