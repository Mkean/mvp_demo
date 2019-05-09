package com.bwie.mvp_demo.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.mvp_demo.MainActivity;
import com.bwie.mvp_demo.R;
import com.bwie.mvp_demo.login.bean.LoginBean;
import com.bwie.mvp_demo.login.bean.User;
import com.bwie.mvp_demo.login.presenter.LoginPresenter;
import com.bwie.mvp_demo.login.view.Iview;
import com.bwie.mvp_demo.register.RegisterActivity;
import com.bwie.mvp_demo.thirdlogin.QQ_Login;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;

import utils.LogUtils;

public class LoginActivity extends AppCompatActivity implements Iview, View.OnClickListener {
    private static final String APP_ID = "222222";//官方获取的APPID
    public static final String TAG = "LoginActivity";
    private EditText ed_pwd;
    private EditText ed_user;
    private ImageView qq_login;
    private ImageView weixin_login;
    private LoginPresenter mLoginPresenter;
    private Button login;
    private TextView reg;
    private Tencent mTencent;
    private QQ_Login mQQ_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mTencent = Tencent.createInstance(APP_ID, LoginActivity.this);
        mLoginPresenter = new LoginPresenter(this);
        ed_pwd = (EditText) findViewById(R.id.ed_pwd);
        ed_user = (EditText) findViewById(R.id.ed_user);
        login = (Button) findViewById(R.id.login);
        reg = (TextView) findViewById(R.id.reg);
        qq_login = (ImageView) findViewById(R.id.qq_login);
        weixin_login = (ImageView) findViewById(R.id.weixin_login);
        qq_login.setOnClickListener(this);
        weixin_login.setOnClickListener(this);
        reg.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login:
                String username = ed_user.getText().toString();
                String pwd = ed_pwd.getText().toString();
                Log.e(TAG, username + ":" + pwd);
                if (username.equals("") || pwd.equals("")) {
                    Toast.makeText(this, "用户名或密码为空，请填写！", Toast.LENGTH_SHORT).show();
                    return;
                }
                mLoginPresenter.login(new User(username, pwd));
                break;
            case R.id.weixin_login:

                break;
            case R.id.qq_login:
                mQQ_login = new QQ_Login(this, mTencent);
                //all表示获取所有权限
                mTencent.login(this, "all", mQQ_login);
                break;

        }
    }

    @Override
    public void onFailed(LoginBean loginBean) {
        String msg = loginBean.getMsg();
        if (msg.equals("密码格式有问题，不能少于6位数")) {
            Toast.makeText(this, "密码格式有问题，不能少于6位数", Toast.LENGTH_SHORT).show();
            return;
        }
        if (msg.equals("天呢！密码错误")) {
            Toast.makeText(this, "天呢！密码错误", Toast.LENGTH_SHORT).show();
            return;
        }
        if (msg.equals("天呢！用户不存在")) {
            Toast.makeText(this, "天呢！用户不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        if (msg.equals("请输入正确的手机号码")) {
            Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        LogUtils.log(TAG, loginBean.toString());
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, mQQ_login);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
