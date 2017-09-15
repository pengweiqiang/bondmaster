package com.huake.bondmaster.ui.main.activity;

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
import com.huake.bondmaster.model.bean.UserBean;
import com.huake.bondmaster.presenter.web.WebPresenter;
import com.huake.bondmaster.util.LogUtil;
import com.huake.bondmaster.widget.ActionBar;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.BindView;

/**
 * @author will on 2017/9/1 15:14
 * @email pengweiqiang64@163.com
 * @description 时讯详情
 * @Version
 */

public class ArticleDetailActivity extends BaseActivity<WebPresenter> implements WebContract.View{


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
        getActivityComponent().inject(ArticleDetailActivity.this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected void initEventAndData() {
        webUrl = getIntent().getStringExtra(Constants.WEB_URL);
        title = getIntent().getStringExtra(Constants.TITLE);
        UserBean userBean = App.getInstance().getUserBeanInstance();
        if(userBean!=null) {
            if (webUrl.contains("?")) {
                webUrl = webUrl+"&token="+userBean.getToken();
            }else{
                webUrl = webUrl+"?token="+userBean.getToken();
            }
        }
        LogUtil.i(webUrl);

        mActionBar.setTitle(title);

        mActionBar.setRightImageActionButton(0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareApp();
            }
        });

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
//        mWebView.addJavascriptInterface(new NativeJsInternation(mContext), "native");

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
                mWebView.loadUrl(ArticleDetailActivity.this.webUrl);
            }
        });
    }


    @Override
    public void onBackPressedSupport() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
//            if(mWebView.canGoBack()){
//                mActionBar.setVisibilyCloseButton(View.VISIBLE);
//            }else{
//                mActionBar.setVisibilyCloseButton(View.GONE);
//            }
        }else{
            super.onBackPressedSupport();
        }
    }

    @Override
    protected void onDestroy() {
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

    public static void open(Context context, String title, String url) {
        Intent intent = new Intent(context, ArticleDetailActivity.class);
        intent.putExtra(Constants.WEB_URL, url);
        intent.putExtra(Constants.TITLE, title);
        context.startActivity(intent);
    }

    private void shareApp(){
        UMWeb web = new UMWeb(webUrl);
        web.setTitle("债券App");//标题
        UMImage umImage = new UMImage(mContext,R.mipmap.ic_launcher);
        web.setThumb(umImage);  //缩略图
        web.setDescription(title);//描述
        new ShareAction(mContext)
                .withMedia(web)
//                .withText("债懂App")
                .setDisplayList(SHARE_MEDIA.WEIXIN,SHARE_MEDIA.QQ,SHARE_MEDIA.SINA,SHARE_MEDIA.SMS)
                .setCallback(shareListener)
                .open();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
//            showLoading("分享中...");
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
//            showErrorMsg("分享成功");
            cancelDialogLoading();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            showErrorMsg("失败"+t.getMessage());
            cancelDialogLoading();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
//            showErrorMsg("取消分享");
            cancelDialogLoading();
        }
    };
}
