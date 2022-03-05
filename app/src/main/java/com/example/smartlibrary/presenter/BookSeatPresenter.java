package com.example.smartlibrary.presenter;

import android.content.Context;

import com.android.volley.VolleyError;
import com.example.smartlibrary.basemvp.BaseMvpPresenter;
import com.example.smartlibrary.bean.SeatStatusBean;
import com.example.smartlibrary.http.VolleyListenerInterface;
import com.example.smartlibrary.http.VolleyRequestUtil;
import com.example.smartlibrary.util.Constant;
import com.example.smartlibrary.view.BookSeatView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: ${周勇}
 * Date: 2020/4/7
 * Description:{}
 */
public class BookSeatPresenter extends BaseMvpPresenter<BookSeatView> {

    private Context context;

    public BookSeatPresenter (Context context){
        this.context = context;
    }
    public void getSeatStatus(String date,String classroom){
        HashMap<String, String> map = new HashMap<>();
        map.put("date",date);
        map.put("place",classroom);
        VolleyRequestUtil.RequestPostAddHeader(context, Constant.URL + "seat/listByCondition",
                "seat/listByCondition", map, new VolleyListenerInterface(context,
                        VolleyListenerInterface.mListener,VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        SeatStatusBean seatStatusBean = new Gson().fromJson(result, SeatStatusBean.class);
                        if (seatStatusBean.isSuccess()){
                            mView.showSeatStatus(seatStatusBean);
                        }
                    }

                    @Override
                    public void onMyError(VolleyError error) {

                    }
                });
    }

}
