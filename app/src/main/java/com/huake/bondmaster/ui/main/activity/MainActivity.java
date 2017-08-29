package com.huake.bondmaster.ui.main.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.RadioGroup;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.App;
import com.huake.bondmaster.base.BaseActivity;
import com.huake.bondmaster.base.contract.main.MainContract;
import com.huake.bondmaster.presenter.main.MainPresenter;
import com.huake.bondmaster.ui.main.adapter.MainFragmentAdapter;
import com.huake.bondmaster.ui.main.fragment.HomeFragment;
import com.huake.bondmaster.ui.main.fragment.MarketFragment;
import com.huake.bondmaster.ui.main.fragment.MyFragment;
import com.huake.bondmaster.ui.main.fragment.SceneFragment;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    HomeFragment homeFragment;
    SceneFragment sceneFragment;
    MarketFragment marketFragment;
    MyFragment myFragment;

    private int showIndex = 0;//显示fragment的索引
    int[] tabIds = {R.id.home,R.id.scene,R.id.market,R.id.my};


    private List<Fragment> fragmentList;
    MainFragmentAdapter mainFragmentAdapter;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;


    @BindView(R.id.tabIndicators)
    RadioGroup mTabIndicators ;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initEventAndData() {
        myFragment = new MyFragment();
        homeFragment = new HomeFragment();
        marketFragment = new MarketFragment();
        sceneFragment = new SceneFragment();

        fragmentList = new ArrayList<>();

        fragmentList.add(homeFragment);
        fragmentList.add(sceneFragment);
        fragmentList.add(marketFragment);
        fragmentList.add(myFragment);
        mainFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager(),fragmentList);

        mViewPager.setAdapter(mainFragmentAdapter);

        initListener();
    }



    public static void open(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        mTabIndicators.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index = 0;
                if(checkedId==tabIds[0]){
                    index = 0;
                }else if(checkedId==tabIds[1]){
                    index = 1;
                }else if(checkedId == tabIds[2]){
                    index = 2;
                }else if(checkedId == tabIds[3]){
                    index = 3;
                }
                showIndex = index;
                mViewPager.setCurrentItem(index,false);
            }
        });
    }


    private boolean isExit = false;
    // 退出操作
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isExit == false) {
                isExit = true;
                handler.sendEmptyMessageDelayed(0, 3000);
                showErrorMsg("再按一次进行退出");
                return true;
            } else {
                App.getInstance().exitApp();
                return false;
            }
        }
        return true;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(handler!=null) {
            handler.removeCallbacksAndMessages(null);
        }
        UMShareAPI.get(this).release();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}
