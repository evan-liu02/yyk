package com.anju.yyk.client.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtils {
    private static SharedPreferences sp;

    public static void init(Context context) {
        sp = context.getSharedPreferences("yyk_sp", Context.MODE_PRIVATE);
    }

    public static void putString(String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    public static void putBoolean(String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    public static String getString(String key) {
        return sp.getString(key, null);
    }

    public static String getString(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public static boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public static void clear() {
        sp.edit().clear().apply();
    }
}
