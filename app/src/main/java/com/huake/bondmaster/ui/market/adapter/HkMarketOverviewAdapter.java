package com.huake.bondmaster.ui.market.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huake.bondmaster.R;
import com.huake.bondmaster.model.bean.HkMarketOverviewBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author will on 2017/8/25 17:11
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class HkMarketOverviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<HkMarketOverviewBean> mList;
    private Context mContext;
    private LayoutInflater inflater;

    private OnItemClickListener onItemClickListener;

    public HkMarketOverviewAdapter(Context mContext, List<HkMarketOverviewBean> mList){
        this.mContext = mContext;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ContentViewHolder(inflater.inflate(R.layout.item_hk_market_overview,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
//        if(holder instanceof ContentViewHolder){
//
//        }

//        ImageLoader.load(mContext,mList.get(position).getbAgencyGuarantornatureName().get(0),((ContentViewHolder)holder).image);
        ContentViewHolder contentViewHolder = (ContentViewHolder)holder;
        HkMarketOverviewBean hkMarketOverviewBean = mList.get(position);

        double rate = hkMarketOverviewBean.getBInfoCouponrate();
        if(rate == 0d){
            contentViewHolder.mTvRate.setText("未知");
        }else{
            contentViewHolder.mTvRate.setText(rate+"%");
        }


        contentViewHolder.mTvCompanyName.setText(hkMarketOverviewBean.getSInfoCompname());
        double bissumeAnoutact = hkMarketOverviewBean.getBIssueAmountact();
        if(bissumeAnoutact==0d){
            contentViewHolder.mTvScale.setText("未知");
        }else {
            contentViewHolder.mTvScale.setText(bissumeAnoutact + "亿元");
        }
        contentViewHolder.mTvDataDate.setText("发行时间："+hkMarketOverviewBean.getBIssueFirstissue());

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
        @BindView(R.id.tv_rate)
        TextView mTvRate;
        @BindView(R.id.tv_scale)
        TextView mTvScale;
        @BindView(R.id.tv_data_date)
        TextView mTvDataDate;


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
