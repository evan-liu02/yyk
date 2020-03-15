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
    private List<NursingRecordRsp.NursingRecordData> tempList = new ArrayList<NursingRecordRsp.NursingRecordData>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nursing_record);

        initViews();
    }

    private void initViews() {
//        TextView name = findViewById(R.id.name);

        RecyclerView normalRv = findViewById(R.id.nursing_record_rv);
        nursingRecordAdapter = new NursingRecordAdapter(this, recordList, new int[]{R.layout.layout_list_header, R.layout.layout_time, R.layout.layout_normal_list_item});
        normalRv.setLayoutManager(new LinearLayoutManager(this));
        normalRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        normalRv.setAdapter(nursingRecordAdapter);

        Intent intent = getIntent();
        if (intent != null) {
            String username = intent.getStringExtra("name");
            NursingRecordRsp.NursingRecordData data = new NursingRecordRsp.NursingRecordData();
            data.setTitle(username);
            tempList.add(data);

            data = new NursingRecordRsp.NursingRecordData();
            data.setTitle(AppHelper.getTimeStr());
            tempList.add(data);
//            name.setText(username);
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
                        recordList.addAll(tempList);
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
    }
}
