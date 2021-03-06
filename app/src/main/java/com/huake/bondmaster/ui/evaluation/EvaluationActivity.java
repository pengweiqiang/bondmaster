package com.huake.bondmaster.ui.evaluation;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.common.design.MaterialDialog;
import com.huake.bondmaster.R;
import com.huake.bondmaster.app.App;
import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.BaseActivity;
import com.huake.bondmaster.base.contract.evaluation.EvaluationContract;
import com.huake.bondmaster.model.bean.AreaNatureTypeBean;
import com.huake.bondmaster.model.bean.IndustryBean;
import com.huake.bondmaster.model.bean.PartyBean;
import com.huake.bondmaster.presenter.evaluation.EvaluationPresenter;
import com.huake.bondmaster.ui.main.adapter.AutoCompleteAdapter;
import com.huake.bondmaster.widget.ActionBar;
import com.huake.bondmaster.widget.MyAutoCompleteTextView;

import org.jsoup.helper.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @BindView(R.id.et_company_name)
    MyAutoCompleteTextView mEtCompanyName;
    @BindView(R.id.btn_next)
    Button mBtnNext;

    AreaNatureTypeBean areaNatureTypeBean;

    private static final String ADDRESS_TITLE = "请选择所属地区";
    private static final String COMPANY_NATURE = "请选择企业性质";
    private static final String COMPANY_TYPE = "请选择企业类型";

    private List<PartyBean.PartyListBean> partyBeanList = new ArrayList<>();
//    private ArrayAdapter<PartyBean.PartyListBean> partyBeanArrayAdapter;
    AutoCompleteAdapter<PartyBean.PartyListBean> autoCompleteAdapter;

    private List<PartyBean.PartyListBean> partyListBeanListByUserId = new ArrayList<>();

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

        initListener();

        autoCompleteAdapter = new AutoCompleteAdapter<>(mContext,android.R.layout.simple_list_item_1,partyBeanList);
//        partyBeanArrayAdapter = new ArrayAdapter<PartyBean.PartyListBean>(mContext,android.R.layout.simple_list_item_1,partyBeanList);
        mEtCompanyName.setAdapter(autoCompleteAdapter);


        mPresenter.getCompanyNameListByUserId(App.getInstance().getUserBeanInstance().getId());
        getAreaNatureType("");

    }
    private PartyBean.PartyListBean partyListBean;
    private void initListener(){
        mEtCompanyName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mEtCompanyName.showDropDown();
                }
            }
        });
        mEtCompanyName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position<partyBeanList.size()) {
                    partyListBean = partyBeanList.get(position);
                    String companyName = mEtCompanyName.getText().toString().trim();
                    if(!StringUtil.isBlank(companyName)) {
                        mPresenter.getEnterpriseInfo(companyName);
                    }
                    mEtCompanyName.dismissDropDown();
                }

            }
        });
        mEtCompanyName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setEnterpriseInfo(null);
                if(TextUtils.isEmpty(s.toString())){
                    setCompanyNameList(partyListBeanListByUserId,true);
                    partyListBean = null;
                }else {
                    mPresenter.searchCompanyByName(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.tv_industry,R.id.tv_address,R.id.tv_company_nature,R.id.tv_company_type,R.id.btn_next})
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
            case R.id.btn_next:
                String companyName = mEtCompanyName.getText().toString().trim();
