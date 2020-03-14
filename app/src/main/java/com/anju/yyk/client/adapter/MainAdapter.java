package com.anju.yyk.client.adapter;

import android.content.Context;
import android.view.View;

import com.anju.yyk.client.R;
import com.anju.yyk.client.data.MenuData;

import java.util.List;

public class MainAdapter extends BaseAdapter<MenuData> {
    public MainAdapter(Context context, List<MenuData> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    private OnItemClickListener itemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    protected void onBindData(BaseHolder holder, MenuData data, final int position) {
        holder.setImage(R.id.icon, data.getIconId());
        holder.setText(R.id.title, data.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.OnItemClicked(position);
                }
            }
        });
    }

    public interface OnItemClickListener {
        void OnItemClicked(int position);
    }
}
