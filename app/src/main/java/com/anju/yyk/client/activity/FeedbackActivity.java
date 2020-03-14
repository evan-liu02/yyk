package com.anju.yyk.client.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.anju.yyk.client.R;
import com.anju.yyk.client.data.FeedbackRsp;
import com.anju.yyk.client.http.RetrofitHelper;
import com.anju.yyk.client.util.AppHelper;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FeedbackActivity extends BaseActivity implements View.OnClickListener {
    private EditText contentEt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        initViews();
    }

    private void initViews() {
        TextView title = findViewById(R.id.title);
        title.setText("意见反馈");

        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.submit).setOnClickListener(this);

        contentEt = findViewById(R.id.feedback_content);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.submit:
                submit();
                break;
            default:
                break;
        }
    }

    private void submit() {
        String content = contentEt.getText().toString();
        if (TextUtils.isEmpty(content)) {
            showToast("请输入反馈意见");
            return;
        }
        RetrofitHelper.getInstance().feedback(new Observer<FeedbackRsp>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(FeedbackRsp feedbackRsp) {
                if (feedbackRsp.getStatus() == 0) {
                    showToast("意见反馈成功！");
                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, content, AppHelper.userId);
    }
}
