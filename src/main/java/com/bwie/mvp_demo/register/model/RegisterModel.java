package com.bwie.mvp_demo.register.model;

import com.bwie.mvp_demo.register.bean.RegisterBean;
import com.bwie.mvp_demo.register.bean.RegisterUser;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import tools.MyApi;
import utils.OkHttpUtils;
import utils.OnUiCallback;

/**
 * 作者：王庆
 * 时间：2017/12/8
 */

public class RegisterModel implements RegModel {

    private onFinishListener listener;

    public void setOnFinishListener(onFinishListener listener) {
        this.listener = listener;
    }

    public interface onFinishListener {
        void onFinish(RegisterBean registerBean);
    }

    @Override
    public void Register(RegisterUser registerUser) {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", registerUser.getUsername());
        map.put("password", registerUser.getPwd());
        OkHttpUtils.doPost(MyApi.REG, map, new OnUiCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSuccess(String result) throws IOException {
                getJS(result);
            }
        });
    }

    private void getJS(String result) {
        Gson gson = new Gson();
        RegisterBean registerBean = gson.fromJson(result, RegisterBean.class);
        if (listener != null) {
            listener.onFinish(registerBean);
        }
    }
}
