package com.huake.bondmaster.base.contract.scene;

import com.huake.bondmaster.base.BasePresenter;
import com.huake.bondmaster.base.BaseView;

/**
 * @author will on 2017/8/26 11:27
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface FinaningPlanReportContract {

    interface View extends BaseView{
        void showContent(String pdfUrl);
    }

    interface Presenter extends BasePresenter<View>{
        void getReportPdfUrl(String path);
    }

}
