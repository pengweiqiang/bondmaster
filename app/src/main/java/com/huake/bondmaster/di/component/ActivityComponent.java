package com.huake.bondmaster.di.component;

import android.app.Activity;

import com.huake.bondmaster.di.module.ActivityModule;
import com.huake.bondmaster.di.scope.ActivityScope;
import com.huake.bondmaster.ui.evaluation.EvaluationActivity;
import com.huake.bondmaster.ui.evaluation.EvaluationDebtInfoActivity;
import com.huake.bondmaster.ui.evaluation.EvaluationInputFinanceInfoActivity;
import com.huake.bondmaster.ui.evaluation.SelectIndustryActivity;
import com.huake.bondmaster.ui.main.activity.ArticleListActivity;
import com.huake.bondmaster.ui.main.activity.MainActivity;
import com.huake.bondmaster.ui.main.activity.SearchTrialCustInfoActivity;
import com.huake.bondmaster.ui.main.activity.WelcomeActivity;
import com.huake.bondmaster.ui.my.LoginActivity;
import com.huake.bondmaster.ui.my.RegisterActivity;
import com.huake.bondmaster.ui.scene.SceneDetailActivity;
import com.huake.bondmaster.ui.web.WebActivity;

import dagger.Component;

/**
 * Created by pengweiqiang on 16/8/7.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(WelcomeActivity welcomeActivity);

    void inject(MainActivity mainActivity);

    void inject(SearchTrialCustInfoActivity searchTrialCustInfoActivity);

    void inject(SceneDetailActivity sceneDetailActivity);

    void inject(LoginActivity loginActivity);

    void inject(RegisterActivity registerActivity);

    void inject(WebActivity webActivity);

    void inject(EvaluationActivity evaluationActivity);

    void inject(SelectIndustryActivity selectIndustryActivity);

    void inject(EvaluationInputFinanceInfoActivity evaluationInputFinanceInfoActivity);

    void inject(EvaluationDebtInfoActivity evaluationDebtInfoActivity);

    void inject(ArticleListActivity articleListActivity);

}
