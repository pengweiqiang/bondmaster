package com.huake.bondmaster.base;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.App;
import com.huake.bondmaster.widget.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by pengweiqiang on 16/8/11.
 * 无MVP的activity基类
 */

public abstract class SimpleActivity extends SupportActivity {

    protected Activity mContext;
    private Unbinder mUnBinder;

    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        onViewCreated();
        App.getInstance().addActivity(this);
        initEventAndData();
    }

    public void requestPermission(){
        if(Build.VERSION.SDK_INT>=23){
//            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }
    }

    public boolean verifyStoragePermissions() {
        boolean isVerify = false;
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(this,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.WRITE_APN_SETTINGS};
                ActivityCompat.requestPermissions(this, mPermissionList,123);

                isVerify =false;
            }else{
                isVerify =true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isVerify;
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }

    protected void onViewCreated() {

    }

    public void showLoading(String msg) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this, R.style.LoadingDialog);
        }
        if(!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
        loadingDialog.setTitle(msg);
    }

    public void cancelDialogLoading() {
        if(loadingDialog!=null && loadingDialog.isShowing()){
            loadingDialog.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
        mUnBinder.unbind();
    }

    protected abstract int getLayout();
    protected abstract void initEventAndData();
}
