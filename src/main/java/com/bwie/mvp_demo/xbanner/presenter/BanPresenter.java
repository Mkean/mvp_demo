package com.bwie.mvp_demo.xbanner.presenter;

import com.bwie.mvp_demo.xbanner.model.BanModel;
import com.bwie.mvp_demo.xbanner.view.BanView;

import bean.Banner_Bean;

/**
 * 作者：王庆
 * 时间：2017/12/12
 */

public class BanPresenter implements BanModel.BannerImage {
    private BanView banView;
    private BanModel banModel;

    public BanPresenter(BanView banView) {
        this.banView = banView;
        banModel = new BanModel();
    }

    public void getImg() {
        banModel.getBanner();
        banModel.setBannerImage(this);
    }

    @Override
    public void getBannerImage(Banner_Bean banner_bean) {
        if (banner_bean != null) {
            banView.getImage(banner_bean);
        }
    }
}
