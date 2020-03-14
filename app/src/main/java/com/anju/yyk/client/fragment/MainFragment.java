package com.anju.yyk.client.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anju.yyk.client.R;
import com.anju.yyk.client.activity.FeedbackActivity;
import com.anju.yyk.client.adapter.MainAdapter;
import com.anju.yyk.client.data.ElderInfoRsp;
import com.anju.yyk.client.data.MenuData;
import com.anju.yyk.client.data.NoticeRsp;
import com.anju.yyk.client.data.TipsRsp;
import com.anju.yyk.client.http.RetrofitHelper;
import com.anju.yyk.client.util.AppHelper;
import com.anju.yyk.client.view.VerticalTextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainFragment extends BaseFragment implements MainAdapter.OnItemClickListener {

    private RetrofitHelper retrofitHelper;
    private List<MenuData> menuList = new ArrayList<MenuData>();

    private LinearLayout infoLayout;
    private TextView name;
    private VerticalTextView notice;
    private VerticalTextView tips;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        retrofitHelper = RetrofitHelper.getInstance();
        initMenu();

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        infoLayout = rootView.findViewById(R.id.info_layout);
        name = rootView.findViewById(R.id.name);
        notice = rootView.findViewById(R.id.notice);
        tips = rootView.findViewById(R.id.tips);
        RecyclerView menuRv = rootView.findViewById(R.id.menu_rv);
        MainAdapter menuAdapter = new MainAdapter(context, menuList, R.layout.layout_main_item);
        menuAdapter.setItemClickListener(this);
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
                    List<ElderInfoRsp.ElderData> data = elderInfoRsp.getData();
                    if (data != null && data.size() > 0) {
                        name.setText(data.get(0).getName());
                        infoLayout.setVisibility(View.VISIBLE);
                    }
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
                if (noticeRsp.getStatus() == 0) {
                    List<NoticeRsp.NoticeData> data = noticeRsp.getData();
                    if (data != null && data.size() > 0) {
                        List<String> noticeList = new ArrayList<String>();
                        for (NoticeRsp.NoticeData notice : data) {
                            noticeList.add(notice.getTitle());
                        }
                        notice.setTextList(noticeList);
                    } else {
                        notice.setTextList(Arrays.asList("暂无通知"));
                    }
                }
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
                if (tipsRsp.getStatus() == 0) {
                    List<TipsRsp.TipsData> data = tipsRsp.getData();
                    if (data != null && data.size() > 0) {
                        List<String> tipsList = new ArrayList<String>();
                        for (TipsRsp.TipsData tips : data) {
                            tipsList.add(tips.getTitle());
                        }
                        tips.setTextList(tipsList);
                    } else {
                        tips.setTextList(Arrays.asList("暂无提醒"));
                    }
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

    @Override
    public void onPause() {
        super.onPause();
        notice.stopFlipping();
    }

    @Override
    public void onResume() {
        super.onResume();
        notice.startFlipping();
    }

    @Override
    public void OnItemClicked(int position) {
        switch (position) {
            case 4:
                Intent intent = new Intent(context, FeedbackActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
