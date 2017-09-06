package com.huake.bondmaster.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.AttributeSet;
import android.view.KeyEvent;

/**
 * @author will on 2017/8/31 18:06
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class MyAutoCompleteTextView extends AppCompatAutoCompleteTextView {

    public MyAutoCompleteTextView(Context context) {
        super(context);
    }

    public MyAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused && getAdapter() != null) {
            performFiltering(getText(), KeyEvent.KEYCODE_UNKNOWN);
        }
    }

    @Override
    public boolean enoughToFilter() {
        return true;
    }
}
