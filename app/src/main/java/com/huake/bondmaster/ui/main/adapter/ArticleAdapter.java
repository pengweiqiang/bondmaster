package com.huake.bondmaster.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.component.ImageLoader;
import com.huake.bondmaster.model.bean.ArticleBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author will on 2017/8/25 17:11
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<ArticleBean> mList;
    private Context mContext;
    private LayoutInflater inflater;

    private OnItemClickListener onItemClickListener;

    public ArticleAdapter(Context mContext, List<ArticleBean> mList){
        this.mContext = mContext;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ContentViewHolder(inflater.inflate(R.layout.item_article,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
//        if(holder instanceof ContentViewHolder){
//
//        }



        ContentViewHolder contentViewHolder = (ContentViewHolder)holder;
        ArticleBean articleBean = mList.get(position);

        ImageLoader.loadByAllCache(mContext, Constants.HOST_URL+articleBean.getImage(),contentViewHolder.mIvImage,R.mipmap.ic_default_item_logo);

        contentViewHolder.mTvAuthor.setText(articleBean.getAuthor());
        contentViewHolder.mTvReadedCount.setText(String.valueOf(articleBean.getHits()));
        contentViewHolder.mTvTitle.setText(Html.fromHtml(articleBean.getTitle()));
        contentViewHolder.mTvDate.setText(articleBean.getCreateDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener != null) {
                    onItemClickListener.onItemClick(position,holder.itemView);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mList == null){
            return 0;
        }
        return mList.size();
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_author)
        TextView mTvAuthor;
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_date)
        TextView mTvDate;
        @BindView(R.id.tv_readed)
        TextView mTvReadedCount;
        @BindView(R.id.iv_image)
        ImageView mIvImage;


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
