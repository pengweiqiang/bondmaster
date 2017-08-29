package com.huake.bondmaster.di.component;


import com.huake.bondmaster.app.App;
import com.huake.bondmaster.di.module.AppModule;
import com.huake.bondmaster.di.module.HttpModule;
import com.huake.bondmaster.model.DataManager;
import com.huake.bondmaster.model.db.RealmHelper;
import com.huake.bondmaster.model.http.RetrofitHelper;
import com.huake.bondmaster.model.prefs.ImplPreferencesHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by pengweiqiang on 16/8/7.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper retrofitHelper();  //提供http的帮助类

    RealmHelper realmHelper();    //提供数据库帮助类

    ImplPreferencesHelper preferencesHelper(); //提供sp帮助类
}
