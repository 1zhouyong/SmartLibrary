package com.example.smartlibrary.presenter;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.example.smartlibrary.basemvp.BaseMvpPresenter;
import com.example.smartlibrary.bean.SubmitSeatBean;
import com.example.smartlibrary.http.VolleyListenerInterface;
import com.example.smartlibrary.http.VolleyRequestUtil;
import com.example.smartlibrary.util.Constant;
import com.example.smartlibrary.util.LoadDialog;
import com.example.smartlibrary.view.Submit_Seat_View;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * author: ${周勇}
 * Date: 2020/4/11
 * Description:{}
 */
public class Submit_Seat_Presenter extends BaseMvpPresenter<Submit_Seat_View> {

    private Context context;

    public Submit_Seat_Presenter(Context context){
        this.context = context;
    }

    public void submit_Seat(int userId,String seatId,String date ){
        Log.i("zhouyong", userId+"-"+seatId+"-"+date);
        LoadDialog.showDialog(context);
        HashMap map = new HashMap<>();
        map.put("userId",userId);
        map.put("id",seatId);
        map.put("date",date);
        VolleyRequestUtil.RequestPostAddHeader(context, Constant.URL + "seat/orderSeat",
                "seat/orderSeat", map, new VolleyListenerInterface(context,VolleyListenerInterface.mListener,
                        VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        SubmitSeatBean submitSeatBean = new Gson().fromJson(result, SubmitSeatBean.class);
                        if (submitSeatBean.isSuccess()){
                            mView.submitSuccess();
                            LoadDialog.disDialog();
                        }else {
                            LoadDialog.disDialog();
                            LoadDialog.showToast(context, (String) submitSeatBean.getErrorMsg());
                        }

                    }

                    @Override
                    public void onMyError(VolleyError error) {
                        LoadDialog.disDialog();

                    }
                });
    }
}
