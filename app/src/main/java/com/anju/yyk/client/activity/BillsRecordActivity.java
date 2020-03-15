package com.anju.yyk.client.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anju.yyk.client.R;
import com.anju.yyk.client.adapter.BillsRecordAdapter;
import com.anju.yyk.client.data.BillsItemData;
import com.anju.yyk.client.data.BillsRsp;
import com.anju.yyk.client.http.RetrofitHelper;
import com.anju.yyk.client.util.AppHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BillsRecordActivity extends BaseActivity {

    private BillsRecordAdapter billsRecordAdapter;
    private ArrayMap<String, String> billsItemMap = new ArrayMap<String, String>();
    private List<BillsItemData> billsItemList = new ArrayList<BillsItemData>();
    private List<BillsItemData> tempList = new ArrayList<BillsItemData>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills_record);

        initBillsItemMap();
        initViews();
    }

    private void initViews() {
        RecyclerView normalRv = findViewById(R.id.bills_record_rv);
        billsRecordAdapter = new BillsRecordAdapter(this, billsItemList, new int[]{
                R.layout.layout_list_header,
                R.layout.layout_time,
                R.layout.layout_normal_list_item,
                R.layout.layout_bills_footer});
        normalRv.setLayoutManager(new LinearLayoutManager(this));
        normalRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        normalRv.setAdapter(billsRecordAdapter);

        Intent intent = getIntent();
        if (intent != null) {
            String username = intent.getStringExtra("name");
            BillsItemData data = new BillsItemData();
            data.setTitle(username);
            tempList.add(data);

            data = new BillsItemData();
            data.setTitle(AppHelper.getMonthStr());
            tempList.add(data);
            billsItemList.addAll(tempList);
            getData();
        }
    }

    private void initBillsItemMap() {
        billsItemMap.put("chuangwei", "床位费");
        billsItemMap.put("huli", "护理费");
        billsItemMap.put("gexing", "个性护理费");
        billsItemMap.put("canyin", "餐饮费");
        billsItemMap.put("xiyang", "吸氧费");
        billsItemMap.put("kongtiao", "空调/暖气费");
        billsItemMap.put("qita", "其他费用");
        billsItemMap.put("richang", "日常扣费");
        billsItemMap.put("chuangshang", "床上用品");
        billsItemMap.put("tuoyang", "托养收入");
        billsItemMap.put("changhuxian", "长护险");
        billsItemMap.put("bujiao", "补缴费用");
        billsItemMap.put("yajin", "押金");
    }

    private void getData() {
        RetrofitHelper.getInstance().getBillsRecord(new Observer<BillsRsp>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BillsRsp billsRsp) {
                if (billsRsp.getStatus() == 0) {
                    BillsRsp.BillsData billsData = billsRsp.getData();
                    if (billsData != null) {
                        initBillsData(billsData);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, AppHelper.id, "2019-05");
    }

    private void initBillsData(BillsRsp.BillsData billsData) {
        Field[] fields = billsData.getClass().getDeclaredFields();
        billsItemList.clear();
        billsItemList.addAll(tempList);
        String total = null;
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            String type = field.getType().toString();
            int index = type.lastIndexOf(".");
            if (index != -1) {
                type = type.substring(index + 1);
                if ("String".equals(type)) {
                    try {
                        Object value = field.get(billsData);
                        if (value != null) {
                            if ("zonge".equals(name)) {
                                total = value + "";
                                continue;
                            }
//                            Log.e("yyk", value + "");
                            BillsItemData billsItemData = new BillsItemData();
                            billsItemData.setTitle(billsItemMap.get(name));
                            billsItemData.setPrice(value + "");
                            billsItemList.add(billsItemData);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        BillsItemData billsItemData = new BillsItemData();
        billsItemData.setPrice(total);
        billsItemList.add(billsItemData);
        billsRecordAdapter.notifyDataSetChanged();
    }
}
