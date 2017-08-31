package com.huake.bondmaster.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.App;

import org.jsoup.helper.StringUtil;

/**
 * 标题栏, 可设置标题和左右图标
 * 
 * @author Will
 *
 */
public class ActionBar extends FrameLayout {

	private TextView mTitleView;
	private TextView mLeftActionButton;
	private TextView mCloseActionButton;
	private TextView mRightActionButton;
	private TextView mActionBarTitle;

	private View mActionBar;

	private Context context;

	public ActionBar(Context context) {
		super(context);
		this.context = context;
	}

	public ActionBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);

	}

	public ActionBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
		this.context = context;
		setLeftActionButton();
		setmCloseActionButton();
	}

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.action_bar_layout,
				this);
		mTitleView = (TextView) findViewById(R.id.actionBarTitle);
		mLeftActionButton = (TextView) findViewById(R.id.leftActionButton);
		mCloseActionButton = (TextView) findViewById(R.id.closeActionButton);
		mRightActionButton = (TextView)findViewById(R.id.rightActionButton);
		mActionBarTitle = (TextView)findViewById(R.id.actionBarTitle);
		mActionBar = findViewById(R.id.actionBarLayout);

	}
	
	public void setBackgroundColor(int colorId){
		mActionBar.setBackgroundColor(colorId);
	}

	public void setTitle(int resId) {
		mTitleView.setText(resId);
	}

	public void setTitle(String text) {
		mTitleView.setText(text);
	}
	
	public void setTitleTextColor(int resId){
		mTitleView.setTextColor(getResources().getColor(resId));
	}

	public void setLeftActionButton(String text, OnClickListener listener){
		mLeftActionButton.setText(text);
		mLeftActionButton.setCompoundDrawables(null,null,null,null);
		mLeftActionButton.setOnClickListener(listener);
	}


	public void setLeftActionButton(int icon,String text, OnClickListener listener) {
		if(icon!=0){
			Drawable drawable= getResources().getDrawable(icon);
			/// 这一步必须要做,否则不会显示.
			drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
			mLeftActionButton.setCompoundDrawables(drawable,null,null,null);
		}
		mLeftActionButton.setText(text);
		mLeftActionButton.setOnClickListener(listener);
//		mLeftActionButton.setVisibility(View.VISIBLE);
	}

	public void setRightActionButton(String text,OnClickListener onClickListener){
		if(!StringUtil.isBlank(text)) {
			mRightActionButton.setText(text);
			mRightActionButton.setVisibility(VISIBLE);
			mRightActionButton.setOnClickListener(onClickListener);
		}
	}

	public void setLeftActionButton(){
		mLeftActionButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(context instanceof Activity){
					Activity activity = (Activity)context;
					activity.finish();
					App.getInstance().removeActivity(activity);
				}
			}
		});
	}

	public void setmCloseActionButton(){
		mCloseActionButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(context instanceof Activity){
					Activity activity = (Activity)context;
					activity.finish();
					App.getInstance().removeActivity(activity);
				}
			}
		});
	}

	public void setLeftActionButton(OnClickListener onClickListener){
		mLeftActionButton.setOnClickListener(onClickListener);
	}


	/**
	 * 隐藏左上角按钮的文字
	 */
	public void hideLeftActionButtonText() {
		mLeftActionButton.setText("");
	}

	public void hideLeftAction(){
		mLeftActionButton.setVisibility(GONE);
	}

	public void setVisibilyCloseButton(int isVisibily){
		mRightActionButton.setVisibility(isVisibily);
	}

	
}
