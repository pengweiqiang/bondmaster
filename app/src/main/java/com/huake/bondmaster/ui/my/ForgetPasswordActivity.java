package com.huake.bondmaster.ui.my;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.App;
import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.BaseActivity;
import com.huake.bondmaster.base.contract.my.ForgetPasswordContract;
import com.huake.bondmaster.model.DataManager;
import com.huake.bondmaster.presenter.my.ForgetPasswordPresenter;
import com.huake.bondmaster.util.ToastUtil;
import com.huake.bondmaster.widget.ActionBar;

import org.jsoup.helper.StringUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/8/28 10:07
 * @email pengweiqiang64@163.com
 * @description 忘记密码
 * @Version
 */

public class ForgetPasswordActivity extends BaseActivity<ForgetPasswordPresenter> implements ForgetPasswordContract.View {

    @BindView(R.id.action_bar)
    ActionBar mActionBar;
    @BindView(R.id.et_mobile)
    EditText mEtMobile;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.btn_get_code)
    Button mBtnGetCode;
    @BindView(R.id.btn_confirm)
    Button mBtnConfirm;

    String mobile = "";

    TextWatcher textWatcher;

    DataManager dataManager;

    /**
     * EditText有内容的个数
     */
    private int mEditTextHaveInputCount = 0;
    /**
     * EditText总个数
     */
    private final int EDITTEXT_AMOUNT = 1;

    @Override
    protected int getLayout() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initEventAndData() {
        mobile = getIntent().getStringExtra(Constants.MOBILE);
        mActionBar.setTitle("忘记登录密码");
        dataManager = App.getAppComponent().getDataManager();
        if(!TextUtils.isEmpty(mobile)){
            mEtMobile.setText(mobile);
        }

        initListener();
    }

    private void initListener(){
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                /** EditText最初内容为空 改变EditText内容时 个数加一*/
                if (TextUtils.isEmpty(s)) {

                    mEditTextHaveInputCount++;
                    /** 判断个数是否到达要求*/
                    if (mEditTextHaveInputCount >= EDITTEXT_AMOUNT)
                        mBtnConfirm.setEnabled(true);

                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /** EditText内容改变之后 内容为空时 个数减一 按钮改为不可以的背景*/
                if (TextUtils.isEmpty(s)) {
                    mEditTextHaveInputCount--;
                    mBtnConfirm.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        mEtCode.addTextChangedListener(textWatcher);
    }

    @OnClick({R.id.btn_confirm,R.id.btn_get_code})
    public void btnOnClick(View view){
        switch (view.getId()){
            case R.id.btn_confirm:
                if(TextUtils.isEmpty(mobile)){
                    mEtMobile.requestFocus();
                    ToastUtil.shortShow("手机号不能为空");
                    return;
                }
                String code = mEtCode.getText().toString().trim();
                if(StringUtil.isBlank(code)){
                    mEtCode.requestFocus();
                    showErrorMsg("请输入验证码");
                    return;
                }
                String password = mEtPassword.getText().toString().trim();
                if(StringUtil.isBlank(password)){
                    mEtPassword.requestFocus();
                    showErrorMsg("请输入密码");
                    return;
                }

                showLoading("");
                mPresenter.forgetPassword(mobile,code,password);
                break;
            case R.id.btn_get_code:
                if(TextUtils.isEmpty(mobile)){
                    mEtMobile.requestFocus();
                    ToastUtil.shortShow("手机号不能为空");
                    return;
                }
                mBtnGetCode.setEnabled(false);
                showLoading("");
                mPresenter.getCode(mobile);

                break;
        }
    }



    public static void open(Context context,String mobile){
        Intent intent = new Intent(context,ForgetPasswordActivity.class);
        intent.putExtra(Constants.MOBILE,mobile);
        context.startActivity(intent);
    }


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void getCodeSuccess() {
        regainCode();
    }


    @Override
    public void updateSuccess() {
        String mobile = mEtMobile.getText().toString().trim();
        LoginActivity.open(mContext,mobile);
        finish();
    }

    private Timer timer;// 计时器
    private int time = 60;//倒计时120秒

    private void regainCode() {
        time = 60;
        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                if(handler!=null) {
                    handler.sendEmptyMessage(time--);
                }
            }
        }, 0, 1000);
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                mBtnGetCode.setEnabled(true);
                mBtnGetCode.setText("获取验证码");
                timer.cancel();
            } else {
                mBtnGetCode.setText(msg.what + "秒后重发");
            }

        };
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer!=null){
            timer.cancel();
            timer = null;
        }
        if(handler!=null){
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }
}
