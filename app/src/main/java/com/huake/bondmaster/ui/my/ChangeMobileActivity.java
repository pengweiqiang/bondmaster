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
import com.huake.bondmaster.base.BaseActivity;
import com.huake.bondmaster.base.contract.user.LoginContract;
import com.huake.bondmaster.model.bean.UserBean;
import com.huake.bondmaster.presenter.my.LoginPresenter;
import com.huake.bondmaster.widget.ActionBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/8/28 10:07
 * @email pengweiqiang64@163.com
 * @description 换绑手机
 * @Version
 */

public class ChangeMobileActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.action_bar)
    ActionBar mActionBar;
    @BindView(R.id.et_mobile)
    EditText mEtMobile;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.btn_confirm)
    Button mBtnConfirm;

    String mobile = "";

    TextWatcher textWatcher;

    /**
     * EditText有内容的个数
     */
    private int mEditTextHaveInputCount = 0;
    /**
     * EditText总个数
     */
    private final int EDITTEXT_AMOUNT = 1;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_change_mobile;
    }

    @Override
    protected void initEventAndData() {
        mobile = getIntent().getStringExtra(Constants.MOBILE);
        mActionBar.setTitle("换绑手机");





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

    @OnClick(R.id.btn_confirm)
    public void btnOnClick(View view){
        if(view.getId() == R.id.btn_confirm){
//            String mobile = mEtUserName.getText().toString().trim();
//            if(StringUtil.isBlank(mobile)){
//                mEtUserName.requestFocus();
//                showErrorMsg("请输入当前密码");
//                return;
//            }
//            String password = mEtPassword.getText().toString().trim();
//
//            if(StringUtil.isBlank(password)){
//                mEtPassword.requestFocus();
//                showErrorMsg("请输入新密码");
//                return;
//            }
            showLoading("");
//            mPresenter.login(mobile,password);
        }
    }

    @Override
    public void setLoginRsa(String rsa) {

    }

    @Override
    public void loginSuccess(UserBean userBean) {
        cancelDialogLoading();
//        MainActivity.open(mContext);
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

    public static void open(Context context){
        Intent intent = new Intent(context,ChangeMobileActivity.class);
        context.startActivity(intent);
    }


}
