package com.huake.bondmaster.ui.web;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.App;
import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.BaseActivity;
import com.huake.bondmaster.base.contract.web.WebContract;
import com.huake.bondmaster.presenter.web.WebPresenter;
import com.huake.bondmaster.util.LogUtil;
import com.huake.bondmaster.widget.ActionBar;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;

/**
 * @author will on 2017/8/29 12:05
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class WebActivity extends BaseActivity<WebPresenter> implements WebContract.View {

    private String webUrl;
    private String title = "";

    @BindView(R.id.wv_tech_content)
    WebView mWebView;
    @BindView(R.id.tv_progress)
    TextView tvProgress;
    @BindView(R.id.action_bar)
    ActionBar mActionBar;

    @Override
    protected void initInject() {
        getActivityComponent().inject(WebActivity.this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected void initEventAndData() {
        webUrl = getIntent().getStringExtra(Constants.WEB_URL);
        title = getIntent().getStringExtra(Constants.TITLE);
        LogUtil.i(webUrl);

        mActionBar.setTitle(title);

        mActionBar.setLeftActionButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        });


        WebSettings settings = mWebView.getSettings();
//        settings.setAppCacheEnabled(true);
//        settings.setDomStorageEnabled(true);
//        settings.setDatabaseEnabled(true);
//        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        mWebView.addJavascriptInterface(new NativeJsInternation(mContext), "native");

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
//                super.onReceivedSslError(webView, sslErrorHandler, sslError);
                sslErrorHandler.proceed();//接受信任所有网站的证书
            }

        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (tvProgress == null)
                    return;
                if (newProgress >= 100) {
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
//                setTitle(title);
                mActionBar.setTitle(title);
            }

        });


        mWebView.loadUrl(webUrl);
    }

    public void loadUrl(String webUrl){
        this.webUrl = webUrl;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mWebView.loadUrl(WebActivity.this.webUrl);
            }
        });
    }


    @Override
    public void onBackPressedSupport() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            if(mWebView.canGoBack()){
                mActionBar.setVisibilyCloseButton(View.VISIBLE);
            }else{
                mActionBar.setVisibilyCloseButton(View.GONE);
            }
        }else{
            super.onBackPressedSupport();
        }
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.destroy();
            mWebView.removeAllViews();
            mWebView = null;
        }
        super.onDestroy();
    }

    @Override
    public void showContent() {

    }

    public static void open(Context context, String title, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(Constants.WEB_URL, url);
        intent.putExtra(Constants.TITLE, title);
        context.startActivity(intent);
    }
}
