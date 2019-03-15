package com.techease.speedracerz.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.techease.speed.R;
import com.techease.speedracerz.dataModels.changePasswordModels.ResetPasswordResponeModel;
import com.techease.speedracerz.views.resetpasswordscreens.ResetPasswordActivity;
import com.techease.speedracerz.views.resetpasswordscreens.VerifyPasswordCodeActivity;

import retrofit2.Callback;

public class GeneralUtils {

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    public static SharedPreferences.Editor putStringValueInEditor(Context context, String key, String value) {
        sharedPreferences = getSharedPreferences(context);
        editor = sharedPreferences.edit();
        editor.putString(key, value).commit();
        return editor;
    }

    public static SharedPreferences.Editor putIntegerValueInEditor(Context context, String key, int value) {
        sharedPreferences = getSharedPreferences(context);
        editor = sharedPreferences.edit();
        editor.putInt(key, value).commit();
        return editor;
    }

    public static SharedPreferences.Editor putBooleanValueInEditor(Context context, String key, boolean value) {
        sharedPreferences = getSharedPreferences(context);
        editor = sharedPreferences.edit();
        editor.putBoolean(key, value).commit();
        return editor;
    }



    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(Configuation.MY_PREF, 0);
    }

    public static boolean isLogin(Context context){
        return getSharedPreferences(context).getBoolean("isLogin",false);
    }


    public static String getApiToken(Context context){
        return getSharedPreferences(context).getString("api_token","");
    }


    public static String getForgotEmail(Context context){
        return getSharedPreferences(context).getString("forgot_email","");
    }

    public static String getLatitude(Context context){
        return getSharedPreferences(context).getString("latitude","");
    }

    public static String getLongitude(Context context){
        return getSharedPreferences(context).getString("longitude","");
    }

}