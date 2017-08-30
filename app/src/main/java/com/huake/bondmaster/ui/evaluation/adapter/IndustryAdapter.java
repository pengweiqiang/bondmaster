package com.huake.bondmaster.ui.evaluation.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huake.bondmaster.R;
import com.huake.bondmaster.model.bean.IndustryBean;

import java.util.List;

/**
 * @author will on 2017/8/30 21:19
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class IndustryAdapter extends BaseAdapter {

    private IndustryBean parentIndustryBean;
    private List<IndustryBean> industryBeanList;
    private Context context;
    private LayoutInflater layoutInflater;

    private int positionSelected = 0;

    public IndustryAdapter(Context context,List<IndustryBean> industryBeanList,int positionSelected){
        this.context = context;
        this.industryBeanList = industryBeanList;
        this.positionSelected = positionSelected;
        layoutInflater = LayoutInflater.from(context);
    }


    public void setData(IndustryBean parentIndustryBean,List<IndustryBean> industryBeanList){
        this.parentIndustryBean = parentIndustryBean;
        this.industryBeanList = industryBeanList;
        notifyDataSetChanged();
    }

    public void setSelectedStatus(int position){
        this.positionSelected = position;
        notifyDataSetChanged();
    }

    /*
    获取父级行业bean
     */
    public IndustryBean getParentIndustryBean(){
        return parentIndustryBean;
    }

    @Override
    public int getCount() {
        if(industryBeanList == null){
            return 0;
        }
        return industryBeanList.size();
    }

    @Override
    public IndustryBean getItem(int position) {
        return industryBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.item_industry,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.mTvName = (TextView)convertView.findViewById(R.id.tv_name);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        if(positionSelected == position){
            convertView.setBackgroundColor(Color.WHITE);
            viewHolder.mTvName.setTextColor(context.getResources().getColor(R.color.tab_home_text_selectd_color));
        }else{
            convertView.setBackground(null);
            viewHolder.mTvName.setTextColor(context.getResources().getColor(R.color.tab_home_text_color));
        }

        viewHolder.mTvName.setText(getItem(position).getTitle());
        return convertView;
    }

    public static class ViewHolder{
        public TextView mTvName;
    }

}
