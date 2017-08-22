package com.huake.bondmaster.di.component;

import android.app.Activity;

import com.huake.bondmaster.MainActivity;
import com.huake.bondmaster.di.module.ActivityModule;
import com.huake.bondmaster.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

//    void inject(WelcomeActivity welcomeActivity);

    void inject(MainActivity mainActivity);

}
