package com.anju.yyk.client.adapter;

import android.content.Context;

import com.anju.yyk.client.R;
import com.anju.yyk.client.data.NursingRecordRsp;

import java.util.List;

public class NursingRecordAdapter extends MultiLayoutBaseAdapter<NursingRecordRsp.NursingRecordData> {

    public NursingRecordAdapter(Context context, List<NursingRecordRsp.NursingRecordData> dataList, int[] layoutIds) {
        super(context, dataList, layoutIds);
    }

    @Override
    public int getItemType(int position) {
        if (position < 2) {
            return position;
        } else {
            return 2;
        }
    }

    @Override
    protected void onBindData(BaseHolder holder, NursingRecordRsp.NursingRecordData nursingRecordData, int position, int itemViewType) {
        if (itemViewType == 0) {
            holder.setText(R.id.name, nursingRecordData.getTitle());
        } else if (itemViewType == 1) {
            holder.setText(R.id.date, nursingRecordData.getTitle());
        } else {
            holder.setText(R.id.title, nursingRecordData.getTitle());
            holder.setText(R.id.time, nursingRecordData.getTime());
        }
    }
}
