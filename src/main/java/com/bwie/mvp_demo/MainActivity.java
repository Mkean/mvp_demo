package com.bwie.mvp_demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bwie.mvp_demo.fragment.Fragment_Cart;
import com.bwie.mvp_demo.fragment.Fragment_Class;
import com.bwie.mvp_demo.fragment.Fragment_Home;
import com.bwie.mvp_demo.fragment.Fragment_My;
import com.hjm.bottomtabbar.BottomTabBar;

public class MainActivity extends AppCompatActivity {

    private BottomTabBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = (BottomTabBar) findViewById(R.id.bar);
        bar.init(getSupportFragmentManager())
                .setChangeColor(Color.RED, Color.YELLOW)
                .addTabItem("首页", R.drawable.ic_nav_home, Fragment_Home.class)
                .addTabItem("分类", R.drawable.ic_nav_class, Fragment_Class.class)
                .addTabItem("购物车", R.drawable.ic_nav_cart, Fragment_Cart.class)
                .addTabItem("我的", R.drawable.ic_nav_user, Fragment_My.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });
    }
}
