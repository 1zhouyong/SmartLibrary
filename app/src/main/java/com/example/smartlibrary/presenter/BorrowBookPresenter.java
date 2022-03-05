package com.example.smartlibrary.presenter;

import android.content.Context;

import com.android.volley.VolleyError;
import com.example.smartlibrary.activity.MainActivity;
import com.example.smartlibrary.basemvp.BaseMvpPresenter;
import com.example.smartlibrary.bean.LoginBean;
import com.example.smartlibrary.http.VolleyListenerInterface;
import com.example.smartlibrary.http.VolleyRequestUtil;
import com.example.smartlibrary.util.Constant;
import com.example.smartlibrary.util.LoadDialog;
import com.example.smartlibrary.view.BorrowBookView;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * author: ${周勇}
 * Date: 2020/5/2
 * Description:{}
 */
public class BorrowBookPresenter extends BaseMvpPresenter<BorrowBookView> {

    private Context context;

    public BorrowBookPresenter(Context context){
        this.context = context;
    }

    public void borrowBook(String bookId,String startTime,String stopTime){
        LoadDialog.showDialog(context);
        HashMap map = new HashMap<>();
        map.put("begin",startTime);
        map.put("bookId",bookId);
        map.put("end",stopTime);
        map.put("userId", MainActivity.userId);
        VolleyRequestUtil.RequestPost(context, Constant.URL + "book/borrowBook",
                "book/borrowBook", map, new VolleyListenerInterface(context,VolleyListenerInterface.mListener,
                        VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        LoginBean bean = new Gson().fromJson(result, LoginBean.class);
                        if (bean.isSuccess()){
                            mView.showBorrowBookMsg("借书成功，请前往我的借书查看");
                        }else {
                            mView.showBorrowBookMsg((String) bean.getErrorMsg());
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
