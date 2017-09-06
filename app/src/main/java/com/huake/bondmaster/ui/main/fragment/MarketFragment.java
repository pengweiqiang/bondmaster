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
import com.huake.bondmaster.widget.ActionBar;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
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

//        mActionBar.setLeftActionButton(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mWebView.goBack();
//            }
//        });

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
                mActionBar.setTitle(title);
//                setTitle(title);
            }

        });

        UserBean userBean = App.getInstance().getUserBeanInstance();
        if(userBean!=null) {
            if (webUrl.contains("?")) {
                webUrl = webUrl+"&token="+userBean.getToken();
            }else{
                webUrl = webUrl+"?token="+userBean.getToken();
            }
        }
        mWebView.loadUrl(webUrl);
    }

    public void loadUrl(final String webUrl){
        this.webUrl = webUrl;
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mWebView.loadUrl(webUrl);
            }
        });
    }




    @Override
    public void onDestroy() {
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
}
