package com.anju.yyk.client.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anju.yyk.client.R;
import com.anju.yyk.client.adapter.MainAdapter;
import com.anju.yyk.client.data.ElderInfoRsp;
import com.anju.yyk.client.data.MenuData;
import com.anju.yyk.client.data.NoticeRsp;
import com.anju.yyk.client.data.TipsRsp;
import com.anju.yyk.client.http.RetrofitHelper;
import com.anju.yyk.client.util.AppHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainFragment extends BaseFragment {

    private RetrofitHelper retrofitHelper;
    private List<MenuData> menuList = new ArrayList<MenuData>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        retrofitHelper = RetrofitHelper.getInstance();
        initMenu();

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView menuRv = rootView.findViewById(R.id.menu_rv);
        MainAdapter menuAdapter = new MainAdapter(context, menuList, R.layout.layout_main_item);
        menuRv.setLayoutManager(new GridLayoutManager(context, 3));
        menuRv.setAdapter(menuAdapter);

        getInfo();
        getNotice();
        getTips();
        return rootView;
    }

    private void initMenu() {
        MenuData menu = new MenuData();
        menu.setTitle("护理记录");
        menu.setIconId(R.mipmap.ic_nursing_record);
        menuList.add(menu);

        menu = new MenuData();
        menu.setTitle("查房记录");
        menu.setIconId(R.mipmap.ic_checking_record);
        menuList.add(menu);

        menu = new MenuData();
        menu.setTitle("账单查询");
        menu.setIconId(R.mipmap.ic_bills);
        menuList.add(menu);

        menu = new MenuData();
        menu.setTitle("管理规定");
        menu.setIconId(R.mipmap.ic_rules);
        menuList.add(menu);

        menu = new MenuData();
        menu.setTitle("意见反馈");
        menu.setIconId(R.mipmap.ic_feedback);
        menuList.add(menu);

        menu = new MenuData();
        menu.setTitle("修改密码");
        menu.setIconId(R.mipmap.ic_change_pwd);
        menuList.add(menu);

        menu = new MenuData();
        menu.setTitle("事故登记");
        menu.setIconId(R.mipmap.ic_fault);
        menuList.add(menu);

        menu = new MenuData();
        menu.setTitle("一键呼叫");
        menu.setIconId(R.mipmap.ic_call);
        menuList.add(menu);
    }

    /**
     * 获取老人信息
     */
    private void getInfo() {
        retrofitHelper.getElderInfo(new Observer<ElderInfoRsp>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ElderInfoRsp elderInfoRsp) {
                int status = elderInfoRsp.getStatus();
                if (status == 0) {

                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, AppHelper.userId);
    }

    /**
     * 获取通知
     */
    private void getNotice() {
        retrofitHelper.getNotice(new Observer<NoticeRsp>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(NoticeRsp noticeRsp) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 获取提醒
     */
    private void getTips() {
        retrofitHelper.getTips(new Observer<TipsRsp>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TipsRsp tipsRsp) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, AppHelper.userId);
    }
}
