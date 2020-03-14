package com.anju.yyk.client.http;

import com.anju.yyk.client.data.CheckingRecordRsp;
import com.anju.yyk.client.data.ElderInfoRsp;
import com.anju.yyk.client.data.FeedbackRsp;
import com.anju.yyk.client.data.LoginRsp;
import com.anju.yyk.client.data.NoticeRsp;
import com.anju.yyk.client.data.PasswordRsp;
import com.anju.yyk.client.data.NursingRecordRsp;
import com.anju.yyk.client.data.TipsRsp;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    String PATH = "tools/jiashujiekou.ashx";

    @POST(PATH)
    Observable<LoginRsp> login(@Query("action") String action, @Query("username") String username, @Query("password") String password);

    @POST(PATH)
    Observable<ElderInfoRsp> getElderInfo(@Query("action") String action, @Query("jiashu_id") String id);

    @POST(PATH)
    Observable<NoticeRsp> getNotice(@Query("action") String action);

    @POST(PATH)
    Observable<TipsRsp> getTips(@Query("action") String action, @Query("id") String id);

    @POST(PATH)
    Observable<FeedbackRsp> feedback(@Query("action") String action, @Query("content") String content, @Query("jiashu_id") String id);

    @POST(PATH)
    Observable<PasswordRsp> editPwd(@Query("action") String action, @Query("oldpassword") String oldPassword,
                                    @Query("password") String password, @Query("cpassword") String cPassword, @Query("jiashu_id") String id);

    @POST(PATH)
    Observable<NoticeRsp> getRules(@Query("action") String action, @Query("channel_id") String channelId, @Query("category_id") String categoryId);

    @POST(PATH)
    Call<ResponseBody> getRuleDetail(@Query("action") String action, @Query("channel_id") String channelId, @Query("id") String id);

    @POST(PATH)
    Observable<NursingRecordRsp> getNursingRecord(@Query("action") String action, @Query("laoren_id") String id, @Query("time") String time);

    @POST(PATH)
    Observable<CheckingRecordRsp> getCheckingRecords(@Query("action") String action, @Query("laoren_id") String id, @Query("time") String time);
}
