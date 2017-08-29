package com.huake.bondmaster.component;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.huake.bondmaster.R;
import com.huake.bondmaster.app.App;

/**
 * Created by pengweiqiang on 2016/8/2.
 */
public class ImageLoader {

    public static void load(Context context, String url, ImageView iv) {    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
        if (!App.getAppComponent().preferencesHelper().getNoImageState()) {
            Glide.with(context).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
        }
    }

    public static void loadByAllCache(Context context, String url, ImageView iv,int drawableId) {    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
        Glide.with(context).load(url).crossFade().placeholder(context.getResources().getDrawable(drawableId))
                .error((context.getResources().getDrawable(drawableId)))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
    }

    public static void loadByCache(Context mContext,String url,ImageView iv){
        Glide.with(mContext).load(url).placeholder(new ColorDrawable(mContext.getResources().getColor(R.color.image_loading_color))).//加载中显示的图片
                error(new ColorDrawable(mContext.getResources().getColor(R.color.image_loading_color)))//加载失败时显示的图片
                .into(iv);
    }

    public static void load(Activity activity, String url, ImageView iv) {    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
        if(!activity.isDestroyed()) {
            Glide.with(activity).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
        }
    }

    public static void loadAll(Context context, String url, ImageView iv) {    //不缓存，全部从网络加载
        Glide.with(context).load(url).crossFade().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
    }

    public static void loadAll(Activity activity, String url, ImageView iv) {    //不缓存，全部从网络加载
        if(!activity.isDestroyed()) {
            Glide.with(activity).load(url).crossFade().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
        }
    }
}
