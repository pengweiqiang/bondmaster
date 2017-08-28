package com.huake.bondmaster.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.huake.bondmaster.R;
import com.huake.bondmaster.component.ImageLoader;
import com.huake.bondmaster.model.bean.HomePageBean;
import com.huake.bondmaster.widget.banner.InfinitePagerAdapter;

import java.util.List;

/**
 * @author will on 2017/8/27 17:26
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class BannerPagerAdapter extends InfinitePagerAdapter{

    private final LayoutInflater mInflater;
    private final Context mContext;

    public List<HomePageBean.ImgsBean> mList;

    private int height;
    public void setDataList(List<HomePageBean.ImgsBean> list) {
        this.mList = list;
        this.notifyDataSetChanged();
    }


    public BannerPagerAdapter(Context context,int height) {
        mContext=context;
        this.height = height;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public View getView(int position, View view, ViewGroup container) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag(R.string.app_name);
        } else {
            view = mInflater.inflate(R.layout.banner_item_viewpager, container, false);
            ViewGroup.LayoutParams viewParams = view.getLayoutParams();
            viewParams.height = height;
            view.setLayoutParams(viewParams);
            holder = new ViewHolder(view);
            view.setTag(R.string.app_name,holder);
            view.setOnClickListener(holder);
//            holder.image.setOnClickListener(holder);
        }
        HomePageBean.ImgsBean item = mList.get(position);
        holder.position = position;
        ImageLoader.load(mContext,item.getImgUrl(),holder.image);
        return view;
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }


    public class ViewHolder implements View.OnClickListener {
        public int position;
        ImageView image;

        public ViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.item_image);
        }

        @Override
        public void onClick(View v) {

        }
    }


}