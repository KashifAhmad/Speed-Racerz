package com.techease.speedracerz.utils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.techease.speed.R;


/**
 * Created by kashif on 1/10/18.
 */

public class GeneralUtils {
    public static Button button;
    public static TextView textView;
    public static GridView gridView;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    public static String deviceToken;
    public static String SERVER_MSG = "Server not responding";
    public static String CONNECTION_MSG = "No internet connection";
    public static String WRONG_MSG = "Something went wrong!";
    public static String SUCCESS = "Success";
    public static AlertDialog progressPie;

    public static Fragment connectFragment(Context context, Fragment fragment) {
        ((AppCompatActivity) context).getFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack("true").commit();
        return fragment;
    }

    public static Fragment connectFragmentWithOutBackStack(Context context, Fragment fragment) {
        ((AppCompatActivity) context).getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        return fragment;
    }

    @SuppressLint("ResourceAsColor")
    public static AlertDialog acProgressPieDialog(Context context) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View dialogView = inflater.inflate(R.layout.custom_progress_dialog_layout, null);
//        dialogBuilder.setView(dialogView);
//        dialogView.setBackgroundColor(R.color.transparent_white);
        progressPie = dialogBuilder.create();
        progressPie.setCancelable(false);
        progressPie.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressPie.show();
        return progressPie;
    }

}