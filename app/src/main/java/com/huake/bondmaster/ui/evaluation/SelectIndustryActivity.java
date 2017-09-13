package com.huake.bondmaster.ui.evaluation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.BaseActivity;
import com.huake.bondmaster.base.contract.evaluation.IndustryContract;
import com.huake.bondmaster.model.bean.EvaluationSuccessBean;
import com.huake.bondmaster.model.bean.IndustryBean;
import com.huake.bondmaster.presenter.evaluation.IndustryPresenter;
import com.huake.bondmaster.ui.evaluation.adapter.IndustryAdapter;
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
    @BindView(R.id.left_listview)
    ListView mLeftListView;
    @BindView(R.id.lright_listview)
    ListView mrightListView;

    IndustryAdapter leftIndustryAdapter;
    IndustryAdapter rightIndustryAdapter;

    List<IndustryBean> industryBeanList;

    @Override
    protected void initInject() {
        getActivityComponent().inject(SelectIndustryActivity.this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_select_industry;
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

        leftIndustryAdapter = new IndustryAdapter(mContext,industryBeanList,0);
        rightIndustryAdapter = new IndustryAdapter(mContext,null,-1);

        mLeftListView.setAdapter(leftIndustryAdapter);
        mrightListView.setAdapter(rightIndustryAdapter);

        initListener();

        showLoading("");
        mPresenter.getIndustryList();
    }

    private void initListener(){
        mLeftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                leftIndustryAdapter.setSelectedStatus(position);
                rightIndustryAdapter.setData(industryBeanList.get(position),industryBeanList.get(position).getSubList());
            }
        });
        mrightListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IndustryBean parentIndustryBean = rightIndustryAdapter.getParentIndustryBean();
                IndustryBean childIndustryBean = rightIndustryAdapter.getItem(position);
                Intent data = new Intent();
                data.putExtra(Constants.INDUSTRY_SELECTED,childIndustryBean);
                data.putExtra(Constants.PARENT_INDUSTRY_SELECTED,parentIndustryBean);
                setResult(RESULT_OK,data);
                finish();
            }
        });
    }

    @Override
    public void showContent(List<IndustryBean> industryBeanList) {
        this.industryBeanList = industryBeanList;
        leftIndustryAdapter.setData(null,industryBeanList);

        if(industryBeanList!=null) {
            rightIndustryAdapter.setData(industryBeanList.get(0),industryBeanList.get(0).getSubList());
        }
    }

    @Override
    public void evaluateSuccess(EvaluationSuccessBean evaluationSuccessBean) {

    }

    public static void open(Context context){
        Intent intent = new Intent(context,SelectIndustryActivity.class);
        ((Activity)context).startActivityForResult(intent, Constants.SELECT_INDUSTRY_REQUEST_CODE);
    }
}
