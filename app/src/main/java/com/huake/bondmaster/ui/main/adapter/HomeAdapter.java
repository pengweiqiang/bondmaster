package com.huake.bondmaster.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.huake.bondmaster.R;
import com.huake.bondmaster.app.App;
import com.huake.bondmaster.app.Constants;
import com.huake.bondmaster.component.ImageLoader;
import com.huake.bondmaster.model.bean.HomePageBean;
import com.huake.bondmaster.model.bean.HotNewsBean;
import com.huake.bondmaster.ui.main.activity.ArticleListActivity;
import com.huake.bondmaster.ui.main.activity.SearchTrialCustInfoActivity;
import com.huake.bondmaster.util.DateUtil;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

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
    int width;
    int bannerHeight ;

    public HomeAdapter(Context mContext, HomePageBean homePageBean){
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        width = App.SCREEN_WIDTH;
        bannerHeight = (int) (width * 1.0 / 375 * 100);
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
        if(viewType == ITEM_TYPE.ITEM_HEADER.ordinal()){
            return new HeaderViewHolder(inflater.inflate(R.layout.header_home, parent, false));
        }else {
            return new ContentViewHolder(inflater.inflate(R.layout.item_home_message, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return ITEM_TYPE.ITEM_HEADER.ordinal();
        }else{
            return ITEM_TYPE.ITEM_CONTENT.ordinal();
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof ContentViewHolder){
            final int realPosition = position -1;


            ContentViewHolder contentViewHolder = (ContentViewHolder)holder;
            HotNewsBean hotNewsBean = mList.get(realPosition);

            String imageUrl = Constants.HOST_URL+hotNewsBean.getImage();

            ImageLoader.loadByAllCache(mContext,imageUrl,contentViewHolder.mIvCompanyLogo,R.mipmap.ic_default_item_logo);


            contentViewHolder.mTvMessageInfo.setText(Html.fromHtml(hotNewsBean.getTitle()));
            contentViewHolder.mTvMessageDate.setText(DateUtil.getDateString(hotNewsBean.getCreateDate(),DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS,DateUtil.FORMAT_YYYY_MM_DD));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClickListener != null) {
                        onItemClickListener.onItemClick(realPosition,holder.itemView);
                    }
                }
            });
        }else if(holder instanceof HeaderViewHolder){
            ((HeaderViewHolder) holder).mEtSearchView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SearchTrialCustInfoActivity.open(mContext,"");
                }
            });

            ((HeaderViewHolder) holder).mIvMoreArticle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArticleListActivity.open(mContext);
                }
            });

            showBannerView((HeaderViewHolder) holder,position);
        }


    }

    private void showBannerView(HeaderViewHolder itemBannerViewHolder,final int position){
//        if(position == 0) {
//            ViewGroup.LayoutParams bannerViewParams = itemBannerViewHolder.mBannerView.getLayoutParams();
//            bannerViewParams.width = width;
//            bannerViewParams.height = bannerHeight;
//            itemBannerViewHolder.mBannerView.setLayoutParams(bannerViewParams);
//
//            BannerPagerAdapter pagerAdapter = new BannerPagerAdapter(mContext, bannerHeight);
//            pagerAdapter.setDataList(imgsBeanList);
//            itemBannerViewHolder.infiniteViewPager.setAdapter(pagerAdapter);
//            itemBannerViewHolder.infiniteViewPager.setAutoScrollTime(5000);
//            itemBannerViewHolder.infiniteViewPager.startAutoScroll();
//            itemBannerViewHolder.circlePageIndicator.setViewPager(itemBannerViewHolder.infiniteViewPager);
//            //平滑滑动
//            ViewPagerScroller scroller = new ViewPagerScroller(mContext);
//            scroller.setmScrollDuration(800);
//            scroller.initViewPagerScroll(itemBannerViewHolder.infiniteViewPager);
//        }

        ViewGroup.LayoutParams bannerViewParams = itemBannerViewHolder.mzBannerView.getLayoutParams();
        bannerViewParams.width = width;
        bannerViewParams.height = bannerHeight;
//        itemBannerViewHolder.mzBannerView.setLayoutParams(bannerViewParams);


        // 设置数据
        itemBannerViewHolder.mzBannerView.setPages(imgsBeanList, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });

//        itemBannerViewHolder.mzBannerView.setDuration(3000);
        itemBannerViewHolder.mzBannerView.setIndicatorVisible(false);
        itemBannerViewHolder.mzBannerView.setDelayedTime(6000);

        itemBannerViewHolder.mzBannerView.start();



    }

    public static class BannerViewHolder implements MZViewHolder<HomePageBean.ImgsBean> {
        private ImageView mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item_viewpager,null);
            mImageView = (ImageView) view.findViewById(R.id.item_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, HomePageBean.ImgsBean data) {
            // 数据绑定
            ImageLoader.loadByAllCache(context,data.getImgUrl(),mImageView,R.mipmap.home_banner2);
        }
    }

    @Override
    public int getItemCount() {
        if(mList == null){
            return 1;
        }
        return mList.size()+1;
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

//    InfiniteViewPager mParentViewPager;
    MZBannerView mzParentBannerView;
    public class HeaderViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_search)
        EditText mEtSearchView;
        @BindView(R.id.iv_more_article)
        ImageView mIvMoreArticle;
//        @BindView(R.id.banner)
//        View mBannerView;
//        @BindView(R.id.viewpager)
//        InfiniteViewPager infiniteViewPager;
//        @BindView(R.id.indicator)
//        CirclePageIndicator circlePageIndicator;
        @BindView(R.id.banner)
        MZBannerView mzBannerView;


        public HeaderViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
//            mParentViewPager = infiniteViewPager;
            mzParentBannerView = mzBannerView;
        }
    }

    public void onStop(){
        if(mzParentBannerView!=null){
            mzParentBannerView.pause();
        }
    }

    public void onStart(){
        if(mzParentBannerView!=null){
            mzParentBannerView.start();
        }
    }

//    public void setCurrentViewPager(int position){
//        if(mParentViewPager!=null){
//            mParentViewPager.setCurrentItem(position,false);
//        }
//    }



    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

}
