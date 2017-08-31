package com.huake.bondmaster.base.contract.evaluation;

import android.content.Context;

import com.huake.bondmaster.base.BasePresenter;
import com.huake.bondmaster.base.BaseView;
import com.huake.bondmaster.model.bean.AreaNatureTypeBean;
import com.huake.bondmaster.model.bean.PartyBean;

import java.util.List;
import java.util.Map;

/**
 * @author will on 2017/8/26 11:27
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface EvaluationContract {

    interface View extends BaseView{
        void showContent();


        void setAreaNatureType(AreaNatureTypeBean areaNatureType,String title);

        void setCompanyNameList(List<PartyBean.PartyListBean> partyBeanList);
    }

    interface Presenter extends BasePresenter<View>{
        void searchCompanyByName(String companyName);

        void getCompanyNameListByUserId(String userId);

        void getAreaNatureTypeList(String title);


        void startNext(Context context, Map<String,String> params);
    }

}
