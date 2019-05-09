package com.bwie.mvp_demo.thirdlogin;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：王庆
 * 时间：2017/12/8
 */

public class QQ_Login implements IUiListener {
    private static final String TAG = "LoginActivity";
    private Tencent mTencent;
    private UserInfo mUserInfo;
    private Context context;

    public QQ_Login(Context context, Tencent mTencent) {
        this.context = context;
        this.mTencent = mTencent;
    }

    /**
     * 自定义监听器实现IUiListener接口后，需要实现的3个方法
     * onComplete完成 onError错误 onCancel取消
     */
    @Override
    public void onComplete(Object response) {
        Toast.makeText(context, "授权成功", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "response:" + response);
        JSONObject obj = (JSONObject) response;
        try {
            String openID = obj.getString("openid");
            String accessToken = obj.getString("access_token");
            String expires = obj.getString("expires_in");
            mTencent.setOpenId(openID);
            mTencent.setAccessToken(accessToken, expires);
            QQToken qqToken = mTencent.getQQToken();
            mUserInfo = new UserInfo(context, qqToken);
            mUserInfo.getUserInfo(new IUiListener() {
                @Override
                public void onComplete(Object response) {
                    Log.e(TAG, "登录成功" + response.toString());
                }

                @Override
                public void onError(UiError uiError) {
                    Log.e(TAG, "登录失败" + uiError.toString());
                }

                @Override
                public void onCancel() {
                    Log.e(TAG, "登录取消");

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(UiError uiError) {
        Toast.makeText(context, "授权失败", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCancel() {
        Toast.makeText(context, "授权取消", Toast.LENGTH_SHORT).show();

    }

}
