package com.example.admin.touying.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Admin on 2018/4/25.
 * 公司：铭轩科技
 */

public abstract class BaseFragment extends Fragment {
    private boolean isLazyLoaded;
    private boolean isPrepared;
    protected static final String TAG = BaseFragment.class.getSimpleName();
    private View view;//视图
    private Unbinder unbinder;
    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(initLayout(),container,false);
        unbinder = ButterKnife.bind(this,view);
        initView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract int initLayout();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e(TAG, "setUserVisibleHint: "+isVisibleToUser );
        lazyLoad();
    }

    /**
     * 调用懒加载
     */

    private void lazyLoad() {
        if (getUserVisibleHint() && isPrepared && !isLazyLoaded) {
            initData();
            isLazyLoaded = true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        isLazyLoaded = false;
        isPrepared = false;
    }

    protected abstract void startLoad();//加载数据
}
