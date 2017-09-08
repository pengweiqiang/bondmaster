package com.huake.bondmaster.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huake.bondmaster.R;
import com.huake.bondmaster.model.bean.SceneBean;
import com.huake.bondmaster.util.BigDecimalUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author will on 2017/8/25 17:11
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class SceneAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<SceneBean> mList;
    private Context mContext;
    private LayoutInflater inflater;

    private OnItemClickListener onItemClickListener;

    public SceneAdapter(Context mContext,List<SceneBean> mList){
        this.mContext = mContext;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }

//    public enum ITEM_TYPE{
//        ITEM_HEADER,    //头部
//        ITEM_CONTENT    //内容
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ContentViewHolder(inflater.inflate(R.layout.item_recycle_view_scene,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
//        if(holder instanceof ContentViewHolder){
//
//        }

//        ImageLoader.load(mContext,mList.get(position).getbAgencyGuarantornatureName().get(0),((ContentViewHolder)holder).image);
        ContentViewHolder contentViewHolder = (ContentViewHolder)holder;
        SceneBean sceneBean = mList.get(position);

        contentViewHolder.mTvBInfoCreditrating.setText(sceneBean.getbInfoCreditrating());
        contentViewHolder.mTvCompanyName.setText(sceneBean.getsInfoCustname());
        String successProbalility = BigDecimalUtil.formartDoubleStr(sceneBean.getSuccessProbability(),2);
        if(successProbalility.equals("")){
            successProbalility = "";
        }
        contentViewHolder.mTvSuccessProbability.setText(successProbalility+"%");

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
        @BindView(R.id.tv_company_name)
        TextView mTvCompanyName;
        @BindView(R.id.tv_success_probability)
        TextView mTvSuccessProbability;
        @BindView(R.id.tv_bInfoCreditrating)
        TextView mTvBInfoCreditrating;
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
        void onItemClick(int position,View view);
    }

}
