package com.bwie.mvp_demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.mvp_demo.R;

import java.util.List;

import bean.Banner_Bean;
import utils.ImageLoaderUtils;

/**
 * 作者：王庆
 * 时间：2017/12/14
 */

public class MyStagAdapter extends RecyclerView.Adapter<MyStagAdapter.MyStaggerHolder> {

    private List<Banner_Bean.MiaoshaBean.ListBeanX> list;
    private Context context;

    public MyStagAdapter(Context context, List<Banner_Bean.MiaoshaBean.ListBeanX> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyStaggerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.stag_item, null, false );
        MyStaggerHolder holder = new MyStaggerHolder( view );
        return holder;
    }

    @Override
    public void onBindViewHolder(MyStaggerHolder holder, int position) {
        ImageLoaderUtils.getInstance().initImageLoader( list.get( position ).getImages().split( "!" )[0], holder.stag_img );
        holder.stag_title.setText( list.get( position ).getSubhead() );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyStaggerHolder extends RecyclerView.ViewHolder {

        private final TextView stag_title;
        private final ImageView stag_img;

        public MyStaggerHolder(View itemView) {
            super( itemView );
            stag_title = itemView.findViewById( R.id.stag_title );
            stag_img = itemView.findViewById( R.id.stag_img );
        }
    }
}
