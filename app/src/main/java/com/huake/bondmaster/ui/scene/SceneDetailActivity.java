package com.huake.bondmaster.ui.scene;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.BaseActivity;
import com.huake.bondmaster.base.contract.scene.SceneDetailContract;
import com.huake.bondmaster.model.bean.EnterpriseInfo;
import com.huake.bondmaster.model.bean.SearchBean;
import com.huake.bondmaster.presenter.scene.SceneDetailPresenter;
import com.huake.bondmaster.ui.web.WebActivity;
import com.huake.bondmaster.util.BigDecimalUtil;
import com.huake.bondmaster.util.DateUtil;
import com.huake.bondmaster.util.TextViewUtils;
import com.huake.bondmaster.util.ToastUtil;
import com.huake.bondmaster.widget.ActionBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/8/26 11:22
 * @email pengweiqiang64@163.com
 * @description 场景详情
 * @Version
 */

public class SceneDetailActivity extends BaseActivity<SceneDetailPresenter> implements SceneDetailContract.View {

    private SearchBean sceneBean;

    @BindView(R.id.action_bar)
    ActionBar mActionBar;
    @BindView(R.id.iv_company_logo)
    ImageView mIvCompanyLogo;
    @BindView(R.id.tv_success_probability)
    TextView mTvSuccessProbability;
    @BindView(R.id.tv_evaluat_count)
    TextView mTvEvaluateCount;
    @BindView(R.id.tv_bInfoCreditrating)
    TextView mTvBinfoCreditrating;
    @BindView(R.id.tv_evaluat_date)
    TextView mTvEvaluateDate;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_scene_detail;
    }

    @Override
    protected void initEventAndData() {
        sceneBean = (SearchBean) getIntent().getSerializableExtra(Constants.SCENE_BEAN);

        if(sceneBean==null) {
            ToastUtil.shortShow("数据为空，请返回上一页");
            return;
        }
        initView(sceneBean.getsInfoCustname(),sceneBean.getbInfoCreditrating(),sceneBean.getSuccessProbability(),sceneBean.getDataDate(),"0");

        showLoading("");
        mPresenter.getEnterpriseInfo(sceneBean.getUserId(),sceneBean.getDataDate(),sceneBean.getTrialCustId());
    }

    private void initView(String sInfoCustNameData,String bInfoCreditrating,String successProbalilityData,String dataData,String count){
        mActionBar.setTitle(sInfoCustNameData);
        mTvBinfoCreditrating.setText(bInfoCreditrating);

        String successProbability = BigDecimalUtil.formartDoubleStr(successProbalilityData,2)+"%";
        SpannableString successProbabilitySpan = TextViewUtils.getSpannableStringSize(successProbability,successProbability.length()-1,successProbability.length(),17);
        mTvSuccessProbability.setText(successProbabilitySpan);



        String dataDate = DateUtil.getDateString(dataData,DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS,DateUtil.FORMAT_YYYY_MM_DD);
        mTvEvaluateDate.setText(dataDate);


        String countStr = count+"次";
        SpannableString countSpann = TextViewUtils.getSpannableStringSize(countStr,countStr.length()-1,countStr.length(),14);
        mTvEvaluateCount.setText(countSpann);


    }

    @OnClick({R.id.rl_bond_atlas,R.id.rl_financing_plan,R.id.rl_my_evaluate,R.id.rl_subscibe})
    public void onClickOptionItem(View view){
        switch (view.getId()){
            case R.id.rl_bond_atlas://债券图谱
                StringBuilder sbUrlMap = new StringBuilder(Constants.HOST_URL+Constants.ASSOCIATION_MAP);
                sbUrlMap.append("?userId=").append(sceneBean.getUserId())
                        .append("&trialCustId=").append(sceneBean.getTrialCustId())
                        .append("&dataDate=").append(sceneBean.getDataDate());
                WebActivity.open(mContext,"",sbUrlMap.toString());
                break;
            case R.id.rl_financing_plan://融资方案
//                StringBuilder sbUrlPlan = new StringBuilder(Constants.FINANCING_PLAN_REPORT);
//                sbUrlPlan.append("?userId=").append(sceneBean.getUserId())
//                        .append("&trialCustId=").append(sceneBean.getTrialCustId())
//                        .append("&dataDate=").append(sceneBean.getDataDate());
//                WebActivity.open(mContext,"",sbUrlPlan.toString());
//                FinaningPlanReportActivity.open(mContext,sbUrlPlan.toString());
                showLoading("");
                mPresenter.getReportPdf(sceneBean.getUserId(),sceneBean.getTrialCustId(),sceneBean.getDataDate());
                break;
            case R.id.rl_my_evaluate://我的评测
                StringBuilder sbUrl = new StringBuilder(Constants.HOST_URL+Constants.EVALUATION_RESULT);
                sbUrl.append("?userId=").append(sceneBean.getUserId())
                        .append("&trialCustId=").append(sceneBean.getTrialCustId())
                        .append("&dataDate=").append(sceneBean.getDataDate());
                WebActivity.open(mContext,"",sbUrl.toString());
                break;
            case R.id.rl_subscibe://订阅管理
                SubscribeManageActivity.open(mContext);
                break;
        }
    }



    public static void open(Context context, SearchBean sceneBean){
        Intent intent = new Intent(context,SceneDetailActivity.class);
        intent.putExtra(com.huake.bondmaster.app.Constants.SCENE_BEAN,sceneBean);
        context.startActivity(intent);
    }


    @Override
    public void showContent(EnterpriseInfo enterpriseInfo) {
        initView(enterpriseInfo.getSInfoCustname(),enterpriseInfo.getBInfoCreditrating(),enterpriseInfo.getSuccessProbability(),enterpriseInfo.getDataDate(),enterpriseInfo.getEvaluateCount()+"");
    }

    @Override
    public void getPdfUrl(String pdfUrl) {
        FinaningPlanReportActivity.open(mContext,Constants.HOST_URL+pdfUrl);
    }
}
