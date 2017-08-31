package com.huake.bondmaster.ui.web;

import android.content.Context;
import android.webkit.JavascriptInterface;

import com.huake.bondmaster.ui.my.LoginActivity;
import com.huake.bondmaster.util.LogUtil;

/**
 * @author will on 2017/8/31 10:32
 * @email pengweiqiang64@163.com
 * @description js调用方法跳转
 * @Version
 */

public class NativeJsInternation {

    private Context context;

    public NativeJsInternation(Context context){
        this.context = context;
    }

    @JavascriptInterface
    public void calculateForJS(String i) {
        LoginActivity.open(context,"123123123");
        LogUtil.i(i);
    }

    @JavascriptInterface
    public void logs(String i) {
        LogUtil.i(i);
    }

    @JavascriptInterface
    public void pushViewControllerTitle(String s1,String s2){
        LogUtil.i(s1+"  "+s2);
    }

}