package com.bwie.mvp_demo.register.bean;

/**
 * 作者：王庆
 * 时间：2017/12/8
 */

public class RegisterUser {
    private String username;
    private String pwd;
    private String pass;
    private String email;

    public RegisterUser() {
    }

    public RegisterUser(String username, String pwd) {
        this.username = username;
        this.pwd = pwd;
    }

    public RegisterUser(String username, String pwd, String pass, String email) {
        this.username = username;
        this.pwd = pwd;
        this.pass = pass;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "RegisterUser{" +
                "username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", pass='" + pass + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
