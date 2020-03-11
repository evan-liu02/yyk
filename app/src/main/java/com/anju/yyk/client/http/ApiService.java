package com.anju.yyk.client.http;

import com.anju.yyk.client.data.LoginRsp;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    String PATH = "tools/jiashujiekou.ashx";

    @POST(PATH)
    Observable<LoginRsp> login(@Query("action") String action, @Query("username") String username, @Query("password") String password);
}
