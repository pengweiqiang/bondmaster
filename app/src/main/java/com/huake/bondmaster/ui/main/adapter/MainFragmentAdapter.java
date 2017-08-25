package com.huake.bondmaster.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author will on 2017/8/24 21:19
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class MainFragmentAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragmentList;

    public MainFragmentAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragmentList = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}
