package com.anju.yyk.client.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anju.yyk.client.R;
import com.anju.yyk.client.adapter.NoticeAdapter;
import com.anju.yyk.client.data.NoticeRsp;

import java.util.ArrayList;

public class NoticeActivity extends BaseActivity implements View.OnClickListener, NoticeAdapter.OnItemClickListener {
    private ArrayList<NoticeRsp.NoticeData> noticeList = new ArrayList<NoticeRsp.NoticeData>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        initViews();
    }

    private void initViews() {
        TextView title = findViewById(R.id.title);
        title.setText("通知");

        findViewById(R.id.back).setOnClickListener(this);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            ArrayList<NoticeRsp.NoticeData> noticeList = intent.getExtras().getParcelableArrayList("notice");
            if (noticeList != null && noticeList.size() > 0) {
                this.noticeList.addAll(noticeList);
            }
        }

        RecyclerView noticeRv = findViewById(R.id.notice_rv);
        NoticeAdapter noticeAdapter = new NoticeAdapter(this, noticeList, R.layout.layout_rules_item);
        noticeAdapter.setItemClickListener(this);
        noticeRv.setLayoutManager(new LinearLayoutManager(this));
        noticeRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        noticeRv.setAdapter(noticeAdapter);
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
        if (noticeList != null && position < noticeList.size()) {
            Intent intent = new Intent(this, RuleDetailActivity.class);
            intent.putExtra("id", noticeList.get(position).getId());
            startActivity(intent);
        }
    }
}
