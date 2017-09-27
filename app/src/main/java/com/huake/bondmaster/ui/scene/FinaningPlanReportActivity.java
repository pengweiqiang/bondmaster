package com.huake.bondmaster.ui.scene;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;
import com.huake.bondmaster.R;
import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.SimpleActivity;
import com.huake.bondmaster.util.DownloadUtil;
import com.huake.bondmaster.util.ToastUtil;
import com.huake.bondmaster.widget.ActionBar;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.sina.helper.MD5;

import java.io.File;

import butterknife.BindView;

/**
 * @author will on 2017/9/16 11:10
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class FinaningPlanReportActivity extends SimpleActivity {

    @BindView(R.id.pdfView)
    PDFView mPdfView;
    @BindView(R.id.action_bar)
    ActionBar mActionBar;

    String pdfUrl = "";

    String fileName = "";

    String filePath = "";

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            showLoading(msg.what+"%");
        }
    };


    @Override
    protected int getLayout() {
        return R.layout.activity_pdf;
    }

    @Override
    protected void initEventAndData() {
        requestPermission();
        mActionBar.setTitle("融资报告");
        mActionBar.setRightImageActionButton(R.mipmap.ic_share, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareApp();
            }
        });
        mActionBar.setRightImageSaveActionButton(R.mipmap.ic_save, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(new File(filePath).exists()) {
                    ToastUtil.show("已保存在" + filePath);
                }else{
                    initData();
                }
            }
        });
        pdfUrl = getIntent().getStringExtra(Constants.URL_KEY);

        fileName = MD5.hexdigest(pdfUrl);

        initData();
    }

    public static void open(Context context, String url){
        Intent intent = new Intent(context,FinaningPlanReportActivity.class);
        intent.putExtra(Constants.URL_KEY,url);
        context.startActivity(intent);
    }

    private void initData(){
        if(verifyStoragePermissions()) {
            filePath = DownloadUtil.get().getFilePath(Constants.PDF + "/" + fileName + Constants.END_PDF);
            if (new File(filePath).exists()) {//已存在文件
                mPdfView.fromFile(new File(filePath))
                        .defaultPage(0)
                        .load();
            } else {
                showLoading("");
                DownloadUtil.get().download(pdfUrl, Constants.PDF, fileName + Constants.END_PDF, new DownloadUtil.OnDownloadListener() {
                    @Override
                    public void onDownloadSuccess() {
                        cancelDialogLoading();
                        mPdfView.fromFile(new File(filePath))
                                .defaultPage(0)
                                .load();
                    }

                    @Override
                    public void onDownloading(int progress) {
//                        Log.i("DownloadUtil", progress + "");
                        if(progress>0 && progress<100) {
                            handler.sendEmptyMessage(progress);
                        }
                    }

                    @Override
                    public void onDownloadFailed() {
                        cancelDialogLoading();
                    }
                });
            }
        }

    }

    private void shareApp(){
        UMWeb web = new UMWeb(pdfUrl);
        web.setTitle("债懂App");//标题
        UMImage umImage = new UMImage(mContext,R.mipmap.ic_launcher);
        web.setThumb(umImage);  //缩略图
        web.setDescription("债懂App");//描述
        new ShareAction(mContext)
                .withMedia(web)
//                .withText("债懂App")
                .setDisplayList(SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.SMS)
                //,SHARE_MEDIA.SINA .QQ
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
