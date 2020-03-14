package com.anju.yyk.client.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anju.yyk.client.R;
import com.anju.yyk.client.adapter.MainAdapter;
import com.anju.yyk.client.adapter.RulesAdapter;
import com.anju.yyk.client.data.NoticeRsp;
import com.anju.yyk.client.http.RetrofitHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RulesActivity extends BaseActivity implements View.OnClickListener, RulesAdapter.OnItemClickListener {
    private List<NoticeRsp.NoticeData> rulesList = new ArrayList<NoticeRsp.NoticeData>();
    private RulesAdapter rulesAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        initViews();
    }

    private void initViews() {
        TextView title = findViewById(R.id.title);
        title.setText("管理规定");

        findViewById(R.id.back).setOnClickListener(this);

        RecyclerView rulesRv = findViewById(R.id.rules_rv);
        rulesAdapter = new RulesAdapter(this, rulesList, R.layout.layout_rules_item);
        rulesAdapter.setItemClickListener(this);
        rulesRv.setLayoutManager(new LinearLayoutManager(this));
        rulesRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rulesRv.setAdapter(rulesAdapter);

        RetrofitHelper.getInstance().getRules(new Observer<NoticeRsp>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(NoticeRsp noticeRsp) {
                if (noticeRsp.getStatus() == 0) {
                    List<NoticeRsp.NoticeData> dataList = noticeRsp.getData();
                    if (dataList != null && dataList.size() > 0) {
                        rulesList.addAll(dataList);
                        rulesAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, "22", "139");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
            default:
                break;
        }
    }

    @Override
    public void OnItemClicked(int position) {

    }
}
