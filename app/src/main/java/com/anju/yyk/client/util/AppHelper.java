package com.anju.yyk.client.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AppHelper {
    public static String userId;
    public static String id; // 老人id

    public static String getTimeStr() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(new Date());
    }

    public static String getMonthStr() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM", Locale.getDefault());
        return dateFormat.format(new Date());
    }
}
