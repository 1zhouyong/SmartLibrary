package com.example.smartlibrary.presenter;

import android.content.Context;

import com.android.volley.VolleyError;
import com.example.smartlibrary.activity.MainActivity;
import com.example.smartlibrary.basemvp.BaseMvpPresenter;
import com.example.smartlibrary.bean.LoginBean;
import com.example.smartlibrary.bean.SeatInfomationBean;
import com.example.smartlibrary.http.VolleyListenerInterface;
import com.example.smartlibrary.http.VolleyRequestUtil;
import com.example.smartlibrary.util.Constant;
import com.example.smartlibrary.util.GetTime;
import com.example.smartlibrary.util.LoadDialog;
import com.example.smartlibrary.view.MySeatView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * author: ${周勇}
 * Date: 2020/4/14
 * Description:{}
 */
public class MySeatPresenter extends BaseMvpPresenter<MySeatView> {

    private Context context;
    private List<SeatInfomationBean> seatInfomationBeanList = new ArrayList<>();


    public MySeatPresenter(Context context){
        this.context = context;
    }

    public void getSeatInfo(int i){
        if (i<1) {
            HashMap map = new HashMap<>();
            map.put("day", GetTime.getNowDay1(++i));
            map.put("userId", MainActivity.userId);
            final int finalI = i;
            VolleyRequestUtil.RequestPostAddHeader(context, Constant.URL + "seat/queryOrderSeatByUserDay",
                    "seat/queryOrderSeatByUserDay", map, new VolleyListenerInterface(context, VolleyListenerInterface.mListener,
                            VolleyListenerInterface.mErrorListener) {
                        @Override
                        public void onMySuccess(String result) {
                            SeatInfomationBean seatInfomationBean = new Gson().fromJson(result, SeatInfomationBean.class);
                            if (seatInfomationBean.getResult()!=null){
                                seatInfomationBean.getResult().setDate(GetTime.getNowDay1(finalI));
                                seatInfomationBeanList.add(seatInfomationBean);
                            }
                            getSeatInfo(finalI);
                        }

                        @Override
                        public void onMyError(VolleyError error) {

                        }
                    });
        }else {
            mView.showSeatInfo(seatInfomationBeanList);
        }
    }
    public void cancelSite(int seatId,String date){
        LoadDialog.showDialog(context);
        HashMap map = new HashMap<>();
        map.put("date",date);
        map.put("id",seatId);
        map.put("userId",MainActivity.userId);
        VolleyRequestUtil.RequestPostAddHeader(context, Constant.URL + "seat/cancelOrderSeat",
                "seat/cancelOrderSeat", map, new VolleyListenerInterface(context,VolleyListenerInterface.mListener,
                        VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        LoginBean bean = new Gson().fromJson(result, LoginBean.class);
                        if (bean.isSuccess()){
                            mView.cancelSuccess();
                        }else {
                            LoadDialog.showToast(context, (String) bean.getErrorCode());
                        }
                        LoadDialog.disDialog();
                    }

                    @Override
                    public void onMyError(VolleyError error) {
                        LoadDialog.disDialog();
                    }
                });
    }
}
