package com.huake.bondmaster.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author will on 2017/8/24 10:33
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class CustomViewPager extends ViewPager{

    private boolean isScroll = false;//是否可以滑动


    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isScroll;
    }
}
