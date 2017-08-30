package com.huake.bondmaster.ui.evaluation;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.common.design.MaterialDialog;
import com.huake.bondmaster.R;
import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.BaseActivity;
import com.huake.bondmaster.base.contract.evaluation.EvaluationContract;
import com.huake.bondmaster.model.bean.AreaNatureTypeBean;
import com.huake.bondmaster.model.bean.IndustryBean;
import com.huake.bondmaster.model.bean.PartyBean;
import com.huake.bondmaster.presenter.evaluation.EvaluationPresenter;
import com.huake.bondmaster.widget.ActionBar;

import org.jsoup.helper.StringUtil;

import java.util.ArrayList;
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
    @BindView(R.id.tv_industry)
    TextView mTvIndustry;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_company_nature)
    TextView mTvCompanyNature;
    @BindView(R.id.tv_company_type)
    TextView mTvComanyType;

    AreaNatureTypeBean areaNatureTypeBean;

    private static final String ADDRESS_TITLE = "请选择所属地区";
    private static final String COMPANY_NATURE = "请选择企业性质";
    private static final String COMPANY_TYPE = "请选择企业类型";

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

        getAreaNatureType("");
    }

    @OnClick({R.id.tv_industry,R.id.tv_address,R.id.tv_company_nature,R.id.tv_company_type})
    public void onOptionItemClick(View view){
        switch (view.getId()){
            case R.id.tv_industry:
                SelectIndustryActivity.open(mContext);
                break;
            case R.id.tv_address:
                getAreaNatureType(ADDRESS_TITLE);
                break;
            case R.id.tv_company_nature:
                getAreaNatureType(COMPANY_NATURE);
                break;
            case R.id.tv_company_type:
                getAreaNatureType(COMPANY_TYPE);
                break;
        }
    }

    private void getAreaNatureType(String title){
        if(areaNatureTypeBean==null){
            showLoading("");
            mPresenter.getAreaNatureTypeList(title);
        }else{
            showSelectDialog(title);
        }
    }

    private void showSelectDialog(final String title){
        if(!StringUtil.isBlank(title)) {

            new MaterialDialog.Builder(this)
                    .setTitle(title)
                    .setItems(getLists(title), new MaterialDialog.OnClickListener() {
                        @Override
                        public boolean onClick(DialogInterface dialog, int which) {
                            selectedItem(title,which);
                            return false;
                        }
                    }).create().show();
        }
    }

    private List<String> getLists(String title){
        List<String> datas = new ArrayList<>();
        if(title.equals(ADDRESS_TITLE)){
            List<AreaNatureTypeBean.ChinaAreasBean> chinaAreasBeanList = areaNatureTypeBean.getChinaAreas();
            for(int i = 0;i<chinaAreasBeanList.size();i++){
                datas.add(chinaAreasBeanList.get(i).getTitle());
            }
        }else if(title.equals(COMPANY_NATURE)){
            List<AreaNatureTypeBean.CompNaturesBean> comNatureList = areaNatureTypeBean.getCompNatures();
            for(int i = 0;i<comNatureList.size();i++){
                datas.add(comNatureList.get(i).getTitle());
            }
        }else if(title.equals(COMPANY_TYPE)){
            List<AreaNatureTypeBean.CompTypesBean> companyTypeList = areaNatureTypeBean.getCompTypes();
            for(int i = 0;i<companyTypeList.size();i++){
                datas.add(companyTypeList.get(i).getTitle());
            }
        }
        return datas;
    }
    //选中的地区、企业性质、企业类型
    private AreaNatureTypeBean.ChinaAreasBean selectedAddress;
    private AreaNatureTypeBean.CompNaturesBean selectedNature;
    private AreaNatureTypeBean.CompTypesBean selectedCompType;

    private void selectedItem(String title,int index){
        if(title.equals(ADDRESS_TITLE)){
            selectedAddress = areaNatureTypeBean.getChinaAreas().get(index);
            mTvAddress.setText(selectedAddress.getTitle());

        }else if(title.equals(COMPANY_NATURE)){
            selectedNature = areaNatureTypeBean.getCompNatures().get(index);
            mTvCompanyNature.setText(selectedNature.getTitle());
        }else if(title.equals(COMPANY_TYPE)){
            selectedCompType = areaNatureTypeBean.getCompTypes().get(index);
            mTvComanyType.setText(selectedCompType.getTitle());
        }
    }


    @Override
    public void showContent() {

    }

    @Override
    public void setAreaNatureType(AreaNatureTypeBean areaNatureType,String title) {
        this.areaNatureTypeBean = areaNatureType;
        showSelectDialog(title);
    }

    @Override
    public void setCompanyNameList(List<PartyBean> partyBeanList) {

    }

    public static void open(Context context){
        Intent intent = new Intent(context,EvaluationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data ==null){
            return;
        }
        if(requestCode == Constants.SELECT_INDUSTRY_REQUEST_CODE){//显示选择的所属行业
            IndustryBean childIndustryBean = (IndustryBean) data.getSerializableExtra(Constants.INDUSTRY_SELECTED);
            IndustryBean parentIndustryBean = (IndustryBean) data.getSerializableExtra(Constants.PARENT_INDUSTRY_SELECTED);

            mTvIndustry.setText(childIndustryBean.getTitle());
        }
    }
}
