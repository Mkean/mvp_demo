package com.bwie.mvp_demo.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.bwie.mvp_demo.R;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import bean.Banner_Bean;
import bean.Market;

/**
 * 作者：王庆
 * 时间：2017/12/14
 */

public class MyAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Banner_Bean.DataBean> data;
    private ArrayList<Market> marketList;
    private List<Banner_Bean.MiaoshaBean.ListBeanX> list;

    public MyAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Banner_Bean.DataBean> model) {
        data.addAll( model );
    }

    public void setList(List<Banner_Bean.MiaoshaBean.ListBeanX> list) {
        list.addAll( list );
    }

    private enum Type_item {
        TypeOne, TypeTwo, TypeThree, TypeFour;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Type_item.TypeOne.ordinal()) {
            View view = LayoutInflater.from( context ).inflate( R.layout.banner_item, null, false );
            return new MyViewHolderA( view );
        } else if (viewType == Type_item.TypeTwo.ordinal()) {
            View view = LayoutInflater.from( context ).inflate( R.layout.market_item, null, false );
            return new MyViewHolderB( view );
        } else if (viewType == Type_item.TypeThree.ordinal()) {
            View view = LayoutInflater.from( context ).inflate( R.layout.toptiao, null, false );
            return new MyViewHolderC( view );
        } else if (viewType == Type_item.TypeFour.ordinal()) {
            View view = LayoutInflater.from( context ).inflate( R.layout.stagger_layout, null, false );
            return new MyViewHolderD( view );
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        initData();
        if (holder instanceof MyViewHolderB) {
            ((MyViewHolderB) holder).mrlv.setLayoutManager( new GridLayoutManager( context, 5 ) );
            MyMarketAdapter adapter = new MyMarketAdapter( context, marketList );
            ((MyViewHolderB) holder).mrlv.setAdapter( adapter );
            Log.e( "TAG", "eqweqw1" );
        } else if (holder instanceof MyViewHolderC) {
            ((MyViewHolderC) holder).top_img.setImageResource( R.drawable.tbtt );
            ((MyViewHolderC) holder).top_content.addView( View.inflate( context, R.layout.pmd, null ) );
            Log.e( "TAG", "dfgdg2" );
        } else if (holder instanceof MyViewHolderD) {
            MyStagAdapter adapter = new MyStagAdapter( context, list );
            ((MyViewHolderD) holder).srlv.setLayoutManager( new GridLayoutManager( context, 2 ) );
            ((MyViewHolderD) holder).srlv.setAdapter( adapter );
            Log.e( "TAG", "ewrwerwe3" );
        } else if (holder instanceof MyViewHolderA) {
            ((MyViewHolderA) holder).banner.setData( data, null );
            ((MyViewHolderA) holder).banner.setmAdapter( new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, View view, int position) {
                    Glide.with( context ).load( data.get( position ).getIcon() ).into( (ImageView) view );
                }
            } );
            Log.e( "TAG", "asd4" );
        }
    }

    public void initData() {
        marketList = new ArrayList<>();
        marketList.add( new Market( R.drawable.g1, "天猫" ) );
        marketList.add( new Market( R.drawable.g2, "聚划算" ) );
        marketList.add( new Market( R.drawable.g3, "天猫国际" ) );
        marketList.add( new Market( R.drawable.g4, "外卖" ) );
        marketList.add( new Market( R.drawable.g5, "天猫超市" ) );
        marketList.add( new Market( R.drawable.g6, "充值中心" ) );
        marketList.add( new Market( R.drawable.g7, "飞猪旅行" ) );
        marketList.add( new Market( R.drawable.g8, "领金币" ) );
        marketList.add( new Market( R.drawable.g9, "拍卖" ) );
        marketList.add( new Market( R.drawable.g10, "分类" ) );
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return Type_item.TypeOne.ordinal();
        } else if (position == 1) {
            return Type_item.TypeTwo.ordinal();
        } else if (position == 2) {
            return Type_item.TypeThree.ordinal();
        } else if (position == 3) {
            return Type_item.TypeFour.ordinal();
        }
        return -1;
    }


    @Override
    public int getItemCount() {
        return 4;
    }

    class MyViewHolderA extends RecyclerView.ViewHolder {

        private final XBanner banner;

        public MyViewHolderA(View itemView) {
            super( itemView );
            banner = itemView.findViewById( R.id.xbanner );
        }
    }

    class MyViewHolderB extends RecyclerView.ViewHolder {

        private final RecyclerView mrlv;

        public MyViewHolderB(View itemView) {
            super( itemView );
            mrlv = itemView.findViewById( R.id.mrlv );
        }
    }

    class MyViewHolderC extends RecyclerView.ViewHolder {

        private final ImageView top_img;
        private final ViewFlipper top_content;

        public MyViewHolderC(View itemView) {
            super( itemView );
            top_img = itemView.findViewById( R.id.top_img );
            top_content = itemView.findViewById( R.id.top_content );
        }
    }

    class MyViewHolderD extends RecyclerView.ViewHolder {

        private final RecyclerView srlv;

        public MyViewHolderD(View itemView) {
            super( itemView );
            srlv = itemView.findViewById( R.id.srlv );
        }
    }
}
