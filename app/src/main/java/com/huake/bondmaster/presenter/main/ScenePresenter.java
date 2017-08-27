package com.huake.bondmaster.presenter.main;

import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.main.SceneContract;
import com.huake.bondmaster.model.DataManager;
import com.huake.bondmaster.model.bean.PageBean;
import com.huake.bondmaster.model.bean.SceneBean;
import com.huake.bondmaster.model.http.response.BondMasterHttpResponse;
import com.huake.bondmaster.util.RxUtil;
import com.huake.bondmaster.widget.CommonSubscriber;

import javax.inject.Inject;

/**
 * @author will on 2017/8/25 11:24
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class ScenePresenter extends RxPresenter<SceneContract.View> implements SceneContract.Presenter {
    private DataManager mDataManager;

    @Inject
    public ScenePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void getSceneList(String userId, long pageNum, String sInfoCustname, String secIndCode, String bAgencyGuarantornature, String bInfoCreditrating) {
        addSubscribe(mDataManager.getScenceList(userId,pageNum, com.huake.bondmaster.app.Constants.PAGE_SIZE,sInfoCustname,secIndCode,bAgencyGuarantornature,bInfoCreditrating)
                .compose(RxUtil.<BondMasterHttpResponse<PageBean<SceneBean>>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<PageBean<SceneBean>>(mView, true) {
                    @Override
                    public void dataHandler(PageBean<SceneBean> pageBean) {
                        if(pageBean!=null) {
                            mView.showContent(pageBean.getRecords(),pageBean.getRows());
                        }else{
                            mView.showErrorMsg("获取结果为空");
                        }
                    }

                })
        );

//        List<SceneBean> mList = new ArrayList<>();
//        SceneBean sceneBean = new SceneBean();
//        sceneBean.setbInfoCreditrating("123");
//        sceneBean.setsInfoCustname("北京水电费");
//        sceneBean.setSuccessProbability("312");
//
//        SceneBean sceneBean2 = new SceneBean();
//        sceneBean2.setbInfoCreditrating("A+~A++");
//        sceneBean2.setsInfoCustname("北京水水电费水电费水电费电费");
//        sceneBean2.setSuccessProbability("100%");
//
//        mList.add(sceneBean);
//        mList.add(sceneBean2);
//        mView.showContent(2,mList);
    }
}
