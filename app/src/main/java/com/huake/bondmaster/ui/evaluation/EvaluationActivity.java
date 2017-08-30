package com.huake.bondmaster.ui.evaluation;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.huake.bondmaster.R;
import com.huake.bondmaster.base.BaseActivity;
import com.huake.bondmaster.base.contract.evaluation.EvaluationContract;
import com.huake.bondmaster.model.bean.AreaNatureTypeBean;
import com.huake.bondmaster.model.bean.PartyBean;
import com.huake.bondmaster.presenter.evaluation.EvaluationPresenter;
import com.huake.bondmaster.widget.ActionBar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/8/29 16:16
 * @email pengweiqiang64@163.com
 * @description 开始测评第一步
 * @Version
 */

public class EvaluationActivity extends BaseActivity<EvaluationPresenter> implements EvaluationContract.View {

    @BindView(R.id.action_bar)
    ActionBar mActionBar;

    AreaNatureTypeBean areaNatureTypeBean;

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

    @OnClick({R.id.tv_industry,R.id.tv_address,R.id.tv_company_nature,R.id.tv_company_type})
    public void onOptionItemClick(View view){
        switch (view.getId()){
            case R.id.tv_industry:
                SelectIndustryActivity.open(mContext);
                break;
            case R.id.tv_address:
                getAreaNatureType();
                break;
            case R.id.tv_company_nature:
                getAreaNatureType();
                break;
            case R.id.tv_company_type:
                getAreaNatureType();
                break;
        }
    }

    private void getAreaNatureType(){
        if(areaNatureTypeBean==null){
            showLoading("");
            mPresenter.getAreaNatureTypeList();
        }
    }


    @Override
    public void showContent() {

    }

    @Override
    public void setAreaNatureType(AreaNatureTypeBean areaNatureType) {

    }

    @Override
    public void setCompanyNameList(List<PartyBean> partyBeanList) {

    }

    public static void open(Context context){
        Intent intent = new Intent(context,EvaluationActivity.class);
        context.startActivity(intent);
    }
}
