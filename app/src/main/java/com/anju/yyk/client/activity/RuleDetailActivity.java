package com.anju.yyk.client.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.anju.yyk.client.R;
import com.anju.yyk.client.data.NoticeDetailRsp;
import com.anju.yyk.client.http.RetrofitHelper;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RuleDetailActivity extends BaseActivity implements View.OnClickListener {

    private TextView content;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule_detail);

        initViews();
    }

    private void initViews() {
        TextView title = findViewById(R.id.title);
        title.setText("规定详情");
        content = findViewById(R.id.content);

        findViewById(R.id.back).setOnClickListener(this);

        Intent intent = getIntent();
        if (intent != null) {
            String id = intent.getStringExtra("id");
            RetrofitHelper.getInstance().getRuleDetail(new Observer<NoticeDetailRsp>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(NoticeDetailRsp noticeDetailRsp) {
                    Log.e("yyk", noticeDetailRsp + "");
                    if (noticeDetailRsp.getStatus() == 0) {
                        NoticeDetailRsp.NoticeDetailData data = noticeDetailRsp.getData();
                        if (data != null) {
                            content.setText(Html.fromHtml(data.getContent()));
                        }
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            }, "22", id);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }
}
