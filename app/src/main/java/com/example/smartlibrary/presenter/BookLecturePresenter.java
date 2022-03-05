package com.example.smartlibrary.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.VolleyError;
import com.example.smartlibrary.activity.MainActivity;
import com.example.smartlibrary.basemvp.BaseMvpPresenter;
import com.example.smartlibrary.bean.LectureListBean;
import com.example.smartlibrary.bean.OrderLectureBean;
import com.example.smartlibrary.http.VolleyListenerInterface;
import com.example.smartlibrary.http.VolleyRequestUtil;
import com.example.smartlibrary.util.Constant;
import com.example.smartlibrary.util.LoadDialog;
import com.example.smartlibrary.view.BookLectureView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * author: ${周勇}
 * Date: 2020/4/19
 * Description:{}
 */
public class BookLecturePresenter extends BaseMvpPresenter<BookLectureView> {

    private Context context;
    LectureListBean.ResultBean resultBeans;


    public BookLecturePresenter(Context context) {
        this.context = context;
    }

    public void getResultBean(Bundle bundle) {
        resultBeans = (LectureListBean.ResultBean) bundle.getSerializable("lectureList");
        mView.showResultBean(resultBeans);
    }

    public void bookLecture(int lectureId){
        LoadDialog.showDialog(context);
        HashMap map = new HashMap<>();
        map.put("userId", MainActivity.userId);
        map.put("lectureId",lectureId);
        VolleyRequestUtil.RequestPostAddHeader(context, Constant.URL + "lecture/orderLecture",
                "lecture/orderLecture", map, new VolleyListenerInterface(context,VolleyListenerInterface.mListener,
                        VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        OrderLectureBean orderLectureBean = new Gson().fromJson(result, OrderLectureBean.class);
                        if (orderLectureBean.isSuccess()){
                            mView.showMessage("预约成功，可前往我的预约中查看");
                        }else {
                            mView.showMessage((String) orderLectureBean.getErrorMsg());
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
