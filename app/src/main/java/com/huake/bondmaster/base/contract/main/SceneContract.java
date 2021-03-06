package com.huake.bondmaster.base.contract.main;

import com.huake.bondmaster.base.BasePresenter;
import com.huake.bondmaster.base.BaseView;
import com.huake.bondmaster.model.bean.SceneBean;

import java.util.List;

/**
 * @author will on 2017/8/25 11:23
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface SceneContract {
    interface View extends BaseView{


        void showContent(long records,long pageNum,long total,List<SceneBean> sceneBeanList);

        void initListener();

        void showCompanyCount(String count);

        void showEmptyMsg(String msg);
    }

    interface  Presenter extends BasePresenter<View>{
        void getSceneList(String userId,long pageNum,String sInfoCustname,String secIndCode,String bAgencyGuarantornature,String bInfoCreditrating);
    }

}
