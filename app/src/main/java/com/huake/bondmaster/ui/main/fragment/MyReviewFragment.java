package com.huake.bondmaster.ui.main.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.App;
import com.huake.bondmaster.base.SimpleFragment;
import com.huake.bondmaster.model.bean.UserBean;

import butterknife.BindView;

/**
 * @author will on 2017/9/1 22:07
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class MyReviewFragment extends SimpleFragment {

    @BindView(R.id.content)
    FrameLayout frameLayout;

    MyFragment myFragment;
    MyUnLoginFragment myUnLoginFragment;
    private boolean isFirst = false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_review;
    }

    @Override
    protected void initEventAndData() {
        changeFragment();
        isFirst = true;
    }


    private void changeFragment() {
        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        UserBean userBean = App.getInstance().getUserBeanInstance();

        if (userBean != null) {
            if(myFragment==null) {
                myFragment = new MyFragment();
            }else{
                if(myFragment.isVisible()){
                    return;
                }
            }
            transaction.replace(R.id.content, myFragment);
        } else {
            if(myUnLoginFragment==null) {
                myUnLoginFragment = new MyUnLoginFragment();
            }else{
                if(myUnLoginFragment.isVisible()){
                    return;
                }
            }
            transaction.replace(R.id.content, myUnLoginFragment);
        }
        transaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!isFirst) {
            changeFragment();
        }
        isFirst = false;
    }
}
