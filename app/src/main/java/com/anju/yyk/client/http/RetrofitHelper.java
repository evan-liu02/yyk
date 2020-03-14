package com.anju.yyk.client.http;

import android.util.Log;

import com.anju.yyk.client.BuildConfig;
import com.anju.yyk.client.data.CheckingRecordRsp;
import com.anju.yyk.client.data.ElderInfoRsp;
import com.anju.yyk.client.data.FeedbackRsp;
import com.anju.yyk.client.data.LoginRsp;
import com.anju.yyk.client.data.NoticeDetailRsp;
import com.anju.yyk.client.data.NoticeRsp;
import com.anju.yyk.client.data.PasswordRsp;
import com.anju.yyk.client.data.NursingRecordRsp;
import com.anju.yyk.client.data.TipsRsp;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private static final String BASE_URL = "http://haohua.zhihuidangjian.com/";
    private static final String ACTION_LOGIN = "denglu";
    private static final String ACTION_INFO = "laoren_list";
    private static final String ACTION_NOTICE = "tongzhi";
    private static final String ACTION_TIPS = "tixing";
    private static final String ACTION_FEEDBACK = "yijian_add";
    private static final String ACTION_PASSWORD = "mima_update";
    private static final String ACTION_RULES = "guanliguiding";
    private static final String ACTION_RULE_DETAIL = "gt_show";
    private static final String ACTION_NURSING_RECORD = "hulijilu";
    private static final String ACTION_CHECKING_RECORDS = "chafangjilu";

    private static RetrofitHelper self;
    private ApiService apiService;

    public static RetrofitHelper getInstance() {
        if (self == null) {
            synchronized (RetrofitHelper.class) {
                if (self == null) {
                    self = new RetrofitHelper();
                }
            }
        }
        return self;
    }

    private RetrofitHelper() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            //Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);//这里可以选择拦截级别
            builder.addInterceptor(loggingInterceptor);
        }

        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public void login(Observer<LoginRsp> observer, String username, String password) {
        apiService.login(ACTION_LOGIN, username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getElderInfo(Observer<ElderInfoRsp> observer, String id) {
        apiService.getElderInfo(ACTION_INFO, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getNotice(Observer<NoticeRsp> observer) {
        apiService.getNotice(ACTION_NOTICE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getTips(Observer<TipsRsp> observer, String id) {
        apiService.getTips(ACTION_TIPS, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void feedback(Observer<FeedbackRsp> observer, String content, String id) {
        apiService.feedback(ACTION_FEEDBACK, content, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void editPwd(Observer<PasswordRsp> observer, String oldPassword, String newPassword, String confirmPassword, String id) {
        apiService.editPwd(ACTION_PASSWORD, oldPassword, newPassword, confirmPassword, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getRules(Observer<NoticeRsp> observer, String channelId, String categoryId) {
        apiService.getRules(ACTION_RULES, channelId, categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getNursingRecord(Observer<NursingRecordRsp> observer, String id, String time) {
        apiService.getNursingRecord(ACTION_NURSING_RECORD, id, time)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getCheckingRecords(Observer<CheckingRecordRsp> observer, String id, String time) {
        apiService.getCheckingRecords(ACTION_CHECKING_RECORDS, id, time)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getRuleDetail(Observer<NoticeDetailRsp> observer, String channelId, String id) {
        Call<ResponseBody> call = apiService.getRuleDetail(ACTION_RULE_DETAIL, channelId, id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.e("yyk", response.body().string());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
