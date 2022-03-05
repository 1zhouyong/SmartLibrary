package com.example.smartlibrary.presenter;

import android.content.Context;

import com.android.volley.VolleyError;
import com.example.smartlibrary.activity.MainActivity;
import com.example.smartlibrary.basemvp.BaseMvpPresenter;
import com.example.smartlibrary.bean.LoginBean;
import com.example.smartlibrary.bean.MyBorrowBean;
import com.example.smartlibrary.http.VolleyListenerInterface;
import com.example.smartlibrary.http.VolleyRequestUtil;
import com.example.smartlibrary.util.Constant;
import com.example.smartlibrary.util.LoadDialog;
import com.example.smartlibrary.view.MyBorrowView;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * author: ${周勇}
 * Date: 2020/5/2
 * Description:{}
 */
public class MyBorrowPresenter extends BaseMvpPresenter<MyBorrowView> {

    private Context context;

    public MyBorrowPresenter(Context context){
        this.context = context;
    }

    public void getMyBorrow(){
        HashMap map = new HashMap<>();
        map.put("userId", MainActivity.userId);
        VolleyRequestUtil.RequestPost(context, Constant.URL + "book/queryMyBorrowingBook",
                "book/queryMyBorrowingBook", map, new VolleyListenerInterface(context,
                        VolleyListenerInterface.mListener,VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        MyBorrowBean myBorrowBean = new Gson().fromJson(result, MyBorrowBean.class);
                        if (myBorrowBean.isSuccess()){
                            mView.showMyBorrow(myBorrowBean.getResult());
                        }
                    }

                    @Override
                    public void onMyError(VolleyError error) {

                    }
                });
    }
    public void returnBook(int bookId){
        LoadDialog.showDialog(context);
        HashMap map = new HashMap<>();
        map.put("bookId",bookId);
        map.put("userId",MainActivity.userId);
        VolleyRequestUtil.RequestPost(context, Constant.URL + "book/returnBook", "book/returnBook",
                map, new VolleyListenerInterface(context,VolleyListenerInterface.mListener,
                        VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onMySuccess(String result) {
                        LoginBean bean = new Gson().fromJson(result, LoginBean.class);
                        if (bean.isSuccess()){
                            mView.showReturnmsg("还书成功");
                        }else {
                            mView.showReturnmsg((String) bean.getErrorMsg());
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