//                if(partyListBean==null){
//                    String msg = "请选择公司名称";
//                    if(!TextUtils.isEmpty(companyName)){
//                        msg = "请输入完整的公司名称";
//                    }
//                    showErrorMsg(msg);
//                    return;
//                }
                if(TextUtils.isEmpty(companyName)){
                    showErrorMsg("请选择公司名称");
                    return;
                }
                if(childIndustryBean==null){
                    showErrorMsg("请选择行业");
                    return;
                }
                if(selectedAddress == null){
                    showErrorMsg("请选择地区");
                    return;
                }
                if(selectedNature == null){
                    showErrorMsg("请选择企业性质");
                    return;
                }
                if(selectedCompType == null){
                    showErrorMsg("请选择企业类型");
                    return;
                }
                Map<String,String> params = new HashMap<>();
                params.put("sInfoCustname",companyName);
                params.put("bAgencyGuarantornature",selectedNature.getId());
                params.put("sInfoComptype",selectedCompType.getId());
                params.put("sInfoProvince",selectedAddress.getId());
                params.put("sInfoCountry","中国");
                params.put("secIndCode1",parentIndustryBean.getId());
                params.put("secIndCode2",childIndustryBean.getId());

                mPresenter.startNext(mContext,params,partyListBeanSystem);
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

    //选中的行业
    private IndustryBean parentIndustryBean;
    private IndustryBean childIndustryBean;

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
    public void setCompanyNameList(List<PartyBean.PartyListBean> partyBeanList,boolean isUserId) {
        if(isUserId){
            this.partyListBeanListByUserId = partyBeanList;
        }
        this.partyBeanList = partyBeanList;
//        autoCompleteAdapter.setListObjects(partyBeanList);
        autoCompleteAdapter = new AutoCompleteAdapter<>(mContext,android.R.layout.simple_list_item_1,partyBeanList);
        mEtCompanyName.setAdapter(autoCompleteAdapter);
        if(partyBeanList!=null && partyBeanList.size() == 1 && partyBeanList.get(0) != null && mEtCompanyName.getText().toString().trim().equals(partyBeanList.get(0).getPartyName())){
            return;
        }
        mEtCompanyName.showDropDown();
    }

    private PartyBean.PartyListBean partyListBeanSystem;//后台返回的公司信息
    @Override
    public void setEnterpriseInfo(PartyBean.PartyListBean partyListBean) {
        this.partyListBeanSystem = partyListBean;
        if(partyListBean!=null){
            //TODO 获取选中信息的id
            selectedAddress = new AreaNatureTypeBean.ChinaAreasBean();
            selectedAddress.setTitle(partyListBean.getSInfoProvince());
            selectedAddress.setId(partyListBean.getSInfoProvince());
            mTvAddress.setText(partyListBean.getSInfoProvince());

            selectedNature = new AreaNatureTypeBean.CompNaturesBean();
            selectedNature.setId(partyListBean.getBAgencyGuarantornature());
            selectedNature.setTitle(partyListBean.getbAgencyGuarantornatureName());
            mTvCompanyNature.setText(partyListBean.getbAgencyGuarantornatureName());

            selectedCompType = new AreaNatureTypeBean.CompTypesBean();
            selectedCompType.setTitle(partyListBean.getSInfoComptype());
            selectedCompType.setId(partyListBean.getSInfoComptype());
            mTvComanyType.setText(partyListBean.getSInfoComptype());


            parentIndustryBean = new IndustryBean();
            parentIndustryBean.setId(partyListBean.getSecIndCode1());

            childIndustryBean = new IndustryBean();
            childIndustryBean.setId(partyListBean.getSecIndCode2());
            childIndustryBean.setTitle(partyListBean.getSecIndCode2Name());

            mTvIndustry.setText(partyListBean.getSecIndCode2Name());

        }else{
            selectedAddress = null;
            mTvAddress.setText("");
            selectedNature = null;
            mTvCompanyNature.setText("");

            selectedCompType = null;
            mTvComanyType.setText("");

            parentIndustryBean = null;
            childIndustryBean = null;
            mTvIndustry.setText("");
        }
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
            childIndustryBean = (IndustryBean) data.getSerializableExtra(Constants.INDUSTRY_SELECTED);
            parentIndustryBean = (IndustryBean) data.getSerializableExtra(Constants.PARENT_INDUSTRY_SELECTED);

            mTvIndustry.setText(childIndustryBean.getTitle());
        }
    }
}
