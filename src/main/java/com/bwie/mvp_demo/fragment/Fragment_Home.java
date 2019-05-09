package com.bwie.mvp_demo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.mvp_demo.R;
import com.bwie.mvp_demo.adapter.MyAdapter;
import com.bwie.mvp_demo.xbanner.presenter.BanPresenter;
import com.bwie.mvp_demo.xbanner.view.BanView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.List;

import bean.Banner_Bean;

/**
 * 作者：王庆
 * 时间：2017/12/12
 */

public class Fragment_Home extends Fragment implements BanView, View.OnClickListener {
    public static final int REQUESTCODE = 5;
    private View view;
    private BanPresenter banPresenter;
    private ImageView erweima;
    private ImageView message;
    private RecyclerView rlv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.fragment_home, container, false );
        initView();
        return view;
    }

    private void initView() {
        banPresenter = new BanPresenter( this );
        banPresenter.getImg();
        erweima = view.findViewById( R.id.erweima );
        message = view.findViewById( R.id.message );
        rlv = view.findViewById( R.id.rlv );
        erweima.setOnClickListener( this );
        message.setOnClickListener( this );
    }

    @Override
    public void getImage(Banner_Bean banner_bean) {
        List<Banner_Bean.DataBean> data = banner_bean.getData();
        List<Banner_Bean.MiaoshaBean.ListBeanX> list = banner_bean.getMiaosha().getList();
        rlv.setLayoutManager( new LinearLayoutManager( getContext(), LinearLayoutManager.VERTICAL, false ) );
        MyAdapter adapter = new MyAdapter( getContext() );
        rlv.setAdapter(adapter);
        adapter.setData(data);
        adapter.setList(list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.erweima:
                Intent intent = new Intent( getContext(), CaptureActivity.class );
                startActivityForResult( intent, REQUESTCODE );
                break;
            case R.id.message:
                Toast.makeText( getContext(), "你还没有消息可阅读", Toast.LENGTH_SHORT ).show();
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == REQUESTCODE) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt( CodeUtils.RESULT_TYPE ) == CodeUtils.RESULT_SUCCESS) {
                    String string = bundle.getString( CodeUtils.RESULT_STRING );
                    Toast.makeText( getContext(), "扫描结果：" + string, Toast.LENGTH_SHORT ).show();
                } else if (bundle.getInt( CodeUtils.RESULT_TYPE ) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText( getContext(), "扫描失败", Toast.LENGTH_SHORT ).show();
                }
            }
        }
    }
}
