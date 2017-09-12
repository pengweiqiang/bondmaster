package com.huake.bondmaster.ui.main.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.App;
import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.RootFragment;
import com.huake.bondmaster.base.contract.main.MarketContract;
import com.huake.bondmaster.model.bean.UserBean;
import com.huake.bondmaster.presenter.main.MarketPresenter;
import com.huake.bondmaster.ui.web.NativeJsInternation;
import com.huake.bondmaster.util.LogUtil;
import com.huake.bondmaster.widget.ActionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;

/**
 * @author will on 2017/8/24 13:42
 * @email pengweiqiang64@163.com
 * @description 市场
 * @Version
 */

public class MarketFragment extends RootFragment<MarketPresenter> implements MarketContract.View {

    @BindView(R.id.wv_tech_content)
    WebView mWebView;
    @BindView(R.id.tv_progress)
    TextView tvProgress;
    @BindView(R.id.action_bar)
    ActionBar mActionBar;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    private String webUrl = Constants.HOST_URL+Constants.MARKET_URL;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_market;
    }


    @Override
    protected void initEventAndData() {

        mActionBar.setTitle("市场分析");
        mActionBar.hideLeftAction();
        initListener();

        WebSettings settings = mWebView.getSettings();
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        mWebView.addJavascriptInterface(new NativeJsInternation(mContext), "native");

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtil.i("MarketFragment"+url);
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
//                super.onReceivedSslError(webView, sslErrorHandler, sslError);
                sslErrorHandler.proceed();//接受信任所有网站的证书
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                mWebView.loadUrl("javascript:window.native.getBody(document.getElementsByTagName('pre')[0].innerHTML);");
            }

            @Override
            public void onReceivedError(WebView webView, int i, String s, String s1) {
//                LogUtil.i("onReceivedError "+i+"\n"+s+"   \n"+s1);
                super.onReceivedError(webView, i, s, s1);
            }

            @Override
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
//                LogUtil.i("onReceivedError "+webResourceRequest.getMethod()+"\n"+webResourceError.getErrorCode()+"   \n"+webResourceError.getDescription());
                super.onReceivedError(webView, webResourceRequest, webResourceError);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (tvProgress == null)
                    return;
                if (newProgress >= 100) {
                    loadFinish();
                    tvProgress.setVisibility(View.GONE);
                } else {
                    tvProgress.setVisibility(View.VISIBLE);
                    ViewGroup.LayoutParams lp = tvProgress.getLayoutParams();
                    lp.width = App.SCREEN_WIDTH * newProgress / 100;
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
//                mActionBar.setTitle(title);
//                setTitle(title);
            }

        });
        loadUrlAddToken();
        mWebView.loadUrl(webUrl);
    }

    private void initListener(){
        smartRefreshLayout.setEnableLoadmore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadUrlAddToken();
                mWebView.loadUrl(webUrl);
            }
        });
    }
    private void loadFinish(){
        smartRefreshLayout.finishRefresh();
        if(mWebView.getVisibility() == View.GONE){
            mWebView.setVisibility(View.VISIBLE);
        }
    }

    public void loadUrlAddToken(){
        UserBean userBean = App.getInstance().getUserBeanInstance();
        if(userBean==null){
            return;
        }
        if (webUrl.contains("?")) {
            if(webUrl.contains("token=")){
                webUrl = webUrl.replaceAll(webUrl.substring(webUrl.indexOf("token=")),"token="+userBean.getToken());
            }
        }else{
            webUrl = webUrl+"?token="+userBean.getToken();
        }
    }

    public void loadUrl(final String webUrl){
        this.webUrl = webUrl;
        if(mActivity!=null && mWebView != null) {
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mWebView.loadUrl(webUrl);
                }
            });
        }
    }

    public void refreshUrl(){
        if(mActivity!=null) {
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mWebView.setVisibility(View.GONE);
                }
            });
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(isVisibleToUser && mWebView.getVisibility() == View.GONE){
            loadUrlAddToken();
            mWebView.loadUrl(webUrl);
        }
    }

    @Override
    public void onDestroy() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

    @Override
    public void showContent() {

    }
}
