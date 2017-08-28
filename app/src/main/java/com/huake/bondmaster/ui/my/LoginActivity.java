package com.huake.bondmaster.ui.my;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.RootActivity;
import com.huake.bondmaster.base.contract.user.LoginContract;
import com.huake.bondmaster.model.bean.UserBean;
import com.huake.bondmaster.presenter.my.LoginPresenter;
import com.huake.bondmaster.ui.main.activity.MainActivity;
import com.huake.bondmaster.widget.ActionBar;

import org.jsoup.helper.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/8/28 10:07
 * @email pengweiqiang64@163.com
 * @description 登陆
 * @Version
 */

public class LoginActivity extends RootActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.action_bar)
    ActionBar mActionBar;
    @BindView(R.id.et_username)
    EditText mEtUserName;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    String mobile = "";

    TextWatcher textWatcher;

    /**
     * EditText有内容的个数
     */
    private int mEditTextHaveInputCount = 0;
    /**
     * EditText总个数
     */
    private final int EDITTEXT_AMOUNT = 2;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {
        mobile = getIntent().getStringExtra(Constants.MOBILE);
        mActionBar.setTitle(R.string.login);
        mActionBar.setRightActionButton(mContext.getResources().getString(R.string.register), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.open(mContext);
            }
        });
        mEtUserName.setText(mobile);

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
                    if (mEditTextHaveInputCount == EDITTEXT_AMOUNT)
                        mBtnLogin.setEnabled(true);

                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /** EditText内容改变之后 内容为空时 个数减一 按钮改为不可以的背景*/
                if (TextUtils.isEmpty(s)) {
                    mEditTextHaveInputCount--;
                    mBtnLogin.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        mEtUserName.addTextChangedListener(textWatcher);
        mEtPassword.addTextChangedListener(textWatcher);
    }

    @OnClick(R.id.btn_login)
    public void btnOnClick(View view){
        if(view.getId() == R.id.btn_login){
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
            showLoading("登陆中...");
            mPresenter.login(mobile,password);
        }
    }

    @Override
    public void loginSuccess(UserBean userBean) {
        cancelDialogLoading();
        MainActivity.open(mContext);
        finish();
    }

    @Override
    public void stateMain() {
        super.stateMain();
        cancelDialogLoading();
    }

    @Override
    public void sendVerificationCodeSuccess() {

    }

    public static void open(Context context, String mobile){
        Intent intent = new Intent(context,LoginActivity.class);
        intent.putExtra(Constants.MOBILE,mobile);
        context.startActivity(intent);
    }


}
