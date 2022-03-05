package com.example.smartlibrary.util;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartlibrary.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author: ${周勇}
 * Date: 2020/4/6
 * Description:{}
 */
public class LoadDialog {

    public static Dialog dialog;
    public static Date data;
    public static SimpleDateFormat format;

    public static void showToast(Context context, String msg){
        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
    }

    public static void showDialog(Context context){
        dialog=new Dialog(context, R.style.Theme_AppCompat_DayNight_Dialog);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.loading_dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
    public static void disDialog(){
        dialog.dismiss();
    }

    public static String getTime(){
        data=new Date(System.currentTimeMillis());
        format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String string=format.format(data);
        return string;
    }
}
