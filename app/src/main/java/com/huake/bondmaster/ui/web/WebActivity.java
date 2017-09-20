package com.huake.bondmaster.ui.web;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
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
import com.huake.bondmaster.util.ToastUtil;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
        requestPermission();
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


        initActionbar();

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

    private void initActionbar(){
        mActionBar.setTitle(title);
        mActionBar.setLeftActionButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        });

        if(webUrl.contains(Constants.ASSOCIATION_MAP)){
            mActionBar.setRightImageSaveActionButton(R.mipmap.ic_save, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bitmap bitmap = captureScreenWindow();
                    long time = System.currentTimeMillis();
                    String  fileName = saveBitmapForSdCard("img" + time, bitmap);
                    if(!TextUtils.isEmpty(fileName)) {
                        ToastUtil.show("已保存在" + fileName);
                    }
                }
            });
            mActionBar.setRightImageActionButton(R.mipmap.ic_share, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shareApp();
                }
            });
        }else{
            mActionBar.hideRightImageActionSaveButton();
        }
    }

    /**
     * 截取全屏
     *
     * @return
     */
    public Bitmap captureScreenWindow() {
        getWindow().getDecorView().setDrawingCacheEnabled(true);
        Bitmap bmp = getWindow().getDecorView().getDrawingCache();
        return bmp;
    }

    /**
     * 保存到内存卡
     *
     * @param bitName
     * @param mBitmap
     */
    public String saveBitmapForSdCard(String bitName, Bitmap mBitmap) {
        if (verifyStoragePermissions()) {
            //创建file对象
            File f = new File(Environment.getExternalStorageDirectory(),Constants.IMAGE);
            try {
                if (!f.mkdirs()) {
                    f.createNewFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            f = new File(f.getAbsolutePath(),bitName+".png");
            FileOutputStream fOut = null;
            try {
                fOut = new FileOutputStream(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if(mBitmap!=null) {
                LogUtil.i(" -----------" + fOut);
                //原封不动的保存在内存卡上
                mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);

                try {
                    fOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return f.getAbsolutePath();
        }
        return null;
    }

    public void loadUrl(String webUrl){
        this.webUrl = webUrl;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initActionbar();
                mWebView.loadUrl(WebActivity.this.webUrl);
            }
        });
    }


    @Override
    public void onBackPressedSupport() {
        if (mWebView.canGoBack()) {
            mActionBar.hideRightImageActionSaveButton();
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
        Intent intent = new Intent(context, WebActivity.class);
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
