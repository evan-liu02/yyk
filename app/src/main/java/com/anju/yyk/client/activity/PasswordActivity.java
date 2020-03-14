package com.anju.yyk.client.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.anju.yyk.client.R;
import com.anju.yyk.client.data.PasswordRsp;
import com.anju.yyk.client.http.RetrofitHelper;
import com.anju.yyk.client.util.AppHelper;
import com.anju.yyk.client.util.SPUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PasswordActivity extends BaseActivity implements View.OnClickListener {
    private EditText oldPwdEt;
    private EditText newPwdEt;
    private EditText confirmPwdEt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        initViews();
    }

    private void initViews() {
        TextView title = findViewById(R.id.title);
        title.setText("修改密码");

        oldPwdEt = findViewById(R.id.old_pwd_et);
        newPwdEt = findViewById(R.id.new_pwd_et);
        confirmPwdEt = findViewById(R.id.confirm_pwd_et);

        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.confirm).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.confirm:
                confirm();
                break;
            default:
                break;
        }
    }

    private void confirm() {
        String oldPassword = oldPwdEt.getText().toString();
        String newPassword = newPwdEt.getText().toString();
        String confirmPassword = confirmPwdEt.getText().toString();

        if (TextUtils.isEmpty(oldPassword)) {
            showToast("请输入原始密码");
            return;
        }
        if (TextUtils.isEmpty(newPassword)) {
            showToast("请输入新密码");
            return;
        }
        if (TextUtils.isEmpty(confirmPassword)) {
            showToast("请输入确认密码");
            return;
        }
        if (!TextUtils.equals(newPassword, confirmPassword)) {
            showToast("新密码与确认密码不一致");
            return;
        }

        RetrofitHelper.getInstance().editPwd(new Observer<PasswordRsp>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PasswordRsp passwordRsp) {
                int status = passwordRsp.getStatus();
                if (status == 0) {
                    SPUtils.remove("password");
                    setResult(RESULT_OK);
                    finish();
                } else if (status == 1) {
                    showToast(passwordRsp.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                showToast("密码修改失败！");
            }

            @Override
            public void onComplete() {

            }
        }, oldPassword, newPassword, confirmPassword, AppHelper.userId);
    }
}
