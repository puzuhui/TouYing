package com.example.admin.touying;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.example.admin.touying.base.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 2018/4/26.
 * 公司：铭轩科技
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.title)
    TextInputEditText title;
    @BindView(R.id.passward)
    TextInputEditText passward;
    @BindView(R.id.login)
    Button login;

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }


    @Override
    protected void initData() {


    }

    @Override
    protected void initView() {

    }

    @OnClick(R.id.login)
    public void onViewClicked() {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);

    }

}
