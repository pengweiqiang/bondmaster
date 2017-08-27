package com.huake.bondmaster.di.component;

import android.app.Activity;

import com.huake.bondmaster.di.module.ActivityModule;
import com.huake.bondmaster.di.scope.ActivityScope;
import com.huake.bondmaster.ui.main.activity.MainActivity;
import com.huake.bondmaster.ui.main.activity.WelcomeActivity;
import com.huake.bondmaster.ui.scene.SceneDetailActivity;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(WelcomeActivity welcomeActivity);

    void inject(MainActivity mainActivity);

    void inject(SceneDetailActivity sceneDetailActivity);

}
