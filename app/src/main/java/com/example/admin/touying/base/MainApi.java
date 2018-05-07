package com.example.admin.touying.base;

import com.example.admin.touying.MyFriendsModel;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Admin on 2018/4/27.
 * 公司：铭轩科技
 */

public interface MainApi {

    @GET("myfridends")
    Call<MyFriendsModel> getFriends(@Query("p_id") int p_id);

    @GET("myfridends")
    Observable<MyFriendsModel> getFriend(@Query("p_id") int p_id);
}
