package com.bwie.mvp_demo.xbanner.model;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import bean.Banner_Bean;
import okhttp3.Call;
import tools.MyApi;
import utils.OkHttpUtils;
import utils.OnUiCallback;

/**
 * 作者：王庆
 * 时间：2017/12/12
 */

public class BanModel {

    private List<Banner_Bean.DataBean> list;
    private BannerImage bannerImage;

    public void setBannerImage(BannerImage bannerImage) {
        this.bannerImage = bannerImage;
    }

    public interface BannerImage {
        void getBannerImage(Banner_Bean banner_bean);
    }

    public void getBanner() {
        OkHttpUtils.doGet(MyApi.BANNER, new OnUiCallback() {
            @Override
            public void onFailed(Call call, IOException e) {
                Log.e("TAG", e.getMessage());
            }

            @Override
            public void onSuccess(String result) throws IOException {
                getJs(result);
            }
        });
    }

    private void getJs(String s) {
        Gson gson = new Gson();

        Banner_Bean banner_bean = gson.fromJson(s, Banner_Bean.class);
        if (bannerImage != null) {
            bannerImage.getBannerImage(banner_bean);
        }


    }

}
