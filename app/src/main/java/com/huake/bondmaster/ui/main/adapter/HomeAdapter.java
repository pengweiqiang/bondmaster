package com.huake.bondmaster.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.component.ImageLoader;
import com.huake.bondmaster.model.bean.HomePageBean;
import com.huake.bondmaster.model.bean.HotNewsBean;
import com.huake.bondmaster.util.LogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author will on 2017/8/25 17:11
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private HomePageBean homePageBean;
    private List<HotNewsBean> mList;//资讯消息
    private List<HomePageBean.ImgsBean> imgsBeanList;//轮播图


    private Context mContext;
    private LayoutInflater inflater;

    private OnItemClickListener onItemClickListener;

    public HomeAdapter(Context mContext, HomePageBean homePageBean){
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    public void setData(HomePageBean homePageBean){
        this.homePageBean = homePageBean;
        this.imgsBeanList = homePageBean.getImgs();
        this.mList = homePageBean.getHotNews();
        notifyDataSetChanged();
    }

    public enum ITEM_TYPE{
        ITEM_HEADER,    //头部
        ITEM_CONTENT    //内容
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ContentViewHolder(inflater.inflate(R.layout.item_home_message,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof ContentViewHolder){
            String imageUrl = Constants.HOST_URL+mList.get(position).getImage();
            LogUtil.i(imageUrl);
            ImageLoader.loadByCache(mContext,imageUrl,((ContentViewHolder)holder).mIvCompanyLogo);

            ContentViewHolder contentViewHolder = (ContentViewHolder)holder;
            HotNewsBean hotNewsBean = mList.get(position);

            contentViewHolder.mTvMessageInfo.setText(hotNewsBean.getTitle());
            contentViewHolder.mTvMessageDate.setText(hotNewsBean.getCreateDate());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClickListener != null) {
                        onItemClickListener.onItemClick(position,holder.itemView);
                    }
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        if(mList == null){
            return 0;
        }
        return mList.size();
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_message_info)
        TextView mTvMessageInfo;
        @BindView(R.id.tv_message_date)
        TextView mTvMessageDate;
        @BindView(R.id.iv_company_logo)
        ImageView mIvCompanyLogo;


        public ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

}
