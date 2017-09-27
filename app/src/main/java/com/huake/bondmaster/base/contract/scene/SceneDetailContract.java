package com.huake.bondmaster.base.contract.scene;

import com.huake.bondmaster.base.BasePresenter;
import com.huake.bondmaster.base.BaseView;
import com.huake.bondmaster.model.bean.EnterpriseInfo;

/**
 * @author will on 2017/8/26 11:27
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface SceneDetailContract {

    interface View extends BaseView{
        void showContent(EnterpriseInfo enterpriseInfo);

        void getPdfUrl(String pdfUrl);
    }

    interface Presenter extends BasePresenter<View>{
        void getEnterpriseInfo(String userId, String dataDate,String trialCustId);

        void getReportPdf(String userId,String tId,String dataDate);
    }

}
