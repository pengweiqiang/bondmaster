package com.huake.bondmaster.ui.my;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.huake.bondmaster.R;
import com.huake.bondmaster.base.RootActivity;
import com.huake.bondmaster.base.contract.user.RegisterContract;
import com.huake.bondmaster.presenter.my.RegisterPresenter;
import com.huake.bondmaster.widget.ActionBar;

import org.jsoup.helper.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/8/28 10:07
 * @email pengweiqiang64@163.com
 * @description 注册
 * @Version
 */

public class RegisterActivity extends RootActivity<RegisterPresenter> implements RegisterContract.View {

    @BindView(R.id.action_bar)
    ActionBar mActionBar;
    @BindView(R.id.et_username)
    EditText mEtUserName;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.checkbox_agreement)
    AppCompatCheckBox mAppCompatCheckBox;
    @BindView(R.id.btn_register)
    Button mBtnRegister;


    TextWatcher textWatcher;

    /**
     * EditText有内容的个数
     */
    private int mEditTextHaveInputCount = 0;
    /**
     * EditText总个数
     */
    private final int EDITTEXT_AMOUNT = 3;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initEventAndData() {
        mActionBar.setTitle(R.string.register);


        initListener();
    }

    private void setRegisterButtonStatus(){
        if(mAppCompatCheckBox.isChecked() && mEditTextHaveInputCount == EDITTEXT_AMOUNT){
            mBtnRegister.setEnabled(true);
        }else{
            mBtnRegister.setEnabled(false);
        }
    }

    private void initListener(){
        mAppCompatCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setRegisterButtonStatus();
            }
        });
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                /** EditText最初内容为空 改变EditText内容时 个数加一*/
                if (TextUtils.isEmpty(s)) {

                    mEditTextHaveInputCount++;
                    /** 判断个数是否到达要求*/
                    setRegisterButtonStatus();

                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /** EditText内容改变之后 内容为空时 个数减一 按钮改为不可以的背景*/
                if (TextUtils.isEmpty(s)) {
                    mEditTextHaveInputCount--;
                    setRegisterButtonStatus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        mEtUserName.addTextChangedListener(textWatcher);
        mEtPassword.addTextChangedListener(textWatcher);
        mEtCode.addTextChangedListener(textWatcher);
    }

    @OnClick({R.id.btn_register,R.id.btn_get_code})
    public void btnOnClick(View view){
        switch (view.getId()){
            case R.id.btn_register:
                register();
                break;
            case R.id.btn_get_code:
                getCode();
                break;
        }

    }

    private void register(){
        String mobile = mEtUserName.getText().toString().trim();
        if(StringUtil.isBlank(mobile)){
            mEtUserName.requestFocus();
            showErrorMsg("请输入手机号");
            return;
        }
        String password = mEtPassword.getText().toString().trim();

        if(StringUtil.isBlank(password)){
            mEtPassword.requestFocus();
            showErrorMsg("请输入密码");
            return;
        }
        String code = mEtCode.getText().toString().trim();

        if(StringUtil.isBlank(code)){
            mEtCode.requestFocus();
            showErrorMsg("请输入验证码");
            return;
        }
        if(!mAppCompatCheckBox.isChecked()){
            showErrorMsg("请先阅读用户条款和隐私协议");
            return;
        }

        showLoading("注册中...");
        mPresenter.register(mobile,password,code);
    }

    private void getCode(){
        String mobile = mEtUserName.getText().toString().trim();
        if(StringUtil.isBlank(mobile)){
            mEtUserName.requestFocus();
            showErrorMsg("请输入手机号");
            return;
        }
        showLoading("发送验证码...");
        mPresenter.sendVerificationCode(mobile);
    }


    @Override
    public void stateMain() {
        super.stateMain();
        cancelDialogLoading();
    }

    @Override
    public void registerSuccess() {

    }

    @Override
    public void sendVerificationCodeSuccess() {

    }

    public static void open(Context context){
        Intent intent = new Intent(context,RegisterActivity.class);
        context.startActivity(intent);
    }


}
