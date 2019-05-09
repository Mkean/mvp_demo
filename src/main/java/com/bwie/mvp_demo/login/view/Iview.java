package com.bwie.mvp_demo.login.view;

import com.bwie.mvp_demo.login.bean.LoginBean;

/**
 * 作者：王庆
 * 时间：2017/12/8
 */

public interface Iview {
    void onFailed(LoginBean loginBean);

    void onSuccess(LoginBean loginBean);
}
