package com.huake.bondmaster.ui.main.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.App;
import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.SimpleFragment;
import com.huake.bondmaster.model.bean.UserBean;
import com.huake.bondmaster.ui.my.CommonSettingActivity;
import com.huake.bondmaster.ui.my.FeedBackActivity;
import com.huake.bondmaster.ui.my.LoginActivity;
import com.huake.bondmaster.ui.web.WebActivity;
import com.huake.bondmaster.util.ToastUtil;
import com.huake.bondmaster.widget.ToggleButton;
import com.tencent.bugly.beta.Beta;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/8/24 13:42
 * @email pengweiqiang64@163.com
 * @description 我的
 * @Version
 */

public class MyFragment extends SimpleFragment {

    @BindView(R.id.toggle_message_notify)
    ToggleButton mToggleMessageNofity;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_user_mobile)
    TextView mTvUserMobile;
    @BindView(R.id.iv_user_head)
    ImageView mIvUserHead;
    @BindView(R.id.rl_logined)
    View mViewLogined;
    @BindView(R.id.tv_go_login)
    View mViewUnLogin;


    UserBean userBean;

//    @Override
//    protected void initInject() {
//        getFragmentComponent().inject(this);
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }


    @Override
    protected void initEventAndData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        checkLogin();
    }

    private void checkLogin(){
        userBean = App.getInstance().getUserBeanInstance();
        if(userBean==null){
            mViewLogined.setVisibility(View.GONE);
            mViewUnLogin.setVisibility(View.VISIBLE);
        }else{
            mTvUserName.setText(userBean.getUsername());
            mTvUserMobile.setText(userBean.getMobile());
            mViewLogined.setVisibility(View.VISIBLE);
            mViewUnLogin.setVisibility(View.GONE);
        }
    }

//    @Override
//    public void showContent() {
//
//    }
//
//    @Override
//    public void stateError() {
//
//    }
//
//    @Override
//    public void stateMain() {
//
//    }

    @OnClick({R.id.rl_feed_back,R.id.rl_share_app,R.id.rl_about_us,R.id.rl_check_upgrade,R.id.rl_user_info})
    public void onClickOptionItem(View view){
        switch (view.getId()){
            case R.id.rl_feed_back:
                FeedBackActivity.open(mContext);
                break;
            case R.id.rl_share_app:
                shareApp();
                break;
            case R.id.rl_about_us:
                WebActivity.open(mContext,"关于我们",Constants.HOST_URL+Constants.ABOUT_US);
//                AboutUsActivity.open(mContext);
                break;
            case R.id.rl_check_upgrade:
                Beta.checkUpgrade(true,false);
                break;
            case R.id.rl_user_info:
                if(App.getInstance().getUserBeanInstance()==null) {
                    LoginActivity.open(mContext, "");
                }else{
                    CommonSettingActivity.open(mContext);
                }
                break;
        }
    }


    private void shareApp(){
        UMWeb web = new UMWeb(Constants.SHARE_APP_URL);
        web.setTitle(Constants.APP_DESC);//标题
        UMImage umImage = new UMImage(mContext,R.mipmap.ic_launcher);
        web.setThumb(umImage);  //缩略图
        web.setDescription(Constants.APP_DESC);//描述
        new ShareAction(mActivity)
                .withMedia(web)
//                .withText("债懂App")
                .setDisplayList(SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE/*,SHARE_MEDIA.SMS*/)
                //SHARE_MEDIA.SINA,
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
            ToastUtil.shortShow("分享失败，稍后重试");
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
    public void onStart() {
        super.onStart();
        cancelDialogLoading();
    }
}
