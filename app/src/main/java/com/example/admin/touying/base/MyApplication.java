package com.example.admin.touying.base;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.admin.touying.R;

/**
 * Created by Admin on 2018/4/25.
 * 公司：铭轩科技
 */

public class MyApplication extends Application  {
    @Override
    public void onCreate() {
        super.onCreate();
//        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
//            @Override
//            public void onActivityCreated(Activity activity, Bundle bundle) {
//                //这里全局给Activity设置toolbar和title
//                if(activity.findViewById(R.id.toolbar) != null ){//找到 Toolbar 并且替换 Actionbar
//                    if(activity instanceof AppCompatActivity){
//                        ((AppCompatActivity) activity).setSupportActionBar((Toolbar) activity.findViewById(R.id.toolbar));
//                        ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowTitleEnabled(false);
//                    }else {
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                            activity.setActionBar((android.widget.Toolbar) activity.findViewById(R.id.toolbar));
//                            activity.getActionBar().setDisplayShowTitleEnabled(false);
//                        }
//                    }
//                }
//
//                if(activity.findViewById(R.id.toolbar_title) != null){
//                    ((TextView) activity.findViewById(R.id.toolbar_title)).setText(activity.getTitle());
//                }
//
//            }
//
//            @Override
//            public void onActivityStarted(Activity activity) {
//
//            }
//
//            @Override
//            public void onActivityResumed(Activity activity) {
//
//            }
//
//            @Override
//            public void onActivityPaused(Activity activity) {
//
//            }
//
//            @Override
//            public void onActivityStopped(Activity activity) {
//
//            }
//
//            @Override
//            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
//
//            }
//
//            @Override
//            public void onActivityDestroyed(Activity activity) {
//
//            }
//        });
    }
}
