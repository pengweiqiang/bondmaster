package com.huake.bondmaster.ui.evaluation.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.huake.bondmaster.model.bean.PartyBean;

import java.util.List;

/**
 * @author will on 2017/8/31 17:35
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class SearchCompanyNameAdapter extends BaseAdapter {

    private List<PartyBean> partyBeanList;
    private Context context;

    public SearchCompanyNameAdapter(Context context,List<PartyBean> partyBeanList){
        this.context = context;
        this.partyBeanList = partyBeanList;
    }

    public void setData(List<PartyBean> partyBeanList){
        this.partyBeanList = partyBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(partyBeanList == null){
            return 0;
        }
        return partyBeanList.size();
    }

    @Override
    public PartyBean getItem(int position) {
        return partyBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
