package com.huake.bondmaster.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.huake.bondmaster.util.SystemUtil;


/**
 * Created by codeest on 16/12/24.
 */

public class CommonItemDecoration extends RecyclerView.ItemDecoration{

    private int distance;
    private int left;
    private int right;
    private int unit;
    private boolean isShowHeader = true;

    public static final int UNIT_DP = 0;
    public static final int UNIT_PX = 1;

    public CommonItemDecoration(int distance, int unit) {
        this.distance = distance;
        this.unit = unit;
    }
    public CommonItemDecoration(int distance,int left,int right,int unit){
        this.distance = distance;
        this.unit = unit;
        this.left = left;
        this.right = right;
    }
    public void setIsShowHeader(boolean isShowHeader){
        this.isShowHeader = isShowHeader;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if(!isShowHeader && position == 0){

        }else if (position > -1) {
            if (unit == UNIT_DP) {
                outRect.set(SystemUtil.dp2px(left), SystemUtil.dp2px(distance), SystemUtil.dp2px(right), 0);
            } else {
                outRect.set(left, distance, right, 0);
            }
        }
    }
}
