package com.example.admin.touying.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;


/**
 * Created by Admin on 2018/4/25.
 * 公司：铭轩科技
 */

public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 是否沉浸状态栏
     **/
    private boolean isSetStatusBar = true;
    /**
     * 是否允许全屏
     **/
    private boolean isAllowFullScreen = true;
    /**
     * 是否禁止旋转屏幕
     **/
    private boolean isAllowScreenRoate = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        ButterKnife.bind(this);

        initView();
        initData();
    }



    protected abstract int initLayout();

    protected abstract void initData();

    protected abstract void initView();


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
