package com.huake.bondmaster.ui.web;

import android.content.Context;
import android.webkit.JavascriptInterface;

import com.huake.bondmaster.Constants;
import com.huake.bondmaster.app.App;
import com.huake.bondmaster.base.BaseActivity;
import com.huake.bondmaster.ui.main.activity.MainActivity;
import com.huake.bondmaster.ui.market.HkMarketOverviewActivity;
import com.huake.bondmaster.ui.scene.FinaningPlanReportActivity;
import com.huake.bondmaster.util.LogUtil;

import org.json.JSONObject;

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
    public void calculateForJS(String url) {
        String token = "";
        if(App.getInstance().getUserBeanInstance()!=null){
            token = App.getInstance().getUserBeanInstance().getToken();
            if(url.contains("?")){
                url = url + "&token="+token;
            }else{
                url = url + "?token="+token;
            }
        }
        LogUtil.i(url);
        if(url.contains(com.huake.bondmaster.app.Constants.FINANCING_PLAN_REPORT)){
            //融资方案
            FinaningPlanReportActivity.open(context,Constants.HOST_URL + url);
            return;
        }
        if(context instanceof WebActivity) {
            ((WebActivity) context).loadUrl(Constants.HOST_URL + url);
        }else if(context instanceof MainActivity){
            //查看债种今日新发债
            if(url.contains(com.huake.bondmaster.app.Constants.HK_MARKET_OVERVIEW)){
                HkMarketOverviewActivity.open(context,url);
            }else {
                ((MainActivity) context).loadUrl(Constants.HOST_URL + url);
            }
        }else if(context instanceof BaseActivity){
            ((WebActivity) context).loadUrl(Constants.HOST_URL + url);
        }

    }

    @JavascriptInterface
    public void getBody(String body){
        try{
            JSONObject bodyJson = new JSONObject(body);
            String stat = bodyJson.getString("stat");
            if(stat.equals(com.huake.bondmaster.app.Constants.CODE_INVALID_TOKEN+"")){
                ((MainActivity)context).refreshMarketFragment();
            }
        }catch (Exception e){

        }
        LogUtil.i(body);
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
