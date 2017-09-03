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
import com.huake.bondmaster.app.App;
import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.base.BaseActivity;
import com.huake.bondmaster.base.contract.my.UpdatePasswordContract;
import com.huake.bondmaster.model.bean.UserBean;
import com.huake.bondmaster.presenter.my.UpdatePasswordPresenter;
import com.huake.bondmaster.widget.ActionBar;

import org.jsoup.helper.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/8/28 10:07
 * @email pengweiqiang64@163.com
 * @description 修改密码
 * @Version
 */

public class UpdatePasswordActivity extends BaseActivity<UpdatePasswordPresenter> implements UpdatePasswordContract.View {

    @BindView(R.id.action_bar)
    ActionBar mActionBar;
    @BindView(R.id.et_current_pwd)
    EditText mEtCurrentPwd;
    @BindView(R.id.et_password)
    EditText mEtPassword;
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
    private final int EDITTEXT_AMOUNT = 2;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_update_password;
    }

    @Override
    protected void initEventAndData() {
        mobile = getIntent().getStringExtra(Constants.MOBILE);
        mActionBar.setTitle(R.string.update_password);



//        if(!StringUtil.isBlank(mobile)) {
//            mEtUserName.setText(mobile);
//        }
//
//        if(!TextUtils.isEmpty(mEtUserName.getText().toString())){
//            mEditTextHaveInputCount ++;
//        }

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

        mEtCurrentPwd.addTextChangedListener(textWatcher);
        mEtPassword.addTextChangedListener(textWatcher);
    }

    @OnClick(R.id.btn_confirm)
    public void btnOnClick(View view){
        if(view.getId() == R.id.btn_confirm){
            String currentPwd = mEtCurrentPwd.getText().toString().trim();
            if(StringUtil.isBlank(currentPwd)){
                mEtCurrentPwd.requestFocus();
                showErrorMsg("请输入当前密码");
                return;
            }
            String password = mEtPassword.getText().toString().trim();

            if(StringUtil.isBlank(password)){
                mEtPassword.requestFocus();
                showErrorMsg("请输入新密码");
                return;
            }
            showLoading("");
            mPresenter.updatePassword(mobile,"",currentPwd,password);
        }
    }


    @Override
    public void stateMain() {
        super.stateMain();
        cancelDialogLoading();
    }


    public static void open(Context context){
        Intent intent = new Intent(context,UpdatePasswordActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void updateSuccess() {
        App.getInstance().removeActivity(CommonSettingActivity.class);
        UserBean userBean = App.getInstance().getUserBeanInstance();
        String mobile = userBean.getMobile();
        App.getInstance().setUserInstance(null);
        App.getAppComponent().getDataManager().setUserInstance(null);
        LoginActivity.open(mContext,mobile);
    }
}
