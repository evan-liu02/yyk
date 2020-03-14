package com.anju.yyk.client.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anju.yyk.client.R;
import com.anju.yyk.client.adapter.NursingRecordAdapter;
import com.anju.yyk.client.data.CheckingRecordRsp;
import com.anju.yyk.client.data.NursingRecordRsp;
import com.anju.yyk.client.http.RetrofitHelper;
import com.anju.yyk.client.util.AppHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class NursingRecordActivity extends BaseActivity {

    private NursingRecordAdapter nursingRecordAdapter;
    private List<NursingRecordRsp.NursingRecordData> recordList = new ArrayList<NursingRecordRsp.NursingRecordData>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nursing_record);

        initViews();
    }

    private void initViews() {
        TextView name = findViewById(R.id.name);

        RecyclerView normalRv = findViewById(R.id.nursing_record_rv);
        nursingRecordAdapter = new NursingRecordAdapter(this, recordList, R.layout.layout_normal_list_item);
        normalRv.setLayoutManager(new LinearLayoutManager(this));
        normalRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        normalRv.setAdapter(nursingRecordAdapter);

        Intent intent = getIntent();
        if (intent != null) {
            String username = intent.getStringExtra("name");
            name.setText(username);
            getData();
        }
    }

    private void getData() {
        RetrofitHelper.getInstance().getNursingRecord(new Observer<NursingRecordRsp>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(NursingRecordRsp nursingRecordRsp) {
                if (nursingRecordRsp.getStatus() == 0) {
                    List<NursingRecordRsp.NursingRecordData> dataList = nursingRecordRsp.getData();
                    if (dataList != null && dataList.size() > 0) {
                        recordList.clear();
                        recordList.addAll(dataList);
                        nursingRecordAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, AppHelper.id, "2019-05-21");

        RetrofitHelper.getInstance().getCheckingRecords(new Observer<CheckingRecordRsp>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CheckingRecordRsp checkingRecordRsp) {
                Log.e("yyk", checkingRecordRsp + "");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, AppHelper.id, "2019-05-21");
    }
}
