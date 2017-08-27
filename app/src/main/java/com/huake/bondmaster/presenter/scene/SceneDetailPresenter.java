package com.huake.bondmaster.presenter.scene;

import com.huake.bondmaster.base.RxPresenter;
import com.huake.bondmaster.base.contract.scene.SceneDetailContract;
import com.huake.bondmaster.model.DataManager;

import javax.inject.Inject;

/**
 * @author will on 2017/8/26 11:30
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class SceneDetailPresenter extends RxPresenter<SceneDetailContract.View> implements SceneDetailContract.Presenter {
    private DataManager dataManager;

    @Inject
    public SceneDetailPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getSceneInfo(long id) {

    }
}
