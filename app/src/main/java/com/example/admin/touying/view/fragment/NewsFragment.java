package com.example.admin.touying.view.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.admin.touying.R;
import com.example.admin.touying.base.BaseFragment;
import com.example.admin.touying.view.news.BezierActivity;
import com.example.admin.touying.view.news.QQStepView;
import com.example.admin.touying.view.news.SwipeRefreshTestActivity;
import com.example.admin.touying.view.news.TestView;
import com.example.admin.touying.view.news.WatchBoardActivity;

import butterknife.BindView;

/**
 * Created by Admin on 2018/4/25.
 * 公司：铭轩科技
 */

public class NewsFragment extends BaseFragment {
    @BindView(R.id.qqStepView)
    Button qqStepView;
    @BindView(R.id.swipeRefresh)
    Button swipeRefresh;
    @BindView(R.id.bezier)
    Button bezier;
    @BindView(R.id.watch_board)
    Button watch_board;

    @Override
    protected void initView() {
        qqStepView.setOnClickListener(onClickListener);
        swipeRefresh.setOnClickListener(onClickListener);
        bezier.setOnClickListener(onClickListener);
        watch_board.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()){
                case R.id.qqStepView:
                    intent = new Intent(mContext, QQStepView.class);
                    startActivity(intent);
                    break;
                case R.id.swipeRefresh:
                    intent = new Intent(mContext, SwipeRefreshTestActivity.class);
                    startActivity(intent);
                    break;
                case R.id.bezier:
                    intent = new Intent(mContext, BezierActivity.class);
                    startActivity(intent);
                    break;
                case R.id.watch_board:
                    intent = new Intent(mContext, WatchBoardActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    @Override
    protected void initData() {
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void startLoad() {

    }
}
