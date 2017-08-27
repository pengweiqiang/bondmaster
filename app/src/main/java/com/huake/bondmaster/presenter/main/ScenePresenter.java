package com.huake.bondmaster.presenter.main;

import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.main.SceneContract;
import com.huake.bondmaster.model.DataManager;
import com.huake.bondmaster.model.bean.SceneBean;

import java.util.ArrayList;
import java.util.List;

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
    public void getSceneList() {
//        addSubscribe(mDataManager.getScenceList()
//                .compose(RxUtil.<BondMasterHttpResponse<List<SceneBean>>>rxSchedulerHelper())
//                .map(new Function<BondMasterHttpResponse<List<SceneBean>, BondMasterHttpResponse<List<SceneBean>>() {
//                    @Override
//                    public BondMasterHttpResponse<List<SceneBean> apply(BondMasterHttpResponse<List<SceneBean> bondMasterHttpResponse) {
//                        List<DailyListBean.StoriesBean> list = dailyListBean.getStories();
//                        for(DailyListBean.StoriesBean item : list) {
//                            item.setReadState(mDataManager.queryNewsId(item.getId()));
//                        }
//                        return dailyListBean;
//                    }
//                })
//                .subscribeWith(new CommonSubscriber<BondMasterHttpResponse<List<SceneBean>>(mView) {
//                    @Override
//                    public void onNext(DailyListBean dailyListBean) {
//                        topCount = dailyListBean.getTop_stories().size();
//                        mView.showContent(dailyListBean);
//                    }
//                })
//        );

        List<SceneBean> mList = new ArrayList<>();
        SceneBean sceneBean = new SceneBean();
        sceneBean.setbInfoCreditrating("123");
        sceneBean.setsInfoCustname("北京水电费");
        sceneBean.setSuccessProbability("312");

        SceneBean sceneBean2 = new SceneBean();
        sceneBean2.setbInfoCreditrating("A+~A++");
        sceneBean2.setsInfoCustname("北京水水电费水电费水电费电费");
        sceneBean2.setSuccessProbability("100%");

        mList.add(sceneBean);
        mList.add(sceneBean2);
        mView.showContent(mList);
    }
}
