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
import com.huake.bondmaster.model.bean.SceneBean;
import com.huake.bondmaster.presenter.scene.SceneDetailPresenter;
import com.huake.bondmaster.util.TextViewUtils;
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

    private SceneBean sceneBean;

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
        sceneBean = (SceneBean) getIntent().getSerializableExtra(Constants.SCENE_BEAN);
        initView();
    }

    private void initView(){
        if(sceneBean!=null){
            mActionBar.setTitle(sceneBean.getsInfoCustname());
            String bInfoCreditrating = sceneBean.getbInfoCreditrating();
            String bInfoCreditratingFirst = "主体级别预估：";
            String bInfoCreditratingStr = bInfoCreditratingFirst+bInfoCreditrating;
            SpannableString bInfoCreditratingSpan = TextViewUtils.getSpannableStringSize(bInfoCreditratingStr,bInfoCreditratingFirst.length(),bInfoCreditratingFirst.length()+bInfoCreditrating.length(),18);
            mTvBinfoCreditrating.setText(bInfoCreditratingSpan);

            String successProbability = sceneBean.getSuccessProbability();
            String successProbabilityFirst = "发债成功概率：";
            String successProbabilityStr = successProbabilityFirst+successProbability;
            SpannableString successProbabilitySpan = TextViewUtils.getSpannableStringSize(successProbabilityStr,successProbabilityFirst.length(),successProbabilityFirst.length()+successProbability.length(),18);
            mTvSuccessProbability.setText(successProbabilitySpan);


//            String bInfoCreditrating = sceneBean.getbInfoCreditrating();
//            String bInfoCreditratingFirst = "主体级别预估：";
//            String bInfoCreditratingStr = bInfoCreditratingFirst+bInfoCreditrating;
//            SpannableString bInfoCreditratingSpan = TextViewUtils.getSpannableStringSize(bInfoCreditratingStr,bInfoCreditratingFirst.length(),bInfoCreditratingFirst.length()+bInfoCreditrating.length(),17);
//            mTvBinfoCreditrating.setText(bInfoCreditratingSpan);


        }
    }

    @OnClick({R.id.rl_bond_atlas,R.id.rl_financing_plan,R.id.rl_my_evaluate,R.id.rl_subscibe})
    public void onClickOptionItem(View view){
        switch (view.getId()){
            case R.id.rl_bond_atlas:

                break;
            case R.id.rl_financing_plan:

                break;
            case R.id.rl_my_evaluate:

                break;
            case R.id.rl_subscibe:

                break;
        }
    }

    @Override
    public void showContent(SceneBean sceneBean) {

    }

    public static void open(Context context, SceneBean sceneBean){
        Intent intent = new Intent(context,SceneDetailActivity.class);
        intent.putExtra(com.huake.bondmaster.app.Constants.SCENE_BEAN,sceneBean);
        context.startActivity(intent);
    }

}
