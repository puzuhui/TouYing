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

public class SwipeRefreshTestActivity extends BaseActivity {
    @BindView(R.id.swipeRefresh)
    SwipeRefreshView swipeRefresh;
    @Override
    protected int initLayout() {
        return R.layout.activity_swipe;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        swipeRefresh.setSwipeFinishText(100);

        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0,100);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                swipeRefresh.setmSwipeStartText((int)animatedValue);
            }
        });

        valueAnimator.start();
    }
}
