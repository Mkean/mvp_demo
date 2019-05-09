package com.bwie.mvp_demo.register.presenter;

import com.bwie.mvp_demo.register.bean.RegisterBean;
import com.bwie.mvp_demo.register.bean.RegisterUser;
import com.bwie.mvp_demo.register.model.RegisterModel;
import com.bwie.mvp_demo.register.view.RegView;

/**
 * 作者：王庆
 * 时间：2017/12/8
 */

public class RegPresenter implements RegisterModel.onFinishListener {
    private RegView regView;
    private RegisterModel registerModel;

    public RegPresenter(RegView regView) {
        this.regView = regView;
        registerModel = new RegisterModel();
    }

    public void Register(RegisterUser user) {
        registerModel.Register(user);
        registerModel.setOnFinishListener(this);
    }

    @Override
    public void onFinish(RegisterBean registerBean) {
        String code = registerBean.getCode();
        if (code.equals("0")) {
            regView.onSuccess(registerBean);
        } else {
            regView.onFailed(registerBean);
        }
    }
}
