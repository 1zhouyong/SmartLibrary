package com.example.smartlibrary.presenter;

import android.content.Context;

import com.android.volley.VolleyError;
import com.example.smartlibrary.activity.MainActivity;
import com.example.smartlibrary.basemvp.BaseMvpPresenter;
import com.example.smartlibrary.bean.LectureListBean;
import com.example.smartlibrary.bean.LoginBean;
import com.example.smartlibrary.bean.MyLectureBean;
import com.example.smartlibrary.http.VolleyListenerInterface;
import com.example.smartlibrary.http.VolleyRequestUtil;
import com.example.smartlibrary.util.Constant;
import com.example.smartlibrary.util.LoadDialog;
import com.example.smartlibrary.view.MyLectureView;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * author: ${周勇}
 * Date: 2020/4/20
 * Description:{}
 */
public class MyLecturePresenter extends BaseMvpPresenter<MyLectureView> {

    private Context context;

    public MyLecturePresenter(Context context){
        this.context = context;
    }

    public void getMyLecture(){
        HashMap map = new HashMap<>();
        map.put("userId", MainActivity.userId);
        VolleyRequestUtil.RequestPostAddHeader(context, Constant.URL + "lecture/selectUserOrderLecture",
                "lecture/selectUserOrderLecture", map, new VolleyListenerInterface(context,VolleyListenerInterface.mListener,
                        VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        LectureListBean myLectureBean = new Gson().fromJson(result, LectureListBean.class);
                        if (myLectureBean.isSuccess()){
                            mView.showMyLecture(myLectureBean.getResult());
                        }
                    }

                    @Override
                    public void onMyError(VolleyError error) {

                    }
                });
    }
    public void cancelLecture(int lectureId){
        LoadDialog.showDialog(context);
        HashMap map = new HashMap<>();
        map.put("lectureId",lectureId);
        map.put("userId",MainActivity.userId);
        VolleyRequestUtil.RequestPostAddHeader(context, Constant.URL + "lecture/orderSeat",
                "lecture/orderSeat", map, new VolleyListenerInterface(context,VolleyListenerInterface.mListener,
                        VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        LoginBean cancelBean = new Gson().fromJson(result, LoginBean.class);
                        if (cancelBean.isSuccess()){
                            mView.showMsg("取消成功");
                        }else {
                            mView.showMsg((String) cancelBean.getErrorMsg());
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
