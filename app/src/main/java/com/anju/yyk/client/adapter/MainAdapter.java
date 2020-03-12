package com.anju.yyk.client.adapter;

import android.content.Context;

import com.anju.yyk.client.R;
import com.anju.yyk.client.data.MenuData;

import java.util.List;

public class MainAdapter extends BaseAdapter<MenuData> {
    public MainAdapter(Context context, List<MenuData> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    @Override
    protected void onBindData(BaseHolder holder, MenuData data, int position) {
        holder.setImage(R.id.icon, data.getIconId());
        holder.setText(R.id.title, data.getTitle());
    }
}
