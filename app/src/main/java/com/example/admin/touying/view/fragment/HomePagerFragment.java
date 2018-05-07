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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 2018/4/25.
 * 公司：铭轩科技
 */

public class HomePagerFragment extends BaseFragment{
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.listView)
    ListView listView;
    List<MyFriendsModel.ResultBean> list ;
    MyFriendAdapter myFriendAdapter;

    @Override
    protected void initView() {
        list = new ArrayList<>();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });

        myFriendAdapter = new MyFriendAdapter(getActivity(),list);
        listView.setAdapter(myFriendAdapter);
    }

    @Override
    protected void initData() {
        swipeRefreshLayout.setRefreshing(true);
        MainApi mainApi = RetrofitFactory.create(MainApi.class);
        Call<MyFriendsModel> call = mainApi.getFriends(7);
        call.enqueue(new Callback<MyFriendsModel>() {
            @Override
            public void onResponse(Call<MyFriendsModel> call, Response<MyFriendsModel> response) {
                swipeRefreshLayout.setRefreshing(false);
                list.clear();
                MyFriendsModel body = response.body();
                List<MyFriendsModel.ResultBean> resultBeans = body.getResult();
                list.addAll(resultBeans);

                myFriendAdapter.notifyDataSetChanged();
            }


            @Override
            public void onFailure(Call<MyFriendsModel> call, Throwable t) {
//                swipeRefreshLayout.setRefreshing(false);
                t.printStackTrace();
            }
        });
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void startLoad() {

    }
}
