package com.example.smartlibrary.http;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.smartlibrary.InitApp;
import com.example.smartlibrary.R;

import org.json.JSONObject;

public abstract class VolleyListenerInterface {
    public Context mContext;
    public static Response.Listener<JSONObject> mListener;
    public static Response.ErrorListener mErrorListener;

    public VolleyListenerInterface(Context context, Response.Listener<JSONObject> listener,
                                   Response.ErrorListener errorListener) {
        this.mContext = context;
        this.mErrorListener = errorListener;
        this.mListener = listener;
    }

    public VolleyListenerInterface() {

    }

    // 请求成功时的回调函数
    public abstract void onMySuccess(String result);

    // 请求失败时的回调函数
    public abstract void onMyError(VolleyError error);

    // 创建请求的事件监听
    public Response.Listener<JSONObject> responseListener() {
        mListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                onMySuccess(response.toString());
            }
        };
        return mListener;
    }

    // 创建请求失败的事件监听
    public Response.ErrorListener errorListener() {
        mErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onMyError(volleyError);
                Toast.makeText(InitApp.getInstance(),
                        InitApp.getInstance().getString(R.string.toast_networkError), Toast.LENGTH_LONG).show();
            }
        };
        return mErrorListener;
    }
}
