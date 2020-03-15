package com.anju.yyk.client.adapter;

import android.content.Context;
import android.view.View;

import com.anju.yyk.client.R;
import com.anju.yyk.client.data.NoticeRsp;

import java.util.List;

public class NoticeAdapter extends BaseAdapter<NoticeRsp.NoticeData> {
    public NoticeAdapter(Context context, List<NoticeRsp.NoticeData> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    private OnItemClickListener itemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    protected void onBindData(BaseHolder holder, NoticeRsp.NoticeData data, final int position) {
        holder.setText(R.id.title, data.getTitle());
        holder.setText(R.id.time, data.getTime());
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
