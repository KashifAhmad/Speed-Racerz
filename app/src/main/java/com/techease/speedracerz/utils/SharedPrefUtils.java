package com.techease.speedracerz.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefUtils {
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    public static String MyConfig = "com.techease.speedracerz";


    public static SharedPreferences getSharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences(MyConfig, Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    public static SharedPreferences.Editor getEditor(Context context) {
        sharedPreferences = context.getSharedPreferences(MyConfig, Context.MODE_PRIVATE);
        return editor = sharedPreferences.edit();
    }

    public static String getApiToken(Context context) {
        return getSharedPref(context).getString("auth_token", "");
    }
    public static String getDeviceToken(Context context) {
        return getSharedPref(context).getString("device_token", "");
    }
    public static boolean isLoggedIn(Context context) {
        return getSharedPref(context).getBoolean("loggedIn", false);
    }
}
