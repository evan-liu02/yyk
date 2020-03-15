package com.anju.yyk.client.adapter;

import android.content.Context;

import com.anju.yyk.client.R;
import com.anju.yyk.client.data.BillsItemData;
import com.anju.yyk.client.data.NursingRecordRsp;

import java.util.List;

public class BillsRecordAdapter extends MultiLayoutBaseAdapter<BillsItemData> {

    public BillsRecordAdapter(Context context, List<BillsItemData> dataList, int[] layoutIds) {
        super(context, dataList, layoutIds);
    }

    @Override
    public int getItemType(int position) {
        if (position < 2) {
            return position;
        } else if (position < getItemCount() - 1) {
            return 2;
        } else {
            return 3;
        }
    }

    @Override
    protected void onBindData(BaseHolder holder, BillsItemData billsItemData, int position, int itemViewType) {
        if (itemViewType == 0) {
            holder.setText(R.id.name, billsItemData.getTitle());
        } else if (itemViewType == 1) {
            holder.setText(R.id.date, billsItemData.getTitle() + "月账单");
        } else if (itemViewType == 2) {
            holder.setText(R.id.title, billsItemData.getTitle());
            holder.setText(R.id.time, billsItemData.getPrice());
        } else {
            holder.setText(R.id.total_tv, billsItemData.getPrice() + "元");
        }
    }
}
