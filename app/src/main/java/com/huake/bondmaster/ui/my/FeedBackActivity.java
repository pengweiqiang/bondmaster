package com.huake.bondmaster.ui.my;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.App;
import com.huake.bondmaster.base.BaseActivity;
import com.huake.bondmaster.base.contract.my.FeedBackContract;
import com.huake.bondmaster.model.bean.UserBean;
import com.huake.bondmaster.presenter.my.FeedBackPresenter;
import com.huake.bondmaster.ui.my.adapter.ImagePickerAdapter;
import com.huake.bondmaster.widget.ActionBar;
import com.huake.bondmaster.widget.GlideImageLoader;
import com.huake.bondmaster.widget.SelectDialog;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/8/27 11:06
 * @email pengweiqiang64@163.com
 * @description 意见反馈
 * @Version
 */

public class FeedBackActivity extends BaseActivity<FeedBackPresenter> implements ImagePickerAdapter.OnRecyclerViewItemClickListener,FeedBackContract.View{

    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 5;               //允许选择图片最大数

    @BindView(R.id.action_bar)
    ActionBar mActionBar;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
//    @BindView(R.id.tv_mobile)
//    TextView mTvMobile;
    @BindView(R.id.et_feed_back_content)
    EditText mEtContent;
    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.tv_content_count)
    TextView mEtContentCount;

    UserBean userBean;


    @Override
    protected int getLayout() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void initEventAndData() {
        mActionBar.setTitle(R.string.feed_back);

        userBean = App.getInstance().getUserBeanInstance();
        if(userBean!=null){
            mTvUserName.setText(userBean.getUsername());
//            mTvMobile.setText(BigDecimalUtil.getsubMobileString(userBean.getMobile()));
            mEtEmail.setText(userBean.getEmail());
        }
        initImagePicker();
        initWidget();

        initListener();
    }

    private void initListener(){
        mEtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(s)){
                    mEtContentCount.setText("0/200");
                }else{
                    mEtContentCount.setText(s.length()+"/200");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void initWidget() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    public static void open(Context context){
        Intent intent = new Intent(context,FeedBackActivity.class);
        context.startActivity(intent);
    }


    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }
    @OnClick(R.id.btn_confirm)
    public void onclick(View view){
        String content =mEtContent.getText().toString().trim();
        if(TextUtils.isEmpty(content)){
            mEtContent.requestFocus();
            return;
        }
        String email = mEtEmail.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            mEtEmail.requestFocus();
            return;
        }
        showLoading("");
        mPresenter.saveFeedBack(content,email,"");
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                Intent intent1 = new Intent(FeedBackActivity.this, ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                startActivityForResult(intent1, REQUEST_CODE_SELECT);
//                List<String> names = new ArrayList<>();
//                names.add("拍照");
//                names.add("相册");
//                showDialog(new SelectDialog.SelectDialogListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        switch (position) {
//                            case 0: // 直接调起相机
//                                /**
//                                 * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
//                                 *
//                                 * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
//                                 *
//                                 * 如果实在有所需要，请直接下载源码引用。
//                                 */
//                                //打开选择,本次允许选择的数量
//                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
//                                Intent intent = new Intent(FeedBackActivity.this, ImageGridActivity.class);
//                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
//                                startActivityForResult(intent, REQUEST_CODE_SELECT);
//                                break;
//                            case 1:
//                                //打开选择,本次允许选择的数量
//                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
//                                Intent intent1 = new Intent(FeedBackActivity.this, ImageGridActivity.class);
//                                /* 如果需要进入选择的时候显示已经选中的图片，
//                                 * 详情请查看ImagePickerActivity
//                                 * */
////                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
//                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
//                                break;
//                            default:
//                                break;
//                        }
//
//                    }
//                }, names);


                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    ArrayList<ImageItem> images = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }
    }


    private void initImagePicker(){
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(5);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void saveSuccess() {
        mEtContent.setText("");
    }

}
