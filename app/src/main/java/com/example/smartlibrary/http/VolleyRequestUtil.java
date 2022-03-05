package com.example.smartlibrary.http;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.smartlibrary.InitApp;
import com.example.smartlibrary.activity.LoginActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyRequestUtil {

    public static JsonObjectRequest jsonObjectRequest;
    public static Context context;
    public static int sTimeOut = 30000;

    /**
     * 获取GET请求内容
     *
     * @param context：当前上下文；
     * @param url：请求的url地址；
     * @param tag：当前请求的标签；
     * @param volleyListenerInterface：VolleyListenerInterface接口；
     * @param timeOutDefaultFlg：是否使用Volley默认连接超时；
     */
//    public static void RequestGet(Context context, String url, String tag,
//                                  VolleyListenerInterface volleyListenerInterface,
//                                  boolean timeOutDefaultFlg) {
//        // 清除请求队列中的tag标记请求
//        InitApp.getQueue().cancelAll(tag);
//        // 创建当前的请求，获取字符串内容
//        stringRequest = new JsonObjectRequest(Request.Method.GET, url,
//                volleyListenerInterface.responseListener(), volleyListenerInterface.errorListener());
//        // 为当前请求添加标记
//        stringRequest.setTag(tag);
//        // 默认超时时间以及重连次数
//        int myTimeOut = timeOutDefaultFlg ? DefaultRetryPolicy.DEFAULT_TIMEOUT_MS : sTimeOut;
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(myTimeOut,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        // 将当前请求添加到请求队列中
//        InitApp.getQueue().add(stringRequest);
//        // 重启当前请求队列
//        //MyApplication.getQueue().start();
//    }

    /**
     * 获取POST请求内容（请求的代码为Map）
     *
     * @param context：当前上下文；
     * @param url：请求的url地址；
     * @param tag：当前请求的标签；                                       //     * @param params：POST请求内容；
     * @param volleyListenerInterface：VolleyListenerInterface接口；
     */
    public static void RequestPostAddHeader(Context context, String url, String tag,
                                            Map<String, String> map,
                                            VolleyListenerInterface volleyListenerInterface) {
        // 清除请求队列中的tag标记请求
        InitApp.getQueue().cancelAll(tag);

        jsonObjectRequest = new JsonObjectRequest(url, new JSONObject(map), volleyListenerInterface.responseListener(),
                volleyListenerInterface.errorListener()) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<>();
                header.put("Login-Pass", LoginActivity.loginToken);
                return header;
            }
        };

//        // 为当前请求添加标记
        jsonObjectRequest.setTag(tag);
//        // 将当前请求添加到请求队列中
        InitApp.getQueue().add(jsonObjectRequest);
//        // 重启当前请求队列
//        //MyApplication.getQueue().start();
    }

    public static void RequestPost(Context context, String url, String tag,
                                   Map<String, String> map,
                                   VolleyListenerInterface volleyListenerInterface) {
        // 清除请求队列中的tag标记请求
        InitApp.getQueue().cancelAll(tag);

        jsonObjectRequest = new JsonObjectRequest(url, new JSONObject(map), volleyListenerInterface.responseListener(),
                volleyListenerInterface.errorListener());

//        // 为当前请求添加标记
        jsonObjectRequest.setTag(tag);
//        // 将当前请求添加到请求队列中
        InitApp.getQueue().add(jsonObjectRequest);
//        // 重启当前请求队列
//        //MyApplication.getQueue().start();
    }
}
