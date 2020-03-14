package com.anju.yyk.client.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.anju.yyk.client.R;
import com.anju.yyk.client.fragment.BaseFragment;
import com.anju.yyk.client.fragment.MainFragment;
import com.anju.yyk.client.util.SPUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigationView;

    private Class<?>[] FRAGMENTS = {MainFragment.class};
    private FragmentManager mFManager;
    private BaseFragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFManager = getSupportFragmentManager();

        initViews();
    }

    private void initViews() {
        navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

        changeFrag(FRAGMENTS[0]);
    }

    private void changeFrag(Class<?> fragmentClazz) {
        // hide all fragment
        FragmentTransaction transaction = mFManager.beginTransaction();
        for (Class<?> fc : FRAGMENTS) {
            /*if (fc == fragmentClazz) {
                continue;
            }*/
            Fragment fragment = mFManager.findFragmentByTag(fc
                    .getCanonicalName());
            if (fragment != null) {
                transaction.hide(fragment);
            }
        }

        Fragment lastFragment = mFManager.findFragmentByTag(fragmentClazz.getCanonicalName());
        if (lastFragment == null) {
            try {
                lastFragment = (Fragment) fragmentClazz.newInstance();
                mCurrentFragment = (BaseFragment) lastFragment;
                transaction.add(R.id.content, lastFragment,
                        fragmentClazz.getCanonicalName());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            transaction.show(lastFragment);
            mCurrentFragment = (BaseFragment) lastFragment;
        }
        transaction.commitAllowingStateLoss();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                logout();
                return false;
            case R.id.home:
                changeFrag(FRAGMENTS[0]);
                return true;
        }
        return false;
    }

    private void logout() {
        new AlertDialog.Builder(this).setMessage("确定要退出当前账号？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                SPUtils.clear();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();
    }
}
