package com.anju.yyk.client.adapter;

import android.content.Context;

import com.anju.yyk.client.R;
import com.anju.yyk.client.data.NursingRecordRsp;

import java.util.List;

public class NursingRecordAdapter extends BaseAdapter<NursingRecordRsp.NursingRecordData> {
    public NursingRecordAdapter(Context context, List<NursingRecordRsp.NursingRecordData> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }


    @Override
    protected void onBindData(BaseHolder holder, NursingRecordRsp.NursingRecordData data, final int position) {
        holder.setText(R.id.title, data.getTitle());
        holder.setText(R.id.time, data.getTime());
    }
}
