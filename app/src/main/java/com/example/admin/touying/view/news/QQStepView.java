package com.example.admin.touying.view.news;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;

import com.example.admin.touying.R;
import com.example.admin.touying.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Admin on 2018/5/4.
 * 公司：铭轩科技
 */

public class QQStepView extends BaseActivity{
    @BindView(R.id.testview)
    TestView testView;
    @Override
    protected int initLayout() {
        return R.layout.activity_qqstep;
    }

    @Override
    protected void initData() {}

    @Override
    protected void initView() {
        testView.setStepMax(4000);

        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0,3000);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                testView.setmCurrenStep((int)animatedValue);
            }
        });

        valueAnimator.start();
    }
}
