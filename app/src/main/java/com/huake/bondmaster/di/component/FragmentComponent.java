package com.huake.bondmaster.di.component;

import android.app.Activity;

import com.huake.bondmaster.di.module.FragmentModule;
import com.huake.bondmaster.di.scope.FragmentScope;
import com.huake.bondmaster.ui.main.fragment.HomeFragment;
import com.huake.bondmaster.ui.main.fragment.MarketFragment;
import com.huake.bondmaster.ui.main.fragment.MyFragment;
import com.huake.bondmaster.ui.main.fragment.SceneFragment;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(MyFragment myFragment);

    void inject(SceneFragment sceneFragment);

    void inject(HomeFragment homeFragment);

    void inject(MarketFragment marketFragment);
//
//    void inject(CommentFragment longCommentFragment);
//
//    void inject(TechFragment techFragment);
//
//    void inject(GirlFragment girlFragment);
//
//    void inject(LikeFragment likeFragment);
//
//    void inject(WechatMainFragment wechatMainFragment);
//
//    void inject(SettingFragment settingFragment);
//
//    void inject(GoldMainFragment goldMainFragment);
//
//    void inject(GoldPagerFragment goldPagerFragment);
//
//    void inject(VtexPagerFragment vtexPagerFragment);
}
