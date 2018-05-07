package com.example.admin.touying.view.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ListView;

import com.example.admin.touying.base.MainApi;
import com.example.admin.touying.adapter.MyFriendAdapter;
import com.example.admin.touying.MyFriendsModel;
import com.example.admin.touying.R;
import com.example.admin.touying.RetrofitFactory;
import com.example.admin.touying.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Admin on 2018/4/25.
 * 公司：铭轩科技
 */

public class AttentionFragment extends BaseFragment{
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    MainApi mainApi;
    List<MyFriendsModel.ResultBean> list;
    MyFriendAdapter myFriendAdapter;

    @Override
    protected void initView() {
        list = new ArrayList<>();

        myFriendAdapter = new MyFriendAdapter(getActivity(),list);
        listView.setAdapter(myFriendAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
    }

    @Override
    protected void initData() {
        swipeRefreshLayout.setRefreshing(true);
        mainApi = RetrofitFactory.create(MainApi.class);
        mainApi.getFriend(7)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyFriendsModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyFriendsModel myFriendsModel) {
                        swipeRefreshLayout.setRefreshing(false);
                        List<MyFriendsModel.ResultBean> resultBeans = myFriendsModel.getResult();
                        list.clear();
                        list.addAll(resultBeans);

                        myFriendAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+e.getMessage() );
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_attention;
    }

    @Override
    protected void startLoad() {}
}
