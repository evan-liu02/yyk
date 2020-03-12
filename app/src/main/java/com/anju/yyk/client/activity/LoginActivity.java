package com.anju.yyk.client.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.anju.yyk.client.R;
import com.anju.yyk.client.data.LoginRsp;
import com.anju.yyk.client.http.RetrofitHelper;
import com.anju.yyk.client.util.AppHelper;
import com.anju.yyk.client.util.SPUtils;
import com.anju.yyk.client.view.LoadingDialog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText usernameEt;
    private EditText passwordEt;
    private CheckBox rememberCb;
    private boolean isRememberPwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        statusInScreen(this);
        initViews();
        autoLogin();
    }

    private void initViews() {
        usernameEt = findViewById(R.id.edt_account);
        passwordEt = findViewById(R.id.edt_pwd);
        rememberCb = findViewById(R.id.cb_remember);
        Button loginBtn = findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(this);
        rememberCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                isRememberPwd = isChecked;
            }
        });
    }

    private void autoLogin() {
        boolean rememberPwd = SPUtils.getBoolean("isRememberPwd");
        if (rememberPwd) {
            String username = SPUtils.getString("username");
            String password = SPUtils.getString("password");
            usernameEt.setText(username);
            usernameEt.setSelection(username.length());
            passwordEt.setText(password);
            rememberCb.setChecked(true);
            login(username, password);
        }
    }

    @Override
    public void onClick(View view) {
        if (R.id.btn_login == view.getId()) {
            clickLogin();
        }
    }

    private void clickLogin() {
        String username = usernameEt.getText().toString();
        String password = passwordEt.getText().toString();
        if (TextUtils.isEmpty(username)) {
            showToast("用户名不能为空！");
            return;
        }
        String reg = "^1[345789][0-9]\\d{8}$";
        if (!username.matches(reg)) {
            showToast("请输入正确的手机号！");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            showToast("密码不能为空！");
            return;
        }
        login(username, password);
    }

    private void login(final String username, final String password) {
        LoadingDialog.Builder loadBuilder = new LoadingDialog.Builder(this)
                .setMessage("登录中...")
                .setCancelable(true)
                .setCancelOutside(false);
        final LoadingDialog dialog = loadBuilder.create();
        dialog.show();
        final long start = SystemClock.uptimeMillis();
        RetrofitHelper.getInstance().login(new Observer<LoginRsp>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LoginRsp loginRsp) {
                long end = SystemClock.uptimeMillis();
                long interval = end - start;
                if (interval < 400) {
                    SystemClock.sleep(400 - interval);
                }
                int status = loginRsp.getStatus();
                dialog.dismiss();
                if (status == 0) {
                    if (isRememberPwd) {
                        SPUtils.putBoolean("isRememberPwd", true);
                        SPUtils.putString("username", username);
                        SPUtils.putString("password", password);
                    }
                    if (loginRsp.getData() != null) {
                        AppHelper.userId = loginRsp.getData().getUser_id();
                    }
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else if (status == 1) {
                    showToast(loginRsp.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                long end = SystemClock.uptimeMillis();
                long interval = end - start;
                if (interval < 400) {
                    SystemClock.sleep(400 - interval);
                }
                dialog.dismiss();
                showToast("登录失败，请重试！");
            }

            @Override
            public void onComplete() {

            }
        }, username, password);
    }
}
