package com.huake.bondmaster.di.module;

import android.app.Activity;


import com.huake.bondmaster.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pengweiqiang on 16/8/7.
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
