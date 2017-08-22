package com.huake.bondmaster.di.module;


import com.huake.bondmaster.app.App;
import com.huake.bondmaster.model.DataManager;
import com.huake.bondmaster.model.db.DBHelper;
import com.huake.bondmaster.model.db.RealmHelper;
import com.huake.bondmaster.model.http.HttpHelper;
import com.huake.bondmaster.model.http.RetrofitHelper;
import com.huake.bondmaster.model.prefs.ImplPreferencesHelper;
import com.huake.bondmaster.model.prefs.PreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by codeest on 16/8/7.
 */

@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper(RealmHelper realmHelper) {
        return realmHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(ImplPreferencesHelper implPreferencesHelper) {
        return implPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, DBHelper DBHelper, PreferencesHelper preferencesHelper) {
        return new DataManager(httpHelper, DBHelper, preferencesHelper);
    }
}
