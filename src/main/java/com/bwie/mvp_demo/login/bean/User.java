package com.bwie.mvp_demo.login.bean;

/**
 * 作者：王庆
 * 时间：2017/12/8
 */

public class User {
    private String username;
    private String pass;

    public User(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
