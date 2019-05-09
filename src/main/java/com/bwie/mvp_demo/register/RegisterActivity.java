package com.bwie.mvp_demo.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.mvp_demo.R;
import com.bwie.mvp_demo.login.LoginActivity;
import com.bwie.mvp_demo.register.bean.RegisterBean;
import com.bwie.mvp_demo.register.bean.RegisterUser;
import com.bwie.mvp_demo.register.presenter.RegPresenter;
import com.bwie.mvp_demo.register.view.RegView;

import utils.LogUtils;

public class RegisterActivity extends AppCompatActivity implements RegView, View.OnClickListener {
    public static final String TAG = "RegisterActivity";
    private RegPresenter mPresenter;
    private EditText ed_user;
    private EditText ed_pwd;
    private EditText ed_pass;
    private EditText ed_email;
    private Button reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        mPresenter = new RegPresenter(this);
        ed_user = (EditText) findViewById(R.id.ed_user);
        ed_pwd = (EditText) findViewById(R.id.ed_pwd);
        ed_pass = (EditText) findViewById(R.id.ed_pass);
        ed_email = (EditText) findViewById(R.id.ed_email);
        reg = (Button) findViewById(R.id.reg);
        reg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String user = ed_user.getText().toString();
        String pwd = ed_pwd.getText().toString();
        String pass = ed_pass.getText().toString();
        String email = ed_email.getText().toString();
        if (user.equals("")) {
            Toast.makeText(this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pwd.equals("")) {
            Toast.makeText(this, "密码不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass.equals("")) {
            Toast.makeText(this, "第二次密码不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.equals("")) {
            Toast.makeText(this, "邮箱不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pwd.equals(pass)) {
            Toast.makeText(this, "两次密码输入不同，请重新输入。", Toast.LENGTH_SHORT).show();
            return;
        }
        mPresenter.Register(new RegisterUser(ed_user.getText().toString(), ed_pwd.getText().toString()));
    }

    @Override
    public void onFailed(RegisterBean registerBean) {
        String msg = registerBean.getMsg();
        if (msg.equals("天呢！用户已注册")) {
            Toast.makeText(this, "天呢！用户已注册", Toast.LENGTH_SHORT).show();
            return;
        }
        if (msg.equals("密码格式有问题，不能少于6位数")) {
            Toast.makeText(this, "密码格式有问题，不能少于6位数", Toast.LENGTH_SHORT).show();
            return;
        }
        if (msg.equals("请输入正确的手机号码")) {
            Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    public void onSuccess(RegisterBean registerBean) {
        LogUtils.log(TAG, registerBean.toString());
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}
